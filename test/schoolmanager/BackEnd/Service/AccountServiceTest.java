package schoolmanager.BackEnd.Service;

import javafx.collections.ObservableList;
import org.junit.Test;
import schoolmanager.BackEnd.DataBaseConnection;
import schoolmanager.BackEnd.Model.Account;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private Account account;

    public AccountServiceTest() {
        DataBaseConnection.Connect();
/*
        account= new Account(4,"2022-08-23 19:12:00",5000);
*/
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
        account.setId(2);
        AccountService.deleteAccount(account);
    }

    @Test
    public void getAccountOfStudent() {
        ObservableList<Account> listAccountOfStudents=AccountService.getAccountOfStudent(account);
        for (Account account : listAccountOfStudents){
            account.PresentAccount();
        }
    }
}