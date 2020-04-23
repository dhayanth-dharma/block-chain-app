/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheeseCoinCore;

/**
 * Get the current reward amount
 * @author user
 */
public class Reward {

    private double reward;
    public Reward() {
        this.reward=0.12;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getReward() {
        return reward;
    }
    
    
}
