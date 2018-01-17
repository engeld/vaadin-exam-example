package cc.engeld.dev;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MachineViewImpl extends VerticalLayout implements MachineView{
	/**
	 * The list of listeners
	 */
	private List<ClickListener> listeners = new ArrayList<ClickListener>();
	
	@Override
	public void enter(ViewChangeEvent event) {
		for (ClickListener listener : this.listeners) {
			if (event.getParameters() != null && !event.getParameters().isEmpty()) {
				((MachinePresenter) listener).subContent(event.getParameters());
			} else {
				((MachinePresenter) listener).subContent("main");
			}
		}
	}
	
	public void displayAllTickets(MachineModel model) {
		System.out.println("Display ALl Tickets");
		this.removeAllComponents();
		this.setSizeFull();

		GridLayout ticketLayout = new GridLayout(model.getNumberOfTickets()/2, model.getNumberOfTickets()/2);
		for(Ticket ticket : model.getTickets()) {
			ticketLayout.addComponent( new Button(
					ticket.getName(), 
					clickEvent -> UI.getCurrent().getNavigator().navigateTo("/details/"+ticket.getID())
				));
		}
		
		this.addComponent(ticketLayout);		
	}
	public void displayTicketDetails(Ticket ticket) {
		System.out.println("Display Ticket Details");
		this.removeAllComponents();
		this.setSizeFull();

		VerticalLayout ticketLayout = new VerticalLayout();
		ticketLayout.addComponent(new Label(ticket.toString()));
		ticketLayout.addComponent(new Button(
				"Ticket kaufen",
				clickEvent -> UI.getCurrent().getNavigator().navigateTo("/purchase/"+ticket.getID())
				));
		
		this.addComponent(ticketLayout);	
	}

	public void displayPurchaseScreen(Ticket ticket, double amountRemaining) {
		System.out.println("Purchase Ticket");
		this.removeAllComponents();
		this.setSizeFull();
		
		TextField moneySlot = new TextField("Geld reinwerfen [CHF]");
		VerticalLayout ticketLayout = new VerticalLayout();
		ticketLayout.addComponent(new Label(ticket.toString()));
		ticketLayout.addComponent(moneySlot);
		
		if(amountRemaining != 0) {
			System.out.println("Betrag von CHF " + amountRemaining + " ist noch offen");
			ticketLayout.addComponent(new Label("Betrag von CHF "+ amountRemaining + " ist noch offen"));
		}
		
		ticketLayout.addComponent(new Button(
				"Geld reinwerfen!",				
				clickEvent -> UI.getCurrent().getNavigator().navigateTo("/insertmoney/"+ticket.getID()+"/"+moneySlot.getValue())
				));
		
		this.addComponent(ticketLayout);	
	}
	
	public void displayTicketPrintScreen(double change) {
		System.out.println("Printing Ticket");
		this.removeAllComponents();
		this.setSizeFull();
		
		VerticalLayout ticketLayout = new VerticalLayout();
		ticketLayout.addComponent(new Label("Ticket erfolgreich gekauft - Ticket wird gedruckt..."));
		
		if(change != 0) {
			System.out.println("Betrag von CHF " + change + " ist noch offen");
			ticketLayout.addComponent(new Label("Rückgeld von CHF "+ change + " wird noch ausgegeben"));
		}
		
		ticketLayout.addComponent(new Button(
				"Zurück zum Start",				
				clickEvent -> UI.getCurrent().getNavigator().navigateTo("")
				));
		
		this.addComponent(ticketLayout);	
	}
	
	public void addListener(ClickListener listener) {
		this.listeners.add(listener);
	}

}
