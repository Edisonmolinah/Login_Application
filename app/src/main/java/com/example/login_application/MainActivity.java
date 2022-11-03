package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword;
    Button btnEntar;
    Button btn_registrarse;
    DaoUsuario daoUsuario;

    public void ingresar(View view) {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Log.i("Email: " , editTextEmail.getText().toString());
        Log.i("Contraseña: " , editTextPassword.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        btnEntar = (Button)findViewById(R.id.btnEntrar);
        btn_registrarse = (Button)findViewById(R.id.btnActualizar);
        daoUsuario = new DaoUsuario(this);

        btnEntar.setOnClickListener(this);
        btn_registrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String correo = editTextEmail.getText().toString();
                String clave = editTextPassword.getText().toString();
                if(correo.equals("")&&clave.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                    finish();
                }else if(daoUsuario.login(correo,clave)==1){
                    Usuario ux = daoUsuario.getUsuarioCorreo(correo,clave);
                    Toast.makeText(this,"DATOS CORRECTOS", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, Inicio.class);
                    i.putExtra("Id", ux.getId());
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario y/o Clave Incorrectos", Toast.LENGTH_LONG).show();

                }
                break;

            case R.id.btnActualizar:
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);

                break;
        }

    }
}