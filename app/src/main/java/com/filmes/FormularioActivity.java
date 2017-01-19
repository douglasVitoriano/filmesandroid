package com.filmes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.filmes.Model.Filmes;
import com.filmes.dao.FilmeDAO;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Douglas on 06/01/2017.
 */

public class FormularioActivity extends AppCompatActivity {

    String filme_year;
    String filme_title;
    String filme_post;

    TextView campoTitulo;
    TextView campoAno;

    TextView txtVtitle;
    TextView txtVYear;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

//        Filmes filme = (Filmes) intent.getSerializableExtra("filme");

        txtVtitle = (TextView) findViewById(R.id.formulario_titulo);
        txtVYear = (TextView) findViewById(R.id.formulario_ano_exibicao);
        imgPoster = (ImageView) findViewById(R.id.formulario_foto);

        filme_title = intent.getStringExtra("filme");
        txtVtitle.setText(filme_title);

        filme_year = intent.getStringExtra("Year");
        txtVYear.setText(filme_year);

        filme_post = intent.getStringExtra("poster");
        Picasso.with(this).load(filme_post).fit().into(imgPoster);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                campoTitulo = (TextView) findViewById(R.id.formulario_titulo);
                campoAno = (TextView) findViewById(R.id.formulario_ano_exibicao);

                Filmes filme = new Filmes(campoTitulo.getText().toString(),campoAno.getText().toString(), filme_post.toString());

                FilmeDAO dao = new FilmeDAO(this);
                if (filme.getId() != null){
                    dao.altera(filme);
                }else {
                    dao.insere(filme);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "Filme " + filme.getTitle() + " salvo!!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

