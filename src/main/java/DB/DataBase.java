package DB;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataBase {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	
	public DataBase() {
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();			
		} 
		catch (HibernateException ex) {			
			System.out.println(ex);
		}

		
	}
	
	public void saveMessageToDB(Object obj) {
		
		try {
			session = sessionFactory.openSession();
			
			Transaction transaction = session.beginTransaction();
			session.persist(obj);
			transaction.commit();
		}
		catch(HibernateException ex){
			System.out.println(ex);
		}
		finally {
			session.close();
		}
	}
	
	public List<Message> readAllFromMessage() {
		
		session = sessionFactory.openSession();
		
		List<Message> messages = session.createQuery("FROM Message", Message.class).getResultList(); 
		
		session.close();
		
		return messages;
	}
}
