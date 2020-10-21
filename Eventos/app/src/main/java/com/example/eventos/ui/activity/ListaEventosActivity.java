package com.example.eventos.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventos.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaEventosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_eventos);
        setTitle("Lista de Eventos");

        List<String> eventos = new ArrayList<>(
                Arrays.asList("P12", "Feelds", "Donna", "Aliança", "Public", "Alternativo", "La Bodeguita", "Bokas", "Bokarra"));
        ListView listaDeEventos = findViewById(R.id.lista_eventos);
        listaDeEventos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventos));
    }
}