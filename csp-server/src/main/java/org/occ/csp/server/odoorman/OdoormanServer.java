package org.occ.csp.server.odoorman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OdoormanServer extends Object {
	public static void main(String[] args) throws IOException {
		ApplicationContext context = 
            new ClassPathXmlApplicationContext("META-INF/server-context.xml");

		ServerSocket srvSocket = new ServerSocket(16888);
		
		try {
			System.out.println("Started:" + srvSocket);
			Socket socket = null;
			try {

				while (true) {
					socket = srvSocket.accept();

					ServiceObject serviceObject = context.getBean(ServiceObject.class);
					serviceObject.initWithSocket(socket);
					
					System.out.println(socket);
				}
			} finally {
				System.out.println("close...");
				socket.close();
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally { 
			srvSocket.close();				
		}		
	}
}
