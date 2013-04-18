package pizzeria.swingui.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pizzeria.core.utils.ActionUnsuccessfullException;

public abstract class ShopActionListener implements ActionListener {
	protected Component frame;
	public ShopActionListener(Component frame) {
		this.frame = frame;
	}
	
	public abstract void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException;
	

	@Override
	public void actionPerformed(ActionEvent ev) {
				
		try {
			shopActionPerformed(ev);
		} catch (ActionUnsuccessfullException e) {
			JOptionPane.showMessageDialog(frame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
