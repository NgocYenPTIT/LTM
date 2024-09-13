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

public class UDPServer {
    public static void main(String[] args) {
        int port = 1234; // Cổng để lắng nghe các gói tin từ client

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Bộ đệm để nhận dữ liệu
            byte[] buffer = new byte[1024];

            // Tạo DatagramPacket để nhận dữ liệu từ client
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet); // Nhận gói tin từ client
            String clientMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Client says: " + clientMessage);

            // Gửi phản hồi lại cho client
            String response = "Thank you, client!";
            byte[] responseBytes = response.getBytes();
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();
            DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
            socket.send(responsePacket); // Gửi gói tin phản hồi

            System.out.println("Response sent to the client.");

        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

