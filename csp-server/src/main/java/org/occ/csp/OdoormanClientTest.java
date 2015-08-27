package org.occ.csp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class OdoormanClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OdoormanClientTest client = new OdoormanClientTest();
		client.doTCPService("");
	}
	
	private void doTCPService(String actType) {
		try {
			Socket socket = new Socket("54.251.104.102", 16888);
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			
			//submit footprint
			dos.writeBytes("1");
			
			//get msg from server
			int count = dis.readInt();
			byte[] payload = new byte[count];
			dis.readFully(payload);
			System.out.println("doTcpService resultStr:" + new String(payload));
		} catch (Exception e) {
			System.err.println("doTCPService error:" + e);
		}
		System.out.println("OK");
	}

}
