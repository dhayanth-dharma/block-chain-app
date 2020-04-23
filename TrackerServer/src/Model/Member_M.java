/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "_member")
public class Member_M {
     @DatabaseField(id = true)
    private int member_id;
    @DatabaseField
    private String member_name;
    @DatabaseField
    private int member_user_id;
    @DatabaseField
    private String member_ip;
    @DatabaseField
    private int member_port;
    @DatabaseField
    private String status;

    public Member_M() {
    }

    public Member_M(int member_id, String member_name, int member_user_id, String member_ip, int member_port) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_user_id = member_user_id;
        this.member_ip = member_ip;
        this.member_port = member_port;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_user_id() {
        return member_user_id;
    }

    public void setMember_user_id(int member_user_id) {
        this.member_user_id = member_user_id;
    }

    public String getMember_ip() {
        return member_ip;
    }

    public void setMember_ip(String member_ip) {
        this.member_ip = member_ip;
    }

    public int getMember_port() {
        return member_port;
    }

    public void setMember_port(int member_port) {
        this.member_port = member_port;
    }
    
    
   
}
