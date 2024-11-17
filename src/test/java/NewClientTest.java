import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.NewClient;

class NewClientTest {

	private static int port = 9463;
	private static String host = "localhost";
	
	private static ServerSocket lestener;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		lestener = new ServerSocket(port);
		System.out.println("listener start");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		lestener.close();
		System.out.println("listener closed");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testNewClient() {		
		
		Socket client = null;
		
		try {
			Socket server = new Socket(host, port);	
	
			client = lestener.accept();	
			
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		} 
		catch (IOException e) {
			
			e.printStackTrace();
		}	
		
		System.out.println(client);
		NewClient test = new NewClient(client);		
		
		assertNotNull(test);
		assertNotNull(client);
		assertNotNull(test.in);
		assertNotNull(test.out);
		
	}

}
