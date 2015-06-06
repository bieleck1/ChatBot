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
    private Statistic statistic;

    public Generator(String userText, Statistic statistic) {
        this.words = userText.split(" ");
        this.rand = new Random();
        this.statistic = statistic;
    }

    public Statistic getStatistic() {
        return this.statistic;
    }

    public String generate(ArrayList<Ngram> prefixes) {

        String first = firstNgram(prefixes);
        String answer = first;
        int r = Math.abs(rand.nextInt() % 5 + 7);

        while (r > 0) {
            for (int i = 0; i < prefixes.size(); i++) {
                if (first.equals(prefixes.get(i).getPrefix())) {
                    if (prefixes.get(i).getSuffixes().isEmpty()) {
                        addBotStatistic(answer);
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

        addBotStatistic(answer);
        return answer;
    }

    private String firstNgram(ArrayList<Ngram> prefixes) {

        String forbidden = ".<,>?/:;'\"{[}]\\|`~!@#$%^&*()-_+=\t\n\r";

        for (int i = 0; i < words.length; i++) {
            StringTokenizer str = new StringTokenizer(words[i], forbidden);
            int state = 0;
            words[i] = new String();

            while (str.hasMoreTokens()) {
                words[i] = words[i].concat(str.nextToken());
            }
            words[i] = words[i].toLowerCase();

            //STATYSTYKA:
            for (int w = 0; w < statistic.getWordsByUser().size(); w++) {
                if (words[i].equals(statistic.getWordsByUser().get(w).getWord())) {
                    state = 1;
                    statistic.getWordsByUser().get(w).setCounter();
                    w = statistic.getWordsByUser().size();
                }
            }

            if (state == 0) {
                Word word = new Word(words[i]);
                statistic.getWordsByUser().add(word);
            }
            statistic.setAllUsages();
        }

        if (prefixes.isEmpty()) {
            return "nie wiem co powiedzieć";
        } else {
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

    private void addBotStatistic(String answer) {
        String[] part = answer.split(" ");

        for (int i = 0; i < part.length; i++) {
            int state = 0;

            for (int w = 0; w < statistic.getWordsByBot().size(); w++) {
                if (part[i].equals(statistic.getWordsByBot().get(w).getWord())) {
                    state = 1;
                    statistic.getWordsByBot().get(w).setCounter();
                    w = statistic.getWordsByBot().size();
                }
            }

            if (state == 0) {
                Word word = new Word(part[i]);
                statistic.getWordsByBot().add(word);
            }
            statistic.setAllUsages();
        }
    }
}
