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

        String[] text = origin.split("");
        String[] subtext = sub.split("");

        for (int i = 0; i < text.length; i++) {
            if (text.length - i - subtext.length >= 0 && text[i].equals(subtext[0])) {
                result = true;
                for (int j = 1; j < subtext.length; j++) {
                    if (!text[i + j].equals(subtext[j])) {
                        result = false;
                        System.out.print(text[i + j]);
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
