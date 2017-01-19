package com.filmes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.filmes.Model.Filmes;
import com.filmes.dao.FilmeDAO;

import java.util.List;

/**
 * Created by Douglas on 19/01/2017.
 */

public class FilmesCadastradosActivity extends AppCompatActivity{
    private ListView lstFilmesCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_cadastrados);
//                      carregaLista();
        lstFilmesCadastrados = (ListView) findViewById(R.id.lista_filmes_cad);
        lstFilmesCadastrados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {

//                Intent intentVaiProFormularioFilmesCad = new Intent(FilmesCadastradosActivity.this, FormularioActivity.class);
//                startActivity(intentVaiProFormularioFilmesCad);
            }
        });
    }

    private void carregaLista() {
        FilmeDAO dao = new FilmeDAO(this);
        List<Filmes> filmes = dao.buscaFilmes();
        dao.close();

//        FilmesAdapter adapter = new FilmesAdapter(this, filmes);
//        lista.setAdapter(adapter);
    }
}


