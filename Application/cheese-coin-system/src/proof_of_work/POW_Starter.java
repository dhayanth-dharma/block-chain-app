/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof_of_work;

import MessageSender.MessageHandler;
import MessageSender.MessageQueueSingleton;
import MessageSender.MessageSender;
import Model.Cheese_M;
import Model.Transaction_M;
import dataAccess.Cheese_DAO;
import dataAccess.Transaction_DAO;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class POW_Starter implements Runnable{
SingletonProofOfWorkInstance powQueue;

    public POW_Starter() {
        powQueue=SingletonProofOfWorkInstance.getInstance();
    }


    @Override
    public void run() {
 
        powQueue=SingletonProofOfWorkInstance.getInstance();
        while(true)
        {  
            try {
                HashMap<String,Object> obj=  powQueue.takeQueue();
                if(obj!=null)
                {
                    Transaction_M tran= (Transaction_M) obj.get("transaction");
                    Cheese_DAO cheeseDao=new Cheese_DAO();
                    Cheese_M preCheese=cheeseDao.getLaste();
                    ProofOfWorkBC pow=new ProofOfWorkBC(preCheese, 
                            tran);
                    Cheese_M newCheese=pow.mine();
                    //send new cheese to all other member
                    if(addCheese(newCheese))
                    {
                        //updating status of the transaction
                        updateTransactionStatus(tran);
                        //broadcasting to all members about new transaction
                        broadcast(newCheese,tran);
                    }
                }
            } 
            catch (Exception ex) {
                System.out.println("Error from handler:"+ex.getMessage());
                Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public boolean addCheese(Cheese_M cheese)
    {
        Cheese_DAO cheeseDao=new Cheese_DAO();
        //checking whether already cheese has been mined by some other
        Cheese_M isCheeseExit=cheeseDao.getOneByPublicTranID(cheese.getPublic_transaction_id());
        if(isCheeseExit==null)
        {
            cheeseDao.create(cheese);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public void updateTransactionStatus(Transaction_M tran)
    {
        Transaction_DAO trandao=new Transaction_DAO();
       trandao.updateStatus(tran, "done");
    }
    public void broadcast(Cheese_M newCheese, Transaction_M tran)
    {
        BroadcastNewCheese broadcast=new BroadcastNewCheese();
        broadcast.putQueue(newCheese, tran);
    }
}
