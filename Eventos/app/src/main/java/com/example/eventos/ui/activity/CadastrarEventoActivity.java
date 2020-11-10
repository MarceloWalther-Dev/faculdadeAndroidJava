package com.example.eventos.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventos.R;

import java.text.DateFormat;
import java.util.Calendar;

import database.EventoDAO;
import database.LocalDAO;
import model.DatePickerFragment;
import model.Eventos;
import model.LocalEvento;


public class CadastrarEventoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private int id = 0;
    private Spinner spinnerLocal;
    private ArrayAdapter<LocalEvento> localAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_evento);
        setTitle("Cadastro de Evento");

        Button btn_data = (Button) findViewById(R.id.btn_data);
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        spinnerLocal = findViewById(R.id.spinner_locais);
        carregarLocal();
        carregarEvento();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String valorData = DateFormat.getDateInstance().format(c.getTime());
        TextView show_data = (TextView) findViewById(R.id.show_data);
        show_data.setText(valorData);
    }

    private void carregarLocal() {
        LocalDAO localDao = new LocalDAO(getBaseContext());
        localAdapter = new ArrayAdapter<LocalEvento>(CadastrarEventoActivity.this,
                android.R.layout.simple_spinner_item,
                localDao.listar());
        spinnerLocal.setAdapter(localAdapter);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {
            Eventos evento = (Eventos) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.field_nome);
            TextView textViewData = findViewById(R.id.show_data);

            editTextNome.setText(evento.getNomeDoEvento());
            textViewData.setText(evento.getDataDoEvento());
            int posicaoLocal = obterPosicaoLocal(evento.getLocalDoEvento());
            spinnerLocal.setSelection(posicaoLocal);
            id = evento.getId();
        }
    }

    private int obterPosicaoLocal(LocalEvento local) {
        for (int posicao = 0; posicao < localAdapter.getCount(); posicao++) {
            if (localAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.field_nome);
        TextView textViewData = findViewById(R.id.show_data);

        String nome = editTextNome.getText().toString();
        String data = textViewData.getText().toString();

        if (nome.equals("")) {
            Toast.makeText(CadastrarEventoActivity.this, "O campo NOME é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        if (data.equals("")) {
            Toast.makeText(CadastrarEventoActivity.this, "O campo DATA é obrigatório", Toast.LENGTH_LONG).show();
            return;
        }

        int posicaoLocal = spinnerLocal.getSelectedItemPosition();
        LocalEvento local = (LocalEvento) localAdapter.getItem(posicaoLocal);
        Eventos evento = new Eventos(id, nome, data, local);
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        boolean salvou = eventoDao.salvar(evento);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastrarEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickExcluir(View v) {
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        boolean excluiu = eventoDao.excluir(id);
        if (excluiu) {
            finish();
        } else {
            Toast.makeText(CadastrarEventoActivity.this, "Erro ao excluir", Toast.LENGTH_LONG).show();
        }
    }
}