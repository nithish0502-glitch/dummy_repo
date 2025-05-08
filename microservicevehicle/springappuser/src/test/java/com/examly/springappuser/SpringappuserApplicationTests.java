package com.examly.springappuser;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringappuserApplicationTests {

    @Autowired
    private MockMvc mockMvc;

	private String generatedToken;

    @Test
    @Order(1)
    void testRegisterInsuranceAgent() throws Exception {
        // Include the mobileNumber in the request body
        String requestBody = "{\"email\": \"demoinsurance@gmail.com\", \"password\": \"insurance@1234\", \"username\": \"insurance123\", \"userRole\": \"InsuranceAgent\", \"mobileNumber\": \"1234567890\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // Use the status method directly
                .andReturn();
    }

	@Test
    @Order(2)
    void testRegisterPolicyManager() throws Exception {
        // Include the mobileNumber in the request body
        String requestBody = "{\"email\": \"demopolicymanager@gmail.com\", \"password\": \"policy@1234\", \"username\": \"policy123\", \"userRole\": \"PolicyManager\", \"mobileNumber\": \"1234567890\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // Use the status method directly
                .andReturn();
    }

	@Test
    @Order(3)
    void testRegisterCustomer() throws Exception {

        String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\", \"username\": \"customer123\", \"userRole\": \"Customer\", \"mobileNumber\": \"1234567890\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // Use the status method directly
                .andReturn();
    }


	@Test
	@Order(4)
	void testLoginInsuranceAgent() throws Exception {
		String requestBody = "{\"email\": \"demoinsurance@gmail.com\", \"password\": \"insurance@1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andReturn();

		String token = result.getResponse().getContentAsString();
		generatedToken = token;

		assertNotNull(token);
	}

	@Test
	@Order(5)
	void testLoginPolicyManager() throws Exception {
		String requestBody = "{\"email\": \"demopolicymanager@gmail.com\", \"password\": \"policy@1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andReturn();

		String token = result.getResponse().getContentAsString();
		generatedToken = token;

		assertNotNull(token);
	}

	@Test
	@Order(6)
	void testLoginCustomer() throws Exception {
		String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\"}";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andReturn();

		String token = result.getResponse().getContentAsString();
		generatedToken = token;

		assertNotNull(token);
	}

}