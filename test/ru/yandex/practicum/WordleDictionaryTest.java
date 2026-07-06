package ru.yandex.practicum;

import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

class WordleDictionaryTest {
    PrintWriter printWriter;
    WordleDictionaryLoader wordleDictionaryLoader;
    List<String> loaderDictionary;
    WordleDictionary dictionary;

    @BeforeEach
    void beforeEach() throws IOException {
        String projectDir = System.getProperty("user.dir");
        printWriter = new PrintWriter(projectDir + "/log.txt", StandardCharsets.UTF_8);
        wordleDictionaryLoader = new WordleDictionaryLoader("\\test\\ru\\yandex\\practicum\\test_words_ru.txt", printWriter);
        loaderDictionary = wordleDictionaryLoader.getDictionary();
        dictionary = new WordleDictionary(loaderDictionary, printWriter);
    }

    @Test
    void testFilterOutLongerWords_1() {
        List<String> testDictionaryList = new ArrayList<>();

        testDictionaryList.add("нефоанализ");
        testDictionaryList.add("date");
        testDictionaryList.add("щёчка");
        testDictionaryList.add("абвер");

        List<String> filteredList = dictionary.filterOutLongerWords(testDictionaryList);

        Assertions.assertEquals(2, filteredList.size());
    }

    @Test
    void testFilterOutLongerWords_2() {
        List<String> testDictionaryList = new ArrayList<>();

        List<String> filteredList = dictionary.filterOutLongerWords(testDictionaryList);

        Assertions.assertEquals(0, filteredList.size());
    }

    @Test
    void testFilterOutLongerWords_3() {
        List<String> testDictionaryList = new ArrayList<>();
        testDictionaryList.add("нефоанализ");
        testDictionaryList.add("абазинец");
        testDictionaryList.add("щи");
        testDictionaryList.add("add");
        testDictionaryList.add("dell");

        List<String> filteredList = dictionary.filterOutLongerWords(testDictionaryList);

        Assertions.assertEquals(0, filteredList.size());
    }

    @Test
    void normalizeWords_1() {
        List<String> testDictionaryList = new ArrayList<>();
        testDictionaryList.add("нееёёё");
        testDictionaryList.add("ещё");
        testDictionaryList.add("ёёёёё");

        List<String> normalizeList = dictionary.normalizeWords(testDictionaryList);

        List<String> totalDictionaryList = new ArrayList<>();
        totalDictionaryList.add("неееее");
        totalDictionaryList.add("еще");
        totalDictionaryList.add("еееее");

        Assertions.assertEquals(normalizeList, totalDictionaryList);
    }

    @Test
    void normalizeWords_2() {
        List<String> testDictionaryList = new ArrayList<>();
        testDictionaryList.add("неё");
        testDictionaryList.add("ещЁ");
        testDictionaryList.add("шшшш");

        List<String> normalizeList = dictionary.normalizeWords(testDictionaryList);

        List<String> totalDictionaryList = new ArrayList<>();
        totalDictionaryList.add("нее");
        totalDictionaryList.add("еще");
        totalDictionaryList.add("шшшш");

        Assertions.assertEquals(normalizeList, totalDictionaryList);
    }
}