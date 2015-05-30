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
public class Ngram {

    private ArrayList<String> suffixesList;
    private final String prefix;
    private int counterBot;
    private int counterUser;

    public Ngram(String prefix) {
        this.prefix = prefix;
        this.suffixesList = new ArrayList<>();
        this.counterBot = 0;
        this.counterUser = 0;
    }

    public String getNgram() {
        return this.prefix;
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
    
    public void addSuffix(String w) {
        this.suffixesList.add(w);
    }
    
    public ArrayList<String> getSuffixes() {
        return suffixesList;
    }
}
