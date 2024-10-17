package com.example.cadastroaluno.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        return 0;
    }

    @Override
    public long update(Aluno obj) {
        return 0;
    }

    @Override
    public long delete(Aluno obj) {
        return 0;
    }

    @Override
    public Aluno getById(int id) {
        return null;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        return null;
    }
}
