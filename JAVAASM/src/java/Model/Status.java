/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Status{

    private int status;
    private String statusTitle;

    public Status() {
    
    }
     
    public Status(int status, String statusTitle) {
        this.status = status;
        this.statusTitle = statusTitle;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }
  
}
