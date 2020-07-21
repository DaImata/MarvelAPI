package com.example.marvelapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("API de Animes");
    }
    public void Animes (View view){
        Intent intentAnimes = new Intent (MainActivity.this, Animes.class);
        startActivity(intentAnimes);
    }
    public void Mangas (View view){
        Intent intentMangas = new Intent (MainActivity.this, Mangas.class);
        startActivity(intentMangas);
    }
}