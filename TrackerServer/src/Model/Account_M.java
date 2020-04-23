/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "_account")
public class Account_M {
    @DatabaseField(id = true)
    private int account_id;
    @DatabaseField
    private String public_key;
    @DatabaseField
    private String private_key;
    @DatabaseField
    private int user_id;
    @DatabaseField
    private int account_number;
    @DatabaseField
    private double balance;
    
    public Account_M() {
    }

    public Account_M(int account_id, String public_key, String private_key, int user_id, int account_number, double balance) {
        this.account_id = account_id;
        this.public_key = public_key;
        this.private_key = private_key;
        this.user_id = user_id;
        this.account_number = account_number;
        this.balance = balance;
    }
     
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    

    
     
    
            }
