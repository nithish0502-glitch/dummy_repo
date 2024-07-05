package com.example.springapp;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

@Test
@Order(1)
void testAddProduct() throws Exception {
    String jsonPayload = "{"
            + "\"productId\": 1,"
            + "\"name\": \"Example Product\","
            + "\"description\": \"A sample product description.\","
            + "\"price\": 49.99,"
            + "\"quantityInStock\": 100,"
            + "\"manufacturer\": \"Example Manufacturer\","
            + "\"category\": \"Electronics\""
            + "}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonPayload)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(jsonPath("$.name").value("Example Product"))
            .andReturn();
}
@Test
@Order(2)
void testgetByName() throws Exception {
    mockMvc.perform(get("/api/product/bycategory/Electronics")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$[*].name").exists())
            .andReturn();
}

     @Test
     @Order(3)
	 void testupdateDetails() throws Exception {  
 
		   mockMvc.perform(MockMvcRequestBuilders.put("/api/product/1/1000")
 
						 .contentType(MediaType.APPLICATION_JSON)
 
						 .accept(MediaType.APPLICATION_JSON))
 
						 .andExpect(MockMvcResultMatchers.status().isOk())
 
						 .andExpect(jsonPath("$.quantityInStock").value(1000))

						 .andReturn();
 
	 }


 @Test
@Order(4)
void testDeleteById() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/product/1")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Product deleted successfully."))
            .andReturn();
}


@Order(5)
	@Test
	
	public void controllerfolder() {
	
	   String directoryPath = "src/main/java/com/example/springapp/controller"; // Replace with the path to your directory
	
	   File directory = new File(directoryPath);
	
	   assertTrue(directory.exists() && directory.isDirectory());
	
	}
	
	@Order(6)
	@Test
	
	public void controllerfile() {
	
	   String filePath = "src/main/java/com/example/springapp/controller/ProductController.java";
	
	   File file = new File(filePath);
	
	   assertTrue(file.exists() && file.isFile());
	
	}
	
	@Order(7)
	@Test
	
	public void testModelFolder() {
	
	   String directoryPath = "src/main/java/com/example/springapp/model"; // Replace with the path to your directory
	
	   File directory = new File(directoryPath);
	
	   assertTrue(directory.exists() && directory.isDirectory());
	
	}
	
	@Order(8)
	@Test
	
	public void testModelFile() {
	
	   String filePath = "src/main/java/com/example/springapp/model/Product.java";
	
	   File file = new File(filePath);
	
	   assertTrue(file.exists() && file.isFile());
	
	}
	
	
	@Order(9)
	@Test
	
	public void testrepositoryfolder() {
	
	   String directoryPath = "src/main/java/com/example/springapp/repository"; // Replace with the path to your directory
	
	   File directory = new File(directoryPath);
	
	   assertTrue(directory.exists() && directory.isDirectory());
	
	}
	
	
	@Order(10)
	@Test
	
	public void testrepositoryFile() {
	
	   String filePath = "src/main/java/com/example/springapp/repository/ProductRepo.java";
	
	   // Replace with the path to your file
	
	   File file = new File(filePath);
	
	   assertTrue(file.exists() && file.isFile());
	
	}
	
	
	
	@Order(11)
	@Test
	
	public void testServiceFolder() {
	
	   String directoryPath = "src/main/java/com/example/springapp/service"; 
	
	   File directory = new File(directoryPath);
	
	   assertTrue(directory.exists() && directory.isDirectory());
	
	}
	
	
	@Order(12)
	@Test
	
	public void testServieFile() {
	
	   String filePath = "src/main/java/com/example/springapp/service/ProductService.java";
	
	   // Replace with the path to your file
	
	   File file = new File(filePath);
	
	   assertTrue(file.exists() && file.isFile());
	
	}
	






	

	




}
