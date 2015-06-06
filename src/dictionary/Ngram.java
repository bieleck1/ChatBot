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

    public Ngram(String prefix) {
        this.prefix = prefix;
        this.suffixesList = new ArrayList<>();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void addSuffix(String w) {
        this.suffixesList.add(w);
    }

    public ArrayList<String> getSuffixes() {
        return suffixesList;
    }
}
