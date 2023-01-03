package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;

import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {
    @Step("Creating store with name:{0},type:{1},address:{2},address2:{3},city:{4},state{5},zip{6},lat{7},lng{8},hours{9},services{10}")
    public ValidatableResponse createStore(String name,
                                           String type,
                                           String address,
                                           String address2,
                                           String city,
                                           String state,
                                           String zip,
                                           double lat,
                                           double lng,
                                           String hours,
                                           HashMap<Object, Object> services) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the storeId information with ID:{0}")
    public HashMap<String, Object> getStoreInfoAddedByName(int storeID) {
        return SerenityRest.given().log().all()
                .when()
                .pathParam("storeID",storeID)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");

    }

    @Step("Updating store information with storeID:{0},name:{1}, type: {2}, address: {3}, address2: {4}, city: {5}, state: {6}, zip: {7}, lat: {8}, lng: {9}, hours: {10}, servicesDate: {11}")
    public ValidatableResponse updateStore(int storeID,
                                           String name,
                                           String type,
                                           String address,
                                           String address2,
                                           String city,
                                           String state,
                                           String zip,
                                           double lat,
                                           double lng,
                                           String hours,
                                           HashMap<Object, Object> services) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .pathParam("storeID", storeID)
                .when()
                .put(EndPoints.UPDATE_SINGLE_STORE_BY_ID)
                .then();

    }
    @Step("Deleting store with ID{0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID",storeID)
                .when()
                .delete(EndPoints.DELETE_SINGLE_STORE_BY_ID)
                .then();
    }
    @Step("Getting Store with ID {0}")
    public ValidatableResponse getStoreByID(int storeID) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
}


