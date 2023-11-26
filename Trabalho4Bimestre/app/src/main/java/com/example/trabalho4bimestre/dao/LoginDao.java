package com.example.trabalho4bimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.trabalho4bimestre.helper.SQLiteDataHelper;
import com.example.trabalho4bimestre.model.Login;
import com.example.trabalho4bimestre.view.MainActivity;
import java.util.ArrayList;

public class LoginDao implements IgenericDaoLogin<MainActivity>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"USUARIO", "SENHA"};
    private String tabela = "LOGIN";
    private Context context;
    private static LoginDao instancia;

    public static LoginDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new LoginDao(context);
        }else{
            return instancia;
        }
    }

    private LoginDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "BANCO_BD", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Login obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getUsuario());
            valores.put(colunas[1], obj.getSenha());

            return baseDados.insert(tabela, null, valores);
        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Login obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getUsuario());
            valores.put(colunas[1], obj.getSenha());

            String[]identificador = {String.valueOf(obj.getUsuario())};

            return baseDados.update(tabela,  valores, colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Login obj) {
        try{
            String[]identificador = {String.valueOf(obj.getUsuario())};return baseDados.delete(tabela, colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Login> getAll() {
        ArrayList<Login> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela, colunas, null, null, null, null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Login login = new Login();
                    login.setUsuario(cursor.getString(0));
                    login.setSenha(cursor.getString(1));

                    lista.add(login);
                }while (cursor.moveToNext());
            }
        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getAll() "+ex.getMessage());
        }
        return lista;
    }

    public Login getById(Login id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas, colunas[0]+"= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Login login = new Login();
                login.setUsuario(cursor.getString(0));
                login.setSenha(cursor.getString(1));

                return login;
            }
        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getById() "+ex.getMessage());
        }
        return null;
    }

    public Login getByUser(String usuario) {
        try{
            String[]identificador = {usuario};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Login login = new Login();
                login.setUsuario(cursor.getString(0));
                login.setSenha(cursor.getString(1));

                return login;
            }

        }catch (SQLException ex){
            Log.e("LOGIN", "ERRO: LoginDao.getByUser() "+ex.getMessage());
        }
        return null;
    }
}