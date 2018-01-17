package cc.engeld.dev;

import java.util.ArrayList;
import java.util.List;

public class MachineModel {
	// List containing all known tickets
	private List<Ticket> ticketList;
	
	public MachineModel() {
		this.ticketList = new ArrayList<Ticket>();
		this.ticketList.add(new Ticket(1, "Biel Einfach", "30min Ticket für Biel", 2.30));
		this.ticketList.add(new Ticket(2, "Biel Retour", "Tagesticket für Biel", 6.60));
		this.ticketList.add(new Ticket(3, "Biel-Bern Halbtax", "Einfaches Ticket für Biel-Bern im Halbtax-Preis", 12.30));
		this.ticketList.add(new Ticket(4, "Biel-Bern Ganzes", "Ganzes Ticket für Biel-Bern", 24.6));
	}
	
	public int getNumberOfTickets() {
		return ticketList.size();
	}
	
	public List<Ticket> getTickets(){
		return this.ticketList;
	}
	
	public Ticket getTicketByID(int id) {
		for(Ticket ticket : this.ticketList) {
			if(ticket.getID() == id) {
				return ticket;
			}
		}
		return null;
	}
}
