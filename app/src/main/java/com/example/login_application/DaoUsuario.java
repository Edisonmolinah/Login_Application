package com.example.login_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.PortUnreachableException;
import java.security.PublicKey;
import java.util.ArrayList;

public class DaoUsuario {
    Context contex;
    Usuario usuario;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, nombre text, apellido text, correo text, clave text)";

    public DaoUsuario(Context context) {
        this.contex = context;
        sql = context.openOrCreateDatabase(bd, context.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        usuario = new Usuario();
    }

    public boolean insertUsuario(Usuario usuario) {
        if (buscar(usuario.getCorreo()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", usuario.getNombre());
            cv.put("apellido", usuario.getApellido());
            cv.put("correo", usuario.getCorreo());
            cv.put("clave", usuario.getClave());
            return (sql.insert("usuario", null, cv) > 0);
        } else {
            return false;
        }
    }

    public int buscar(String correo) {
        int x = 0;
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getCorreo()!= null && us.getCorreo().equals(correo)) {
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellido(cr.getString(2));
                u.setCorreo(cr.getString(3));
                u.setClave(cr.getString(4));
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String correo, String clave ){
        int a=0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr != null && cr.moveToFirst()){
            do {
                if(cr.getString(3) != null && cr.getString(3).equals(correo) && cr.getString(4) != null && cr.getString(4).equals(clave)){
                    a++;
                }
            }while (cr.moveToNext());
        }return a;
    }

    public Usuario getUsuarioCorreo ( String correo, String clave){
        lista = selectUsuarios();
        for (Usuario us:lista) {
            if (us.getCorreo()!= null && us.getCorreo().equals(correo) && us.getClave()!= null&& us.getClave().equals(clave)){
                return us;
            }

        }return null;
    }

    public Usuario getUsuarioById ( int id){
        lista = selectUsuarios();
        for (Usuario us:lista) {
            if (us.getId()== id){
                return us;
            }

        }return null;
    }

    public boolean updateUsuario (Usuario usuario){
        if (buscar(usuario.getCorreo()) != 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", usuario.getNombre());
            cv.put("apellido", usuario.getApellido());
            cv.put("correo", usuario.getCorreo());
            cv.put("clave", usuario.getClave());
            int id = sql.update("usuario",cv,"id="+usuario.getId(), null);

            return (id > 0);
        } else {
            return false;
        }
    }

    public boolean deleteUsuario(int id){
        return (sql.delete("usuario","id" ,null)>0);
    }

}
