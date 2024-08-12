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
public class ScoreDAO {

    Connection conn = DBConnection.getConnection();
    PreparedStatement ps;

    //get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select max(student_id) from scores");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }

    public boolean getDetails(int sid, int semesterNO) {
        try {
            ps = conn.prepareStatement("select * from courses where id=? and semester=?");
            ps.setInt(1, sid);
            ps.setInt(2, semesterNO);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AddFrame.txtId3.setText(String.valueOf(rs.getInt(2)));
                AddFrame.txtSem.setText(String.valueOf(rs.getInt(3)));
                AddFrame.txtCourse1.setText(rs.getString(4));
                AddFrame.txtCourse2.setText(rs.getString(5));
                AddFrame.txtCourse3.setText(rs.getString(6));
                AddFrame.txtCourse4.setText(rs.getString(7));
                AddFrame.txtCourse5.setText(rs.getString(8));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Student id or semester number doesn't exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Check score id is already exists
    public boolean isIdExist(int id) {
        try {
            ps = conn.prepareStatement("select * from scores where student_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Check whether the student id or semester number exist or not
    public boolean isSidSemesterNoExist(int sid, int semesterNo) {
        try {
            ps = conn.prepareStatement("select * from scores where id=? and semester=?");
            ps.setInt(1, sid);
            ps.setInt(2, semesterNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //insert score into scores table
    public void insert(int id, int sid, int semester, String course1, String course2, String course3, String course4, String course5, double score1, double score2, double score3, double score4, double score5, double average) {
        try {
            String sql = "Insert into scores values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semester);
            ps.setString(4, course1);
            ps.setDouble(5, score1);
            ps.setString(6, course2);
            ps.setDouble(7, score2);
            ps.setString(8, course3);
            ps.setDouble(9, score3);
            ps.setString(10, course4);
            ps.setDouble(11, score4);
            ps.setString(12, course5);
            ps.setDouble(13, score5);
            ps.setDouble(14, average);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Score added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //get all the score from database scores table
    public void getScoreValue(JTable table, String searchValue) {
        String sql = "select * from scores";
        try {
            ps = conn.prepareStatement(sql);
//            ps.setString(1, "%"+searchValue+"%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[14];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                row[6] = rs.getDouble(7);
                row[7] = rs.getString(8);
                row[8] = rs.getDouble(9);
                row[9] = rs.getString(10);
                row[10] = rs.getDouble(11);
                row[11] = rs.getString(12);
                row[12] = rs.getDouble(13);
                row[13] = rs.getDouble(14);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //update score
    public void update(int id, double score1, double score2, double score3, double score4, double score5, double average) {
        String sql = "update scores set score1=?,score2=?,score3=?,score4=?,score5=?,average=? where student_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, score1);
            ps.setDouble(2, score2);
            ps.setDouble(3, score3);
            ps.setDouble(4, score4);
            ps.setDouble(5, score5);
            ps.setDouble(6, average);
            ps.setInt(7, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Score updated successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
