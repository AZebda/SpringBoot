package com.example.demo.controller;
 
import com.example.demo.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
 
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceControllerTest {

    @Autowired
    private MockMvc mvc;
 
    private ObjectMapper mapper = new ObjectMapper();
    private Product product;
 
    @Before
    public void setUp() throws Exception {
        product=new Product();
        product.setId("3");
        product.setName("Product3");
    }
 
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getProduct() throws Exception {
        this.mvc.perform(get("/products")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updateProduct() throws Exception {
        product.setName("ProductUpdated");
        this.mvc.perform(put("/products/" + product.getId()).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product))).andDo(print()).andExpect(status().isOk()).andExpect(content().string("Product with id:" + product.getId() + " is updated successsfully"));
    }

    @Test
    public void createProduct() throws Exception {
        this.mvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(product))).andDo(print()).andExpect(status().isCreated()).andExpect(content().string("Product with id:" + product.getId() + " is created successfully"));
    }
 
    @Test
    public void deleteProduct() throws Exception {
 
        this.mvc.perform(delete("/products/" + product.getId())).andDo(print()).andExpect(status().isOk()).andExpect(content().string("Product with id:" + product.getId() + " is deleted successsfully"));
    }
}