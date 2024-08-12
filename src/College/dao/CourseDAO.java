/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.dao;

import College.dbutil.DBConnection;
import College.gui.AddFrame;
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
public class CourseDAO {

    Connection conn = DBConnection.getConnection();
    PreparedStatement ps;

    //get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(student_id) from courses");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public boolean getId(int id) {
        try {
            ps = conn.prepareStatement("select * from students where student_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AddFrame.txtId2.setText(String.valueOf(rs.getInt(1)));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Student id doesn't exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int countSemester(int id) {
        int total = 0;
        try {
            ps = conn.prepareStatement("select count(*) from courses where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            if (total == 8) {
                JOptionPane.showMessageDialog(null, "This student has completed all the courses");
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    //check whether the student has already taken this semester or not
    public boolean isSemesterExist(int sid, int semesterNo) {
        try {
            ps = conn.prepareStatement("select * from courses where id= ? and semester=?");
            ps.setInt(1, sid);
            ps.setInt(2, semesterNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //check whether the student has already taken this course or not
    public boolean isCourseExist(int sid, String courseNo, String course) {
        try {
            ps = conn.prepareStatement("select * from courses where id= ? and "+courseNo+" = ?");
            ps.setInt(1, sid);
            ps.setString(2, course);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //insert data into course table
    public void insert(int id, int sid, int semester, String course1, String course2, String course3, String course4, String course5){
        try {
            String sql = "Insert into courses values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semester);
            ps.setString(4, course1);
            ps.setString(5, course2);
            ps.setString(6, course3);
            ps.setString(7, course4);
            ps.setString(8, course5);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Course added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //get all the course values from database course table
    public void getCourseValue(JTable table, String searchValue) {
        String sql = "select * from courses";
        try {
            ps = conn.prepareStatement(sql);
//            ps.setString(1, "%"+searchValue+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[8];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
