/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheeseCoinCore;

import Model.Cheese_M;
import dataAccess.Cheese_DAO;
import java.util.Calendar;
import java.util.Date;


public class GenerateGenesis {

    public GenerateGenesis() {
    
       
        
    }
    public int generateGenesis()
    {
        Cheese_DAO cheedao=new Cheese_DAO();
        Cheese_M genCheese= cheedao.getGenesis();
        if(genCheese!=null)
        {
            return 2; 
        }
        Calendar cal = Calendar.getInstance();
        Date todayDate = new Date();
        cal.setTime(todayDate);

        // Set time fields to zero
        cal.set(Calendar.DAY_OF_YEAR, 2020);
        cal.set(Calendar.MONTH, 03);
        cal.set(Calendar.DATE, 10);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        todayDate = cal.getTime();
        
       
        Cheese_M cheese=new Cheese_M();
        cheese.setDificulty(3);
        //hello world hash
        cheese.setHash("b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9");
        cheese.setPrevious_hash("");
        cheese.setMember_id(0);
        cheese.setNonce(0);
        cheese.setPublic_transaction_id(0);
        cheese.setReward(0);
        cheese.setTime_stamp(todayDate);
        cheedao.create(cheese);
        return 1;
    }
    
}
