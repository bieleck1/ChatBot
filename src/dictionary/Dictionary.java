/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author abc
 */
public class Dictionary {

    private ArrayList<Ngram> prefixList;
    private ArrayList<String> files;
    private int grade;

    public Dictionary() {
        this.prefixList = new ArrayList<>();
        this.files = new ArrayList<>();
        this.files.add("Dictionary1.txt");
        this.files.add("Dictionary2.txt");
        this.files.add("Dictionary3.txt");
        this.files.add("Dictionary4.txt");
        this.grade = 0;
    }

    public ArrayList<Ngram> getPrefixList() {
        return prefixList;
    }

    public void setGrade(int n) {
        if (this.grade != n) {
            saveDictionary();
            prefixList.clear();
            this.grade = n;
            loadDictionary();
        }
    }

    public void saveDictionary() {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(files.get(grade)), "UTF8"));

            for (int i = 0; i < prefixList.size(); i++) {
                out.write(prefixList.get(i).getPrefix() + ";");

                for (int j = 0; j < prefixList.get(i).getSuffixes().size(); j++) {
                    out.write(prefixList.get(i).getSuffixes().get(j) + ";");
                }
                out.write(System.getProperty("line.separator"));
            }
            out.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Problem: " + e);
        } catch (IOException e) {
            System.out.println("Problem: " + e);
        }
    }

    public void loadDictionary() {
        try {
            FileReader in = new FileReader(files.get(grade));
            BufferedReader buffReader = new BufferedReader(in);
            String line = null;

            while ((line = buffReader.readLine()) != null) {
                String[] words = line.split(";");
                Ngram ngram = new Ngram(words[0]);
                prefixList.add(ngram);

                for (int i = 1; i < words.length; i++) {
                    ngram.addSuffix(words[i]);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Problem: " + e);
        }
    }

    public void removeDictionary() {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(files.get(grade)), "UTF8"));
            out.write("");
            out.close();
            prefixList.clear();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Problem: " + e);
        } catch (IOException e) {
            System.out.println("Problem: " + e);
        }
    }
}
