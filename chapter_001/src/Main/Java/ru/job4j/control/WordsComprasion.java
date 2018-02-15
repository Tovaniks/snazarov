package ru.job4j.control;

/**
 * ArrayDuplicate.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.02.15
 */


public class WordsComprasion {

    /**
     * Проверяем, что слово содержит другое слово
     *
     * @param origin исходное слово
     * @param sub    слово, вхождение которого ищем в исходном слове
     * @return признак, что одно слово содержится в другом.
     */

    public boolean constains(String origin, String sub) {

        boolean result = false;

        String[] originWord = origin.split("");
        String[] subWord = sub.split("");

        for (int i = 0; i < originWord.length; i++) {
            if (originWord.length - i - subWord.length >= 0 && originWord[i].equals(subWord[0])) {
                result = true;
                for (int j = 1; j < subWord.length; j++) {
                    if (!originWord[i + j].equals(subWord[j])) {
                        result = false;
                        System.out.print(originWord[i + j]);
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

}
