package com.example.marvelapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Herois extends AppCompatActivity {
    private EditText txtHeroi;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herois);

        setTitle("Heróis Marvel");
        txtHeroi = findViewById(R.id.txtNomeHeroi);
        btnPesquisar = findViewById(R.id.btnPesquisarH);
    }

    public void pesquisarHerois(View view) {
        String queryString = txtHeroi.getText().toString();

        InputMethodManager escondeTeclado = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (escondeTeclado != null) {
            escondeTeclado.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
