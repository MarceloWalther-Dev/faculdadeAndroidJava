package com.example.calculadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora.R;
import com.example.calculadora.model.Botoes;
import com.example.calculadora.model.Calculadora;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String primeiroNumero = "";
    private String segundoNumero = "";
    private String operacao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Calculadora");
        display = findViewById(R.id.display);

    }

    public void onClickBotao1(View v){
        atualizaNumeroDigitado("1");
        atualizarDisplay("1");
    }


    public void onClickBotao2(View v){
        atualizaNumeroDigitado("2");
        atualizarDisplay("2");
    }

    public void onClickBotao3(View v){
        atualizaNumeroDigitado("3");
        atualizarDisplay("3");
    }

    public void onClickBotao4(View v){
        atualizaNumeroDigitado("4");
        atualizarDisplay("4");
    }

    public void onClickBotao5(View v){
        atualizaNumeroDigitado("5");
        atualizarDisplay("5");
    }

    public void onClickBotao6(View v){
        atualizaNumeroDigitado("6");
        atualizarDisplay("6");
    }

    public void onClickBotao7(View v){
        atualizaNumeroDigitado("7");
        atualizarDisplay("7");
    }

    public void onClickBotao8(View v){
        atualizaNumeroDigitado("8");
        atualizarDisplay("8");
    }

    public void onClickBotao9(View v){
        atualizaNumeroDigitado("9");
        atualizarDisplay("9");
    }

    public void onClickBotao0(View v){
        atualizaNumeroDigitado("0");
        atualizarDisplay("0");
    }


    public void  onClickSoma(View v){
        operacao = "+";
        atualizarDisplay("+");
    }


    public void  onClickMulti(View v){
        operacao = "X";
        atualizarDisplay("X");
    }

    public void  onClickDivisao(View v){
        operacao = "÷";
        atualizarDisplay("÷");
    }



    public void onClickIgual(View v) {
        if (!primeiroNumero.isEmpty() && !segundoNumero.isEmpty()) {
            int numero1 = Integer.parseInt(primeiroNumero);
            int numero2 = Integer.parseInt(segundoNumero);

            if (operacao.equals("+")) {
                int resultado = numero1 + numero2;
                display.setText(String.valueOf(resultado));
            } else if (operacao.equals("X")) {
                int resultado = numero1 * numero2;
                display.setText(String.valueOf(resultado));
            } else if (operacao.equals("÷") && numero2 != 0) {
                int resultado = numero1 / numero2;
                display.setText(String.valueOf(resultado));
                Toast.makeText(MainActivity.this, "O numero 1 é  " + numero1 + "o numero 2 é " + numero2, Toast.LENGTH_LONG).show();
            } else if (operacao.equals("÷") && numero2 == 0) {
                Toast.makeText(MainActivity.this, "Impossivel dividir por 0" + numero2, Toast.LENGTH_LONG).show();

            }
        }
    }


    private void atualizaNumeroDigitado(String numero){
        if (operacao.isEmpty()){
            primeiroNumero = primeiroNumero + numero;
        }else{
            segundoNumero = segundoNumero + numero;
        }
    }

    public void atualizarDisplay(String text){
        TextView display = findViewById(R.id.display);
        String textDisplay = display.getText().toString();
        textDisplay = textDisplay + text;
        display.setText(textDisplay);
    }


    public void limpaDisplay(View v){
        primeiroNumero = "";
        segundoNumero = "";
        TextView display = findViewById(R.id.display);
        display.setText("");

    }

}