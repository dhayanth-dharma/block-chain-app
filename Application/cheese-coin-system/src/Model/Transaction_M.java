/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "_transaction")
public class Transaction_M {
     @DatabaseField(id = true)
    private int transaction_id;
    @DatabaseField
    private double amount;
    @DatabaseField
    private int receiver_account_id;
    @DatabaseField
    private Date date_time;
    
    @DatabaseField
    private int public_transaction_id;
    
    @DatabaseField
    private Date time_stamp;
    @DatabaseField
    private String status;
    
    
    public List<Sender_M> senderList;
    public Transaction_M() {
    
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    

    public Transaction_M(int transaction_id, double amount, int receiver_account_id, Date date_time, int public_transaction_id) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.receiver_account_id = receiver_account_id;
        this.date_time = date_time;
        this.public_transaction_id = public_transaction_id;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Date getTime_stamp() {
        return time_stamp;
    }

    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public void setPublic_transaction_id(int public_transaction_id) {
        this.public_transaction_id = public_transaction_id;
    }

    public void setReceiver_account_id(int receiver_account_id) {
        this.receiver_account_id = receiver_account_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate_time() {
        return date_time;
    }

    public int getPublic_transaction_id() {
        return public_transaction_id;
    }

    public int getReceiver_account_id() {
        return receiver_account_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setSenderList(List<Sender_M> senderList) {
        this.senderList = senderList;
    }

    public List<Sender_M> getSenderList() {
        return senderList;
    }
    
    
    
    
    
   
}
