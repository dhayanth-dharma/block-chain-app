/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//**THIS CLASS SHOULD HANDLE ALL RESPONSE QUEUE

import Model.ReponseQueue;
import Model.ResponseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ResponseHandler {

    private static ResponseHandler instance ;
    private List<ReponseQueue> responceQueueList;
    private ResponseHandler() {
    responceQueueList=new ArrayList<ReponseQueue>();

    }
    public static ResponseHandler getInstace()
    {
        if(instance==null)
        {
            instance =new ResponseHandler();
        }
        return instance;
    }
    
    public void sendResponse(ResponseModel responseModel, String ip, int port)
    {
         responceQueueList.add(new ReponseQueue(ip,port,responseModel));
    }
    
}
