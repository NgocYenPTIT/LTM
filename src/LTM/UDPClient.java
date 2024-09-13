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
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 3000; // Cổng để lắng nghe các gói tin từ client
    private static final String HOST = "localhost" ;  
    private static final Scanner sc = new Scanner(System.in) ;  
    public static void main(String[] args) throws Exception{
        try (DatagramSocket datagramSocket = new DatagramSocket(PORT)) {
            System.out.println("Client is listening on port " + PORT);
            Thread readThread = new Thread(() -> {
                while(true){
                    try {
                    byte[] buffer = new byte[1024] ; 
                DatagramPacket packet = new DatagramPacket(buffer , buffer.length) ; 
                datagramSocket.receive(packet);
                
                // get message
               String serverMessage = new String(packet.getData() , 0 , packet.getLength());
               
               // get port
               int serverPort = packet.getPort() ;
               
               // get address
               InetAddress serverAddress = packet.getAddress() ;
               
               // message
                System.out.println("serverPort: " + serverPort);
                System.out.println("serverAddress: " + serverAddress);
                System.out.println("serverMessage: " + serverMessage);
                System.out.println("-----------------------------------\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
            Thread writeThread = new Thread(() -> {
                while(true){
                    try {
                    String message = sc.nextLine() ;
                    InetAddress serverAdress  = InetAddress.getByName("localhost");
                    DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length() ,serverAdress , 8888);
                    datagramSocket.send(packet);
                    System.out.println("client says: " + message);
                    System.out.println("-----------------------------------\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
            
            readThread.start();
            writeThread.start();
            
            readThread.join();
            writeThread.join();
            
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

