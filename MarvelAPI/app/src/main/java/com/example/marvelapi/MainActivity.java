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

        setTitle("Marvel API");
    }
    public void Herois (View view){
        Intent intentHerois = new Intent (MainActivity.this, Herois.class);
        startActivity(intentHerois);
    }
    public void Quadrinhos (View view){
        Intent intentQuadrinhos = new Intent (MainActivity.this, Quadrinhos.class);
        startActivity(intentQuadrinhos);
    }
    public void Criadores (View view){
        Intent intentCriadores = new Intent (MainActivity.this, Criadores.class);
        startActivity(intentCriadores);
    }
}