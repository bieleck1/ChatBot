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


public class Fragmentation {
    ArrayList<Ngram> wordList = new ArrayList<>();
    String g = "cos";
    Ngram w = new Ngram(g);
    
    
   
    
    public void Fragmentation(){
        Ngram j = new Ngram("co");
       
       wordList.add(j);
       System.out.println(j.getNgram());
    }
}
