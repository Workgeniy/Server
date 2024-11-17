package Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NewClient {
	
	public ObjectOutputStream out;
	public ObjectInputStream in;
	public Socket client;
	
	public NewClient(Socket client) {
	this.client = client;
		
		try {			
			out = new ObjectOutputStream(client.getOutputStream());
			out.flush();		
			
			in = new ObjectInputStream(client.getInputStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		
}
