package com.example.eventos.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eventos.R;

import java.util.ArrayList;
import java.util.List;

import database.EventoDAO;
import database.LocalDAO;
import model.Eventos;
import model.LocalEvento;

public class ListaDeLocaisActivity extends AppCompatActivity {

    public ListView listViewLocal;
    private ArrayAdapter<LocalEvento> adapterLocal;
    private EventoDAO eventoDao = new EventoDAO(getBaseContext());
    private List<String> listaQuery = new ArrayList<String>() {{
        add("");
        add("");
        add("ASC");
    }};
    private List<Eventos> eventos = eventoDao.listar(listaQuery);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_locais);
        setTitle("Locais");
        listViewLocal = findViewById(R.id.listView_lista_locais);
        registerForContextMenu(listViewLocal);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDao = new LocalDAO(getBaseContext());
        adapterLocal = new ArrayAdapter<LocalEvento>(ListaDeLocaisActivity.this,
                android.R.layout.simple_list_item_1,
                localDao.listar());
        listViewLocal.setAdapter(adapterLocal);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Editar");
        menu.add(0, v.getId(), 0, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        LocalEvento localClicado = adapterLocal.getItem(position);
        if (item.getTitle() == "Editar") {
            Intent intent = new Intent(ListaDeLocaisActivity.this, CadastroDeLocaisActivity.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        } else if (item.getTitle() == "Excluir") {
            LocalDAO localDao = new LocalDAO(getBaseContext());
            if (!localDao.excluir(localClicado.getId(), eventos)) {
                Toast.makeText(ListaDeLocaisActivity.this, "Erro ao excluir. Primeiro remova os eventos ligados a este local", Toast.LENGTH_LONG).show();
            } else {
                adapterLocal.remove(localClicado);
            }
        }
        return true;
    }

    private void definirOnClickListenerListView() {
        listViewLocal.setOnItemClickListener((parent, view, position, id) -> {
            LocalEvento localClicado = adapterLocal.getItem(position);
            Intent intent = new Intent(ListaDeLocaisActivity.this, CadastroDeLocaisActivity.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        });
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListaDeLocaisActivity.this, CadastroDeLocaisActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v) {
        Intent intent = new Intent(ListaDeLocaisActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}