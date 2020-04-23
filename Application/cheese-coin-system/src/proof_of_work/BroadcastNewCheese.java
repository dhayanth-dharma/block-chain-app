/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proof_of_work;

import JsonParser.JsonParserCustom;
import MessageSender.MessageQueueSingleton;
import MessageSender.MessageQueueSingleton;
import Model.Cheese_M;
import Model.RequestModel;
import Model.Transaction_M;
import enums.ReqCommand;
import enums.RequestType;
import java.util.HashMap;
import requestMaker.BroadCastAllMember;

/**
 *
 * @author user
 */
public class BroadcastNewCheese {

    
    public BroadcastNewCheese() {
    
    }
    public void putQueue(Cheese_M cheese, Transaction_M tran)
    {
        try 
        {
            MessageQueueSingleton messQueue=MessageQueueSingleton.getInstance();
            JsonParserCustom<Cheese_M> jsonParser=new JsonParserCustom();
            String cheesString=jsonParser.objectToJsonString(cheese);
            RequestModel<String> req= new RequestModel
                    (RequestType.POST.toString(),ReqCommand.REQ_NEW_CHEESE.toString(),cheesString);
            BroadCastAllMember broadcast=new BroadCastAllMember(req);
        
          } catch (Exception e) {
              System.out.println("Error from Class :BroadcastNewCheese :"+e.getMessage());
              
        } 
    }
    
    
    
}
