package com.edu.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Cart extends AppCompatActivity {

    private ImageButton btnScreenMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        begin();
        this.btnScreenMenu.setOnClickListener(this::ScreenMenu);
    }

    private void ScreenMenu(View view) {
        Intent irMenu = new Intent(getApplicationContext(), Menu.class);
        startActivity(irMenu);
    }
    private void begin(){
        this.btnScreenMenu=findViewById(R.id.btnScreenMenu);
    }
}