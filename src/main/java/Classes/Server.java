package Classes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;




public class Server {
	public static int port = 9463;
	public ServerSocket lestener; 	
	
	// это тестовый вариант, далее ArrayList поменять на Map (Dictionary)
	public ArrayList<NewClient> clients = new ArrayList<NewClient>();	

	
	// старт сервера
	public void run() {
		
		try {
			lestener = new ServerSocket(port);
			System.out.println("Ожидаю подключения к порту " + port);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		while(true) {
			try {				
				Socket client = lestener.accept();
				
				NewClient newClient = new NewClient(client);
				
				clients.add(newClient);
				System.out.println("Client +++");
				System.out.println(client.toString() + " is connected");
				
				CompletableFuture.runAsync(() -> {
					
					try {
						listenClient(newClient);
					} 
					catch (IOException e) {						
						System.out.println("CompletableFuture ->" + e);
					}
				});		
			} 
			catch (IOException e) {				
				System.out.println("run function -> " + e);
			}
		}	
		
	}
	
	// слушаем клиента
	public void listenClient(NewClient client) throws IOException {	
		
		while(true) {
			
			if (client.client.isConnected()) {
				try {
					String message = (String)client.in.readObject ();					
					
					System.out.println(message);
					// save to database					
					AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
					MessageHandler messHand = context.getBean(MessageHandler.class);
					messHand.saveToDB(message);	

					senddAll(message);	
				}
				catch (ClassNotFoundException | IOException e) {			
					System.out.println("listenClient function -> " + e);
				} 
				catch (BeansException e) {
					
					e.printStackTrace();
				}
			}		
		}

	}
	
	// отправка всем
	public Integer senddAll(String message) {			
		 
		int count = 0 ;
		for(NewClient client : clients) {
			
			if(client.client.isConnected()) {
				try {
					
					client.out.writeObject(message);	
					++count;
				} 
				catch (IOException e) {
				}
			}			
		}
		
		return count;
	}
}
