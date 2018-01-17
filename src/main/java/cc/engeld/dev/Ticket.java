package cc.engeld.dev;

public class Ticket {
	private int ticket_id;
	private String name;
	private String details;
	private double price;
	
	public Ticket(int id, String name, String details, double price) {
		this.ticket_id = id;
		this.name = name;
		this.details = details;
		this.price = price;
	}
	
	public int getID() {
		return ticket_id;
	}

	public String getName() {
		return name;
	}

	public String getDetails() {
		return details;
	}

	public double getPrice() {
		return price;
	}
	
	public void setID(int id) {
		this.ticket_id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ID: " + this.ticket_id + " Ticketname: " + this.name + " Beschreibung: " + this.details + " Preis: " + this.price;
	}
}
