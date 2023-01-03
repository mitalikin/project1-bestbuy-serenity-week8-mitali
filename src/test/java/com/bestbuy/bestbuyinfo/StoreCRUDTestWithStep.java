package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
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
public class StoreCRUDTestWithStep extends TestBase {
    static String name = "john";
    static String type = "BigBox";
    static String address = "12 HILL Rd";
    static String address2 = "watford";
    static String city = "London";
    static String state = "U.K";
    static String zip = "Wd12 5tr";
    static double lat = 45;
    static double lng = 95;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9;Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int storeID = 8977;

    @BeforeClass
    public static void endPath() {
        RestAssured.basePath = Path.STORES;
    }

    @Steps
    StoresSteps storesSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        HashMap<Object, Object> services = new HashMap<>();
        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
    }

    @Title("This will verify store added to list")
    @Test
    public void test002() {
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoAddedByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        // storeID = (int) productMap.get("id");
    }

    @Title("update the store information")
    @Test
    public void test003() {
        name = name + "_updated";
        HashMap<Object, Object> services = new HashMap<>();
        storesSteps.updateStore(storeID, name, type, address, address2, city, state, zip, lat, lng, hours, services)
                .log().all().statusCode(200);
        HashMap<String, Object> productMap = storesSteps.getStoreInfoAddedByName(storeID);
        Assert.assertThat(productMap, hasValue(name));


    }

    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeID).statusCode(200);
        storesSteps.getStoreByID(storeID).statusCode(404);
    }

}
