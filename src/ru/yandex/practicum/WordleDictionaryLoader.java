package ru.yandex.practicum;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    private final String pathFile;
    private final List<String> dictionary = new ArrayList<>();
    private final PrintWriter printWriter;

    public WordleDictionaryLoader(String pathFile, PrintWriter printWriter) {
        this.printWriter = printWriter;
        this.pathFile = pathFile;
        getWordsFromDictionary();
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void getWordsFromDictionary() {
        String projectDir = System.getProperty("user.dir");
        System.out.println(projectDir);
        try (FileReader fileReader = new FileReader(projectDir + pathFile, StandardCharsets.UTF_8)) {
            BufferedReader br = new BufferedReader(fileReader);
            while (br.ready()) {
                String line = br.readLine();
                dictionary.add(line);
            }
        } catch (IOException e) {
            printWriter.println(e.getMessage());
        }
    }
}
