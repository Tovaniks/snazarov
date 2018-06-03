package ru.job4j.monitor;


/**
 * Class User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.05.17
 */
public class User {
    private final int id;
    private final String name;
    private int amount;

    /**
     * Конструктор
     *
     * @param id     ID
     * @param name   Имя
     * @param amount Сумма на счете
     */
    public User(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    /**
     * Получить ID
     *
     * @return ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Получить пользователя
     *
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Получить сумму
     *
     * @return сумма
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Присвоить новую сумму на счет
     *
     * @param amount сумма
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("ID - %s; Name = %s; Money = %d", this.id, this.name, this.amount);
    }

    @Override
    public boolean equals(Object object) {
        boolean success = false;
        if (object instanceof User) {
            User user = (User) object;
            success = this.id == user.getID() && this.name.equals(user.getName()) && this.amount == user.getAmount();
        }
        return success;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        result = 31 * result + amount;
        return result;
    }

}
