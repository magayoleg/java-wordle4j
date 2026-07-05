package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

class WordleGameTest {
    PrintWriter printWriter;
    WordleDictionaryLoader wordleDictionaryLoader;
    List<String> loaderDictionary;
    WordleDictionary dictionary;

    @BeforeEach
    void beforeEach() throws IOException {
        printWriter = new PrintWriter("../../log.txt", StandardCharsets.UTF_8);
        wordleDictionaryLoader = new WordleDictionaryLoader("\\test\\ru\\yandex\\practicum\\test_words_ru.txt", printWriter);
        loaderDictionary = wordleDictionaryLoader.getDictionary();
        dictionary = new WordleDictionary(loaderDictionary, printWriter);
    }

    @Test
    void testIsMatchCheckTrue() {
        WordleGame wordleGame = new WordleGame("абвер", 6, dictionary, printWriter);

        Assertions.assertTrue(wordleGame.isMatchCheck("абвер", "абвер"));
    }
}
