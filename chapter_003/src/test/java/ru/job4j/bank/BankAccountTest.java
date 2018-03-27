package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankAccountTest {

    @Test
    public void whenAddDuplicateUserThenNotAdded() {
        User first = new User("Sergey", "12234");
        BankAccounts accounts = new BankAccounts();
        accounts.addUser(first);
        accounts.addUser(first);
        String expected = "{User=Sergey, Passport=12234:[]}";
        assertThat(accounts.toString(), is(expected));
    }

    @Test
    public void whenAddDuplicateAccountToUserThenNotAdded() {
        User first = new User("Sergey", "12234");
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        BankAccounts accounts = new BankAccounts();
        accounts.addUser(first);
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(first.getPassport(), secondAccount);
        String expected = "{User=Sergey, Passport=12234:[{value=1000.0, requisites=r555},{value=2000.0, requisites=r755}]}";
        assertThat(accounts.toString(), is(expected));
    }

    @Test
    public void whenDeleteUserThenDeleted() {
        BankAccounts accounts = new BankAccounts();
        User first = new User("Sergey", "12234");
        User second = new User("Andrey", "55184");
        User third = new User("Oleg", "12235");
        accounts.addUser(first);
        accounts.addUser(second);
        accounts.addUser(third);
        Account firstAccount = new Account(1000, "r555");
        accounts.addAccountToUser(second.getPassport(), firstAccount);
        accounts.deleteUser(second);
        String expected = "{User=Oleg, Passport=12235:[]},{User=Sergey, Passport=12234:[]}";
        assertThat(accounts.toString(), is(expected));
    }

    @Test
    public void whenDeleteAccountFromUserThenDeleted() {
        BankAccounts accounts = new BankAccounts();
        User first = new User("Andrey", "55184");
        User second = new User("Oleg", "12235");
        accounts.addUser(first);
        accounts.addUser(second);
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        Account thirdAccount = new Account(2000, "w755");
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(second.getPassport(), secondAccount);
        accounts.addAccountToUser(second.getPassport(), thirdAccount);
        accounts.deleteAccountFromUser(second.getPassport(), secondAccount);
        String expected = "{User=Andrey, Passport=55184:[{value=1000.0, requisites=r555}]},{User=Oleg, Passport=12235:[{value=2000.0, requisites=w755}]}";
        assertThat(accounts.toString(), is(expected));
    }

    @Test
    public void whenGetAccountFromUserThenGet() {
        BankAccounts accounts = new BankAccounts();
        User first = new User("Andrey", "55184");
        User second = new User("Oleg", "12235");
        accounts.addUser(first);
        accounts.addUser(second);
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        Account thirdAccount = new Account(2000, "w755");
        Account fourAccount = new Account(300, "w745");
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(second.getPassport(), secondAccount);
        accounts.addAccountToUser(second.getPassport(), thirdAccount);
        accounts.addAccountToUser(second.getPassport(), fourAccount);
        List<Account> result = accounts.getUserAccounts(second.getPassport());
        List<Account> expected = new ArrayList<>();
        expected.add(secondAccount);
        expected.add(thirdAccount);
        expected.add(fourAccount);
        assertThat(result, is(expected));
    }


    @Test
    public void whenUserAddThenAdd() {
        User first = new User("Sergey", "12234");
        User second = new User("Andrey", "55184");
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        BankAccounts accounts = new BankAccounts();
        accounts.addUser(first);
        accounts.addUser(second);
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(second.getPassport(), secondAccount);
        String expected = "{User=Andrey, Passport=55184:[{value=2000.0, requisites=r755}]},{User=Sergey, Passport=12234:[{value=1000.0, requisites=r555}]}";
        assertThat(accounts.toString(), is(expected));
    }

    @Test
    public void whenTransferBetweenTwoUserIsWorking() {
        User first = new User("Sergey", "12234");
        User second = new User("Andrey", "55184");
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        BankAccounts accounts = new BankAccounts();
        accounts.addUser(first);
        accounts.addUser(second);
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(second.getPassport(), secondAccount);
        boolean result = accounts.transferMoney(first.getPassport(), firstAccount.getRequisites(), second.getPassport(), secondAccount.getRequisites(), 100);
        assertThat(result, is(true));
    }


    @Test
    public void whenTransferBetweenTwoUserIsNotWorking() {
        User first = new User("Sergey", "12234");
        User second = new User("Andrey", "55184");
        Account firstAccount = new Account(1000, "r555");
        Account secondAccount = new Account(2000, "r755");
        BankAccounts accounts = new BankAccounts();
        accounts.addUser(first);
        accounts.addUser(second);
        accounts.addAccountToUser(first.getPassport(), firstAccount);
        accounts.addAccountToUser(first.getPassport(), secondAccount);
        accounts.addAccountToUser(second.getPassport(), secondAccount);
        boolean result = accounts.transferMoney(first.getPassport(), firstAccount.getRequisites(), second.getPassport(), secondAccount.getRequisites(), -500);
        assertThat(result, is(false));
    }

    @Test
    public void whenTransferWithBadParamsIsNotWorking() {
        BankAccounts accounts = new BankAccounts();
        boolean result = accounts.transferMoney(null, null, null, null, -500);
        assertThat(result, is(false));
    }
}
