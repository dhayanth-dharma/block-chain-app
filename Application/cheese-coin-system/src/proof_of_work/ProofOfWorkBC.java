/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof_of_work;

import CheeseCoinCore.CurrentMember;
import CheeseCoinCore.Reward;
import Model.*;
import CheeseCoinCore.Difficulty_DAO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author user
 */
public class ProofOfWorkBC {

    int dificulty=0;
    Cheese_M previous_cheese;
    int startPos;
    int nonce=0;
    String hash;
    Transaction_M trans;
    int member_id;
    
    public ProofOfWorkBC( Cheese_M previousCheese, Transaction_M trans ) 
    {
       
        Difficulty_DAO diff=new Difficulty_DAO();
        this.dificulty=diff.getDifficulty();
        this.previous_cheese=previousCheese;
        this.trans=trans;
        CurrentMember mem=new CurrentMember();
        this.member_id=mem.getMember_id();
    }

    public Cheese_M mine()
    {
        this.hash=computeHash();
        while(!hash.substring(startPos, dificulty).equals(
                String.format("%0"+dificulty+"d",0)))
        {
           nonce++;
           this.hash=computeHash();
        }
        Reward reward=new Reward();
        Cheese_M newCheese=new Cheese_M();
        newCheese.setDificulty(dificulty);
        newCheese.setHash(hash);
        newCheese.setMember_id(member_id);
        newCheese.setNonce(nonce);
        Date date = new Date();
        newCheese.setTime_stamp(date);
        newCheese.setPrevious_hash(previous_cheese.getHash());
        newCheese.setPublic_transaction_id(trans.getPublic_transaction_id());
        newCheese.setReward(reward.getReward());
        return newCheese;
    }
    
    
    
    public String getHash() {
        return hash;
    }
    
    
    public String computeHash() {
		
		String dataToHash = "" 
                        + trans.getAmount() 
                        + trans.getReceiver_account_id()
                        + trans.getPublic_transaction_id()
                        + previous_cheese.getHash()
                        +nonce;
		
                String encoded=bytesToSHA1(dataToHash.getBytes());
                return encoded;
    }
    
    public  String bytesToSHA1(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] digestedBytes = messageDigest.digest(bytes);

            // convert the digestedBytes to hexadecimal format to reduce the size of the ouput
            String result = "";
            for (int i = 0; i < digestedBytes.length; i++) {
                result += Integer.toString((digestedBytes[i] & 0xff) + 0x100, 16).substring(1);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }        
    }
}
