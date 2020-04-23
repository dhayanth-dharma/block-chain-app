
package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;
import java.util.Date;

@DatabaseTable(tableName = "_cheese")
public class Cheese_M {
   @DatabaseField(id = true)
    private int cheese_id;
    @DatabaseField
    private String previous_hash;
    @DatabaseField
    private String hash;
    @DatabaseField
    private int nonce;
    @DatabaseField
    private int dificulty;
    @DatabaseField
    private int member_id;
    @DatabaseField
    private int public_transaction_id;
    @DatabaseField
    private Date time_stamp;
    @DatabaseField
    private double reward;

    public Cheese_M() {
    }

    public Cheese_M(int cheese_id, String previous_hash, String hash, 
            int nonce, int dificulty, int member_id, int transaction_id, double reward) {
        this.cheese_id = cheese_id;
        this.previous_hash = previous_hash;
        this.hash = hash;
        this.nonce = nonce;
        this.dificulty = dificulty;
        this.member_id = member_id;
        this.public_transaction_id = transaction_id;
        this.reward = reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getReward() {
        return reward;
    }
    

    public Date getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }
    
    
    public Cheese_M(String previous_hash, String hash, 
            int nonce, int dificulty, int member_id, int transaction_id) {
        this.cheese_id = cheese_id;
        this.previous_hash = previous_hash;
        this.hash = hash;
        this.nonce = nonce;
        this.dificulty = dificulty;
        this.member_id = member_id;
        this.public_transaction_id = transaction_id;
    }

    public int getCheese_id() {
        return cheese_id;
    }

    public void setCheese_id(int cheese_id) {
        this.cheese_id = cheese_id;
    }

    public String getPrevious_hash() {
        return previous_hash;
    }

    public void setPrevious_hash(String previous_hash) {
        this.previous_hash = previous_hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getDificulty() {
        return dificulty;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setPublic_transaction_id(int public_transaction_id) {
        this.public_transaction_id = public_transaction_id;
    }

    public int getPublic_transaction_id() {
        return public_transaction_id;
    }

  
    
    
    
}
