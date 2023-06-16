package com.example.agendaordorealitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String BANCO_AGENDA = "db_agenda";

    public BancoDados(Context content) {
        super(content, BANCO_AGENDA, null, VERSAO_BANCO);
    }

    public static final String TABELA_MISSAO = "tb_pessoa";

    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_NOME = "nomeAgente";
    public static final String COLUNA_PATENTE = "patenteAgente";
    public static final String COLUNA_LOCAL = "local";
    public static final String COLUNA_ORGANIZACAO = "organizacao";

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CRIAR_TABELA = "CREATE TABLE " + TABELA_MISSAO + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUNA_NOME + " TEXT, " +
                COLUNA_PATENTE + " TEXT, " + COLUNA_LOCAL + " TEXT, " + COLUNA_ORGANIZACAO + " TEXT) ";

        db.execSQL(CRIAR_TABELA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addMissao(infoMissao missao) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, missao.getNomeAgente());
        valor.put(COLUNA_PATENTE, missao.getPatenteAgente());
        valor.put(COLUNA_LOCAL, missao.getLocal());
        valor.put(COLUNA_ORGANIZACAO, missao.getOrganizacao());

        db.insert(TABELA_MISSAO, null, valor);
        db.close();
    }

    void apagarMissao(infoMissao missao) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_MISSAO, COLUNA_CODIGO + " =?", new String[]{
                String.valueOf(missao.getCodigo())
        });

        db.close();
    }

    infoMissao selecionarMissao(int codigo) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_MISSAO, new String[]{COLUNA_CODIGO, COLUNA_NOME, COLUNA_PATENTE, COLUNA_LOCAL, COLUNA_ORGANIZACAO},
                COLUNA_CODIGO + " = ?", new String[]{String.valueOf(codigo)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        infoMissao missao = new infoMissao(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));

        return missao;
    }

    void atualizarMissao(infoMissao missao) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(COLUNA_NOME, missao.getNomeAgente());
        valor.put(COLUNA_PATENTE, missao.getPatenteAgente());
        valor.put(COLUNA_LOCAL, missao.getLocal());
        valor.put(COLUNA_ORGANIZACAO, missao.getOrganizacao());

        db.update(TABELA_MISSAO, valor, COLUNA_CODIGO + " =?", new String[]{
                String.valueOf(missao.getCodigo())});

    }

    public List<infoMissao> listaMissao() {

        List<infoMissao> pessoaLista = new ArrayList<infoMissao>();

        String query = "SELECT * FROM " + TABELA_MISSAO;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {

                infoMissao missao = new infoMissao();

                missao.setCodigo(Integer.parseInt(cursor.getString(0)!=null?cursor.getString(0):"0"));
                missao.setNomeAgente(cursor.getString(1));
                missao.setPatenteAgente(cursor.getString(2));
                missao.setLocal(cursor.getString(3));
                missao.setOrganizacao(cursor.getString(4));

                pessoaLista.add(missao);

            }while (cursor.moveToNext());
        }
        return pessoaLista;
    }

}
