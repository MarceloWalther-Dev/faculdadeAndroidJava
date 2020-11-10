package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos.R;

import java.util.ArrayList;
import java.util.List;

import database.EventoDAO;
import database.LocalDAO;
import model.Eventos;
import model.LocalEvento;

public class CadastroDeLocaisActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextClubName;
    private EditText editTextBairro;
    private EditText editTextCity;
    private EditText editTextCapacidadePublico;
    private EventoDAO eventoDao = new EventoDAO(getBaseContext());
    private List<String> lista = new ArrayList<String>(){{
        add("");
        add("");
        add("ASC");
    }};


    private List<Eventos> eventos = eventoDao.listar(lista);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_locais);
        setTitle("Cadastro de Clubs");
        editTextClubName = findViewById(R.id.editTextText_local_nome);
        editTextBairro = findViewById(R.id.editText_local_bairro);
        editTextCity = findViewById(R.id.editText_local_cidade);
        editTextCapacidadePublico = findViewById(R.id.editText_local_capacidade);
        carregarLocal();
    }

    public void carregarLocal(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("localEdicao") != null){
            LocalEvento localEvento = (LocalEvento) intent.getExtras().get("localEdicao");
            editTextClubName.setText(localEvento.getNome());
            editTextBairro.setText(localEvento.getBairro());
            editTextCity.setText(localEvento.getCidade());
            editTextCapacidadePublico.setText(localEvento.getCapacidadePublico());
            id = localEvento.getId();
        }
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        String nome = editTextClubName.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String city = editTextCity.getText().toString();
        int capacidade =Integer.parseInt(editTextCapacidadePublico.getText().toString());
        LocalEvento localEvento = new LocalEvento(id,nome,bairro,city,capacidade);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(localEvento);

        if (salvou){
            finish();
        }else{
            Toast.makeText(CadastroDeLocaisActivity.this
                    , "Erro ao salvar", Toast.LENGTH_LONG).show();
        }

    }


    public void onClickExcluir(View v) {
        LocalDAO localDao = new LocalDAO(getBaseContext());
        boolean excluiu = localDao.excluir(id, eventos);
        if (excluiu) {
            finish();
        } else {
            Toast.makeText(CadastroDeLocaisActivity.this, "Erro ao excluir.", Toast.LENGTH_LONG).show();
        }
    }

}