package com.example.cadastroaluno.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroaluno.R;
import com.example.cadastroaluno.model.Aluno;

import java.util.ArrayList;

public class AlunoListAdapter extends
        RecyclerView.Adapter<AlunoListAdapter.ViewHolder> {

    private ArrayList<Aluno> listaAlunos;
    private Context context;

    /**
     * Contrutor
     * @param listaAlunos: lista de alunos retornados do banco
     * @param context
     */
    public AlunoListAdapter(ArrayList<Aluno> listaAlunos,
                            Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    /**
     * Método responsável em carregar o arquivo xml do layout para
     * cada elemento da lista
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemList = inflater.inflate(R.layout.item_list_aluno,
                parent, false);

        return new ViewHolder(itemList);
    }

    /**
     * Método que adiciona os dados do Aluno na tela
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.tvRa.setText(String.valueOf(aluno.getRa()));
        holder.tvNome.setText(aluno.getNome());
    }

    /**
     * Determina a quantidade de elementos na lista
     * @return
     */
    @Override
    public int getItemCount() {
        return this.listaAlunos.size();
    }

    /**Clase que vincula os componentes do xml
     * para cada posiçao da lista*/
    public class ViewHolder extends RecyclerView.ViewHolder{

       public TextView tvRa;
        public TextView tvNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvRa = itemView.findViewById(R.id.tvRa);
            this.tvNome = itemView.findViewById(R.id.tvNome);
        }
    }
}
