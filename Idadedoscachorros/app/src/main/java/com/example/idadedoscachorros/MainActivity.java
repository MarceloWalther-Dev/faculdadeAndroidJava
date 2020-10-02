package com.example.idadedoscachorros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void conversorDeIdade(View v){
        EditText inputIdade = findViewById(R.id.idade);
        TextView inputResultado = findViewById(R.id.resultado);
        int idade = Integer.parseInt(inputIdade.getText().toString());
        int resultado = idade*7;

        inputResultado.setText("Idade igual a: " + resultado);
    }
}