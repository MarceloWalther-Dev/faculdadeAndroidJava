package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos.R;

import database.EventoDAO;
import model.Eventos;

public class CadastroDeEventosActivity extends AppCompatActivity {

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
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v) {
        EditText nomeDoEvento = findViewById(R.id.activity_cadastro_evento_nome_do_evento);
        EditText dataDoEvento = findViewById(R.id.activity_cadastro_evento_data_do_evento);
        EditText localDoEvento = findViewById(R.id.activity_cadastro_evento_local_do_evento);

        String nome = nomeDoEvento.getText().toString();
        String date = dataDoEvento.getText().toString();
        String local = localDoEvento.getText().toString();

        if (nome.length() > 0 && nome != "" && date.length() == 9 && date != "" && local.length() > 3 && local != "") {

            Eventos eventos = new Eventos(id, nome, date, local);
            EventoDAO eventoDAO = new EventoDAO(getBaseContext());
            boolean salvou = eventoDAO.salvar(eventos);
            if (salvou) {
                finish();
            } else {
                Toast.makeText(CadastroDeEventosActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(CadastroDeEventosActivity.this, "Por favor, preencher todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDeletarEvento(View v){
        EventoDAO eventoDAO = new EventoDAO((getBaseContext()));
        boolean excluiu = eventoDAO.excluir(id);
        if(excluiu){
            finish();
        }else{
            Toast.makeText(CadastroDeEventosActivity.this, "Erro ao excluir", Toast.LENGTH_LONG).show();
        }
    }

}