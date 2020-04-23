/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author user
 */
public class Transaction_B {
    String user_id;
    Date t_date;
    float amount;
    String first_name;
    String last_name;
    String bank_iban;
    String bank_swift;
    Date dob;
    String agent_id;
    String type;
    String status;
    String public_key;
    public int c_id;

    public Transaction_B() {
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPublic_key() {
        return public_key;
    }
    
    
    public void setBank_iban(String bank_iban) {
        this.bank_iban = bank_iban;
    }

    public void setBank_swift(String bank_swift) {
        this.bank_swift = bank_swift;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setT_date(Date t_date) {
        this.t_date = t_date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public float getAmount() {
        return amount;
    }

    public String getBank_iban() {
        return bank_iban;
    }

    public String getBank_swift() {
        return bank_swift;
    }

    public Date getDob() {
        return dob;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getStatus() {
        return status;
    }

    public Date getT_date() {
        return t_date;
    }

    public String getType() {
        return type;
    }

    public String getUser_id() {
        return user_id;
    }
    
   
           
    
}
