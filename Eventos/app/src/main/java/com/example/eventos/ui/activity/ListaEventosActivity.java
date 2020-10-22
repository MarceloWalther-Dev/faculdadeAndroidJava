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

import model.Eventos;

public class ListaEventosActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int REQUEST_CODE_EDITAR_EVENTO = 2;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_EXCLUIDO = 21;

    private ListView listaDeEventos;
    private ArrayAdapter<Eventos> adapterEventos;


    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_eventos);
        setTitle("Lista de Eventos");


        listaDeEventos = findViewById(R.id.lista_eventos);
        ArrayList<Eventos> eventos = new ArrayList<Eventos>();

        adapterEventos = new ArrayAdapter<Eventos>(ListaEventosActivity.this, android.R.layout.simple_list_item_1, eventos);
        listaDeEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();
    }


    public void onClickAdicionarEvento(View v){
        Intent intent = new Intent(ListaEventosActivity.this, CadastroDeEventosActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);
    }


    private void definirOnClickListenerListView(){
        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Eventos eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(ListaEventosActivity.this, CadastroDeEventosActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivityForResult(intent,REQUEST_CODE_EDITAR_EVENTO);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO){
            Eventos eventos = (Eventos) data.getExtras().getSerializable("novoEvento");
            eventos.setId(++id);
            this.adapterEventos.add(eventos);
        }else if(requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EDITADO){
            Eventos eventoEditado = (Eventos) data.getExtras().getSerializable("eventoEditado");
            for (int i =0; i < adapterEventos.getCount(); i++){
                Eventos eventos = adapterEventos.getItem(i);
                if(eventos.getId() == eventoEditado.getId()){
                    adapterEventos.remove(eventos);
                    adapterEventos.insert(eventoEditado, i);
                    break;
                }
            }
        }else if(requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EXCLUIDO){
            int idExcluir = data.getExtras().getInt("eventoExcluido");
            for(int i =0; i < adapterEventos.getCount(); i++){
                Eventos evento = adapterEventos.getItem(i);
                if(evento.getId() == idExcluir){
                    adapterEventos.remove(evento);
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}