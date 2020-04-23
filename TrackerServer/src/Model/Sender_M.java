/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;
//import jdk.nashorn.internal.ir.annotations.Ignore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@DatabaseTable(tableName = "_sender")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Sender_M {
    @DatabaseField(id = true)
    private int sender_id;
    @DatabaseField
    private int public_transaction_id;
    @DatabaseField
    private int sender_account_id;
    @DatabaseField
    private double amount;
    @DatabaseField
    private Date date_time;
    @DatabaseField
    
    private int receiver_account_id;

    public Sender_M() {
    }

    
    public Sender_M(int sender_id, int public_transaction_id, int sender_account_id, double amount, Date date_time) {
        this.sender_id = sender_id;
        this.public_transaction_id = public_transaction_id;
        this.sender_account_id = sender_account_id;
        this.amount = amount;
        this.date_time = date_time;
    }
 
    public void setReceiver_account_id(int receiver_account_id) {
        this.receiver_account_id = receiver_account_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    

    public void setSender_account_id(int sender_account_id) {
        this.sender_account_id = sender_account_id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate_time() {
        return date_time;
    }

    public int getReceiver_account_id() {
        return receiver_account_id;
    }

    public int getSender_account_id() {
        return sender_account_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setPublic_transaction_id(int public_transaction_id) {
        this.public_transaction_id = public_transaction_id;
    }

    public int getPublic_transaction_id() {
        return public_transaction_id;
    }

   
    
    
}
