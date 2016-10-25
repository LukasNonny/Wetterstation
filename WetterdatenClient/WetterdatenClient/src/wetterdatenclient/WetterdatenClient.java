/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterdatenclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

/**
 *
 * @author lfnonnenmacher
 */
public class WetterdatenClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception 
 {
	DatagramPacket packet;
	DatagramSocket dSocket = new DatagramSocket();
	InetAddress serverAddress=InetAddress.getByName("localhost");

	while (true) {           
		String s = "'1','Station12','Temperatur','50.2'";
		packet = new DatagramPacket(s.getBytes(), s.length(),serverAddress, 4711);
		dSocket.send(packet);
		System.out.println("Paket " + packet + " abgeschickt");
		Thread.sleep(1000);
	}
 }  
}
