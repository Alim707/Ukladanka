package ukladanka_z_paskiem;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Progres extends Thread {
	private volatile JProgressBar pr;
	private Obrazek o;
	private int razy;

	public JProgressBar getPr() {
		return pr;
	}

	public Progres(Obrazek o) {
		this.o = o;
		razy = o.getMax();
		pr = new JProgressBar(0, razy);
	}

	@Override
	public void run() {
		int n = 1;
		pr.setStringPainted(true);
		while (true) {
			while (n < razy && o.getTrwa()) {
				pr.setString(Integer.toString(razy - n));
				pr.setValue(n);
				n++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			pr.setString(Integer.toString(razy - n));
			pr.setValue(n);
			if (n >= razy) {
				JOptionPane.showMessageDialog(null, "Czas uplyna.");
				o.ponowne();

			} else if (razy != 0) {
				try {
					synchronized (o) {
						o.wait();

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			n = 0;
			razy = o.getMax();
			pr.setMaximum(razy);
			pr.setValue(n);
		}
	}

}
