package com.example.eventos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventos.R;

import java.util.ArrayList;

import database.EventoDAO;
import model.Eventos;

public class ListaEventosActivity extends AppCompatActivity {



    private ListView listaDeEventos;
    private ArrayAdapter<Eventos> adapterEventos;


    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_eventos);
        setTitle("Lista de Eventos");
        listaDeEventos = findViewById(R.id.lista_eventos);
        definirOnClickListenerListView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Eventos>(ListaEventosActivity.this, android.R.layout.simple_list_item_1, eventoDAO.listar());
        listaDeEventos.setAdapter(adapterEventos);
    }

    public void onClickAdicionarEvento(View v){
        Intent intent = new Intent(ListaEventosActivity.this, CadastroDeEventosActivity.class);
        startActivity(intent);
    }


    private void definirOnClickListenerListView(){
        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Eventos eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(ListaEventosActivity.this, CadastroDeEventosActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

}
