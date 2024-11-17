import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.NewClient;
import Classes.Server;

class ServerTest {

	//private static ArrayList<NewClient> clients = new ArrayList<NewClient>();	
			private static ServerSocket lestener; 
			
			private static int port = 9464;
			private static String host = "localhost";
			
			private Server serv = new Server();

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
			void testRun() {	
				
				// new Thread for running method
				CompletableFuture.runAsync(() -> {
					serv.run();
				});
				
				// add new clients
				for(int i = 0 ; i < 2 ; ++i) {				
					try {
						Socket server = new Socket(host, serv.port);	
						
						ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
						
						Thread.sleep(500);
					} 
					catch (InterruptedException e) {					
							e.printStackTrace();
					}
					catch (IOException e) {				
						e.printStackTrace();
					}	
				}
				
				assertNotNull(serv.lestener);		
				assert(serv.clients.size() > 0);
			}
			

			@Test
			void testListenClient() {
				fail("Not yet implemented");
			}
			

			@Test
			void testSenddAll() {
				
				// подключение новых клиентов к серверу
				for(int i = 0 ; i < 100 ; ++i) {
					
					Socket client = null;			
					try {
						Socket server = new Socket(host, port);	
						
						client = lestener.accept();	
						
						ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
					} 
					catch (IOException e) {				
						e.printStackTrace();
					}
					
					serv.clients.add(new NewClient(client));
				}
				
				assert(serv.senddAll("test") == serv.clients.size());
				
			}

}
