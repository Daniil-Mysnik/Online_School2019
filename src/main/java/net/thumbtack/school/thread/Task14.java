package net.thumbtack.school.thread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task14 {
    public static void main(String[] args) {
        List<String> emails = getEmails(new File("emails.txt"));
        for (String email : emails) {
            Message message = new Message(null, "From@Mail.ru", "Some subject", "Some text");
            message.setEmail(email);
            new TransportThread(message, email).start();
        }
    }

    private static List<String> getEmails(File file) {
        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}

class Message implements Serializable {
    private String email;
    private String sender;
    private String subject;
    private String body;

    public Message(String email, String sender, String subject, String body) {
        this.email = email;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Message{" +
                "email='" + email + '\'' +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}' + "\n";
    }
}

class Transport {

    public static void send(Message message, String email) {
        System.out.println("Sending letter on email " + email);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("AllLetters.txt", true));
            writer.write(message.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
        System.out.println("Sended");
    }

}

class TransportThread extends Thread {
    private Message message;
    private String email;

    public TransportThread(Message message, String email) {
        this.message = message;
        this.email = email;
    }

    @Override
    public void run() {
        Transport.send(message, email);
    }

}