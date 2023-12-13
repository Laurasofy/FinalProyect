package com.edu.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    private ImageButton btnBackMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        begin();
        this.btnBackMenu.setOnClickListener(this::screenMenu);
    }

    private void screenMenu(View view) {
        Intent irMenu = new Intent(getApplicationContext(), Menu.class);
        startActivity(irMenu);
    }


    private void begin(){
        this.btnBackMenu = findViewById(R.id.btnBackMenu);
    }
}