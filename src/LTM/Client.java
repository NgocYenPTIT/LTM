package LTM;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        String hostname = "localhost";
        int port = 8888;
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to the server");

            // Sử dụng DataOutputStream để gửi dữ liệu
            OutputStream output = socket.getOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(output);

            // Sử dụng DataInputStream để nhận dữ liệu từ server
            InputStream input = socket.getInputStream();
            DataInputStream dataInput = new DataInputStream(input);

            Thread writeThread = new Thread(() -> {
                while(true){
                    try {
                         //Gửi tin nhắn tới server sử dụng DataOutputStream
                         String s = sc.nextLine();
                         System.out.println("Client says: " + s);
                        dataOutput.writeUTF(s);  // Ghi chuỗi sử dụng UTF-8
                        dataOutput.flush(); // Đảm bảo dữ liệu được gửi đi ngay
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
            Thread readThread = new Thread(() -> {
                while(true){
                    try {
                        // Nhận và in tin nhắn từ server
                        String serverMessage = dataInput.readUTF(); // Đọc chuỗi sử dụng UTF-8
                        System.out.println("Server says: " + serverMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
            writeThread.start();
            readThread.start();
            
            writeThread.join();
            readThread.join();
            // Đóng kết nối
//            socket.close();
//            System.out.println("Connection closed");

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
