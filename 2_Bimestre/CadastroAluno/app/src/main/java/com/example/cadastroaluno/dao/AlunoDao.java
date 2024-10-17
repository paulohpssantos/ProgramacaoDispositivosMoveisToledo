package com.example.cadastroaluno.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cadastroaluno.helper.SQLiteDataHelper;
import com.example.cadastroaluno.model.Aluno;

import java.util.ArrayList;

public class AlunoDao implements IGenericDao<Aluno>{

    //Variavel responsável por abrir a conexão com o BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //Nome das colunas da Tabela
    private String[] colunas = {"RA", "NOME"};

    //Nome da tabela
    private String tabela = "ALUNO";

    //View que chamara essa classe
    private Context context;

    private static AlunoDao instancia;

    public static AlunoDao getInstancia(Context context){
        if(instancia == null){
            instancia = new AlunoDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }

    private AlunoDao(Context context) {
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SQLiteDataHelper(this.context,
                "BD_UNIPAR", null, 1);

        baseDados = openHelper.getWritableDatabase();

    }

    @Override
    public long insert(Aluno obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getRa());
            valores.put(colunas[1], obj.getNome());

            return baseDados.insert(tabela,
                    null, valores);

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.insert(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Aluno obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());

            String[]identificador = {String.valueOf(obj.getRa())};

            return baseDados.update(tabela, valores,
                    colunas[0]+" = ?", identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.update(): "+ex.getMessage());
        }
        return 0;

    }

    @Override
    public long delete(Aluno obj) {
        try{

            String[]identificador = {String.valueOf(obj.getRa())};

            return baseDados.delete(tabela,
                    colunas[0]+" = ?", identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.delete(): "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public Aluno getById(int id) {
        try{
           String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela,
                    colunas, colunas[0]+" = ?",
                    identificador, null,
                    null, null);

            //verifica se retornou dados da tabela
            if(cursor.moveToFirst()){
                Aluno aluno = new Aluno();
                aluno.setRa(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));

                return aluno;
            }


        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.getById(): "+ex.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null, null,
                    null, null, colunas[0]);

            if(cursor.moveToFirst()){
                do{
                   Aluno aluno = new Aluno();
                   aluno.setRa(cursor.getInt(0));
                   aluno.setNome(cursor.getString(1));

                   lista.add(aluno);
                }while (cursor.moveToNext());
            }
            return lista;

        }catch (SQLException ex){
            Log.e("UNIPAR",
                    "ERRO: AlunoDao.getAll(): "+ex.getMessage());
        }
        return null;
    }
}
