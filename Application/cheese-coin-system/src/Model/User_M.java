/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "_user")
public class User_M {
    @DatabaseField(id = true)
    private int use_id;
    @DatabaseField
    private String user_name;
    @DatabaseField
    private String password;
    @DatabaseField
    private String user_type;

    public User_M() {
    }

    public User_M(int use_id, String user_name, String password, String user_type) {
        this.use_id = use_id;
        this.user_name = user_name;
        this.password = password;
        this.user_type = user_type;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    
    
}
