package com.edu.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private Button btnRegresoMain;
    private Button btnRegistrar;
    private TextView tvTituloRegistro;
    private EditText etUsuarioRegistro;
    private EditText etContraseñaRegistro;
    private EditText etConfirmarContraseña;

    private String usuario;
    private String contraseña;
    private String confirmarContraseña;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        this.btnRegresoMain.setOnClickListener(this::screenMain);
        this.btnRegistrar.setOnClickListener(this::confirmPassword);

    }

    private void screenMain(View view){  //Boton de regreso a la pantalla main
        Intent irMainActivity= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(irMainActivity);
    }

    private void data(){  //Asignamos valores a usuario,contraseña y confirmarcontrasñea
        this.usuario = etUsuarioRegistro.getText().toString();
        this.contraseña = etContraseñaRegistro.getText().toString();
        this.confirmarContraseña = etConfirmarContraseña.getText().toString();
    }

    private boolean validationData(){   //Validacion con expresiones regulares de los datos (usuario, contraseña y confirmarcontraseña)

        String regexUsuario = "^[A-Za-zÁáÉéÍíÓóÚúÑñ ]+$";
        String regexContraseña = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-*/_.])[A-Za-z\\d-*/_.]{8,}$";

        if(!usuario.matches(regexUsuario)){
            etUsuarioRegistro.setError("Usuario invalido");
            Toast.makeText(getApplicationContext(), "u:"+usuario, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!contraseña.matches(regexContraseña)){
            etContraseñaRegistro.setError("Contraseña no valida");
            return false;
        }
        return true;
    }
    private void confirmPassword(View view){     //Validamos que las contraseñas sean iguales y guardamos el usuario
        data();
        if (validationData()) {
            if (contraseña.equals(confirmarContraseña)) {
                userSave();
            } else {
                //Si las contraseñas no coinciden, muestra un mensaje al usuario para informarle de esto.
                etConfirmarContraseña.setError("Las contraseñas no coinciden");
            }
        }
    }

    private void userSave(){

    }
    private void begin(){   //Conectamos nuestro objeto en java con el xml
        this.btnRegresoMain = findViewById(R.id.btnRegresoMain);
        this.btnRegistrar = findViewById(R.id.btnRegistrar);
        this.tvTituloRegistro = findViewById(R.id.tvTituloRegistro);
        this.etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        this.etContraseñaRegistro = findViewById(R.id.etContraseñaRegistro);
        this.etConfirmarContraseña = findViewById(R.id.etConfirmarContraseña);
        this.usuario = "";
        this.contraseña = "";
        this.confirmarContraseña = "";
    }
}