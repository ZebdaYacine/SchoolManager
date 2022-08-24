package schoolmanager.BackEnd.Service;

import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Account;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private Account account;

    public AccountServiceTest() {
        DataBaseConnection.Connect();
        account= new Account(4,"2022-08-23 19:12:00",5000);
    }

    @Test
    public void addAccount() {
        AccountService.addAccount(account);
    }

    @Test
    public void updateAccount() {
        account.setId(2);
        account.setAmount(6000);
        account.PresentAccount();
        AccountService.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
    }

    @Test
    public void getAccountOfStudent() {
    }
}