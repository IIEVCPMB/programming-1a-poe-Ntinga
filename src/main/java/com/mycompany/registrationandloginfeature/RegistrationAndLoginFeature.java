/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandloginfeature;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.mycompany.registrationandloginfeature.Messages;
import com.mycompany.registrationandloginfeature.Message1;
/**
 *
 * @author samue
 */
public class RegistrationAndLoginFeature {
    
    private static String savedUser; 
    private static String savedPass; 
    private static String loggedInUserPhoneNumber; 

    private static List<Message1> sentMessages = new ArrayList<>();
    private static List<Message1> disregardedMessages = new ArrayList<>();
    private static List<Message1> storedMessages = new ArrayList<>();
    
    
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return false;
        }
        return true;
    }

    
    public static boolean isValidSAphone(String number){
        return Messages.checkRecipientCell(number);
    }

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Please enter your first name");
        String sname = JOptionPane.showInputDialog("Please enter your surnname");
        String username;

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
                return; 
            }
            if (isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured.");
                isPasswordValid = true;
                savedPass = password; 
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that your password contains at least eight characters, a capital letter, a number and a special character.");
            }
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
                loggedInUserPhoneNumber = phone; 
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code");
            }
        }

        JOptionPane.showMessageDialog(null, "Registration Successful");

        String loginUser = JOptionPane.showInputDialog("Enter your username.");
        String loginPass = JOptionPane.showInputDialog("Enter your password.");

        if (loginUser.equals(savedUser) && loginPass.equals(savedPass)) {
            JOptionPane.showMessageDialog(null, "Welcome " + name + ", " + sname + " it is great to see you again.");
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
            return; 
        }

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
            String select = JOptionPane.showInputDialog(
                "Please select a service (Use assigned number only): \n" +
                "1) Send Message \n" +
                "2) Show recently sent messages \n" +
                "3) Find Longest Message Sent \n" +
                "4) Search for Message/Recipient by Message ID \n" +
                "5) Search All Messages Sent to One Receiver \n" +
                "6) Delete Message by Message Hash \n" +
                "7) Display Full Sent Messages Report \n" +
                "8) Quit \n" +
                "9) Load Sample Data (For Testing)"
            );
            if (select == null) {
                JOptionPane.showMessageDialog(null, "QuickChat cancelled.");
                return;
            }

            try {
                soption = Integer.parseInt(select);
                if (soption >= 1 && soption <= 8) { 
                    isOptionValid = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }

        switch (soption) {
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
                List<String> currentMessagesContent = new ArrayList<>(); 
                for (int i = 0; i < numberOfMessages; i++) {
                    boolean isMessageContentValid = false;
                    while (!isMessageContentValid) {
                        String content = JOptionPane.showInputDialog("Enter your message " + (i + 1) + " (Max 250 characters):");

                        if (content == null) {
                            JOptionPane.showMessageDialog(null, "Message collection cancelled.");
                            return;
                        }

                        if (content.length() < 250) {
                            currentMessagesContent.add(content);
                            isMessageContentValid = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                        }
                    }
                }

                if (!currentMessagesContent.isEmpty()) {
                    StringBuilder sb = new StringBuilder("Messages Collected:\n");
                    for (int q = 0; q < currentMessagesContent.size(); q++) {
                        sb.append(q + 1).append(". ").append(currentMessagesContent.get(q)).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Collected Messages", JOptionPane.INFORMATION_MESSAGE);

                    Random random = new Random();
                    long messageBatchId = 1000000000L + (long)(random.nextDouble() * 9000000000L);
                    int OTD = Integer.parseInt(String.valueOf(messageBatchId).substring(0,1)); 

                    String lastMessageContent = currentMessagesContent.get(currentMessagesContent.size() - 1);
                    String firstWord = "";
                    String lastWord = "";
                    String[] words = lastMessageContent.split("\\s+");
                    if (words.length > 0) {
                        firstWord = words[0];
                        lastWord = words[words.length - 1];
                    }
                    String FLWords = (firstWord + lastWord);
                    int a_counter = currentMessagesContent.size();
                    
                    String messageBatchHash = Messages.createMessageHash(OTD, a_counter, FLWords);

                    JOptionPane.showMessageDialog(null,
                            "Unique Message Batch ID: " + messageBatchId +
                            "\nMessageHash (Batch): " + messageBatchHash +
                            "\nNumber of recipient: " + recipientPhoneNumber +
                            "\nLast Message Content: " + lastMessageContent,
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

                    
                    for (String msgContent : currentMessagesContent) {
                        
                        Message1 msg = new Message1(loggedInUserPhoneNumber, recipientPhoneNumber, msgContent, messageBatchId, messageBatchHash);
                        switch (sendOption){
                            case 1:
                                sentMessages.add(msg);
                                break;
                            case 2:
                                disregardedMessages.add(msg);
                                break;
                            case 3:
                                storedMessages.add(msg);
                                break;
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

            case 2: 
                displayRecentlySentMessages();
                break;
            case 3: 
                findLongestMessageSent();
                break;
            case 4: 
                searchMessageById();
                break;
            case 5: 
                searchMessagesToReceiver();
                break;
            case 6: 
                deleteMessageByHash();
                break;
            case 7: 
                displaySentMessagesReport();
                break;
            case 8: 
                JOptionPane.showMessageDialog(null, "Quitting QuickChat.");
                return;
            case 9:
                loadSampleMessages();
                break;
            default:
                JOptionPane.showMessageDialog(null, "An unexpected error occurred. Please restart.");
                break;
        }
    } 

    private static void displayRecentlySentMessages() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages have been sent yet.", "Recent Messages", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("Recently Sent Messages (Last 5):\n");
        int count = 0;
        for (int i = sentMessages.size() - 1; i >= 0 && count < 5; i--) {
            Message1 msg = sentMessages.get(i);
            sb.append("\n--- Message ").append(count + 1).append(" ---\n");
            sb.append("ID: ").append(msg.getMessageId()).append("\n");
            sb.append("Hash: ").append(msg.getMessageHash()).append("\n");
            sb.append("From: ").append(msg.getSenderPhoneNumber()).append("\n");
            sb.append("To: ").append(msg.getRecipientPhoneNumber()).append("\n");
            sb.append("Content: ").append(msg.getContent()).append("\n");
            count++;
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Recent Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void findLongestMessageSent() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages have been sent to determine the longest.", "Longest Message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Message1 longestMsg = sentMessages.stream()
                                        .max(Comparator.comparingInt(msg -> msg.getContent().length()))
                                        .orElse(null);

        if (longestMsg != null) {
            JOptionPane.showMessageDialog(null,
                "The longest message sent is:\n" +
                "ID: " + longestMsg.getMessageId() +
                "\nHash: " + longestMsg.getMessageHash() +
                "\nFrom: " + longestMsg.getSenderPhoneNumber() +
                "\nTo: " + longestMsg.getRecipientPhoneNumber() +
                "\nContent (" + longestMsg.getContent().length() + " chars): " + longestMsg.getContent(),
                "Longest Message", JOptionPane.INFORMATION_MESSAGE
            );
        } else {
             JOptionPane.showMessageDialog(null, "Could not determine longest message.", "Longest Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void searchMessageById() {
        String idStr = JOptionPane.showInputDialog("Enter the Message ID to search for:");
        if (idStr == null) return; // User cancelled
        try {
            long searchId = Long.parseLong(idStr);
            StringBuilder results = new StringBuilder("Search Results for Message ID " + searchId + ":\n");
            boolean found = false;

            
            for (Message1 msg : sentMessages) {
                if (msg.getMessageId() == searchId) {
                    results.append("\n--- Found in Sent Messages ---\n").append(msg.toString()).append("\n");
                    found = true;
                }
            }
            
            for (Message1 msg : disregardedMessages) {
                if (msg.getMessageId() == searchId) {
                    results.append("\n--- Found in Disregarded Messages ---\n").append(msg.toString()).append("\n");
                    found = true;
                }
            }
            
            for (Message1 msg : storedMessages) {
                if (msg.getMessageId() == searchId) {
                    results.append("\n--- Found in Stored Messages ---\n").append(msg.toString()).append("\n");
                    found = true;
                }
            }

            if (!found) {
                results.append("No messages found with ID: " + searchId);
            }
            JOptionPane.showMessageDialog(null, results.toString(), "Search by Message ID", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void searchMessagesToReceiver() {
        String searchRecipient = JOptionPane.showInputDialog("Enter the recipient phone number to search for (+27XXXXXXXXX):");
        if (searchRecipient == null) return; 

        
        if (!Messages.checkRecipientCell(searchRecipient)) {
            JOptionPane.showMessageDialog(null, "Invalid recipient phone number format. Please use +27 followed by 9 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder results = new StringBuilder("Messages sent to " + searchRecipient + ":\n");
        boolean found = false;

        for (Message1 msg : sentMessages) {
            if (msg.getRecipientPhoneNumber().equals(searchRecipient)) {
                results.append("\n--- Message ---\n").append(msg.toString()).append("\n");
                found = true;
            }
        }

        if (!found) {
            results.append("No messages found sent to " + searchRecipient);
        }
        JOptionPane.showMessageDialog(null, results.toString(), "Search by Recipient", JOptionPane.INFORMATION_MESSAGE);
    }


    private static void deleteMessageByHash() {
        String searchHash = JOptionPane.showInputDialog("Enter the Message Hash to delete:");
        if (searchHash == null) return;

        boolean deleted = false;

        Iterator<Message1> sentIterator = sentMessages.iterator();
        while (sentIterator.hasNext()) {
            Message1 msg = sentIterator.next();
            if (msg.getMessageHash().equals(searchHash)) {
                sentIterator.remove();
                deleted = true;
            }
        }
        
        Iterator<Message1> disregardedIterator = disregardedMessages.iterator();
        while (disregardedIterator.hasNext()) {
            Message1 msg = disregardedIterator.next();
            if (msg.getMessageHash().equals(searchHash)) {
                disregardedIterator.remove();
                deleted = true;
            }
        }
        
        Iterator<Message1> storedIterator = storedMessages.iterator();
        while (storedIterator.hasNext()) {
            Message1 msg = storedIterator.next();
            if (msg.getMessageHash().equals(searchHash)) {
                storedIterator.remove();
                deleted = true;
            }
        }

        if (deleted) {
            JOptionPane.showMessageDialog(null, "Message(s) with hash " + searchHash + " deleted successfully.", "Delete Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No message found with hash: " + searchHash, "Delete Message", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void displaySentMessagesReport() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages to generate a report.", "Sent Messages Report", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder report = new StringBuilder("--- Full Report of Sent Messages ---\n\n");
        for (int i = 0; i < sentMessages.size(); i++) {
            Message1 msg = sentMessages.get(i);
            report.append("--- Message ").append(i + 1).append(" ---\n");
            report.append("Unique ID: ").append(msg.getMessageId()).append("\n");
            report.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
            report.append("Sender: ").append(msg.getSenderPhoneNumber()).append("\n");
            report.append("Recipient: ").append(msg.getRecipientPhoneNumber()).append("\n");
            report.append("Content: ").append(msg.getContent()).append("\n");
            report.append("Length: ").append(msg.getContent().length()).append(" characters\n");
        }
        JOptionPane.showMessageDialog(null, report.toString(), "Sent Messages Report", JOptionPane.INFORMATION_MESSAGE);
        
        
    
    }
    private static void loadSampleMessages() {
        
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();

        long id1 = 1234567890L; String hash1 = Messages.createMessageHash(1, 2, "HelloWorld");
        long id2 = 9876543210L; String hash2 = Messages.createMessageHash(9, 1, "AnotherMsg");

        sentMessages.add(new Message1(loggedInUserPhoneNumber, "+27834557896", "Did you get the cake", id1, hash1));
        sentMessages.add(new Message1(loggedInUserPhoneNumber, "+27838884567", "Where are you? You are late! I have asked you to be on time.", id1, hash1));
        sentMessages.add(new Message1(loggedInUserPhoneNumber, "+27834484567", "Yohooooooo, I am at your gate.", id2, hash2));
        sentMessages.add(new Message1(loggedInUserPhoneNumber, "0838884567", "It is dinner time !.", id1, hash1));
        
        storedMessages.add(new Message1(loggedInUserPhoneNumber, "+27838884567", "Ok, I am leaving without you.", 3333333333L, "3:1:StoredTest"));

        disregardedMessages.add(new Message1(loggedInUserPhoneNumber, "+27790001111", "This message was disregarded.", 4444444444L, "4:1:Disregarded"));

        JOptionPane.showMessageDialog(null, "Sample data has been loaded into the message lists!", "Data Loaded", JOptionPane.INFORMATION_MESSAGE);
}
}