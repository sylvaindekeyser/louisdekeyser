package com.example.jorda.jeux.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class EtatJeu {

    public static void chargerJeu(Context context, String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        Map<Categorie, QuestionBank> instances = (Map<Categorie, QuestionBank>) is.readObject();
        QuestionBank.setInstances(instances);
        is.close();
        fis.close();
    }

    public static void sauvegarderJeu(Context context,String fileName) throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(QuestionBank.getInstances());
        os.close();
        fos.close();
    }

}
