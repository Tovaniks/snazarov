package ru.job4j.wait;


import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.07
 */

@ThreadSafe
public class ParallelSearch {

    private final String root;
    private final String text;
    private final List<String> exts;
    private volatile boolean finish = false;
    private final Object lock = new Object();
    private final Queue<String> files = new LinkedBlockingQueue<>();
    private final List<String> paths = new CopyOnWriteArrayList<>();

    /**
     * Конструктор
     *
     * @param root - корневая директорая, где ведем поиск файлов
     * @param text - текст, который будем искать в файлах
     * @param exts - список с расширением файлов, которые будем рассматривать
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Инициализация работы поиска файлов
     *
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        Thread search = new Thread() {
            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(root), new MyVisitor());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                synchronized (lock) {
                    finish = true;
                    lock.notifyAll();
                }
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                read();
            }
        };
        search.start();
        read.start();
        read.join();
    }


    /**
     * Возвращаем результат нашего поиска
     *
     * @return результат
     */
    public Queue<String> result() throws InterruptedException {
        Queue<String> result = new LinkedList<>();
        synchronized (lock) {
            while (!finish || !files.isEmpty()) {
                lock.wait();
            }
            result.addAll(this.paths);
        }
        return result;
    }

    class MyVisitor extends SimpleFileVisitor<Path> {

        /**
         * Переопределяем вызов visitFile, чтобы он добавлял подходящий файл в список
         *
         * @param file  путь к файлу
         * @param attrs атрибуты файла
         * @return возвращаем FileVisitResult. В нашем случае только CONTINUE, чтобы поиск продолжался
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            for (String ext : exts) {
                if (file.getFileName().toString().endsWith(ext)) {
                    ParallelSearch.this.files.add(file.toAbsolutePath().toString());
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }


    /**
     * Читаем файлы из списка Files и проверяем наличие в файле совпадений с переменной text.
     * Если совпадения есть, перебрасываем в результирующий список.
     */
    private void read() {
        while (!finish || !files.isEmpty()) {
            if (!files.isEmpty()) {
                Path path = Paths.get(files.poll());
                List<String> lines = new ArrayList<>();
                try {
                    lines = Files.readAllLines(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (String line : lines) {
                    if (line.contains(text)) {
                        this.paths.add(path.toString());
                        break;
                    }
                }
            }

        }
    }
}

