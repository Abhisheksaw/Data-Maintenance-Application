/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package College.util;

/**
 *
 * @author hp
 */
public class AdminProfile {

    public static String getAdminName() {
        return adminName;
    }

    public static void setAdminName(String adminName) {
        AdminProfile.adminName = adminName;
    }

    public static String getBranchName() {
        return branchName;
    }

    public static void setBranchName(String branchName) {
        AdminProfile.branchName = branchName;
    }

    public static String getBranchId() {
        return branchId;
    }

    public static void setBranchId(String branchId) {
        AdminProfile.branchId = branchId;
    }
    private static String adminName;
    private static String branchName;
    private static String branchId;
}
