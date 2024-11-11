package com.example.cadastroaluno.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroaluno.R;
import com.example.cadastroaluno.adapter.AlunoListAdapter;
import com.example.cadastroaluno.controller.AlunoController;
import com.example.cadastroaluno.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AlunoActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroAluno;
    private RecyclerView rvAlunos;
    private AlunoController controller;
    private AlertDialog dialog;
    private EditText edRa, edNome;
    private View viewCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aluno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new AlunoController(this);
        rvAlunos = findViewById(R.id.rvAlunos);
        btCadastroAluno = findViewById(R.id.btCadastroAluno);

        btCadastroAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizaListaAlunos();

    }

    private void atualizaListaAlunos(){
        try{
            ArrayList<Aluno> listaAlunos =
                    controller.retornarTodosAlunos();

            AlunoListAdapter adapter =
                    new AlunoListAdapter(listaAlunos, this);

            rvAlunos.setLayoutManager(
                    new LinearLayoutManager(this));

            rvAlunos.setAdapter(adapter);

        }catch (Exception ex){
            Toast.makeText(this,
                    "Erro ao carregar lista",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void abrirCadastro(){

        //carregar o arquivo xml do layout de cadastro
        viewCadastro = getLayoutInflater()
                .inflate(R.layout.dialog_cadastro_aluno,null);

        edRa = viewCadastro.findViewById(R.id.edRa);
        edNome = viewCadastro.findViewById(R.id.edNome);

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO DE ALUNO"); //seta o titulo no popup
        builder.setView(viewCadastro); //seta o layout no popup
        builder.setCancelable(false); //não deixa fechar o popup ao clicar fora

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss(); //fecha o popup
            }
        });

        builder.setPositiveButton("Salvar", null);
        dialog = builder.create(); //cria o popup em memoria

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btSalvar =
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btSalvar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //salvar o aluno
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();

    }

    private void salvarDados(){
        String retorno = controller
                .salvarAluno(edRa.getText().toString(),
                        edNome.getText().toString());

        if(retorno.contains("RA")){
            edRa.setError(retorno);
            edRa.requestFocus();
        }
        if(retorno.contains("NOME")){
            edNome.setError(retorno);
            edNome.requestFocus();
        }

        Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
        if(retorno.contains("sucesso")){
            atualizaListaAlunos();
            dialog.dismiss(); //fecha a tela de cadastro
        }else if(retorno.contains("Erro")){
            dialog.dismiss(); //fecha a tela de cadastro
        }

    }



}