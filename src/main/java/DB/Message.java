package DB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class Message {	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String text;
	
	public Message() {}
	
	public Message(String text) {		
		this.text = text;
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

	@Override
	public String toString() {
		return "Message " + id +  text;
	}
	
	
}
