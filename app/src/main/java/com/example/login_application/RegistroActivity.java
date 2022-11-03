package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nombre, apellido, correo, clave;
    Button registrar, cancelar;
    DaoUsuario daoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = (EditText)findViewById(R.id.ediNombre);
        apellido = (EditText)findViewById(R.id.ediApellido);
        correo = (EditText)findViewById(R.id.ediCorreo);
        clave = (EditText)findViewById(R.id.ediClave);
        registrar = (Button)findViewById(R.id.btnActualizar);
        cancelar = (Button)findViewById(R.id.ediBtnCancelar);
        daoUsuario = new DaoUsuario(this);

        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnActualizar:
                Usuario u = new Usuario();
                u.setNombre(nombre.getText().toString());
                u.setApellido(apellido.getText().toString());
                u.setCorreo(correo.getText().toString());
                u.setClave(clave.getText().toString());

                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(daoUsuario.insertUsuario(u)){
                    Toast.makeText(this,"REGISTRO EXITOSO", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(j);
                    finish();
                }else{
                    Toast.makeText(this," USUARIO YA REGISTRADO - INICIE SESION ", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(j);
                    finish();
                }
                break;
            case R.id.ediBtnCancelar:

                Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(i);

                break;
        }
    }
}