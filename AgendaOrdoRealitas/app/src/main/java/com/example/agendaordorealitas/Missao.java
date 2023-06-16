package com.example.agendaordorealitas;

import android.annotation.SuppressLint;
import android.app.Service;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Missao extends AppCompatActivity {

    EditText cCodigo;
    EditText cNome;
    EditText cPatente;
    EditText cLocal;
    EditText cOrganizacao;
    Button btSalvar;
    Button btExcluir;
    Button btLimpar;
    ListView viewMissao;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    InputMethodManager imm;

    BancoDados db = new BancoDados(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missao);

        imm = (InputMethodManager) this.getSystemService(
                Service.INPUT_METHOD_SERVICE );

        cCodigo = findViewById(R.id.edtMissao);

        cNome = findViewById(R.id.edtNome);
        cPatente = findViewById(R.id.edtPtt);
        cLocal = findViewById(R.id.edtLocal);
        cOrganizacao = findViewById(R.id.edtOrg);
        btSalvar = findViewById(R.id.btnSalvar);
        btExcluir = findViewById(R.id.btnExcluir);
        btLimpar = findViewById(R.id.btnLimpar);

        viewMissao = findViewById(R.id.ListViewPessoa);

        cNome.requestFocus();

        viewMissao.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                String conteudo = (String)
                        viewMissao.getItemAtPosition(position);

                String codigo = conteudo.substring(0,
                        conteudo.indexOf("-"));

                infoMissao missao = db.selecionarMissao(Integer.parseInt(codigo));

                cCodigo.setText(String.valueOf(missao.getCodigo()));

                cNome.setText(missao.getNomeAgente());
                cPatente.setText(missao.getPatenteAgente());
                cLocal.setText(missao.getLocal());
                cOrganizacao.setText(missao.getOrganizacao());
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cCodigo.setText("");
                cNome.setText("");
                cPatente.setText("");
                cLocal.setText("");
                cOrganizacao.setText("");

                cNome.requestFocus();
            }
        });

        // Botão Salvar.
        btSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String codigo = cCodigo.getText().toString();
                String nome = cNome.getText().toString();
                String patente = cPatente.getText().toString();
                String local = cLocal.getText().toString();
                String organizacao = cOrganizacao.getText().toString();

                if (nome.isEmpty()) {
                    cNome.setError("Este campo é obrigatório!");
                } else if (codigo.isEmpty()) {

                    db.addMissao(new infoMissao(nome, patente, local, organizacao));

                    Toast.makeText(Missao.this, "Cadastro de Missão salvo com " + "sucesso", Toast.LENGTH_SHORT).show();
                    listarMissoes();
                    limpaCampos();
                    escondeTeclado();
                } else {
                    db.atualizarMissao(new infoMissao(Integer.parseInt(codigo), nome, patente, local, organizacao));
                    Toast.makeText(Missao.this, "Cadastro de Missão atualizado" + "com sucesso", Toast.LENGTH_SHORT).show();
                    listarMissoes();
                    limpaCampos();
                    escondeTeclado();
                }
            }
        });

        // Botão Excluir.
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigo = cCodigo.getText().toString();
                if (codigo.isEmpty()) {
                    Toast.makeText(Missao.this, "Nenhuma pessoa está" + "Selecionada", Toast.LENGTH_SHORT).show();
                }else{

                    infoMissao missao = new infoMissao();
                    missao.setCodigo(Integer.parseInt(codigo));
                    db.apagarMissao(missao);
                    Toast.makeText(Missao.this, "Registro da pessoa" + "apagado com sucesso", Toast.LENGTH_SHORT).show();
                    cCodigo.setText("");
                    listarMissoes();
                    limpaCampos();
                }
            }
        });
    }

    void escondeTeclado() {
        imm.hideSoftInputFromWindow(cNome.getWindowToken(),0);
    }

    public void limpaCampos() {
        cCodigo.setText("");
        cNome.setText("");
        cPatente.setText("");
        cLocal.setText("");
        cOrganizacao.setText("");

        cNome.requestFocus();
    }

    public void listarMissoes() {

        List<infoMissao> pessoas = db.listaMissao();

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(Missao.this,
                android.R.layout.simple_list_item_1, arrayList);

        viewMissao.setAdapter(adapter);

        for(infoMissao c : pessoas) {
            //Log.d( "Lista", "\nID: " + c.getCodigo() + "Nome: " + c.getNome(  ));            //arrayList. add( c.getCodigo() + "-" + c.getCodigo());
            arrayList.add(c.getCodigo() + "-" + c.getNomeAgente());
            adapter.notifyDataSetChanged();
        }
    }




}