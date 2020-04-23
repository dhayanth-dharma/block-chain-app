/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "_proof_of_work")
public class Proof_Of_Work_M {
    
    @DatabaseField(id = true)
    private int proof_of_work_id;
    @DatabaseField
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
    private String status;

    public Proof_Of_Work_M() {
    }

    public Proof_Of_Work_M(int proof_of_work_id, int transaction_id, double amount, int receiver_account_id, Date date_time, int public_transaction_id, String status) {
        this.proof_of_work_id = proof_of_work_id;
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.receiver_account_id = receiver_account_id;
        this.date_time = date_time;
        this.public_transaction_id = public_transaction_id;
        this.status = status;
    }

    public int getProof_of_work_id() {
        return proof_of_work_id;
    }

    public void setProof_of_work_id(int proof_of_work_id) {
        this.proof_of_work_id = proof_of_work_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getReceiver_account_id() {
        return receiver_account_id;
    }

    public void setReceiver_account_id(int receiver_account_id) {
        this.receiver_account_id = receiver_account_id;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public int getPublic_transaction_id() {
        return public_transaction_id;
    }

    public void setPublic_transaction_id(int public_transaction_id) {
        this.public_transaction_id = public_transaction_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
