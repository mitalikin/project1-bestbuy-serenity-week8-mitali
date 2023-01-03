package com.bestbuy.constants;

public class EndPoints {
/***
 * This is endpoint of products
 * //http://localhost:3030/products/43900
 * */
   public static final String Get_All_Products= "/products" ;
   public static final String CREATE_PRODUCT= "/products";
   public static final String GET_SINGLE_PRODUCT_BY_ID = "/{productID}";
   public static final String UPDATE_SINGLE_PRODUCT_BY_ID = "/{productID}";
   public static final String DELETE_SINGLE_PRODUCT_BY_ID = "/{productID}";


   public static final String GET_ALL_STORE="/stores";
   public static final String CREATE_STORES="/stores";
   public static final String GET_SINGLE_STORE_BY_ID="/{storeID}";
   public static final String UPDATE_SINGLE_STORE_BY_ID="/{storeID}";
   public static final String DELETE_SINGLE_STORE_BY_ID="/{storeID}";



}
