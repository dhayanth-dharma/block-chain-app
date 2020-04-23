/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author user
 */
public class RequestModel<T> {
    public String command;
    public List<T>  args=new ArrayList<T>();
    public Object  argsObject=null;
    public String reqest_type;
    HashMap<T, String> args2 = new HashMap<T, String>();
    HashMap<String, Object> argsMapObject = new HashMap<String, Object>();
    public String argsString;
    public String public_key;
    public RequestModel(String reqest_type,String command, Object argsObject,  String argsString) {
        this.command = command;
        this.argsObject = argsObject;
        this.reqest_type = reqest_type;
        this.argsString = argsString;
    }
    public RequestModel(String reqest_type,String command, Object argsObject) {
        this.command = command;
        this.argsObject = argsObject;
        this.reqest_type = reqest_type;
    }
    public RequestModel(String reqest_type,String command, String encriptedMesg,String publicKey) {
        this.command = command;
        this.argsString = encriptedMesg;
        this.public_key= publicKey;
        this.reqest_type = reqest_type;
    }
    public RequestModel(String reqest_type,String command, HashMap<String,Object> argHasObj) {
        this.command = command;
        this.reqest_type = reqest_type;
        this.argsMapObject = argHasObj;
    }
    
    
    public RequestModel(String reqest_type, String command,  List<T> args, HashMap<T, String> args2) {
        
        this.command=command;
        this.args=args;
        this.reqest_type=reqest_type;
        this.args2=args2;
    }

    public RequestModel(String reqest_type,String command,  String argsString) {
        this.command = command;
        this.reqest_type = reqest_type;
        this.argsString = argsString;
    }
    
     public RequestModel(String reqest_type, String command,  List<T> args) {
        
        this.command=command;
        this.args=args;
        this.reqest_type=reqest_type;
        this.args2=args2;
    }
    
    public RequestModel(String reqest_type, String command ) {
        
        this.command=command;
        this.args=args;
        this.reqest_type=reqest_type;
        this.args2=args2;
    }
    public RequestModel()
    {
    }

    public List<T> getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }

    public String getReqest_type() {
        return reqest_type;
    }

    public void setArgs(List<T> args) {
        this.args = args;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setReqest_type(String reqest_type) {
        this.reqest_type = reqest_type;
    }

    public HashMap<T, String> getArgs2() {
        return args2;
    }

    public void setArgs2(HashMap<T, String> args2) {
        this.args2 = args2;
    }
    
}
