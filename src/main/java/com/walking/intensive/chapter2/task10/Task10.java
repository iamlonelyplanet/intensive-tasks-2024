package com.walking.intensive.chapter2.task10;

import java.util.Locale;

/**
 * Реализуйте метод isPalindrome(), который проверяет, является ли строка палиндромом.
 *
 * <p>Метод должен игнорировать пунктуацию, пробелы и регистр.
 *
 * <p>P.S. Мой любимый палиндром: Муза! Ранясь шилом опыта, ты помолишься на разум.
 *
 * <p><a href="https://github.com/KFalcon2022/intensive-tasks-2024/blob/master/README.md">Требования к оформлению</a>
 */
public class Task10 {
    public static void main(String[] args) {
        String inputString1 = "   Муза,   ранясь !!! )шилом (опыта), ты помолишься на разум!?!   ";
        String inputString2 = "Ежу хуже";
        String inputString = "гааг ";
        System.out.println(isPalindrome(inputString));
        //символы нижней кириллицы: [1072,1103] + 1105
    }

    static boolean isPalindrome(String inputString) {
        if (inputString == null || inputString.length() <= 1 || inputString.length() == 0) {
            return false;
        }

        inputString = inputString.toLowerCase();
        int halfLength = inputString.length() / 2;
        int i = 0;
        char leftLetter = inputString.charAt(i);
        while (i <= halfLength) {
            leftLetter = inputString.charAt(i);
            if (isCyrillicLetter(leftLetter)) {
                leftLetter = inputString.charAt(i);
                char rightLetter = getRightLetter(inputString, i);
                System.out.println("Сравниваем букву " + leftLetter + " с буквой " + rightLetter);
                if (leftLetter != rightLetter) {
                    System.out.println("Они не одинаковы!");
                    return false;
                }
            }
            i++;
        }
        return leftLetter == getRightLetter(inputString, i - 1);
    }

    static char getRightLetter(String inputString, int a) {
        int j = inputString.length() - a - 1;
        int halfLength = inputString.length() / 2;
        int odd = inputString.length() % 2 == 0 // Чётность приведённой строки. Чтобы проверить лишь до половины строки.
                ? 1
                : 0;
        char rightLetter = inputString.charAt(halfLength * 2 - a - odd);
        while (j > halfLength) {
            rightLetter = inputString.charAt(j);
            if (isCyrillicLetter(rightLetter)) {
                rightLetter = inputString.charAt(j);
                break;
            }
            j--;
        }
        return rightLetter;
    }

    static boolean isCyrillicLetter(char letter) {
        return ((int) letter >= 1072 && (int) letter <= 1105 && (int) letter != 1104);
    }

    static boolean isPalindromeByExceptions(String inputString) {
        if (inputString == null) {
            return false;
        }

        // Удаляем пробелы и знаки препинания, затем переводим всё в нижний регистр
        inputString = inputString.replace(" ", "");
        inputString = inputString.replace(".", "");
        inputString = inputString.replace("!", "");
        inputString = inputString.replace(",", "");
        inputString = inputString.replace("?", "");
        inputString = inputString.replace("'", "");
        inputString = inputString.replace("(", "");
        inputString = inputString.replace(")", "");
        inputString = inputString.replace("-", "");
        inputString = inputString.replace("_", "");

        inputString = inputString.toLowerCase();

        if (inputString.length() <= 1) {
            return false;
        }

        int halfLength = inputString.length() / 2;
        int odd = inputString.length() % 2 == 0 // Чётность приведённой строки. Чтобы проверить лишь до половины строки.
                ? 1
                : 0;

        for (int i = 0; i <= halfLength; i++) {
            char leftLetter = inputString.charAt(i);
            char rightLetter = inputString.charAt(halfLength * 2 - odd - i);

            if (leftLetter != rightLetter) {
                return false;
            }
        }

        return true;
    }
}
