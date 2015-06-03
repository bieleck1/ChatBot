/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author abc
 */
public class Fragmentation {

    private int grade;

    public Fragmentation(int grade) {
        this.grade = grade + 1;
    }

    public void fragmentationUser(String userText, ArrayList<Ngram> prefixes) {

        fragmentation(userText, prefixes);

    }

    public void fragmentationFile(File file, ArrayList<Ngram> prefixes) {
        try {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = null;

            while ((line = buffReader.readLine()) != null) {
                fragmentation(line, prefixes);
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("Problem: " + e);
        }
    }

    private void fragmentation(String text, ArrayList<Ngram> prefixes) {
        if (text.equals("\n")) {

        } else {
            String[] words = text.split(" ");
            int state = 0;

            if (words.length < grade) {
            } else {
                String forbidden = ".<,>?/:;'\"{[}]\\|`~!@#$%^&*()-_+=\t\n\r";
                int i;

                for (i = 0; i < words.length; i++) {
                    StringTokenizer str = new StringTokenizer(words[i], forbidden);
                    words[i] = new String();

                    while (str.hasMoreTokens()) {

                        words[i] = words[i].concat(str.nextToken());
                    }
                    words[i] = words[i].toLowerCase();
                }

                String prefix = words[0];

                for (i = 1; i < grade; i++) {
                    prefix = prefix.concat(" ").concat(words[i]);
                }

                while (i <= words.length) {
                    //If Ngram(prefix) already exist.
                    for (int w = 0; w < prefixes.size(); w++) {
                        if (prefix.equals(prefixes.get(w).getPrefix())) {
                            state = 1;
                            //If suffix already exist.
                            if (prefixes.get(w).getSuffixes().isEmpty() && i < words.length) {
                                prefixes.get(w).addSuffix(words[i]);
                            } else {
                                for (int k = 0; k < prefixes.get(w).getSuffixes().size() && i < words.length; k++) {
                                    if (words[i].equals(prefixes.get(w).getSuffixes().get(k))) {
                                        k = prefixes.get(w).getSuffixes().size();
                                    } else {
                                        prefixes.get(w).addSuffix(words[i]);
                                        k = prefixes.get(w).getSuffixes().size();
                                    }
                                }
                            }
                            w = prefixes.size();
                        } else {
                            state = 0;
                        }
                    }

                    if (state == 0) {
                        Ngram ngram = new Ngram(prefix);
                        if (i < words.length) {
                            ngram.addSuffix(words[i]);
                        }
                        prefixes.add(ngram);
                    }

                    if (i < words.length) {
                        prefix = newPrefix(prefix, words[i]);
                    }
                    i++;
                }
            }
        }
    }

    private String newPrefix(String oldPrefix, String oldSuffix) {
        String[] words = oldPrefix.split(" ");
        String newPrefix = new String();

        for (int i = 1; i < words.length; i++) {
            newPrefix = newPrefix.concat(words[i]).concat(" ");
        }
        newPrefix = newPrefix.concat(oldSuffix);

        return newPrefix;
    }

}
