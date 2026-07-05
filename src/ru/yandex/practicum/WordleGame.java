package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    public static final Scanner scanner = new Scanner(System.in);

    private final String correctAnswer;
    private final int steps;
    private final WordleDictionary dictionary;
    ArrayList<String> allAnswersUser = new ArrayList<>();
    private final PrintWriter printWriter;

    public WordleGame(String correctAnswer, int steps, WordleDictionary dictionary, PrintWriter printWriter) {
        this.correctAnswer = correctAnswer;
        this.steps = steps;
        this.dictionary = dictionary;
        this.printWriter = printWriter;
    }

    public void startGame() {
        int currentStep = 0;
        String currentAnswer = "";
        boolean isCorrectAnswer = false;

        try {
            while (!isCorrectAnswer & currentStep <= steps) {
                System.out.println("Введите ответ:");
                currentAnswer = scanner.nextLine();

                if (currentAnswer.isEmpty()) {
                    currentAnswer = hintWord();
                }

                String normalizeCurrentAnswer = dictionary.normalizeWord(currentAnswer);

                isCorrectAnswer = isMatchCheck(correctAnswer, normalizeCurrentAnswer);
                System.out.println("Осталось " + (steps - currentStep) + " попыток");

                boolean isSearchedWordInDictionary = dictionary.getWords().contains(normalizeCurrentAnswer);
                if (isSearchedWordInDictionary) {
                    allAnswersUser.add(currentAnswer);
                    currentStep++;
                }
            }

            if (isCorrectAnswer & currentStep <= steps) {
                System.out.println("Вы угадали слово: " + correctAnswer);
            } else {
                System.out.println("Количество попыток закончилось. Загаданное слово: " + correctAnswer);
            }
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
        } catch (InputException e) {
            printWriter.println(e);
        }
    }

    public boolean isMatchCheck(String correctAnswer, String currentAnswer) {
        try {
            if (correctAnswer.equals(currentAnswer)) {
                return true;
            }
            String clue = "";
            for (int i = 0; i < correctAnswer.length(); i++) {
                String ch1 = correctAnswer.substring(i, i + 1);
                String ch2 = currentAnswer.substring(i, i + 1);
                if (ch1.equals(ch2)) {
                    clue += "+";
                } else if (correctAnswer.contains(ch2)) {
                    clue += "^";
                } else {
                    clue += "-";
                }
            }

            System.out.println(currentAnswer);
            System.out.println(clue);

            return false;
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return false;
        }
    }

    public String hintWord() {
        String[] regex = {"[а-я]", "[а-я]", "[а-я]", "[а-я]", "[а-я]"};
        String containsCharInCorrectAnswer = "";

        try {
            if (allAnswersUser.isEmpty()) {
                int dictionaryRandomIndex = (int) (Math.random() * dictionary.getWords().size());
                return dictionary.getWords().get(dictionaryRandomIndex);
            }
            for (String answer : allAnswersUser) {
                for (int i = 0; i < answer.length(); i++) {
                    boolean isAnswerEqualsCorrectAnswer = answer.charAt(i) == correctAnswer.charAt(i);
                    if (isAnswerEqualsCorrectAnswer) {
                        regex[i] = "[" + answer.charAt(i) + "]";
                    }

                    String answerCharString = Character.toString(answer.charAt(i));

                    if (answer.charAt(i) != correctAnswer.charAt(i) && correctAnswer.contains(answerCharString)) {
                        containsCharInCorrectAnswer += answerCharString;
                    }
                }
            }

            final Pattern pattern = Pattern.compile(String.join("", regex), Pattern.MULTILINE);
            ArrayList<String> filterDictionary = new ArrayList<>();

            for (String word : dictionary.getWords()) {
                if (pattern.matcher(word).matches()) {
                    filterDictionary.add(word);
                }
            }

            for (char ch : containsCharInCorrectAnswer.toCharArray()) {
                ArrayList<String> newFilterDictionary = new ArrayList<>();
                for (String word : filterDictionary) {
                    if (word.contains(Character.toString(ch))) {
                        newFilterDictionary.add(word);
                    }
                }
                filterDictionary = newFilterDictionary;
            }


            if (filterDictionary.isEmpty()) {
                return "";
            } else {
                int filterDictionaryRandomIndex = (int) (Math.random() * filterDictionary.size());
                return filterDictionary.get(filterDictionaryRandomIndex);
            }
        } catch (RuntimeException e) {
            printWriter.println(e.getMessage());
            return "";
        }
    }
}
