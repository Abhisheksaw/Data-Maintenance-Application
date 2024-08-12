/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.dao;

import College.dbutil.DBConnection;
import College.pojo.BranchPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class BranchDAO {
    //ID GENERATE
    public static String getNewId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(branch_id) from branchs");
        rs.next();
        String id=rs.getString(1);
        String braId="";
        if(id!=null){
            id=id.substring(4);
            braId="BRA-"+(Integer.parseInt(id)+1);
        }
        else{
            braId="BRA-101";
        }
        return braId;
    } 
    
    public static boolean addUser(BranchPojo comp)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into branchs values(?,?,?,?,?,?,?)");
        
        ps.setString(1, getNewId());
        ps.setString(2, comp.getBranchName());
        ps.setString(3, comp.getAdminName());
        ps.setString(4, comp.getPassword());
        ps.setString(5, "ACTIVE");
        ps.setString(6, comp.getEmailId());
        ps.setString(7, comp.getSecurityKey());
        
        return ps.executeUpdate()==1;
    }
    
    public static BranchPojo validate(String braName,String password)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from branchs where branch_name=? and password=? and status='ACTIVE'");
        ps.setString(1, braName);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        BranchPojo bra=null;
        if(rs.next()){
            bra=new BranchPojo();
            bra.setBranchId(rs.getString(1));
            bra.setAdminName(rs.getString(3));
            bra.setBranchName(rs.getString(2));
        }
        return bra;
    }
    
    public static Map<String,String> getEmailCredentailsByBranchId(String branchId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select email_id,security_key from branchs where branch_id=? and status='ACTIVE'");
        ps.setString(1, branchId);
        Map<String,String> branchCredentials=new HashMap<>();
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            String emailId=rs.getString(1);
            String secKey=rs.getString(2);
            branchCredentials.put("emailId", emailId);
            branchCredentials.put("securityKey", secKey);
        }
        return branchCredentials;
    }
    
//    public static Map<String,String> getAllCompanyIdAndName()throws SQLException{
//        Connection conn=DBConnection.getConnection();
//        PreparedStatement ps=conn.prepareStatement("select company_id,company_name from companies where status='ACTIVE' and company_id in (select company_id from products)");
//        ResultSet rs=ps.executeQuery();
//        Map<String,String> compList=new HashMap<>();
//        while(rs.next()){
//            String c_id=rs.getString(1);
//            String c_name=rs.getString(2);
//            compList.put(c_name, c_id);
//        }
//        return compList;
//    }
}
