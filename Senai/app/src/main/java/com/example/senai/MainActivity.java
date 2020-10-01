package com.example.senai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final EditText editTextTexto = findViewById(R.id.et_texto);
//        Button btnMostrar = findViewById(R.id.btn_mostrar);

//        btnMostrar.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View v){
//                Toast.makeText(MainActivity.this,editTextTexto.getText(),Toast.LENGTH_LONG).show();
//            }
//        });
//
   }



    public void onCLickBtnMostrar(View v){
        EditText editText = findViewById(R.id.et_texto);
        Toast.makeText(MainActivity.this, editText.getText(),Toast.LENGTH_LONG).show();
    }


    public void onClickBtnNomeCompleto(View v){
      final   EditText primeiroNome = findViewById(R.id.et_texto);
      final   EditText segundoNome = findViewById(R.id.et_segundoNome);
      final   EditText nomeCompleto = findViewById(R.id.et_nomeCompleto);

      nomeCompleto.setText(primeiroNome.getText().toString() + " " + segundoNome.getText().toString());

        Toast.makeText(MainActivity.this, nomeCompleto.getText() ,Toast.LENGTH_LONG).show();
    }


    public void limparNomes(View v){
        final EditText primeiroNome = findViewById(R.id.et_texto);
        final EditText segundoNome = findViewById(R.id.et_segundoNome);

        primeiroNome.setText("");
        segundoNome.setText("");
    }
}