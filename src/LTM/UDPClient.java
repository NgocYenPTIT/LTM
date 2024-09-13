/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LTM;

/**
 *
 * @author Acer
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;

        try (DatagramSocket socket = new DatagramSocket()) {
            // Tin nhắn gửi tới server
            String message = "Hello, server!";
            byte[] buffer = message.getBytes();

            // Địa chỉ và cổng của server
            InetAddress serverAddress = InetAddress.getByName(hostname);

            // Tạo DatagramPacket để gửi tin nhắn tới server
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            socket.send(packet); // Gửi gói tin

            // Bộ đệm để nhận phản hồi từ server
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(responsePacket); // Nhận gói tin phản hồi từ server

            // Đọc và in phản hồi từ server
            String serverMessage = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server says: " + serverMessage);

        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

