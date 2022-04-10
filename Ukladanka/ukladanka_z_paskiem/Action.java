package ukladanka_z_paskiem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class Action extends KeyAdapter {
	Obrazek o ;
	public Action(Obrazek obrazek) {
		o=obrazek;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int x=e.getKeyCode();
		if(x>=37&&x<=40)
		{
			o.klik(x-36);
			if(o.sprawdzanie())
			{
				o.setTrwa(false);
				JOptionPane.showMessageDialog(null, "Obrazek ulozony w "+o.getrazy()+" ruchach.");
				o.ponowne();
				
			}
			
		}
		
		
	}

}
