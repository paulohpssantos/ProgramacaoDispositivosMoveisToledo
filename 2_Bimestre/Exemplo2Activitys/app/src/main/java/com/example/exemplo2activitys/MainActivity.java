package com.example.exemplo2activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scanner s = new Scanner(System.in);
        System.out.println("Insira o tempo da viagem: ");
        double edTempoGasto = s.nextDouble();

        btCadastro = findViewById(R.id.button);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                CadastroAlunoActivity.class);

                startActivity(intent);

            }
        });

    }
}