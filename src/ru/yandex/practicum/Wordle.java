package ru.yandex.practicum;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;
import java.util.List;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {
    public static void main(String[] args) {
        try (PrintWriter printWriter = new PrintWriter("../../log.txt", StandardCharsets.UTF_8);) {
            WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader("\\words_ru.txt", printWriter);
            List<String> loaderDictionary = wordleDictionaryLoader.getDictionary();

            WordleDictionary dictionary = new WordleDictionary(loaderDictionary, printWriter);
            int dictionaryRandomIndex = (int) (Math.random() * dictionary.getWords().size());
            String answer = dictionary.getWords().get(dictionaryRandomIndex);

            WordleGame wordsGame = new WordleGame(answer, 6, dictionary, printWriter);
            wordsGame.startGame();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
