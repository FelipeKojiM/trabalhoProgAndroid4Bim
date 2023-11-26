package com.example.trabalho4bimestre.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CLIENTE VARCHAR(100), ITEM VARCHAR(100), QUANTIDADE INTEGER, VALORUNITARIO DOUBLE, FORMAPAGAMENTO VARCHAR(100), VALORTOTALITEMPEDIDO DOUBLE, VALORTOTALPEDIDOS DOUBLE)");
        sqLiteDatabase.execSQL("CREATE TABLE LOGIN (USUARIO VARCHAR(100), SENHA VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
}

