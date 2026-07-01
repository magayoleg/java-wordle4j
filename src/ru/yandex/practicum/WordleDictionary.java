package ru.yandex.practicum;

import java.util.List;
import java.io.PrintWriter;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final List<String> words;
    private final PrintWriter printWriter;

    public WordleDictionary(List<String> dictionary, PrintWriter printWriter) {
        List<String> filterWords = filterWords(dictionary);
        words = normalizeWords(filterWords);
        this.printWriter = printWriter;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> filterWords(List<String> dictionary) {
        try {
            return dictionary.stream().filter(word -> word.length() == 5).toList();
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return dictionary;
        }
    }

    public List<String> normalizeWords(List<String> words) {
        try {
            return words.stream().map(this::normalizeWord).toList();
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return words;
        }
    }

    public String normalizeWord(String word) {
        try {
            String lowercaseWord = word.toLowerCase();
            if (lowercaseWord.contains("ё")) {
                lowercaseWord = lowercaseWord.replace("ё", "е");
            }

            return lowercaseWord;
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return "";
        }
    }
}
