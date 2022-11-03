package com.example.login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    TextView titulo;
    ListView lista;
    DaoUsuario daoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        titulo = findViewById(R.id.titleList);
        lista = findViewById(R.id.listaUsuarios);
        daoUsuario = new DaoUsuario(this);
        ArrayList<Usuario> l = daoUsuario.selectUsuarios();
        ArrayList<String> list = new ArrayList<String>();

        for (Usuario u: l) {
            list.add(u.getNombre());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);
    }
}