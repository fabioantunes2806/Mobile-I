package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_AGENDA = "db_agenda";

    public BancoDados(Context content) {
        super(content, BANCO_AGENDA, null, VERSAO_BANCO);
    }

    public static final String TABELA_PESSOA = "tb_pessoa";

    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_ENDERECO = "endereco";


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRIAR_TABELA = "CREATE TABLE " + TABELA_PESSOA + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, " +
                COLUNA_EMAIL + " TEXT, " + COLUNA_TELEFONE + " TEXT, " + COLUNA_ENDERECO + " TEXT) ";

        db.execSQL(CRIAR_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addPessoa(Pessoa pessoa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, pessoa.getNome());
        valor.put(COLUNA_EMAIL, pessoa.getEmail());
        valor.put(COLUNA_TELEFONE, pessoa.getTelefone());
        valor.put(COLUNA_ENDERECO, pessoa.getEndereco());

        db.insert(TABELA_PESSOA, null, valor);
        db.close();
    }

    void apagarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PESSOA, COLUNA_CODIGO + " =?", new String[]{
                String.valueOf(pessoa.getCodigo())
        });

        db.close();
    }

    Pessoa selecionarPessoa(int codigo) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_PESSOA, new String[]{
                COLUNA_CODIGO, COLUNA_NOME, COLUNA_EMAIL, COLUNA_TELEFONE, COLUNA_ENDERECO, COLUNA_CODIGO + " =?",
                new String[]{String.valueOf(codigo)}, null, null, null, null});

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));

        return pessoa;
    }

    void atualizarPessoa(Pessoa pessoa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, pessoa.getNome());
        valor.put(COLUNA_EMAIL, pessoa.getEmail());
        valor.put(COLUNA_TELEFONE, pessoa.getTelefone());
        valor.put(COLUNA_ENDERECO, pessoa.getEndereco());

        db.update(TABELA_PESSOA, valor, COLUNA_CODIGO + " =?", new String[]{String.valueOf(pessoa.getCodigo())});

        //Fechar banco de dados
        db.close();
    }
}