package ru.job4j.coffee;

/**
 * CoffeeMachine.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.19
 */
public class CoffeeMachine {

    private final int[] money = {10, 5, 2, 1};


    /**
     * Валидированный метод выдачи сдачи
     *
     * @param value всего денег
     * @param price цена
     * @return массив с минимальным кол-вом денег
     */
    public int[] change(int value, int price) throws PriceNotValidException {
        int[] result = null;
        if (value < 0 || price < 0 || value - price < 0) {
            throw new PriceNotValidException("Цена/сумма не корректны");
        }
        if (value - price > 0) {
            result = calculate(value - price);
        }
        return result;
    }


    /**
     * Валидированный метод выдачи сдачи
     *
     * @param value всего денег
     * @param price цена
     * @return массив с минимальным кол-вом денег
     */
    public int[] validChange(int value, int price) {
        int[] result = null;
        try {
            result = change(value, price);
        } catch (PriceNotValidException e) {
            System.out.println("Цена/сумма не корректны");
        }
        return result;
    }


    /**
     * Рассчитываем сдачу сдачи
     *
     * @param value деньги
     * @return массив с минимальным кол-вом денег
     */
    private int[] calculate(int value) {
        int[] result = new int[0];
        int[] sub;
        int position = 0;
        int count;
        for (int coin : money) {
            if (value > 0 && value / coin > 0) {
                count = value / coin;
                sub = new int[position + count];
                System.arraycopy(result, 0, sub, 0, result.length);
                result = sub;
                for (int i = 0; i < count; i++) {
                    result[position] = coin;
                    position++;
                    value -= coin;
                }
            } else {
                break;
            }
        }
        return result;
    }
}
