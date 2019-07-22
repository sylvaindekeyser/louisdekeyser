package com.example.jorda.jeux.controller;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jorda.jeux.model.Categorie;
import com.example.jorda.jeux.model.EtatJeu;
import com.example.jorda.jeux.model.Question;
import com.example.jorda.jeux.model.QuestionBank;
import com.example.jorda.jeux.R;

import java.io.IOException;
import java.util.regex.Pattern;

public class QuestionActivity extends AppCompatActivity {


    private ImageView affiche;
    private Question questionActuelle;
    private Button validation;
    private EditText reponse;
    private int categorieActuelle,numQuestionActuelle;
    private QuestionBank questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        categorieActuelle = getIntent().getIntExtra("categorie", 0);
        numQuestionActuelle = getIntent().getIntExtra("numQuestion",0);

        if(categorieActuelle == R.id.cat1) {
            questions = QuestionBank.getinstance(null, Categorie.CATEGORIE1);
        } else if (categorieActuelle == R.id.cat2) {
            questions = QuestionBank.getinstance(null, Categorie.CATEGORIE2);
        } else if (categorieActuelle == R.id.cat3) {
            questions = QuestionBank.getinstance(null, Categorie.CATEGORIE3);
        } else if (categorieActuelle == R.id.cat4) {
            questions = QuestionBank.getinstance(null, Categorie.CATEGORIE4);
        } else if (categorieActuelle == R.id.cat5) {
            questions = QuestionBank.getinstance(null, Categorie.CATEGORIE5);
        }

        questionActuelle = questions.getQuestions().get(numQuestionActuelle-1);
        Drawable d = getResources().getDrawable(questionActuelle.getImage());

        affiche = findViewById(R.id.affiche);
        affiche.setImageDrawable(d);

        validation = findViewById(R.id.validation);
        reponse = findViewById(R.id.reponse);

        validation.setOnClickListener( (View v ) ->{
                //Verification avec le regex pour avoir plusieurs reponses possibles
                if(Pattern.matches(questionActuelle.getRegex(),reponse.getText().toString().toUpperCase())){
                    questions.ajoutPoint(questionActuelle);
                    try {
                        EtatJeu.sauvegarderJeu(getApplicationContext(), "savedGame");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
                else{
                    Toast.makeText(QuestionActivity.this, "Mauvaise réponse", Toast.LENGTH_SHORT).show();
                }
//                if(reponse.getText().toString().toUpperCase().equals(questionActuelle.getReponse().toUpperCase())){
//                    questions.ajoutPoint(questionActuelle);
//                    finish();
//                }

            }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("QuestionActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("QuestionActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("QuestionActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("QuestionActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("QuestionActivity::onDestroy()");
    }
}
