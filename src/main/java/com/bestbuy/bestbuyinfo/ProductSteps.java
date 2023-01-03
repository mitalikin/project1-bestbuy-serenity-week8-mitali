package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {
    @Step("Creating products with name:{0},type:{1},price:{2},shipping:{3},upc:{4},description{5},manufacturer{6},model{7},url{8},image{9}")
    public ValidatableResponse createProduct(String name,
                                             String type,
                                             int price,
                                             int shipping,
                                             String upc,
                                             String description,
                                             String manufacturer,
                                             String model,
                                             String url,
                                             String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the product information with productId{0}")
    public HashMap<String, Object> getProductInfoAddedByName(int productID) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("productID", productID)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

    }

    @Step("Updating products with productID{0}, name{1},type{2},price{3},shipping{4},upc{5},description{6},manufacturer{7},model{8},url{9},image{10}")
    public ValidatableResponse updateProduct(int productID,
                                             String name,
                                             String type,
                                             int price,
                                             int shipping,
                                             String upc,
                                             String description,
                                             String manufacturer,
                                             String model,
                                             String url,
                                             String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .pathParam("productID", productID)
                .when()
                .put(EndPoints.UPDATE_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting product information with productID: {0}")

    public ValidatableResponse deleteProduct(int productID) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .delete(EndPoints.DELETE_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting product information with productID: {0}")
    public ValidatableResponse getProductById(int productID) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }
}
