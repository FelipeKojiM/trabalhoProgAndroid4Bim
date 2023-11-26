package com.example.trabalho4bimestre.dao;

import com.example.trabalho4bimestre.model.Login;

import java.util.ArrayList;

public interface IgenericDaoLogin<Object> {

    long insert(Login obj);
    long update(Login obj);
    long delete(Login obj);
    ArrayList<Login> getAll();
    Login getById(Login id);
}
