package com.example.login_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnEliminar, btnMostar, btnSalir;
    TextView nombre;
    int id=0;
    Usuario usuario;
    DaoUsuario daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        nombre = (TextView)findViewById(R.id.nombreUsuario);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        daoUsuario = new DaoUsuario(this);
        usuario = daoUsuario.getUsuarioById(id);
        nombre.setText(usuario.getNombre()+" "+usuario.getApellido());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                Intent i = new Intent(Inicio.this, Editar.class);
                i.putExtra("Id", usuario.getId());
                startActivity(i);
                finish();
                break;
            case R.id.btnEliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("esta seguro???");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (daoUsuario.deleteUsuario(id)){
                            Toast.makeText(Inicio.this,"CUENTA ELIMINADA", Toast.LENGTH_LONG).show();
                            Intent j = new Intent(Inicio.this, MainActivity.class);
                            j.putExtra("Id", usuario.getId());
                            startActivity(j);
                            finish();
                        }else {
                            Toast.makeText(Inicio.this,"ERROR: NO SE PUDO ELIMINAR", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;

            case R.id.btnSalir:
                Intent k = new Intent(Inicio.this, MainActivity.class);
                startActivity(k);
                finish();
                break;
        }

    }
}