/**
 * 
 */
package cc.engeld.dev;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author engeld
 *
 */
public interface MachineView extends View {
	/**
	 * Add a listener to this view. 
	 * @param listener - the listener
	 */
	public void addListener(ClickListener listener);
	
}
