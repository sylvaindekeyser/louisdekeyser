package com.example.jorda.jeux.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jorda.jeux.R;
import com.example.jorda.jeux.model.EtatJeu;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button mPlayButton;
    private Button quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            EtatJeu.chargerJeu(this.getApplicationContext(), "savedGame");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set des boutons.
        mPlayButton = findViewById(R.id.jouer);
        quitter = findViewById(R.id.quitter);

        mPlayButton.setOnClickListener((View v) -> {
                Intent menu = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(menu);
            }
        );

        quitter.setOnClickListener((View v ) -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }


}
