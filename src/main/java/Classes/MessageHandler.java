package Classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import DB.DataBase;
import DB.Message;




@Component
public class MessageHandler  {
	
	
	private DataBase db;

	public MessageHandler(DataBase db) {		
		this.db = db;
	}	
	
	public DataBase getDb() {
		return db;
	}
	
	@Autowired
	public void setDb(DataBase db) {
		this.db = db;
	}
	
	
	
	public boolean saveToDB(String mes) {
		
		Message message = new Message(mes);
		db.saveMessageToDB(message);
		
		System.out.println("saved");
		
		return true;
	}
	
	
	public List<Message> getAllMessage(){
		return db.readAllFromMessage();
	}
}
