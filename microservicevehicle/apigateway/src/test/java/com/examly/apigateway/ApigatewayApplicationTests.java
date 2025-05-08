package com.examly.apigateway;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private String insuranceagenttoken;
    private String policymanagertoken;
    private String customertoken;

    private ObjectMapper objectMapper = new ObjectMapper(); // Initialize ObjectMapper

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterInsuranceAgent() throws Exception {
        String requestBody = "{\"userId\": 1,\"email\": \"demoinsurance@gmail.com\", \"password\": \"insurance@1234\", \"username\": \"insurance123\", \"userRole\": \"InsuranceAgent\", \"mobileNumber\": \"1234567890\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void backend_testRegisterPolicyManager() {
        String requestBody = "{\"userId\": 2,\"email\": \"demopolicymanager@gmail.com\", \"password\": \"policy@1234\", \"username\": \"policy123\", \"userRole\": \"PolicyManager\", \"mobileNumber\": \"1234567890\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(5)
    void backend_testRegisterCustomer() {
        String requestBody = "{\"userId\": 3,\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\", \"username\": \"customer123\", \"userRole\": \"Customer\", \"mobileNumber\": \"1234567890\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void backend_testLoginInsuranceAgent() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"demoinsurance@gmail.com\", \"password\": \"insurance@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        insuranceagenttoken = token;
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
       
    }

    @Test
    @Order(4)
    void backend_testLoginPolicyManager() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"demopolicymanager@gmail.com\", \"password\": \"policy@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        policymanagertoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(6)
    void backend_testLoginCustomer() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        customertoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }
  
    @Test
    @Order(7)
    void backend_testAddInsuranceByInsuranceAgent() throws Exception {
        Assertions.assertNotNull(insuranceagenttoken, "insurancetoken should not be null");
 
        String requestBody = "{"
        + "\"insuranceId\": " + 1 + ","
        + "\"insuranceType\": \"Home Insurance\","
        + "\"premiumRate\": " + 1.5 + ","
        + "\"coverageAmount\": " + 250000.00 + ","
        + "\"policyTermYears\": " + 10 + ","
        + "\"description\": \"Provides coverage for damages to your home and its contents.\","
        + "\"status\": \"Active\""
        + "}";

        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + insuranceagenttoken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/insurances", HttpMethod.POST, requestEntity,
                String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(8)
    void backend_testAddInsuranceByPolicyManager() throws Exception {
        Assertions.assertNotNull(policymanagertoken, "policymanagertoken should not be null");
 
        String requestBody = "{"
        + "\"insuranceId\": " + 1 + ","
        + "\"insuranceType\": \"Home Insurance\","
        + "\"premiumRate\": " + 1.5 + ","
        + "\"coverageAmount\": " + 250000.00 + ","
        + "\"policyTermYears\": " + 10 + ","
        + "\"description\": \"Provides coverage for damages to your home and its contents.\","
        + "\"status\": \"Active\""
        + "}";

        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + policymanagertoken);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/insurances", HttpMethod.POST, requestEntity,
                String.class);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(9)
    void backend_testGetAllInsurancesByPolicyManager() throws Exception {
        Assertions.assertNotNull(policymanagertoken, "PolicyManager token should not be null");
     
        // Set up headers with Authorization token
        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + policymanagertoken);
    
        // Perform GET request to fetch all pets
        ResponseEntity<String> response = restTemplate.exchange("/api/insurances", HttpMethod.GET,
                new HttpEntity<>(headers), // Use HttpEntity with headers
                String.class);
    
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
    }

    @Test
    @Order(10)
    void backend_testGetAllInsurancesByCustomer() throws Exception {
        Assertions.assertNotNull(customertoken, "Customer token should not be null");
    
        // Set up headers with Authorization token
        HttpHeaders headers = createHeaders();
        headers.set("Authorization", "Bearer " + customertoken);
    
        // Perform GET request to fetch all pets
        ResponseEntity<String> response = restTemplate.exchange("/api/insurances", HttpMethod.GET,
                new HttpEntity<>(headers), // Use HttpEntity with headers
                String.class);
    
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
    }

    
