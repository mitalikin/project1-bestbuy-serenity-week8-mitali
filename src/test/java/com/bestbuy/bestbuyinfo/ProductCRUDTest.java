package com.bestbuy.bestbuyinfo;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {

    @Test
    public void verifyUserCreatedSuccessfully() {

        ProductPojo productPojo = new ProductPojo();
      //  productPojo.setId(1234);
        productPojo.setName("Duracell - AA Batteries (4-Pack)");
        productPojo.setType("HardGood");
        productPojo.setPrice(6);
        productPojo.setUpc("041333424029");
        productPojo.setShipping(0);
        productPojo.setDescription("Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack");
        productPojo.setManufacturer("Duracell");
        productPojo.setModel("MN2400B4Z");
        productPojo.setUrl("http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC");
        productPojo.setImage("http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg");
       // productPojo.setCreatedAt("2022-11-17T17:58:03.298Z");
       //productPojo.setUpdatedat("2022-11-17T17:58:03.298Z");


        Response response = given().log().all()
                .headers("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }




}
