/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import dictionary.Ngram;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author abc
 */
public class Generator {

    private Random rand;
    private String[] words;

    public Generator(String userText) {
        this.words = userText.split(" ");
        this.rand = new Random();
    }

    public String generate(ArrayList<Ngram> prefixes) {

        String first = firstNgram(prefixes);
        String answer = first;
        int r = Math.abs(rand.nextInt() % 5 + 7);

        while (r > 0) {
            for (int i = 0; i < prefixes.size(); i++) {
                if (first.equals(prefixes.get(i).getPrefix())) {
                    if (prefixes.get(i).getSuffixes().isEmpty()) {
                        return answer;
                    } else {
                        int r2 = Math.abs(rand.nextInt() % prefixes.get(i).getSuffixes().size());
                        String suffix = prefixes.get(i).getSuffixes().get(r2);
                        answer = answer.concat(" ").concat(suffix);
                        i = prefixes.size();
                        first = newPrefix(first, suffix);
                    }
                }
            }
            r--;
        }
        return answer;

    }

    private String firstNgram(ArrayList<Ngram> prefixes) {

        String forbidden = ".<,>?/:;'\"{[}]\\|`~!@#$%^&*()-_+=\t\n\r";

        for (int i = 0; i < words.length; i++) {
            StringTokenizer str = new StringTokenizer(words[i], forbidden);
            words[i] = str.nextToken();

            while (str.hasMoreTokens()) {
                words[i] = words[i].concat(str.nextToken());
            }
            words[i] = words[i].toLowerCase();
        }

        for (int i = 0; i < words.length; i++) {
            int r = Math.abs(rand.nextInt() % words.length);
            for (int w = 0; w < prefixes.size(); w++) {
                if ((prefixes.get(w).getPrefix().contains(words[r]))) {
                    return prefixes.get(w).getPrefix();
                }
            }
        }

        for (int i = 0; i < words.length; i++) {
            for (int w = 0; w < prefixes.size(); w++) {
                if ((prefixes.get(w).getPrefix().contains(words[i]))) {
                    return prefixes.get(w).getPrefix();
                }
            }
        }

        int r = Math.abs(rand.nextInt() % prefixes.size());

        return prefixes.get(r).getPrefix();
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
