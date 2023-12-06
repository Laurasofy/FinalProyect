package com.edu.proyectofinal;

import static com.edu.proyectofinal.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.proyectofinal.api.ServiceLogin;
import com.edu.proyectofinal.model.Credentials;
import com.edu.proyectofinal.model.Loger;
import com.edu.proyectofinal.model.ResponseCredentials;
import com.edu.proyectofinal.remote.ClienteRetrofit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private Button btnCrearCuenta;
    private Button btnIniciarSesion;
    private EditText etUsuario;
    private EditText etContraseña;

    private String usuario;
    private String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin();
        this.btnCrearCuenta.setOnClickListener(this::screenRegistro);
        this.btnIniciarSesion.setOnClickListener(this::processLogin);
    }
    private void screenRegistro(View view){//Cambiar de vista a Registro
        Intent irRegistro = new Intent(getApplicationContext(), Registro.class);
        startActivity(irRegistro);
    }

    private void processLogin(View view) {
        if (!validEmail(etUsuario.getText().toString()) && etContraseña.getText().length()<=3){
            alerView("error en los datos");

        }else{
            String password = md5(etContraseña.getText().toString());
            Loger loger = new  Loger();
            loger.setUse_mail(etUsuario.getText().toString());
            loger.setUse_pss(password);
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin servicesLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = servicesLogin.accessLogin(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if (response.isSuccessful()) {
                        ResponseCredentials body =response.body();
                        String mensaje = body.getMensaje();
                        ArrayList<Credentials> list =body.getCredentials();
                        Toast.makeText(MainActivity.this,"Ingresadon a la App"+ mensaje, Toast.LENGTH_LONG).show();
                        if ( mensaje.equals("OK")&& !isNullOrEmpty(list)){
                            for (Credentials c:list){
                                SharedPreferences shared = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("key",c.getUs_key());
                                editor.putString("identificador", c.getUs_identifier());
                                editor.putString("id",c.getUse_id());
                                editor.commit();
                                goTo();

                            }

                        }else {
                            alerView("usuario no existe o contraseña invalida");
                        }


                    } else {
                        alerView(" Usuariol no existe, intente de nuevo");
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("response", t.getMessage());
                    alerView("Error en sevicio comuniquese con soporte técnico  ");

                }
            });
        }
    }
    private  void  goTo(){
        try {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)return true;
        if(obj instanceof String) return ((String)obj).trim().equals("") || ((String)obj).equalsIgnoreCase("NULL");
        if(obj instanceof Integer) return ((Integer)obj)==0;
        if(obj instanceof Long) return ((Long)obj).equals(new Long(0));
        if(obj instanceof Double) return ((Double)obj).equals(0.0);
        if(obj instanceof Collection) return (((Collection)obj).isEmpty());
        return false;
    }
    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        return false;
    }
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private void  alerView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR", null);
        builder.create();
        builder.show();
    }
    private void data(){//Asignamos valores a usuario y contraseña
        usuario = etUsuario.getText().toString();
        contraseña = etContraseña.getText().toString();
    }
    private void begin(){   //Conectamos nuestros objetos java con el xml
        this.btnCrearCuenta=findViewById(R.id.btnCrearCuenta);
        this.btnIniciarSesion=findViewById(R.id.btnIniciarSesion);
        this.etUsuario=findViewById(R.id.etUsuario);
        this.etContraseña=findViewById(R.id.etContraseña);
        this.usuario = "";
        this.contraseña = "";
    }
}