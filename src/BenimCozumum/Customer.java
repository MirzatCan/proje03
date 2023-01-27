package BenimCozumum;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public String userName;
    public String passWord;
    public List<Account> hesaplar;


    public Customer(String username, String password, List<Account> hesaplar) {
        this.userName = username;
        this.passWord = password;
        this.hesaplar = hesaplar;
    }
}
