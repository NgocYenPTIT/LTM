package LTM;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        int port = 8888; // Cổng lắng nghe
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Chấp nhận kết nối từ client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Sử dụng DataInputStream để nhận dữ liệu từ client
            InputStream input = socket.getInputStream();
            DataInputStream dataInput = new DataInputStream(input);

            // Sử dụng DataOutputStream để gửi dữ liệu  client
            OutputStream output = socket.getOutputStream();
            DataOutputStream dataOutput = new DataOutputStream(output);

             Thread writeThread = new Thread(() -> {
                while(true){
                    try {
                         //Gửi tin nhắn tới server sử dụng DataOutputStream
                         String s = sc.nextLine();
                         System.out.println("Server says: " + s);
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
                        String clientMessage = dataInput.readUTF(); // Đọc chuỗi sử dụng UTF-8
                        System.out.println("Client says: " + clientMessage);
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

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
