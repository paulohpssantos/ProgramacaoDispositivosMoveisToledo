package com.example.calculadoraimc;

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

    private EditText edAltura;
    private EditText edPeso;
    private Button btHomem;
    private Button btMulher;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Instanciando a variavel e vinculando ao
        // componentte no arquivo de layout xml
        edAltura = findViewById(R.id.edAltura);
        edPeso = findViewById(R.id.edPeso);
        btHomem = findViewById(R.id.btHomem);
        btMulher = findViewById(R.id.btMulher);
        tvResultado = findViewById(R.id.tvResultado);

        btHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double imc = calcularIMC();
                calcularIMCHomem(imc);
            }
        });

    }

    private void calcularIMCHomem(double imc) {
        String msg = "";
        //Abaixo do peso: < 20,7
        if(imc < 20.7){
            msg = "Abaixo do Peso!";
        }

        tvResultado.setText("O IMC é: "+imc+" - "+msg);
    }

    /**
     * calcula o IMC
     * @return o valor do calculo
     */
    private double calcularIMC(){

        //retornando o valor do campo de texto
        String strPeso = edPeso.getText().toString();

        //convertendo o texto em número decimal
        double peso = Double.parseDouble(strPeso);

        //REtornando o valor no campo de texto de altura
        // e convertendo em número decimal
        double altura = Double.parseDouble(edAltura.getText().toString());

        double resultado = peso / (altura * altura);

        return resultado;
    }
























}