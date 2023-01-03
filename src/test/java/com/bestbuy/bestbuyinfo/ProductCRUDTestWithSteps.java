package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends TestBase {

    static String name = "Duracell" + TestUtils.getRandomValue();
    static String type = "Hardgood";
    static Integer price = 6;

    static Integer shipping = 25;
    static String upc = "0123456789";
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productID;

    @BeforeClass
    public static void endPath() {
        RestAssured.basePath = Path.PRODUCTS;
    }

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test001_CreateProduct() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
    }

    @Title("Verify if the Product was added ")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getProductInfoAddedByName(productID);
        Assert.assertThat(productMap, hasValue(name));

    }

    @Title("update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        productSteps.updateProduct(productID, name, type, price, shipping, upc, description, manufacturer, model, url, image)
                .log().all().statusCode(200);
        HashMap<String, Object> productMap = productSteps.getProductInfoAddedByName(productID);
        Assert.assertThat(productMap, hasValue(name));


    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productSteps.deleteProduct(productID).statusCode(200);
        productSteps.getProductById(productID).statusCode(404);
    }


}
