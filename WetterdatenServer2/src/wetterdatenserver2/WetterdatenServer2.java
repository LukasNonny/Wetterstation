/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wetterdatenserver2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author lfnonnenmacher
 */
public class WetterdatenServer2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception 
 {
	byte data[] = new byte[1024];
	DatagramPacket packet;
	DatagramSocket socket = new DatagramSocket(4711);
        
        
        
	while (true){
		packet=new DatagramPacket(data, data.length);
		socket.receive(packet);
		InetAddress address = packet.getAddress();
		int port = packet.getPort();

		System.out.println("Paket von " + packet.getAddress() +
			"am Port " + packet.getPort() + " erhalten");
		
		insertIntoDatabase(packet.getData());
        }
}

    private static void insertIntoDatabase(byte[] data) throws SQLException {
        
        
        String clientDaten = new String(data,StandardCharsets.UTF_8);
        System.out.println(clientDaten);
        
        
        //Treiber laden
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sensordata","root",""); 
        Statement stmt = con.createStatement(); 
        String sql = "INSERT INTO messdaten (Timestemp,Stationsname,Messgroesse,Value) VALUES( '1','Station12','Temperatur','50.2' )";
        stmt.executeUpdate(sql);       
    }
}
