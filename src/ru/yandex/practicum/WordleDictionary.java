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
    final int MAX_LENGTH_WORD = 5;

    public WordleDictionary(List<String> dictionary, PrintWriter printWriter) {
        List<String> filterWords = filterOutLongerWords(dictionary);
        words = normalizeWords(filterWords);
        this.printWriter = printWriter;
    }

    public List<String> getWords() {
        return words;
    }

    public List<String> filterOutLongerWords(List<String> dictionary) {
        try {
            return dictionary.stream().filter(word -> word.length() == MAX_LENGTH_WORD).toList();
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return dictionary;
        }
    }

    public List<String> normalizeWords(List<String> words) {
        try {
            return words.stream().map(word -> {
                try {
                    return normalizeWord(word);
                } catch (InputException e) {
                    printWriter.println("Ошибка: " + e.getMessage());
                    return word;
                }
            }).toList();
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return words;
        }
    }

    public String normalizeWord(String word) throws InputException {
        try {
            boolean containsDigit = word.matches("^[а-яА-Я]+$");
            if(containsDigit) {
                throw new InputException("Слово содержит цифры или символы");
            }

            String lowercaseWord = word.toLowerCase();
            if (lowercaseWord.contains("ё")) {
                lowercaseWord = lowercaseWord.replace("ё", "е");
            }

            return lowercaseWord;
        } catch (InputException e) {
            printWriter.println("Ошибка валидации: " + e.getMessage());
            return "";
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return "";
        }
    }
}
