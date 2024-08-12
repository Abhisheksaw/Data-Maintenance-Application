/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.dao;

import College.dbutil.DBConnection;
import College.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hp
 */
public class UserDAO {
    public static String getNewId() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(user_id) from users");
        rs.next();
        String id=rs.getString(1);
        String userId="";
        if(id!=null){
            id=id.substring(4);
            userId="USR-"+(Integer.parseInt(id)+1);
        }
        else{
            userId="STF-101";
        }
        return userId;
    }
    
    public static String addUser(UserPojo user)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
        user.setUserId(getNewId());
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getBranchId());
        ps.setString(3, user.getEmailId());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getUserName());
        
        return (ps.executeUpdate()==1?user.getUserId():null);
    }
    
    public static List<String> getAllUserIdByBranchId(String branchId)throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select user_id from users where branch_id=?");
        ps.setString(1, branchId);
        List<String>userList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            userList.add(rs.getString(1));
        }
        return userList;
    }
    
    public static UserPojo getUserDetailsbyId(String userId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where user_id=?");
        ps.setString(1, userId);
        UserPojo user=new UserPojo();
        ResultSet rs=ps.executeQuery();
        rs.next();
        user.setUserName(rs.getString(5));
        user.setEmailId(rs.getString(3));
        return user;
    }
    
    public static String getRandomUserIdFromBranch(String braId)throws SQLException{
        List<String>userList=UserDAO.getAllUserIdByBranchId(braId);
        Random rand=new Random();
        int index=rand.nextInt(userList.size());
        return userList.get(index);
    }
    
    public static UserPojo validate(String emailId,String password)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select user_id,user_name from users where email_Id=? and password=?");
        ps.setString(1, emailId);
        ps.setString(2, password);
        ResultSet rs=ps.executeQuery();
        UserPojo user=null;
        if(rs.next()){
            user=new UserPojo();
            user.setUserId(rs.getString(1));
            user.setUserName(rs.getString(2));
            user.setEmailId(emailId);
        }
        return user;
    }

}
