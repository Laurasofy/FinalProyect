package com.edu.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.edu.proyectofinal.api.ValuesApi.BASE_URL;
import static com.edu.proyectofinal.remote.ClienteRetrofit.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.edu.proyectofinal.api.ServiceLogin;
import com.edu.proyectofinal.model.Products;
import com.edu.proyectofinal.model.ProductsAdapter;
import com.edu.proyectofinal.remote.ClienteRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menu extends AppCompatActivity {
    private ServiceLogin serviceLogin;
    private Retrofit retrofit;
    private ImageButton btnScreenProfile;
    private ImageButton btnScreenCart;
    private List<Products> products;
    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        begin();
        this.btnScreenProfile.setOnClickListener(this::ScreenProfile);
        this.btnScreenCart.setOnClickListener(this::ScreenCart);
        recyclerView=findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        showProducts();
        serviceLogin = ClienteRetrofit.getClient(BASE_URL).create(ServiceLogin.class);
    }

private void showProducts() {
    retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    serviceLogin = retrofit.create(ServiceLogin.class);

    Call<List<Products>> call = serviceLogin.getProducts();
    call.enqueue(new Callback<List<Products>>() {

        @Override
        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
            Toast.makeText(Menu.this, "Porfin", Toast.LENGTH_SHORT).show();
            products = response.body();
            productsAdapter = new ProductsAdapter(products, getApplicationContext());
            recyclerView.setAdapter(productsAdapter);

        }

        @Override
        public void onFailure(Call<List<Products>> call, Throwable t) {
            Toast.makeText(Menu.this, "ERROR NO CONECTA" + t, Toast.LENGTH_LONG).show();
            Log.e("MiEtiqueta", String.valueOf(t));
        }
    });
}

    private void ScreenProfile(View view) {
        Intent irProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(irProfile);
    }
    private void ScreenCart(View view) {
        Intent irCart = new Intent(getApplicationContext(), Cart.class);
        startActivity(irCart);
    }
    private void begin(){
        this.btnScreenProfile=findViewById(R.id.btnScreenProfile);
        this.btnScreenCart=findViewById(R.id.btnScreenCart);
    }
}