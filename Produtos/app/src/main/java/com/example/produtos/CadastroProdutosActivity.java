package com.example.produtos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.produtos.database.ProdutoDAO;
import com.example.produtos.model.Produto;

public class CadastroProdutosActivity extends AppCompatActivity {




    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
        setTitle("Cadastro de Produto");
        carregarProduto();

    }

    private void carregarProduto(){

        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("produtoEdicao") != null){
            Produto produto = (Produto) intent.getExtras().get("produtoEdicao");
            EditText editTextNome = findViewById(R.id.editTextTextNome);
            EditText editTextValor = findViewById(R.id.editText_valor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));

            id= produto.getId();
        }

    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editTextTextNome);
        EditText editTextValor = findViewById(R.id.editText_valor);

        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());

        Produto produto = new Produto(id, nome,valor);
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
            boolean salvou = produtoDAO.salvar(produto);
            if(salvou){
                finish();
            }else{
                Toast.makeText(CadastroProdutosActivity.this, "erro ao salvar", Toast.LENGTH_LONG).show();
            }
        }


    public void onClickDeletar(View v){
        ProdutoDAO produtoDAO = new ProdutoDAO(getBaseContext());
        boolean excluiu = produtoDAO.excluir(id);
            if(excluiu){
                finish();
            }else{
                Toast.makeText(CadastroProdutosActivity.this, "Erro ao excluir", Toast.LENGTH_LONG).show();
            }
        }
    }
