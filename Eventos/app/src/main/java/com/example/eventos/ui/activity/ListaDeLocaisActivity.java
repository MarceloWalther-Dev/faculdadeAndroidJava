package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eventos.R;

import database.LocalDAO;
import model.LocalEvento;

public class ListaDeLocaisActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<LocalEvento> adapterLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_locais);
        listViewLocais = findViewById(R.id.listView_lista_locais);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<LocalEvento>(ListaDeLocaisActivity.this, android.R.layout.simple_list_item_1, localDAO.listar());
        listViewLocais.setAdapter(adapterLocais);
    }

     private void definirOnClickListenerListView(){
        listViewLocais.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                LocalEvento localEventoClicado = adapterLocais.getItem(position);
                Intent intent = new Intent(ListaDeLocaisActivity.this, CadastroDeLocaisActivity.class);
                intent.putExtra("localEdicao", localEventoClicado);
                startActivity(intent);
            }
         });
    }

    public void onClickNovaCategogira(View v){
        Intent intent = new Intent(ListaDeLocaisActivity.this, CadastroDeLocaisActivity.class);
        startActivity(intent);
    }

    public void onClickEvento(View v){
        Intent intent = new Intent(ListaDeLocaisActivity.this, ListaEventosActivity.class);
        startActivity(intent);
        finish();
    }
}