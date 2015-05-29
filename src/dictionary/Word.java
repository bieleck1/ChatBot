/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.ArrayList;

/**
 *
 * @author abc
 */
public class Word {

    private ArrayList<Word> suffixesList;
    private String word;
    private int counterBot;
    private int counterUser;

    public Word(String word) {
        this.word = word;
        this.suffixesList = new ArrayList<>();
        this.counterBot = 0;
        this.counterUser = 0;
    }

    public String getWord() {
        return this.word;
    }
    
    public int getCounterBot(){
        return this.counterBot;
    }
    
    public int getCounterUser(){
        return this.counterUser;
    }
    
    public void setCounterBot(){
        this.counterBot++;
    }
    
    public void setCounterUser(){
        this.counterUser++;
    }
    
    public void addSuffix(Word w) {
        this.suffixesList.add(w);
    }
}
