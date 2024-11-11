package com.example.cadastroaluno.controller;

import android.content.Context;
import android.text.TextUtils;

import com.example.cadastroaluno.dao.AlunoDao;
import com.example.cadastroaluno.model.Aluno;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(String ra, String nome){
        try{
            //Valida os campos estão preenchidos
            if(TextUtils.isEmpty(ra)){
                return "Informe o RA do Aluno.";
            }
            if(TextUtils.isEmpty(nome)){
                return "Informe o NOME do Aluno";
            }

            //Verifica se já existe o RA cadastrado
            Aluno aluno = AlunoDao
                    .getInstancia(context)
                    .getById(Integer.parseInt(ra));

            if(aluno != null){
                return "O RA ("+ra+") já está cadastrado.";
            }else{
                aluno = new Aluno();
                aluno.setRa(Integer.parseInt(ra));
                aluno.setNome(nome);

                long id = AlunoDao
                        .getInstancia(context)
                        .insert(aluno);

                if(id > 0) {
                    return "Dados do aluno gravados com sucesso.";
                }else{
                    return "Erro ao gravar dados do Aluno na BD.";
                }
            }

        }catch (Exception ex){
            return "Erro ao Gravar dados do Aluno.";
        }

    }

    /**
     * Retorna todos os alunos cadastrados no banco
     * @return
     */
    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstancia(context).getAll();
    }
}
