package pizzeria.swingui.util;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;


public abstract class ConfirmedShopActionListener extends ShopActionListener {

	public ConfirmedShopActionListener(Component frame){
		super(frame);
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		
		if(confirmAction(ev)){
			super.actionPerformed(ev);
		}
		
	}
	
	public abstract String getConfirmMessage();
	
	public boolean confirmAction(ActionEvent ev) {
		int confirmation = 
			JOptionPane.showConfirmDialog(frame, getConfirmMessage());
		return (confirmation == JOptionPane.OK_OPTION);
	
	}

}
