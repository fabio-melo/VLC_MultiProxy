/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vlc_multiproxy;


import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;


public class UDP_Cliente {

	public static void main(String[] args) throws Exception {
		
		DatagramSocket ds = new DatagramSocket();
		
		int i = 8;
		byte[] b = String.valueOf(i).getBytes();
		
		InetAddress ia = InetAddress.getLocalHost();		
		DatagramPacket dp = new DatagramPacket(b, b.length, ia,  9999);
		ds.send(dp);
		//enviou para o servidor
		

		byte[] b1 = new byte[1024];
		DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
		ds.receive(dp1);
		//recebeu do servidor
		
		String str = new String(dp1.getData(), 0, dp1.getLength());
		System.out.println("O resultado é: " +str);
	
	
	
	}

}
