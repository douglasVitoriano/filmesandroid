//package com.filmes;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.filmes.Model.Filmes;
//
///**
// * Created by Douglas on 06/01/2017.
// */
//
//public class FormularioHelper {
//    private Filmes filme;
//    private final TextView campoTitulo;
//    private final TextView campoAnoExibicao;
//    private final TextView campoTempoExecucao;
//    private final TextView campoDiretor;
//    private final TextView campoGenero;
//    private final TextView campoEscritor;
//    private final TextView campoAtores;
//    private final TextView campoEnredo;
//    private final ImageView campoFoto;
//
//    public FormularioHelper(FormularioActivity activity){
//        campoTitulo = (TextView) activity.findViewById(R.id.formulario_titulo);
//        campoAnoExibicao = (TextView) activity.findViewById(R.id.formulario_ano_exibicao);
//        campoTempoExecucao = (TextView) activity.findViewById(R.id.formulario_tempo_execucao);
//        campoDiretor = (TextView) activity.findViewById(R.id.formulario_diretor);
//        campoGenero = (TextView) activity.findViewById(R.id.formulario_genero);
//        campoEscritor = (TextView) activity.findViewById(R.id.formulario_escritor);
//        campoAtores = (TextView) activity.findViewById(R.id.formulario_atores);
//        campoEnredo = (TextView) activity.findViewById(R.id.formulario_enredo);
//        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
//
////        filme = new Filmes();
//    }
//
//    public Filmes pegaFilme() {
//        filme.setTitle(campoTitulo.getText().toString());
//        filme.setYear(campoAnoExibicao.getText().toString());
////        filme.setRuntime(campoTempoExecucao.getText().toString());
////        filme.setDirector(campoDiretor.getText().toString());
////        filme.setGenre(campoGenero.getText().toString());
////        filme.setWriter(campoEscritor.getText().toString());
////        filme.setActors(campoAtores.getText().toString());
////        filme.setPlot(campoEnredo.getText().toString());
////        filme.setCaminhoFoto((String) campoFoto.getTag());
//
//        return filme;
//    }
//
//    public void preencheFormulario(Filmes filme) {
//        campoTitulo.setText(filme.getTitle());
//        campoAnoExibicao.setText(filme.getYear());
//        campoTempoExecucao.setText(filme.getRuntime());
//        campoDiretor.setText(filme.getDirector());
//        campoGenero.setText(filme.getGenre());
//        campoEscritor.setText(filme.getWriter());
//        campoAtores.setText(filme.getActors());
//        campoEnredo.setText(filme.getPlot());
//        carregaImage(filme.getCaminhoFoto());
//
//        this.filme = filme;
//    }
//
//    public void carregaImage(String caminhoFoto) {
//        if (caminhoFoto != null) {
//            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
//            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
//            campoFoto.setImageBitmap(bitmapReduzido);
//            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
//            campoFoto.setTag(caminhoFoto);
//        }
//    }
//}
