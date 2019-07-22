package com.example.jorda.jeux.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.jorda.jeux.R;
import com.example.jorda.jeux.model.Categorie;
import com.example.jorda.jeux.model.Question;
import com.example.jorda.jeux.model.QuestionBank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MenuQuestionActivity extends AppCompatActivity implements Observer {

    private QuestionBank questions;
    private int categorieActuelle, hauteur;
    private ImageButton q1,q2,q3,q4,q5,q6,q7,q8;
    private ConstraintLayout layout;

    private List<ImageButton> boutons;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_question);

        QuestionBank.setObservers(this);
        generateQuestions();

        layout = findViewById(R.id.layout);

        if(categorieActuelle == R.id.cat1) {
            layout.setBackgroundColor(Color.rgb(183, 251, 241));
        } else if (categorieActuelle == R.id.cat2) {
            layout.setBackgroundColor(Color.rgb(253, 143, 142));
        } else if (categorieActuelle == R.id.cat3) {
            layout.setBackgroundColor(Color.rgb(251, 241, 160));
        } else if (categorieActuelle == R.id.cat4) {
            layout.setBackgroundColor(Color.rgb(253, 182, 130));
        } else if (categorieActuelle == R.id.cat5) {
            layout.setBackgroundColor(Color.rgb(224, 166, 253  ));
        }


        boutons = new ArrayList<>();

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);

        boutons.add(q1);
        boutons.add(q2);
        boutons.add(q3);
        boutons.add(q4);
        boutons.add(q5);
        boutons.add(q6);
        boutons.add(q7);
        boutons.add(q8);

        update(null,null);

        //On boucle sur tout les boutons de la liste et on les set avec la bonne question.

        for (int i=0; i<boutons.size();i++){
            //Il faut une variable final pour utiliser des methodes lambda
            final int j=i;
            boutons.get(i).setOnClickListener((View v) -> {
                    Intent menu = new Intent(MenuQuestionActivity.this, QuestionActivity.class);
                    menu.putExtra("categorie", categorieActuelle);
                    menu.putExtra("numQuestion", j+1);
                    startActivity(menu);
                }
            );
        }



        for(int i = 0; i < questions.getQuestions().size(); i++){
            ImageButton b = boutons.get(i);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),questions.getQuestions().get(i).getImage());
            //pour faire l'image en petit on fait un ratio entre hauteur et largeur qui est de 0.707 (la hauteur est 70% plus grande que la largeur)
            hauteur = 650;
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(hauteur*0.707),hauteur,true);

            b.setImageBitmap(bitmap);
            b.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }



    }

    //Suivant la catégorie les questions sont générés
    private void generateQuestions() {
        categorieActuelle = getIntent().getIntExtra("categorie",0);

        Question question1 = new Question(R.drawable.mep3, ".*(DEATH)( )*(NOTE).*");
        Question question2 = new Question(R.drawable.mep14, ".*(OLIVE)( )*(ET)( )*(TOM).*");
        Question question3 = new Question(R.drawable.mep5, ".*(INTERSTELLAR).*");
        Question question4 = new Question(R.drawable.mep, ".*(INSAISISSABLE).*");
        Question question5 = new Question(R.drawable.mep25, ".*(HARRY)( )*(POTTER).*");
        Question question6 = new Question(R.drawable.mep33, ".*(ALIEN).*");
        Question question7 = new Question(R.drawable.mep21, ".*(SEIGNEUR)( )*(DES)( )*(ANNEAUX).*");
        Question question8 = new Question(R.drawable.mep23, ".*(AMELIE)( )*(POULAIN).*");

        Question question9 = new Question(R.drawable.mep48, ".*(DRAGONS).*");
        Question question10 = new Question(R.drawable.mep2, ".*(SHERLOCK)( )*(HOLMES).*");
        Question question11 = new Question(R.drawable.mep15, ".*(STAR)( )*(WARS).*");
        Question question12 = new Question(R.drawable.mep16, ".*(CINQUIEME)( )*(ELEMENT).*");
        Question question13 = new Question(R.drawable.mep17, ".*(TITANIC).*");
        Question question14 = new Question(R.drawable.mep18, ".*(X)( )*(MEN).*");
        Question question15 = new Question(R.drawable.mep22, ".*(JURASSIC)( )*(PARK).*");
        Question question16 = new Question(R.drawable.mep11, ".*(PRINCESSE)( )*(MONONOKE).*");

        Question question17 = new Question(R.drawable.mep4, ".*(avata).*");
        Question question18 = new Question(R.drawable.mep13, ".*(LE)( )*(HOBBIT).*");
        Question question19 = new Question(R.drawable.mep19, ".*(INDIANA)( )*(JONES).*");
        Question question20 = new Question(R.drawable.mep24, ".*(LE)( )* (SILENCE)( )*(DES)( )*(AGNEAUX).*");
        Question question21 = new Question(R.drawable.mep35, ".*(six).*");
        Question question22 = new Question(R.drawable.mep37, ".*(NEUF).*");
        Question question23 = new Question(R.drawable.mep43, ".*(ROX)( )*(ET)( )*(ROUKY).*");
        Question question24 = new Question(R.drawable.mep38, ".*(FIGHT)( )*(CLUB).*");

        Question question25 = new Question(R.drawable.mep8, ".*(ZELDA).*");
        Question question26 = new Question(R.drawable.mep6, ".*(SPLIT).*");
        Question question27 = new Question(R.drawable.mep50, ".*(WALL)( )*(E).*");
        Question question28 = new Question(R.drawable.mep51, ".*(LE)( )* (MONDE)( )*(DE)( )*(RALPH).*");
        Question question29 = new Question(R.drawable.mep32, ".*(LA)( )*(LIGNE)( )*(VERTE).*");
        Question question30 = new Question(R.drawable.mep56, ".*(PAC)( )*(MAN).*");
        Question question31 = new Question(R.drawable.mep57, ".*(GAME)( )*(OF)( )*(THRONES).*");
        Question question32 = new Question(R.drawable.mep34, ".*(INCEPTION).*");

        Question question33 = new Question(R.drawable.mep58, ".*(DR)( )*(HOUSE).*");
        Question question34 = new Question(R.drawable.mep53, ".*(ROI)( )*(LION).*");
        Question question35 = new Question(R.drawable.mep54, ".*(SPIRIT).*");
        Question question36 = new Question(R.drawable.mep39, ".*(PIRATE)( )*(DES)( )*(CARAIBES).*");
        Question question37 = new Question(R.drawable.mep20, ".*(MATRIX).*");
        Question question38 = new Question(R.drawable.mep27, ".*(JE)( )*(SUIS)( )*(UNE)( )*(LEGENDE).*");
        Question question39 = new Question(R.drawable.mep30, ".*(SCREAM).*");
        Question question40 = new Question(R.drawable.mep10, ".*(POKEMON).*");

        List<Question> questCat1 = new ArrayList<>();
        List<Question> questCat2 = new ArrayList<>();
        List<Question> questCat3 = new ArrayList<>();
        List<Question> questCat4 = new ArrayList<>();
        List<Question> questCat5 = new ArrayList<>();

        //On met les bonnes questions dans les catégories
        Collections.addAll(questCat1, question1, question2, question3, question4, question5, question6, question7, question8);
        Collections.addAll(questCat2, question9, question10, question11, question12, question13, question14, question15, question16);
        Collections.addAll(questCat3, question17, question18, question19, question20, question21, question22, question23, question24);
        Collections.addAll(questCat4, question25, question26, question27, question28, question29, question30, question31, question32);
        Collections.addAll(questCat5, question33, question34, question35, question36, question37, question38, question39, question40);


        if(categorieActuelle == R.id.cat1) {
            questions = QuestionBank.getinstance(questCat1, Categorie.CATEGORIE1);
        } else if (categorieActuelle == R.id.cat2) {
            questions = QuestionBank.getinstance(questCat2, Categorie.CATEGORIE2);
        } else if (categorieActuelle == R.id.cat3) {
            questions = QuestionBank.getinstance(questCat3, Categorie.CATEGORIE3);
        } else if (categorieActuelle == R.id.cat4) {
            questions = QuestionBank.getinstance(questCat4, Categorie.CATEGORIE4);
        } else if (categorieActuelle == R.id.cat5) {
            questions = QuestionBank.getinstance(questCat5, Categorie.CATEGORIE5);
        }
    }
  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void update(Observable o, Object arg) {
        for(int i = 0; i < questions.getQuestions().size(); i++){
            if(questions.getQuestionScore(questions.getQuestions().get(i)) == 1){
                ImageButton b = boutons.get(i);
                b.setBackgroundColor(Color.rgb(76,175,80));
               // b.getBackground().setColorFilter(Color.rgb(76,175,80), PorterDuff.Mode.MULTIPLY);
            }
        }
    }
}
