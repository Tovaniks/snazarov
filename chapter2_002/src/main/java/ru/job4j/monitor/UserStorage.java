package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserStorage.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.05.17
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    /**
     * Добавить пользователя
     *
     * @param user пользователь
     * @return true/false
     */
    public synchronized boolean add(User user) {
        boolean success = false;
        if (!this.users.containsKey(user.getID())) {
            this.users.put(user.getID(), user);
            success = true;
        }
        return success;
    }

    /**
     * Обновить пользователя по ID
     *
     * @param user новый пользователь
     * @return true/false
     */
    public synchronized boolean update(User user) {
        boolean success = false;
        if (this.users.containsKey(user.getID())) {
            this.users.put(user.getID(), user);
            success = true;
        }
        return success;

    }

    /**
     * Удалить пользователя
     *
     * @param user пользователь
     * @return true/false
     */
    public synchronized boolean delete(User user) {
        boolean success = false;
        if (this.users.containsKey(user.getID())) {
            this.users.remove(user.getID());
            success = true;
        }
        return success;

    }

    /**
     * Перевести деньги
     *
     * @param fromId ID пользователя источника
     * @param toId   ID целевого пользователя
     * @param amount сумма
     * @return true/false
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean success = false;
        if (this.users.containsKey(fromId) && this.users.containsKey(toId) && this.users.get(fromId).getAmount() >= amount) {
            User from = this.users.get(fromId);
            User to = this.users.get(toId);
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            success = true;
        }
        return success;
    }

    @Override
    public synchronized String toString() {
        return this.users.toString();
    }
}