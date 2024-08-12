/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.dao;

import College.dbutil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class StudentDAO {

    Connection conn = DBConnection.getConnection();
    PreparedStatement ps;
    
    //get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(student_id) from students");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }
    
    //insert data into student table
    public void insert(String id, String name, String dob, String gender, String email, String phone, String father, String mother, String address1, String address2, String imagePath) throws SQLException, ParseException {
        try {
            String sql = "Insert into students values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            String date_of = dob;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date util_date = sdf.parse(date_of);
            java.sql.Date sql_date = new java.sql.Date(util_date.getTime());
            ps.setDate(3, sql_date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, address1);
            ps.setString(10, address2);
            ps.setString(11, imagePath);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Student added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Check student email address is already exists
    public boolean isEmailExist(String email) {
        try {
            ps = conn.prepareStatement("select * from students where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Check student phone address is already exists
    public boolean isPhoneExist(String phone) {
        try {
            ps = conn.prepareStatement("select * from students where phone_no=?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Check student id is already exists
    public boolean isIdExist(String id) {
        try {
            ps = conn.prepareStatement("select * from students where student_id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //get all the student values from database student table
    public void getStudentValue(JTable table, String searchValue) {
        String sql = "select * from students";
        try {
            ps = conn.prepareStatement(sql);
//            ps.setString(1, "%"+searchValue+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[11];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //update student value
    public void update(String id, String name, String dob, String gender, String email, String phone, String father, String mother, String address1, String address2, String imagePath) throws ParseException {
        String sql = "update students set name=?,dob=?,gender=?,email=?,phone_no=?,father_name=?,mother_name=?,local_address=?,permanent_address=?,image_path=? where student_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            String date_of = dob;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date util_date = sdf.parse(date_of);
            java.sql.Date sql_date = new java.sql.Date(util_date.getTime());
            ps.setDate(2, sql_date);
            ps.setString(3, gender);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, father);
            ps.setString(7, mother);
            ps.setString(8, address1);
            ps.setString(9, address2);
            ps.setString(10, imagePath);
            ps.setString(11, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Student data updated successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //student data delete
    public void delete(String id) {
        int yesorNo = JOptionPane.showConfirmDialog(null, "Course and score records will also be deleted", "Student Delete", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesorNo == JOptionPane.OK_OPTION) {
            try {
                ps = conn.prepareStatement("delete from students where student_id=?");
                ps.setString(1, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Student deleted successfully");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
