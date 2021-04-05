package com.stock.pharmacy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.pharmacy.Domain.StockDO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PharmacyApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	@Test
	void getStock() throws Exception {
		long StockId=11;
		mockMvc.perform(get("/pharmacy/stock/get/{StockId}",StockId)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.type").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.purposeOfUsage").exists())
				.andExpect(jsonPath("$.amountOfSubstance").exists())
				.andExpect(jsonPath("$.piece").exists())
				.andExpect(jsonPath("$.company").exists());

	}

	@Test
	void createStock()throws Exception {
		StockDO newStock=new StockDO();
		newStock.setType("İğne");
		newStock.setName("BionTech");
		newStock.setPurposeOfUsage("Korona Virüs Aşısı");
		newStock.setAmountOfSubstance(25);
		newStock.setPiece(25);
		newStock.setCompany("BionTech");
		mockMvc.perform(post("/pharmacy/stock/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newStock)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.type").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.purposeOfUsage").exists())
				.andExpect(jsonPath("$.amountOfSubstance").exists())
				.andExpect(jsonPath("$.piece").exists())
				.andExpect(jsonPath("$.company").exists());
	}

	@Test
	void updateStock() throws Exception {
		StockDO updatestock = new StockDO();

		updatestock.setId(10L);
		updatestock.setType("hap");
		updatestock.setName("parol");
		updatestock.setPurposeOfUsage("grip ilacı");
		updatestock.setAmountOfSubstance(100);
		updatestock.setPiece(250);
		updatestock.setCompany("abdi ibrahim");
		mockMvc.perform(put("/pharmacy/stock/update").content(asJsonString(updatestock))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.type").value(updatestock.getType()))
				.andExpect(jsonPath("$.name").value(updatestock.getName()))
				.andExpect(jsonPath("$.purposeOfUsage").value(updatestock.getPurposeOfUsage()))
				.andExpect(jsonPath("$.amountOfSubstance").value(updatestock.getAmountOfSubstance()))
				.andExpect(jsonPath("$.piece").value(updatestock.getPiece()))
				.andExpect(jsonPath("$.company").value(updatestock.getCompany()));
	}


	@Test
	void updateStokcByNameAndSubstance() throws Exception {
		String name="zzz";int amountOfsubstance=200,piece=200;
		StockDO Updatestock = new StockDO();
		mockMvc.perform(put("/pharmacy/stock/Update/{name}/{amountOfsubstance}/{piece}", name,amountOfsubstance,piece)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void deleteStokcByNameAndSubstance() throws Exception{
		String name="kkk"; int amountOfsubstance=300;
		mockMvc.perform(delete("/pharmacy/stock/Delete/{name}/{amountOfsubstance}", name,amountOfsubstance)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getStokcByNameAndSubstance() throws Exception {
		String name="ooo"; int amountOfsubstance=150;
		mockMvc.perform(get("/pharmacy/stock/Get/{name}/{amountOfsubstance}", name,amountOfsubstance)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getAllStock() throws Exception {
		mockMvc.perform(get("/pharmacy/stock/gets-all")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].id").exists())
				.andExpect(jsonPath("$[*].type").exists())
				.andExpect(jsonPath("$[*].name").exists())
				.andExpect(jsonPath("$[*].purposeOfUsage").exists())
				.andExpect(jsonPath("$[*].amountOfSubstance").exists())
				.andExpect(jsonPath("$[*].piece").exists())
				.andExpect(jsonPath("$[*].company").exists());
	}

	@Test
	void deleteStock() throws Exception {
		String stockId = "27";

		mockMvc.perform(delete("/pharmacy/stock/delete/{stockId}", stockId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
