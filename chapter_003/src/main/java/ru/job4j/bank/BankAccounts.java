package ru.job4j.bank;

import java.util.*;

/**
 * BankAccounts.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.27
 */
public class BankAccounts {

    private Map<User, List<Account>> accounts = new TreeMap<>();

    /**
     * Добавляем нового пользователя
     *
     * @param user пользователь
     */
    public void addUser(User user) {
        if (!accounts.containsKey(user)) {
            accounts.putIfAbsent(user, new ArrayList<>());
        }
    }

    /**
     * Удаляем пользователя
     *
     * @param user пользователь
     */
    public void deleteUser(User user) {
        this.accounts.remove(user);
    }

    /**
     * Добавляем пользователю аккаунт в банке
     *
     * @param passport паспорт пользователя
     * @param account  аккаунт
     */
    public void addAccountToUser(String passport, Account account) {
        if (accounts.get(getUserByPassport(passport)).indexOf(account) == -1) {
            accounts.get(getUserByPassport(passport)).add(account);
        }
    }

    /**
     * Удаляем у пользователя аккаунт
     *
     * @param passport паспорт пользователя
     * @param account  аккаунт
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.accounts.get(getUserByPassport(passport)).remove(account);
    }

    /**
     * Возвращаем все аккаунты пользователя
     *
     * @param passport паспорт пользователя
     * @return список аккаунтов пользователя
     */
    public List<Account> getUserAccounts(String passport) {
        return this.accounts.get(getUserByPassport(passport));
    }

    /**
     * Переводим деньги с аккаунта на аккаунт
     *
     * @param srcPassport   паспорт пользователя, откуда делаем перевод
     * @param srcRequisite  аккаунт, откуда делаем перевод
     * @param destPassport  паспорт пользователя, куда делаем перевод
     * @param destRequisite аккаунт, куда делаем перевод.
     * @param amount        сумма
     * @return успешность операции true/false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean success = false;
        if (srcPassport != null && srcRequisite != null && destPassport != null && destRequisite != null && !srcRequisite.equals(destRequisite)) {
            Account srcAccount = getAccountByRequisites(this.accounts.get(getUserByPassport(srcPassport)), srcRequisite);
            Account destAccount = getAccountByRequisites(this.accounts.get(getUserByPassport(destPassport)), destRequisite);
            success = srcAccount.transfer(destAccount, amount);
        }
        return success;
    }

    /**
     * Получаем пользователя по паспорту
     *
     * @param passport паспорт
     * @return пользователь
     */
    private User getUserByPassport(String passport) {
        User result = null;
        for (User user : this.accounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Получаем аккаунт пользователя среди всех его аккаунтов по реквезитам
     *
     * @param userAccounts список аккаунтов пользователя
     * @param requisites   реквезит
     * @return аккаунт
     */
    private Account getAccountByRequisites(List<Account> userAccounts, String requisites) {
        Account result = null;
        for (Account account : userAccounts) {
            if (account.getRequisites().equals(requisites)) {
                result = account;
                break;
            }
        }
        return result;
    }


    /**
     * Возвращаем список пользователей аккаунтов в виде {Имя пользователя, Пасспорт пользователя:[Список его аккаунтов]}
     * @return строку
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (User index : accounts.keySet()) {
            result.append(String.format("{User=%s, Passport=%s:[", index.getName(), index.getPassport()));
            for (Account account : accounts.get(index)) {
                result.append(String.format("{value=%s, requisites=%s}", account.getValues(), account.getRequisites()));
            }
            result.append("]}");
        }
        return result.toString().replace("}{", "},{");
    }
}
