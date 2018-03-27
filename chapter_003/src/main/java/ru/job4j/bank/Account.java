package ru.job4j.bank;

/**
 * Account.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.27
 */
public class Account {

    private double value;
    private String requisites;

    /**
     * Конструктор аккаунта
     *
     * @param value      сумма
     * @param requisites реквизиты
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Задаем сумму
     *
     * @param value сумма
     */
    public void setValue(double value) {
        this.value = value;
    }


    /**
     * Возвращаем реквизиты
     *
     * @return реквизиты
     */
    public String getRequisites() {
        return this.requisites;
    }

    /**
     * Возвращаем деньги на счете
     *
     * @return деньги
     */
    public double getValues() {
        return this.value;
    }

    /**
     * Перевод денег между счетами
     *
     * @param dest   куда переводим деньги
     * @param amount сумма
     * @return успех перевода true/false
     */
    public boolean transfer(Account dest, double amount) {
        boolean result = this.value >= amount && amount > 0;
        if (result) {
            this.value -= amount;
            dest.setValue(dest.getValues() + amount);
        }
        return result;
    }
}
