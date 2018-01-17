/**
 * 
 */
package cc.engeld.dev;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author engeld
 *
 */
public class MachinePresenter implements ClickListener, ValueChangeListener {
	MachineViewImpl view;
	MachineModel model;
	double balance = 0;

	public MachinePresenter(MachineViewImpl view, MachineModel model) {
		this.view = view;
		this.model = model;
		
		this.view.addListener(this);

	}
	
	public void subContent(String subView) {
		String[] view = subView.split("/");
		
		switch (view[0]) {
		case "main":
			this.balance = 0;
			this.view.displayAllTickets(model);
			break;
		case "details":
			this.view.displayTicketDetails(model.getTicketByID(Integer.parseInt(view[1])));
			break;
		case "purchase":
			this.view.displayPurchaseScreen(model.getTicketByID(Integer.parseInt(view[1])), 0);
			break;
		case "insertmoney":
			Ticket tmpTicket = model.getTicketByID(Integer.parseInt(view[1]));
			
			double difference = calculateBalance(Double.parseDouble(view[2]), tmpTicket.getPrice());
			if ( difference == 0 ) {
				System.out.println("Print Ticket");
				this.view.displayTicketPrintScreen(0);
			} else if (difference < 0) {
				System.out.println("Print Ticket and return " + Math.abs(difference) + " CHF change");
				this.view.displayTicketPrintScreen(Math.abs(difference));
			} else if (difference > 0 ) {
				System.out.println("Display Remaining price: " + difference);
				this.view.displayPurchaseScreen(model.getTicketByID(Integer.parseInt(view[1])), difference);
			} 
			break;
		}
	}

	private double calculateBalance(double insertedMoney, double price) {
		System.out.println("balance: " + balance + " insertedMoney: " + insertedMoney + " price:: " + price);
		if( this.balance == 0 ) {
			this.balance = insertedMoney;
		} else {
			this.balance = this.balance + insertedMoney;
		}
		
		double difference = price - this.balance;
		System.out.println(difference);
		if(difference == 0) {
			return 0; // print ticket
		} else if (difference < 0){
			return difference; // print ticket + return change
		} else if (difference > 0) {
			return difference; // display remaining price
		}
		return -1;
	}

	public void buttonClick(ClickEvent event) {
		System.out.println("Button pressed: " + event);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		System.out.println("value changed: " + event);
	}

}
