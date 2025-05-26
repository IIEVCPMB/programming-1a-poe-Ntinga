/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

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
        
        boolean isUsernameValid = false;
        while (!isUsernameValid) {
            username = JOptionPane.showInputDialog("Create a username (Max 5 characters, must contain '_'): ");
            if (username == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled.");
                return;
            }
            if (!username.contains("_") || username.length() > 5) {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure it contains an underscore and is no more than five characters in length.");
            } else {
                JOptionPane.showMessageDialog(null, "Username successfully captured.");
                isUsernameValid = true; 
                savedUser = username; 
            }
        }
        String password; 
        boolean isPasswordValid = false;
        while (!isPasswordValid) {
        password = JOptionPane.showInputDialog("Create a password (Min 8 characters, a capital letter, a number and a special character): ");
        
            if (password == null){
                JOptionPane.showMessageDialog(null, "Registration cancelled!");
        }
            if (isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                isPasswordValid = true;
                savedPass = password;
         } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that your password contains at least eight characters, a capital letter, a number and a special character.");
        }
        
        String phone;
        boolean isPhoneValid = false;
        while (!isPhoneValid){
            phone = JOptionPane.showInputDialog("Enter SA phone number (+27XXXXXXXXX): ");
                
            if (phone == null){
                JOptionPane.showMessageDialog(null, "Registration cancelled!");
                return;
            }
            if (isValidSAphone(phone)) {
                JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
                isPhoneValid = true;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code");
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
        
        
        Random random = new Random();
        long randomNo = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        String OTD = String.valueOf(randomNo).substring(0,1);
        
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        String recipientPhoneNumber = null;
        boolean isRecipientPhoneValid = false;
        while (!isRecipientPhoneValid) {
            recipientPhoneNumber = JOptionPane.showInputDialog("Enter the Cellphone number you want to connect to (+27XXXXXXXXX):");
            if (recipientPhoneNumber == null) {
                JOptionPane.showMessageDialog(null, "QuickChat cancelled.");
                return;
            }
            if (isValidSAphone(recipientPhoneNumber)) {
                isRecipientPhoneValid = true;
            } else {
                JOptionPane.showMessageDialog(null, "Recipient cellphone number incorrectly formatted or does not contain international code (+27). Please try again.");
            }
        }
        int soption = 0;
        boolean isOptionValid = false;
        while (!isOptionValid) {
            String select = JOptionPane.showInputDialog("Please select a service (Use assigned number only): \nOption 1) Send Message \nOption 2) Show recently sent messages (Coming Soon) \nOption 3) Quit");
            if (select == null) {
                JOptionPane.showMessageDialog(null, "QuickChat cancelled.");
                return;
            }

            try {
                soption = Integer.parseInt(select);
                if (soption >= 1 && soption <= 3) {
                    isOptionValid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid option. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number (1, 2, or 3).");
            }
        }

        switch (soption) {
            case 2:
                JOptionPane.showMessageDialog(null, "This feature is still in development");
                return;
            case 3:
                JOptionPane.showMessageDialog(null, "Quitting QuickChat.");
                return;
            case 1:
                int numberOfMessages = 0;
                boolean isNumMessagesValid = false;
                while (!isNumMessagesValid) {
                    String NoOfMesSt = JOptionPane.showInputDialog("Enter how many messages you want to send.");
                    if (NoOfMesSt == null) {
                        JOptionPane.showMessageDialog(null, "Message sending cancelled.");
                        return;
                    }
                    try {
                        numberOfMessages = Integer.parseInt(NoOfMesSt);
                        if (numberOfMessages > 0) {
                            isNumMessagesValid = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a positive number of messages.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                    }
                }
                List<String> messagesToSend = new ArrayList<>();
                String currentMessageContent = "";
                for (int i = 0; i < numberOfMessages; i++) {
                    boolean isMessageContentValid = false;
                    while (!isMessageContentValid) {
                        currentMessageContent = JOptionPane.showInputDialog("Enter your message " + (i + 1) + " (Max 250 characters):");

                        if (currentMessageContent == null) {
                            JOptionPane.showMessageDialog(null, "Message collection cancelled.");
                            return;
                        }

                        if (currentMessageContent.length() < 250) {
                            messagesToSend.add(currentMessageContent);
                            isMessageContentValid = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                        }
                    }
                }

                if (!messagesToSend.isEmpty()) {
                    StringBuilder sb = new StringBuilder("Messages Collected:\n");
                    for (int q = 0; q < messagesToSend.size(); q++) {
                        sb.append(q + 1).append(". ").append(messagesToSend.get(q)).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Collected Messages", JOptionPane.INFORMATION_MESSAGE);
                    String lastMessage = messagesToSend.get(messagesToSend.size() - 1);
                    

                    String firstWord = "";
                    String lastWord = "";

                    String[] words = lastMessage.split("\\s+");

                    if (words.length > 0) {
                        firstWord = words[0];
                        lastWord = words[words.length - 1];
                    }

                    String FLWords = (firstWord + lastWord);
                    int a_counter = messagesToSend.size(); 

                    JOptionPane.showMessageDialog(null,
                            "Unique Message ID: " + randomNo +
                            "\nMessageHash: " + OTD + ":" + a_counter + ":" + FLWords +
                            "\nNumber of recipient: " + recipientPhoneNumber + 
                            "\nLast Message Sent: " + lastMessage, 
                            "Message Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    int sendOption = 0;
                    boolean isSendOptionValid = false;
                    while (!isSendOptionValid) {
                        String sndMsgStr = JOptionPane.showInputDialog("Please choose an option: \n1) Send Messages \n2) Disregard Messages \n3) Store Messages and send later");

                        if (sndMsgStr == null) {
                            JOptionPane.showMessageDialog(null, "Message action cancelled.");
                            return;
                        }

                        try {
                            sendOption = Integer.parseInt(sndMsgStr);
                            if (sendOption >= 1 && sendOption <= 3) {
                                isSendOptionValid = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid option. Please enter 1, 2, or 3.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number (1, 2, or 3).");
                        }
                    }
                    switch (sendOption){
                        case 1:
                        JOptionPane.showMessageDialog(null, "Messages sent! Total sent: " + numberOfMessages);
                        break;
                        case 2:
                        JOptionPane.showMessageDialog(null, "Messages disregarded.");
                        break;
                        case 3:
                        JOptionPane.showMessageDialog(null, "Messages stored to be sent later.");
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No messages were entered.");
                }
                break;
        }
    }
 }
  }}