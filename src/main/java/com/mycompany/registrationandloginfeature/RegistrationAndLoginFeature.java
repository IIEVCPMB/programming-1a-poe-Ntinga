/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;

import javax.swing.JOptionPane;

/**
 *
 * @author samue
 */
public class RegistrationAndLoginFeature {
    
    private static String savedUser;
    private static String savedPass;
    
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && !password.equals(password.toLowerCase()) && password.matches(".*\\d.*") && !password.matches("[A-Za-z0-9]*");
    }
    public static boolean isValidSAphone(String number){
        return number.matches("\\+27\\d{9}") && number.length() == 12;
    }
    
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Please enter your first name");
        String sname = JOptionPane.showInputDialog("Please enter your surnname");
        String username = JOptionPane.showInputDialog("Create a username (Max 5 characters, use _): ");
        
        if (!username.contains("_") || username.length() > 5) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            return;
        }else {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        }
        
        String password = JOptionPane.showInputDialog("Create a password (Min 8 characters, a capital letter, a number and a special character): ");
        
        if (isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
         } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that your password contains at least eight characters, a capital letter, a number and a special character.");
        return;
        }
        
        String phone = JOptionPane.showInputDialog("Enter SA phone number (+27XXXXXXXXX): ");
                
        if (isValidSAphone(phone)) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code");
        return;
        }
        
        savedUser = username;
        savedPass = password;
        
        JOptionPane.showMessageDialog(null, "Registration Successful");
        
        String loginUser = JOptionPane.showInputDialog("Enter your username.");
        String loginPass = JOptionPane.showInputDialog("Enter your password.");
        
        if (loginUser.equals(savedUser) && loginPass.equals(savedPass)) {
            JOptionPane.showMessageDialog(null, "Welcome " + name + ", " + sname + " it is great to see you again.");
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
        }
        
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");
        }
    }