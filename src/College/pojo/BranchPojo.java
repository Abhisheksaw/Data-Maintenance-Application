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
public class BranchPojo {

    public BranchPojo(String branchId, String branchName, String adminName, String password, String emailId, String securityKey) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.adminName = adminName;
        this.password = password;
        this.emailId = emailId;
        this.securityKey = securityKey;
    }
    
    public BranchPojo(){
        
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    private String branchId;
    private String branchName;
    private String adminName;
    private String password;
    private String emailId;
    private String securityKey;

}
