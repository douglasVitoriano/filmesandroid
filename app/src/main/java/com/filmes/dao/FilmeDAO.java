package com.filmes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.filmes.Model.Filmes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas on 06/01/2017.
 */

public class FilmeDAO extends SQLiteOpenHelper {

    public FilmeDAO(Context context) {
        super(context, "Filmes", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Filme (id INTEGER PRIMARY KEY, " +
                "title TEXT NOT NULL, " +
                "year TEXT, " +
                "poster TEXT, " +
                "imdbID TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";

        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Filme ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql); // indo para versão 2
        }
    }

    public void insere(Filmes filme) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoFilme(filme);

        db.insert("Filmes", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoFilme(Filmes filme) {
        ContentValues dados = new ContentValues();
        dados.put("title", filme.getTitle());
        dados.put("year", filme.getYear());
        dados.put("poster", filme.getPoster());

        return dados;
    }

    public List<Filmes> buscaFilmes() {
        String sql = "SELECT * FROM Filmes;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Filmes> filmes = new ArrayList<Filmes>();
        while (c.moveToNext()) {

//            Filmes filme = new Filmes(title, year, poster, "");
//            filme.setId(c.getLong(c.getColumnIndex("id")));
//            filme.setTitle(c.getString(c.getColumnIndex("title")));
//            filme.setYear(c.getString(c.getColumnIndex("year")));
//            filme.setPoster(c.getString(c.getColumnIndex("poster")));

//            filmes.add(filme);

        }
        c.close();

        return filmes;
    }

    public void deleta(Filmes filme) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {filme.getId().toString()};
        db.delete("Filmes", "id = ?", params);
    }

    public void altera(Filmes filme) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoFilme(filme);

        String[] params = {filme.getId().toString()};
        db.update("Filmes", dados, "id = ?", params);
    }


}