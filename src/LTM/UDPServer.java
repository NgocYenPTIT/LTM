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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
    private static final int PORT = 8888; // Cổng để lắng nghe các gói tin từ client
    private static final String HOST = "localhost" ;  
    private static final Scanner sc = new Scanner(System.in) ;  
    public static void main(String[] args) throws Exception{
        try (DatagramSocket datagramSocket = new DatagramSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            Thread readThread = new Thread(() -> {
                while(true){
                    try {
                    byte[] buffer = new byte[1024] ; 
                DatagramPacket packet = new DatagramPacket(buffer , buffer.length) ; 
                datagramSocket.receive(packet);
                
                // get message
               String clientMessage = new String(packet.getData() , 0 , packet.getLength());
               
               // get port
               int clientPort = packet.getPort() ;
               
               // get address
               InetAddress clientAddress = packet.getAddress() ;
               
               // message
                System.out.println("clientPort: " + clientPort);
                System.out.println("clientAddress: " + clientAddress);
                System.out.println("clientMessage: " + clientMessage);
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
                    InetAddress clientAdress  = InetAddress.getByName("localhost");
                    DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length() ,clientAdress , 3000);
                    datagramSocket.send(packet);
                    System.out.println("Server says: " + message);
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
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

