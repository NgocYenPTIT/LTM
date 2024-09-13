/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LTM;

/**
 *
 * @author Acer
 */
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class NICExample {
    public static void main(String[] args) {
//        try {
//            // Lấy danh sách tất cả các giao diện mạng (NIC) trên hệ thống
//            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//
//            // Duyệt qua từng giao diện mạng
//            for (NetworkInterface netInterface : Collections.list(interfaces)) {
//                System.out.println("Interface Name: " + netInterface.getName());
//                System.out.println("Display Name: " + netInterface.getDisplayName());
//
//                // Lấy địa chỉ MAC (nếu có)
//                byte[] mac = netInterface.getHardwareAddress();
//                if (mac != null) {
//                    System.out.print("MAC Address: ");
//                    for (int i = 0; i < mac.length; i++) {
//                        System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
//                    }
//                    System.out.println();
//                } else {
//                    System.out.println("MAC Address: Not available");
//                }
//
//                // Lấy danh sách các địa chỉ IP liên kết với giao diện mạng
//                Enumeration<InetAddress> inetAddresses = netInterface.getInetAddresses();
//                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
//                    System.out.println("InetAddress: " + inetAddress.getHostAddress());
//                }
//
//                System.out.println("-----------------------------------");
//            }
//        } catch (SocketException e) {
//            System.out.println("Error getting network interfaces: " + e.getMessage());
//        }
try {
InetAddress local = InetAddress.getByName("127.0.0.1");
NetworkInterface ni = NetworkInterface.getByInetAddress(local);
    System.out.println(ni);
if (ni == null) {
System.err.println("That's weird. No local loopback address.");
}
} catch (SocketException ex) {
System.err.println("Could not list sockets.");
} catch (UnknownHostException ex) {
System.err.println("That's weird. No local loopback address.");
}

    }
}

