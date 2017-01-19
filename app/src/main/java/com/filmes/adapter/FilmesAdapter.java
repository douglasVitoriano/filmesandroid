package com.filmes.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filmes.MainActivity;
import com.filmes.Model.Filmes;
import com.filmes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Douglas on 05/01/2017.
 */

public class FilmesAdapter extends ArrayAdapter<HashMap<String, String>>{

    public FilmesAdapter(Context context, ArrayList<HashMap<String, String>> filmes){
        super(context, R.layout.list_item_filmes, filmes);
    }

    @Override
    public View getView(int position, View converterView, ViewGroup parent){
        LayoutInflater filmesInflater = LayoutInflater.from(getContext());
        View customView = filmesInflater.inflate(R.layout.list_item_filmes, parent, false);

        HashMap<String, String> singleFilmeItem = getItem(position);
        TextView tituloText = (TextView) customView.findViewById(R.id.item_titulo);
        ImageView imgPoster = (ImageView) customView.findViewById(R.id.item_foto);

        tituloText.setText(singleFilmeItem.get("title"));
        Picasso.with(getContext()).load(singleFilmeItem.get("poster")).resize(100,100).into(imgPoster);

        return customView;
    }
}