@Test
@Order(11)
void backend_testGetpolicyApplicationByUserIdAsPolicyManager() throws Exception {
    Assertions.assertNotNull(policymanagertoken, "PolicyManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + policymanagertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/policyapplications/user/" + 1,  // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}
  

@Test
@Order(12)
void backend_testGetpolicyApplicationByUserIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "Customer token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/policyapplications/user/" + 1,  // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}


@Test
@Order(13)
void backend_testGetInsurancesByIdAsInsuranceAgent() throws Exception {
    Assertions.assertNotNull(insuranceagenttoken, "InsuranceAgent token should not be null");
 
    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + insuranceagenttoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/insurances/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(14)
void backend_testGetInsuranceByIdAsPolicyManager() throws Exception {
    Assertions.assertNotNull(policymanagertoken, "PolicyManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + policymanagertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/insurances/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(15)
void backend_testGetInsuranceByIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "Customer token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/insurances/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

    @Test
@Order(16)  // Ensure the order is unique and appropriate
void backend_testAddPolicyApplicationByCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "customertoken should not be null");

    String requestBody = "{"
        + "\"policyApplicationId\": " + 1 + ","
        + "\"applicationDate\": \"2024-09-15\","
        + "\"coverageAmount\": " + 250000.00 + ","
        + "\"policyTermYears\": " + 10 + ","
        + "\"applicationStatus\": \"Pending\","
        + "\"remarks\": \"All documents submitted are complete.\","
        + "\"proof\": \"base64EncodedImageString\","
        + "\"user\": {"
        + "\"userId\": " + 1
        + "},"
        + "\"insurance\": {"
        + "\"insuranceId\": " + 1
        + "}"
        + "}";



    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/policyapplications", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}

@Test
@Order(17)  // Ensure the order is unique and appropriate
void backend_testAddPolicyApplicationByInsuranceAgent() throws Exception {
    Assertions.assertNotNull(insuranceagenttoken, "insuranceagenttoken should not be null");
 
    String requestBody = "{"
        + "\"policyApplicationId\": " + 1 + ","
        + "\"applicationDate\": \"2024-09-15\","
        + "\"coverageAmount\": " + 250000.00 + ","
        + "\"policyTermYears\": " + 10 + ","
        + "\"applicationStatus\": \"Pending\","
        + "\"remarks\": \"All documents submitted are complete.\","
        + "\"proof\": \"base64EncodedImageString\","
        + "\"user\": {"
        + "\"userId\": " + 1
        + "},"
        + "\"insurance\": {"
        + "\"insuranceId\": " + 1
        + "}"
        + "}";


    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + insuranceagenttoken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/policyapplications", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
}

@Test
@Order(18)  // Ensure the order is unique and appropriate
void backend_testAddFeedbackByCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "Customer token should not be null");

    String requestBody = "{"
        + "\"feedbackId\": 1,"
        + "\"feedbackText\": \"Great application, really user-friendly!\","
        + "\"date\": \"2024-09-15\","
        + "\"user\": {"
        + "\"userId\": " + 1
        + "}"
        + "}";

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}

@Test
@Order(19)  // Ensure the order is unique and appropriate
void backend_testAddFeedbackByPolicyManager() throws Exception {
    Assertions.assertNotNull(policymanagertoken, "PolicyManager should not be null");

    String requestBody = "{"
        + "\"feedbackId\": 1,"
        + "\"feedbackText\": \"Great application, really user-friendly!\","
        + "\"date\": \"2024-09-15\","
        + "\"user\": {"
        + "\"userId\": " + 1
        + "}"
        + "}";

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + policymanagertoken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
}

@Test
@Order(20)
void backend_testGetAllFeedbackByPolicyManager() throws Exception {
    Assertions.assertNotNull(policymanagertoken, "PolicyManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + policymanagertoken);

    // Perform GET request to fetch all pets
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(21)
void backend_testGetAllFeedbackByInsuranceAgent() throws Exception {
    Assertions.assertNotNull(insuranceagenttoken, "InsuranceAgent token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + insuranceagenttoken);

    // Perform GET request to fetch all pets
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(22)
void backend_testGetAllFeedbackByCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "customer token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);

    // Perform GET request to fetch all pets
    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}


@Test
@Order(23)
void backend_testGetFeedbackByUserIdAsCustomer() throws Exception {
    Assertions.assertNotNull(customertoken, "customer token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + customertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1,  // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(24)
void backend_testGetFeedbackByUserIdAsPolicyManager() throws Exception {
    Assertions.assertNotNull(policymanagertoken, "PolicyManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + policymanagertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1,  // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(25)
void backend_testGetFeedbackByUserIdAsInsuranceAgent() throws Exception {
    Assertions.assertNotNull(insuranceagenttoken, "InsuranceAgent token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + insuranceagenttoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1,  // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}


    
}
