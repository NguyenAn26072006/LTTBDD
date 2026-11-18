package com.example.studentmarketandroid.api;
import com.example.studentmarketandroid.model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public class ApiService {
    @GET("api/products")
    Call<List<Product>> getProducts();

}

