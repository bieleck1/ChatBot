/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author abc
 */
public class Statistic {

    private ArrayList<Word> wordsUsedByBot;
    private ArrayList<Word> wordsUsedByUser;
    private int allUsages;

    public Statistic() {
        wordsUsedByBot = new ArrayList<>();
        wordsUsedByUser = new ArrayList<>();
        allUsages = 0;
    }

    public ArrayList<Word> getWordsByBot() {
        return this.wordsUsedByBot;
    }
    
    public ArrayList<Word> getWordsByUser() {
        return this.wordsUsedByUser;
    }

    public void setAllUsages() {
        this.allUsages++;
    }
    
    public int getAllUsages() {
        return this.allUsages;
    }

    public void fillMax() {
        Collections.sort(wordsUsedByBot);
        Collections.sort(wordsUsedByUser);
    }
}
