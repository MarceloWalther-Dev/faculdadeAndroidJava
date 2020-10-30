package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eventos.R;

import database.EventoDAO;
import database.LocalDAO;
import model.Eventos;
import model.LocalEvento;

public class CadastroDeEventosActivity extends AppCompatActivity {

    private Spinner spinnerLocais;
    private ArrayAdapter<LocalEvento> localAdapter;
    private EditText nomeDoEvento;
    private EditText dataDoEvento;
    private EditText localDoEvento;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro_de_evento);
        setTitle("Cadastro de Eventos");
        spinnerLocais = findViewById(R.id.spinner_locais);
        nomeDoEvento = findViewById(R.id.activity_cadastro_evento_nome_do_evento);
        dataDoEvento = findViewById(R.id.activity_cadastro_evento_data_do_evento);
        localDoEvento = findViewById(R.id.activity_cadastro_evento_local_do_evento);
        carregarLocal();
        carregarEvento();
    }

    private void carregarEvento(){
        Intent intent = getIntent();

        if(intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null){

            Eventos evento = (Eventos) intent.getExtras().get("eventoEdicao");
            nomeDoEvento.setText(evento.getNomeDoEvento());
            dataDoEvento.setText(evento.getDataDoEvento());
            int posicaoLocais = obterPosicaoLocais(evento.getLocalDoEvento());
            spinnerLocais.setSelection(posicaoLocais);
            id = evento.getId();
        }
    }

    private void carregarLocal(){
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        localAdapter = new ArrayAdapter<LocalEvento>(CadastroDeEventosActivity.this, android.R.layout.simple_spinner_item, localDAO.listar());
        spinnerLocais.setAdapter(localAdapter);
    }

    private int obterPosicaoLocais(LocalEvento localEvento){
        for (int posicao = 0; posicao < localAdapter.getCount(); posicao++){
            if(localAdapter.getItem(posicao).getId() == localEvento.getId()){
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v) {

        String nome = nomeDoEvento.getText().toString();
        String date = dataDoEvento.getText().toString();
        String local = localDoEvento.getText().toString();
        int posicaoLocais = spinnerLocais.getSelectedItemPosition();
        LocalEvento localEvento = (LocalEvento) localAdapter.getItem(posicaoLocais);

        if (nome.length() > 0 && nome != "" && date.length() == 9 && date != "" && local.length() > 3 && local != "") {

            Eventos eventos = new Eventos(id, nome, date, localEvento);
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