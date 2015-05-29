/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;

/**
 *
 * @author abc
 */


public class Fragmentation {
    ArrayList<Word> wordList = new ArrayList<>();
    String g = "cos";
    Word w = new Word(g);
    
    
   
    
    public void Fragmentation(){
        Word j = new Word("co");
       
       wordList.add(j);
       System.out.println(j.getWord());
    }
}
