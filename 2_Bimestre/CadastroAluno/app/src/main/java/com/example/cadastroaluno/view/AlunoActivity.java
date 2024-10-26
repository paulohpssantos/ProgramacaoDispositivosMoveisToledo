package com.example.cadastroaluno.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
}