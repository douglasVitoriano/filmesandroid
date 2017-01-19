package com.filmes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.filmes.Model.Filmes;
import com.filmes.Utils.HTTPDataHandler;
import com.filmes.adapter.FilmesAdapter;
import com.filmes.dao.FilmeDAO;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnBuscarFilme;
    private EditText edtTitleFilme;
    private ListView lstFilmes;

    String srtApi = "http://www.omdbapi.com/?";
    String titleEntrada = "";
    String urlapi = "";


    Filmes filme;
    List<Filmes> listFilmes;
    List<String> str;

//    ImageView imageView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscarFilme = (ImageButton) findViewById(R.id.btnBuscarFilme);
        edtTitleFilme = (EditText) findViewById(R.id.edtTitleFilme);
//        imageView = (ImageView) findViewById(R.id.item_foto);

        lstFilmes = (ListView) findViewById(R.id.lstFilmes);
        lstFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
//                Filmes filmes = (Filmes) lista.getItemAtPosition(position);
//
//                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);
//                Toast.makeText(getApplicationContext(), "testeee " + filmes, Toast.LENGTH_LONG).show();
//
//                intentVaiProFormulario.putExtra("filme", filmes);


//                Filmes filmes = (Filmes) lista.getItemAtPosition(position);
                Filmes filme = (Filmes) listFilmes.get(position);


                Intent intentVaiProFormulario = new Intent(MainActivity.this, FormularioActivity.class);

                intentVaiProFormulario.putExtra("filme", filme.getTitle());

                intentVaiProFormulario.putExtra("Year", filme.getYear());

                intentVaiProFormulario.putExtra("poster", filme.getPoster());

                startActivity(intentVaiProFormulario);
            }
        });

        btnBuscarFilme.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBuscarFilme:
                if (CheckConnection() != false) {

                    titleEntrada = edtTitleFilme.getText().toString();
                    edtTitleFilme.setText("");

                    if (!titleEntrada.isEmpty()) {
                        urlapi = srtApi + "s=" + titleEntrada + "&type=movie&plot=full&r=json";

                        progressDialog = ProgressDialog.show(MainActivity.this, "Carregando . . . ", "", true);
                        progressDialog.setCancelable(true);

                        new ProcessJSON().execute(urlapi);
                    } else {
                        Toast.makeText(getApplicationContext(), "Por favor, insira um título ",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, verifique a conectividade " +
                            "de seu dispositivo.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean CheckConnection() {
        boolean lblnRet = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() &&
                    cm.getActiveNetworkInfo().isConnected()) {
                lblnRet = true;
            } else {
                lblnRet = false;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return lblnRet;
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler httpDH = new HTTPDataHandler();
            stream = httpDH.GetHTTPData(urlString);

            return stream;
        }

        protected void onPostExecute(String stream) {

            if (stream != null && !stream.contains("erro")) {

                try {

                    // Obter os dados completo HTTP como JSONObject
                    JSONObject reader = new JSONObject(stream);

                    JSONArray jsonMainArr = reader.getJSONArray("Search");

                    ArrayList<HashMap<String, String>> inviteList = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> map;
                    listFilmes = new ArrayList<Filmes>();

                    for (int i = 0; i < jsonMainArr.length(); i++) {

                        JSONObject childJSONObject = jsonMainArr.getJSONObject(i);

//                            filme = new Filmes(childJSONObject.getString("Title"), childJSONObject.getString("Year"),
//                                 childJSONObject.getString("Poster"), childJSONObject.getString("imdbID"));

                        map = new HashMap<String, String>();
                        map.put("imdbID", childJSONObject.getString("imdbID"));
                        map.put("title", childJSONObject.getString("Title"));
                        map.put("year", childJSONObject.getString("Year"));
                        map.put("poster", childJSONObject.getString("Poster"));
                        inviteList.add(map);

                        listFilmes.add(new Filmes(childJSONObject.getString("Title"), childJSONObject.getString("Year"),
                                childJSONObject.getString("Poster")));

                    }

                    progressDialog.dismiss();

                    ListAdapter arrayAdapter = new FilmesAdapter(MainActivity.this, inviteList);
                    lstFilmes.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Erro Json: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            } else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Titulo não encontrado!", Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_formulario_filmes_cad:

                Intent intentVaiProFormularioFilmesCad = new Intent(MainActivity.this, FilmesCadastradosActivity.class);
                startActivity(intentVaiProFormularioFilmesCad);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

