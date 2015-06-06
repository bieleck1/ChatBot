/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

/**
 *
 * @author abc
 */
public class Word implements Comparable<Word> {

    private final String word;
    private int counter;

    public Word(String word) {
        this.word = word;
        this.counter = 1;
    }

    public String getWord() {
        return this.word;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter() {
        this.counter++;
    }

    @Override
    public int compareTo(Word o) {
        return o.counter - counter;
    }
}
