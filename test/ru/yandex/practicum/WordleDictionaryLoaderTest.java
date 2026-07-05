package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class WordleDictionaryLoaderTest {
    PrintWriter printWriter;
    WordleDictionaryLoader wordleDictionaryLoader;

    @BeforeEach
    void beforeEach() throws IOException {
        String projectDir = System.getProperty("user.dir");
        printWriter = new PrintWriter(projectDir + "/log.txt", StandardCharsets.UTF_8);
        wordleDictionaryLoader = new WordleDictionaryLoader("\\test\\ru\\yandex\\practicum\\test_words_ru.txt", printWriter);
    }

    @Test
    void testGetWordsFromDictionary() {
        List<String> testDictionaryList = new ArrayList<>();

        testDictionaryList.add("абаз");
        testDictionaryList.add("абазин");
        testDictionaryList.add("абазинец");
        testDictionaryList.add("абазинцы");
        testDictionaryList.add("абвер");
        testDictionaryList.add("нефелометр");
        testDictionaryList.add("нефелометрия");
        testDictionaryList.add("нефоанализ");
        testDictionaryList.add("ничья");
        testDictionaryList.add("щёточница");
        testDictionaryList.add("щёчка");
        testDictionaryList.add("щи");

        Assertions.assertEquals(wordleDictionaryLoader.getDictionary().size(), testDictionaryList.size());
    }
}
