package com.example.calculadora.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculadora.R;
import com.example.calculadora.model.Botoes;
import com.example.calculadora.model.Calculadora;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String primeiroNumero = "";
    private String segundoNumero = "";
    private String operacao = "";

    DecimalFormat df = new DecimalFormat("0.00");

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

    public void onClickBotaoPi(View v){
        atualizaNumeroDigitado("3.14");
        atualizarDisplay("3.14");
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

    public void onClickRaiz(View v){
        operacao = "√";
        atualizarDisplay("√");
    }



    public void onClickIgual(View v) {
        if (!primeiroNumero.isEmpty() && !segundoNumero.isEmpty()) {
            float numero1 = Float.parseFloat(primeiroNumero);
            float numero2 = Float.parseFloat(segundoNumero);

            if (operacao.equals("+")) {
                float resultado = numero1 + numero2;
                display.setText(df.format(resultado));
            } else if (operacao.equals("X")) {
                float resultado = numero1 * numero2;
                display.setText(df.format(resultado));
            } else if (operacao.equals("÷") && numero2 != 0) {
                float resultado = numero1 / numero2;
                display.setText(df.format(resultado));
            } else if (operacao.equals("÷") && numero2 == 0) {
                limps();
                Toast.makeText(MainActivity.this, "Impossivel dividir por 0" , Toast.LENGTH_LONG).show();
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
        limps();
    }

    public void limps(){
        primeiroNumero = "";
        segundoNumero = "";
        operacao = "";
        display.setText("");

    }

}