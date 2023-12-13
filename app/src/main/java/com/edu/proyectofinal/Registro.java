package com.edu.proyectofinal;

import static com.edu.proyectofinal.api.ValuesApi.BASE_URL;

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

import com.edu.proyectofinal.api.ServiceLogin;
import com.edu.proyectofinal.model.Loger;
import com.edu.proyectofinal.model.Register;
import com.edu.proyectofinal.model.ResponseCredentials;
import com.edu.proyectofinal.remote.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registro extends AppCompatActivity {

    private Button btnRegresoMain;
    private Button btnRegistrar;
    private EditText etUsuarioRegistro;
    private EditText etContraseñaRegistro;
    private EditText etConfirmarContraseña;

    private String usuario;
    private String contrasena;
    private String confirmarContrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        begin();
        this.btnRegresoMain.setOnClickListener(this::screenMain);
        this.btnRegistrar.setOnClickListener(this::registrarUsuario);

    }

    private void registrarUsuario(View view) {
        Register registro = new Register();
        registro.setUse_mail(usuario);
        registro.setUse_contras(contrasena);
        Retrofit retrofit = ClienteRetrofit.getClient(BASE_URL);
        ServiceLogin servicesLogin = retrofit.create(ServiceLogin.class);
        Call<String> call = servicesLogin.registrousuario(registro);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String mensaje = response.body();
                Toast.makeText(Registro.this, ""+mensaje, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Registro.this, "Error" + t, Toast.LENGTH_SHORT).show();
                Log.e("MiEtiqueta", String.valueOf(t));
            }
        });

    }

    private void screenMain(View view){  //Boton de regreso a la pantalla main
        Intent irMainActivity= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(irMainActivity);
    }

    private void data(){  //Asignamos valores a usuario,contraseña y confirmarcontrasñea
        this.usuario = etUsuarioRegistro.getText().toString();
        this.contrasena = etContraseñaRegistro.getText().toString();
        this.confirmarContrasena = etConfirmarContraseña.getText().toString();
    }

    private boolean validationData(){   //Validacion con expresiones regulares de los datos (usuario, contraseña y confirmarcontraseña)

        String regexUsuario = "^[A-Za-zÁáÉéÍíÓóÚúÑñ ]+$";
        String regexContraseña = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-*/_.])[A-Za-z\\d-*/_.]{8,}$";

        if(!usuario.matches(regexUsuario)){
            etUsuarioRegistro.setError("Usuario invalido");
            Toast.makeText(getApplicationContext(), "u:"+usuario, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!contrasena.matches(regexContraseña)){
            etContraseñaRegistro.setError("Contraseña no valida");
            return false;
        }
        return true;
    }
    private void confirmPassword(View view){     //Validamos que las contraseñas sean iguales y guardamos el usuario
        data();
        if (validationData()) {
            if (contrasena.equals(confirmarContrasena)) {
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
        this.etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        this.etContraseñaRegistro = findViewById(R.id.etContraseñaRegistro);
        this.etConfirmarContraseña = findViewById(R.id.etConfirmarContraseña);
        this.usuario = etUsuarioRegistro.getText().toString();
        this.contrasena = etContraseñaRegistro.getText().toString();
        this.confirmarContrasena = "";
    }
}