package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos.R;

import model.Eventos;

public class CadastroDeEventosActivity extends AppCompatActivity {


    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_EXCLUIDO = 21;

    private boolean edicao = false;
    private int id = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro_de_evento);
        setTitle("Cadastro de Eventos");

        carregarEvento();
    }

    private void carregarEvento(){
        Intent intent = getIntent();

        if(intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null){
            Eventos evento = (Eventos) intent.getExtras().get("eventoEdicao");
            EditText nomeDoEvento = findViewById(R.id.activity_cadastro_evento_nome_do_evento);
            EditText dataDoEvento = findViewById(R.id.activity_cadastro_evento_data_do_evento);
            EditText localDoEvento = findViewById(R.id.activity_cadastro_evento_local_do_evento);
            nomeDoEvento.setText(evento.getNomeDoEvento());
            dataDoEvento.setText(evento.getDataDoEvento());
            localDoEvento.setText(evento.getLocalDoEvento());
            edicao = true;
            id = evento.getId();
        }
    }



    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText nomeDoEvento = findViewById(R.id.activity_cadastro_evento_nome_do_evento);
        EditText dataDoEvento = findViewById(R.id.activity_cadastro_evento_data_do_evento);
        EditText localDoEvento = findViewById(R.id.activity_cadastro_evento_local_do_evento);

        String nome = nomeDoEvento.getText().toString();
        String date = dataDoEvento.getText().toString();
        String local = localDoEvento.getText().toString();


        if(nome != null && nome.length() > 2 && date != null && date.length() > 4 && local != null && local.length() > 5) {
            Eventos evento = new Eventos(id, nome, date, local);
            Intent intent = new Intent();

            if (edicao) {
                intent.putExtra("eventoEditado", evento);
                setResult(RESULT_CODE_EVENTO_EDITADO, intent);
            } else {
                intent.putExtra("novoEvento", evento);
                setResult(RESULT_CODE_NOVO_EVENTO, intent);
            }
        }else{
            Toast.makeText(CadastroDeEventosActivity.this,"Dados incompletos, verifique seus dados e tente novamente",Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public void onClickDeletarEvento(View v){
        if(edicao){
            Intent intent = new Intent();
            intent.putExtra("eventoExcluido", id);
            setResult(RESULT_CODE_EVENTO_EXCLUIDO, intent);
            finish();
        }
    }

}