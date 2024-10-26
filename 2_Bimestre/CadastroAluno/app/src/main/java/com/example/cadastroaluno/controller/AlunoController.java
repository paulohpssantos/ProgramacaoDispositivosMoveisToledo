package com.example.cadastroaluno.controller;

import android.content.Context;

import com.example.cadastroaluno.dao.AlunoDao;
import com.example.cadastroaluno.model.Aluno;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(){
        return null;
    }

    /**
     * Retorna todos os alunos cadastrados no banco
     * @return
     */
    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstancia(context).getAll();
    }
}
