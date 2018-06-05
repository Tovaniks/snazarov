package ru.job4j.test2;


import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class WorldIndexTest.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.05
 */
public class WorldIndex {

    private final Trie trie = new Trie();

    /**
     * Загрузка текстового файла для обработки
     *
     * @param filename текст
     */
    public void loadFile(String filename) {
        for (int index = 0; index < filename.split(" ").length; index++) {
            trie.addWord(filename.split(" ")[index], index);
        }

    }

    /**
     * Поиск вхождений слова в тексте
     *
     * @param searchWorld искомое слово
     * @return список с позициями в тексте. Если не найдено, возвращаем NULL
     */
    public Set<Integer> getIndexes4Word(String searchWorld) {
        return trie.getPositions(searchWorld);
    }

    /**
     * Префиксное дерево
     */
    private class Trie {
        private final HashMap<Character, Trie> node = new HashMap<>();
        private final TreeSet<Integer> input = new TreeSet<>();

        /**
         * Добавляем слово и позицию слова
         *
         * @param text     слова
         * @param position позиция в тексте
         */
        void addWord(String text, int position) {
            Trie currentNode = this;
            for (int index = 0; index < text.length(); index++) {
                Trie child = currentNode.getCurrentNode().get(text.charAt(index));
                child = child == null ? new Trie() : child;
                currentNode.getCurrentNode().putIfAbsent(text.charAt(index), child);
                if (index == text.length() - 1) {
                    currentNode.addInput(position);
                } else {
                    currentNode = currentNode.getCurrentNode().get(text.charAt(index));
                }
            }
        }

        /**
         * Возвращаем текущую ноду
         *
         * @return нода
         */
        private HashMap<Character, Trie> getCurrentNode() {
            return this.node;
        }

        /**
         * Возвращаем вхождения слова.
         *
         * @return вхождения
         */
        private Set<Integer> getInput() {
            return this.input;
        }

        /**
         * Добавляем вхождение слова
         *
         * @param position позиция в тексте
         */
        private void addInput(Integer position) {
            this.input.add(position);
        }

        /**
         * Получаем список позиций для заданного слова
         *
         * @param word слово
         * @return список позиций или Null, если не найдено
         */
        Set<Integer> getPositions(String word) {
            Set<Integer> result = null;
            Trie currentNode = this;
            for (int index = 0; index < word.length(); index++) {
                result = currentNode.getInput();
                currentNode = currentNode.getCurrentNode().get(word.charAt(index));
                if (currentNode == null) {
                    result = null;
                    break;
                }
            }
            return result;
        }


    }

}

