package com.example.jorda.jeux.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class QuestionBank extends Observable implements Serializable {

    private ConcurrentMap<Question, Integer> score;
    private Categorie categorie;
    private List<Question> questions;

    private static Map<Categorie, QuestionBank> instances;
    private static List<Observer> observers;

    public static QuestionBank getinstance(List<Question> questions, Categorie categorie){
        if(instances == null){
            instances = new HashMap<>();
        }
        QuestionBank instance = instances.get(categorie);
        if(instance == null){
            instance = new QuestionBank(questions,categorie);
            instances.put(categorie,instance);
        }
        return instance;
    }

    private QuestionBank(List<Question> questions, Categorie categorie) {

        this.score = new ConcurrentHashMap<>();
        for(Question q : questions){
            this.score.put(q,0);
        }
        this.categorie = categorie;
        this.questions = questions;
    }

    public static void setObservers (Observer observer){
        if(observers == null){
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    public void ajoutPoint(Question question){
        // Contrôle des observateurs
        for(Observer o : observers){
            addObserver(o);
        }

        this.score.put(question,1);
        setChanged();
        notifyObservers();
    }

    public int getQuestionScore(Question question){ return score.get(question); }

    public Categorie getCategorie() {
        return categorie;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getScore() {
        int somme = 0;
        for(int i : score.values()){
            somme+=i;
        }
        return somme;
    }

    public static int getTotalScore(){

        if(instances == null) {
            return 0;
        }

        Iterator iterator = instances.entrySet().iterator();
        int somme = 0;
        while(iterator.hasNext()){
            Map.Entry couple = (Map.Entry)iterator.next();
            QuestionBank questionBank = (QuestionBank)couple.getValue();
            somme += questionBank.getScore();
        }
        return somme;

    }

    public static Map<Categorie, QuestionBank> getInstances() {
        return instances;
    }

    public static void setInstances(Map<Categorie, QuestionBank> instances) {
        QuestionBank.instances = instances;
    }

}
