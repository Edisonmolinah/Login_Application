package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener{
    EditText ediNombre, ediApellido, ediCorreo, ediClave;
    Button btnActualizar, ediBtnCancelar;
    int id = 0;
    Usuario usuario;
    DaoUsuario daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        ediNombre = (EditText) findViewById(R.id.ediNombre);
        ediApellido = (EditText) findViewById(R.id.ediApellido);
        ediCorreo = (EditText) findViewById(R.id.ediCorreo);
        ediClave = (EditText) findViewById(R.id.ediClave);

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        ediBtnCancelar = (Button) findViewById(R.id.ediBtnCancelar);
        btnActualizar.setOnClickListener(this);
        ediBtnCancelar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        daoUsuario = new DaoUsuario(this);
        usuario = daoUsuario.getUsuarioById(id);
        ediNombre.setText(usuario.getNombre());
        ediApellido.setText(usuario.getApellido());
        ediCorreo.setText(usuario.getCorreo());
        ediClave.setText(usuario.getClave());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActualizar:
                usuario.setNombre(ediNombre.getText().toString());
                usuario.setApellido(ediApellido.getText().toString());
                usuario.setCorreo(ediCorreo.getText().toString());
                usuario.setClave(ediClave.getText().toString());

                if(!usuario.isNull()){
                    Toast.makeText(this,"ERROR: campos vacios", Toast.LENGTH_LONG).show();
                }else if(daoUsuario.updateUsuario(usuario)){
                    Toast.makeText(this,"ACTUALIZACION EXITOSA", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(Editar.this, Inicio.class);
                    j.putExtra("Id", usuario.getId());

                    startActivity(j);
                    finish();
                    break;
                }else{
                    Toast.makeText(this," USUARIO NO ACTUALIZADO ", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(Editar.this, Editar.class);
                    j.putExtra("Id", usuario.getId());
                    startActivity(j);
                    finish();
                    break;
                }

            case R.id.ediBtnCancelar:
                Intent j = new Intent(Editar.this, Inicio.class);
                j.putExtra("Id", usuario.getId());
                startActivity(j);
                finish();
                break;
        }
    }
}
