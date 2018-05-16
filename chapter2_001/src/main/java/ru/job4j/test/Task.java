package ru.job4j.test;



/**
 * Class Task.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.04.23
 */
public class Task implements Comparable {
    private String id;
    private String book;
    private Type type;
    private Action action;
    private Double price;
    private Integer volume;

    /**
     * Конструктор
     *
     * @param id     ID заявки
     * @param book   эмитет
     * @param type   тип операции
     * @param action действие
     * @param price  цена
     * @param volume объем
     */
    public Task(String id, String book, Type type, Action action, Double price, Integer volume) {
        this.id = id;
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
    }

    /**
     * Возвращаем ID таски
     *
     * @return ID таски
     */
    public String getID() {
        return this.id;
    }

    /**
     * Возвращаем эмитета
     *
     * @return эмитет
     */
    public String getBook() {
        return this.book;
    }

    /**
     * Возвращаем тип таски
     *
     * @return тип таски
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Возвращаем действие
     *
     * @return действие
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Возвращаем объем
     *
     * @return объем
     */
    public Integer getVolume() {
        return this.volume;
    }

    /**
     * Возвращаем цену
     *
     * @return цена
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * Меняем объем таски
     *
     * @param volume новый объем
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * Компаратор. Считаем, что в пределах эмитета ID таски должны быть уникальны. Если ID совпадают - таски равны, в противном случае смотрим на цены. Сортируем в порядке убывания цены.
     *
     * @param o объект, с которым сравниваем.
     * @return -1 если данные объект > объекта, с которым сравниваем, 0, если равны, 1, если меньше.
     */
    @Override
    public int compareTo(Object o) {
        int result;
        if (o instanceof Task) {
            Task task = (Task) o;
            int bookCompare = this.book.compareTo(task.getBook());
            if (bookCompare == 0) {
                if (this.id.compareTo(task.id) == 0) {
                    result = 0;
                } else if (price.compareTo(task.price) == 0) {
                    result = this.id.compareTo(task.id);
                } else {
                    result = this.price.compareTo(task.price) * -1;
                }
            } else {
                result = bookCompare;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
