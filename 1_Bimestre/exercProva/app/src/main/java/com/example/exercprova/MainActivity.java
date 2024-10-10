package com.example.exercprova;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edLarguraA, edLarguraB, edAlturaA, edAlturaB;
    private TextView tvArea, tvPerimetro;
    private Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double alturaA = Double.parseDouble(edAlturaA.getText().toString());
                double alturaB = Double.parseDouble(edAlturaB.getText().toString());
                double larguraA = Double.parseDouble(edLarguraA.getText().toString());
                double larguraB = Double.parseDouble(edLarguraB.getText().toString());

                double perimetro = alturaA + alturaB +larguraA +larguraB;
                tvPerimetro.setText(String.valueOf(perimetro));

                double area = alturaA * larguraB;
                tvArea.setText(String.valueOf(area));

            }
        });

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}