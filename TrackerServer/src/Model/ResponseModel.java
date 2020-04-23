/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author user
 */
public class ResponseModel<T> {
    
    public int status_code;
    public List<T> args;
    public String argsString;
    public ResponseModel(int status_code, List<T> args) {
        this.status_code=status_code;
        this.args=args;
    }
    public ResponseModel(int status_code, String JsonString) {
        this.status_code=status_code;
        this.argsString=JsonString;
    }
    public ResponseModel() {
        
    }
}
