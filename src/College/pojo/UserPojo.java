/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.pojo;

/**
 *
 * @author hp
 */
public class UserPojo {
    
    private String UserId;
    private String BranchId;
    private String emailId;
    private String password;
    private String UserName;

    @Override
    public String toString() {
        return "UserPojo{" + "UserId=" + UserId + ", BranchId=" + BranchId + ", emailId=" + emailId + ", password=" + password + ", UserName=" + UserName + '}';
    }

    public UserPojo(String UserId, String BranchId, String emailId, String password, String UserName) {
        this.UserId = UserId;
        this.BranchId = BranchId;
        this.emailId = emailId;
        this.password = password;
        this.UserName = UserName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String BranchId) {
        this.BranchId = BranchId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public UserPojo(){
        
    } 

}
