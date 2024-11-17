import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Classes.AppConfig;
import Classes.MessageHandler;
import DB.Message;

class MessageHandlerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void saveTest() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MessageHandler mHand = context.getBean(MessageHandler.class);
			
		assert(mHand.saveToDB("Green tomato"));
	}
	
	@Test
	void getAllTest() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MessageHandler mHand = context.getBean(MessageHandler.class);
			
		List<Message> messages = mHand.getAllMessage();
		for(Message message : messages) {
			System.out.println(message);
		}
	}

}
