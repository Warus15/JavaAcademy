package task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.stream.Collectors;

public class Solution {
    private final String key;
    private final String regex = "[^A-Za-z ]";
    private final String spRegex = "[^A-Za-z]";
    private final HashSet<Character> keyCharacters;

    private double keyCharsInSentenceOccurrences = 0;
    private String sentence;

    private ArrayList<String> wordsInSentence;

    private ArrayList<String> groupNames;
    private ArrayList<WordGroup> wordGroups;

    private ArrayList<Record> results;

    public Solution(String key) {
        this.groupNames = new ArrayList<>();
        this.wordGroups = new ArrayList<>();
        this.results = new ArrayList<>();

        this.key = key.toLowerCase(Locale.ROOT);
        this.keyCharacters = this.key.chars().mapToObj(e -> (char) e).collect(Collectors.toCollection(HashSet::new));
    }

    public void solve(String sentence){
        this.sentence = prepareSentence(sentence);
        prepareWordsList();
        countKeyCharsOccurances();
        prepareGroups();
        listGroups();
        calculateTotalFrequency();

        clear();
        System.out.println();
    }

    private String prepareSentence(String sentence){
        sentence = sentence.toLowerCase(Locale.ROOT);
        sentence = sentence.replaceAll(regex, "");


        return sentence;
    }

    private void prepareWordsList(){
        wordsInSentence = Arrays.stream(sentence.split(" ")).collect(Collectors.toCollection(ArrayList::new));

        System.out.println(wordsInSentence.toString());
    }

    private void countKeyCharsOccurances(){
        int occurrences = 0;
        for(int i=0; i<sentence.length(); ++i)
            if(keyCharacters.contains(sentence.charAt(i)))
                ++occurrences;

        keyCharsInSentenceOccurrences = occurrences;
    }

    private void prepareGroups(){
        for(String word : wordsInSentence){
            int wordLength = word.length();
            String groupName = "";
            HashSet<Character> keyCharsInWord = new HashSet<>();

            for(char c : keyCharacters){
                if(word.indexOf(c) >= 0)
                    keyCharsInWord.add(c);
            }

            for(char c : keyCharsInWord){
                groupName += c;
            }

            groupName += " " + wordLength;

            if(!groupNames.contains(groupName) && !keyCharsInWord.isEmpty()){
                groupNames.add(groupName);
                wordGroups.add(new WordGroup(keyCharsInWord, wordLength, word, keyCharsInSentenceOccurrences));
            } else if (!keyCharsInWord.isEmpty()){
                int index = groupNames.indexOf(groupName);
                wordGroups.get(index).addWord(word);
            }
        }
    }

    private void listGroups(){
        for(WordGroup g : wordGroups)
            results.add(g.getResult());

        results.sort(Record::compareTo);

        for(Record r : results)
            r.printResult();
    }

    private void calculateTotalFrequency(){
        sentence = sentence.replaceAll(spRegex, "");

        double length = sentence.length();
        double quotient = keyCharsInSentenceOccurrences/length;
        double totalFrequency = Math.round(quotient * 100.0) / 100.0;

        System.out.println("TOTAL: " + totalFrequency + " (" + keyCharsInSentenceOccurrences + "/" + length + ")");
    }

    private void clear(){
        wordsInSentence = new ArrayList<>();
        groupNames = new ArrayList<>();
        wordGroups = new ArrayList<>();
        results = new ArrayList<>();

        keyCharsInSentenceOccurrences = 0;
    }
}
