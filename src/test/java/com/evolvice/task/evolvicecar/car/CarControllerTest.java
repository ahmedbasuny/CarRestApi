package com.evolvice.task.evolvicecar.car;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CarControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarService carService;
	
	@Mock
	private CarResourceAssembler carAssembler;
	
	@InjectMocks
	private CarController carController;

	@Test
	@WithMockUser(username = "user", password = "user123")
	public void getAllTest() throws Exception {
		List<Car> cars = Arrays.asList(
				new Car("BMW", "M4 Cabrio", "2014", "M4 Cabrio (2979 cm³, 431 PS) 2014"),
				new Car("Honda", "Civic Tourer 1.6 i-DTEC", "2015", "Civic Tourer 1.6 i-DTEC (1597 cm³, 120 PS) 2015")
				);
		Mockito.when(carService.findAll()).thenReturn(cars);
		MvcResult result = this.mockMvc.perform(get("/api/v1/cars").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		mockMvc.perform(get("/api/v1/cars"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._embedded.carList[0].brand", is("BMW")))
			.andExpect(jsonPath("$._embedded.carList[0].model", is("M4 Cabrio")))
			.andExpect(jsonPath("$._embedded.carList[0].productionYear", is("2014")))
			.andExpect(jsonPath("$._embedded.carList[1].brand", is("Honda")))
			.andExpect(jsonPath("$._embedded.carList[1].model", is("Civic Tourer 1.6 i-DTEC")))
			.andExpect(jsonPath("$._embedded.carList[1].productionYear", is("2015")));
	}

	
	@Test
	@WithMockUser(username = "user", password = "user123")
	public void getCar() throws Exception {
		Car car = new Car("BMW", "M4 Cabrio", "2014", "M4 Cabrio (2979 cm³, 431 PS) 2014");
		car.setId(1);
	    Mockito.when(carService.find(1)).thenReturn(car);
	    MvcResult result = this.mockMvc.perform(get("/api/v1/cars/{id}", 1).accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	    mockMvc.perform(get("/api/v1/cars/{id}", 1))
            .andExpect(status().isOk())
			.andExpect(jsonPath("$.brand", is("BMW")))
			.andExpect(jsonPath("$.model", is("M4 Cabrio")))
			.andExpect(jsonPath("$.productionYear", is("2014")));
	}
	
	@Test
	@WithMockUser(username = "user", password = "user123")
	public void saveCar() throws Exception {
		Car car = new Car("BMW", "M4 Cabrio", "2014", "M4 Cabrio (2979 cm³, 431 PS) 2014");
		Mockito.when(carService.find(1)).thenReturn(car);
	    mockMvc.perform(get("/api/v1/cars/{id}", 1)
	    		.with(csrf())
	    		.contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isOk())
			.andExpect(jsonPath("$.brand", is("BMW")))
			.andExpect(jsonPath("$.model", is("M4 Cabrio")))
			.andExpect(jsonPath("$.productionYear", is("2014")));
	}
	
	@Test
	@WithMockUser(username = "user", password = "user123")
	public void deleteCar() throws Exception {
	    doNothing().when(carService).delete(Mockito.anyInt());
	    mockMvc.perform(delete("/api/v1/cars/{id}", 1)
	    		.with(csrf()))
            .andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user123")
	public void updateCar() throws Exception {
		Car car = new Car("BMW", "M4 Cabrio", "2014", "M4 Cabrio (2979 cm³, 431 PS) 2014");
		Mockito.when(carService.find(1)).thenReturn(car);
		mockMvc.perform(get("/api/v1/cars/{id}", 1)
	    		.with(csrf())
	    		.contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isOk())
			.andExpect(jsonPath("$.brand", is("BMW")))
			.andExpect(jsonPath("$.model", is("M4 Cabrio")))
			.andExpect(jsonPath("$.productionYear", is("2014")));
	}
	
}
