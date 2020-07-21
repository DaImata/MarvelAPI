package com.example.marvelapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Animes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText txtHeroi;
    private TextView txtInformacaoHeroi, txtInformacao, txtInformacaoHeroi3;
    private Button btnMaisInformacoes;
    String Score, Episodes, Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animes);

        setTitle("Pesquisar Animes");
        txtHeroi = findViewById(R.id.txtNomeHeroi);
        txtInformacaoHeroi = findViewById(R.id.txtInformacaoHeroi);
        txtInformacao = findViewById(R.id.txtInformacaoHeroi2);
        txtInformacaoHeroi3 = findViewById(R.id.txtInformacaoHeroi3);
        btnMaisInformacoes = findViewById(R.id.btnPesquisarH2);
        btnMaisInformacoes.setEnabled(false);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void pesquisarHerois(View view) {
        String queryString = txtHeroi.getText().toString();

        InputMethodManager escondeTeclado = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (escondeTeclado != null) {
            escondeTeclado.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        ConnectivityManager statusConexao = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (statusConexao != null) {
            networkInfo = statusConexao.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            txtInformacaoHeroi.setText(R.string.carregando);
            txtInformacao.setText(R.string.carregando);
        }
        else {
            if (queryString.length() == 0) {
                txtInformacaoHeroi.setText(R.string.sem_termodebusca);
                txtInformacao.setText(R.string.sem_termodebusca);
            } else {
                txtInformacaoHeroi.setText(R.string.sem_internet);
                txtInformacao.setText(R.string.sem_internet);
            }
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaAnimes(this, queryString);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Obtem o JSONArray dos itens de livros
            JSONArray resultados = jsonObject.getJSONArray("results");
            // Procura pro resultados nos itens do array
                // Obtem a informação
                JSONObject obj= resultados.getJSONObject(0);
                Score = obj.getString("score");
                Episodes = obj.getString("episodes");
                Url = obj.getString("url");

            //mostra o resultado qdo possivel.
            if (Score != null || Episodes != null) {
                txtInformacaoHeroi.setText("Nota do anime: " + Score + "/10");
                txtInformacao.setText("Número de episódios: " + Episodes);
                btnMaisInformacoes.setEnabled(true);
                txtInformacaoHeroi3.setText(R.string.mais_informacoes);
            } else {
                // If none are found, update the UI to show failed results.
                txtInformacaoHeroi.setText(R.string.sem_resultados);
                txtInformacao.setText(R.string.sem_resultados);
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            txtInformacaoHeroi.setText(R.string.sem_resultados);
            txtInformacao.setText(R.string.sem_resultados);
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
    public void MaisInformacoes (View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(Url));
        startActivity(intent);
    }
}