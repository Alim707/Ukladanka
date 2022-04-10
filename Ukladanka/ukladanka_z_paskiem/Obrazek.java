package ukladanka_z_paskiem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Obrazek extends JPanel {
	private Image obrazki[][];
	private final int szer;
	private final int wys;
	private int pozycja1;
	private int pozycja2;
	private int nowa1;
	private int nowa2;
	private int razy;
	private int max;
	private static boolean FAZA1 = true;
	private static boolean FAZA2 = false;
	private volatile boolean trwa = true;

	public void setTrwa(boolean trwa) {
		this.trwa = trwa;
	}

	public boolean getTrwa() {
		return this.trwa;
	}

	public int getMax() {
		return max;
	}

	public Obrazek(BufferedImage img) {
		max = 0;
		razy = 0;
		this.obrazki = new Image[3][3];
		szer = img.getWidth() / 3;
		wys = img.getHeight() / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.obrazki[i][j] = new Image(img.getSubimage(i * szer, j * wys, szer, wys));

			}

		}
		pozycja1 = 2;// (int) (Math.random() * 3);
		pozycja2 = 2;// (int) (Math.random() * 3);
		nowa1 = 0;
		nowa2 = 0;
		int pom = 0;
		this.obrazki[pozycja1][pozycja2].setIm(null);
		boolean zmiana = false;
		while (losowanie()) {
			zmiana = false;
			while (!zmiana) {
				zmiana((int) (Math.random() * 4) + 1);
				if (nowa1 < 3 && nowa1 > -1 && nowa2 < 3 && nowa2 > -1) {
					zmiana = true;

				}

			}
			obrazki[pozycja1][pozycja2].setIm(obrazki[nowa1][nowa2].getIm());
			obrazki[nowa1][nowa2].setIm(null);
			pom = obrazki[pozycja1][pozycja2].getObecny();
			obrazki[pozycja1][pozycja2].setObecny(obrazki[nowa1][nowa2].getObecny());
			obrazki[nowa1][nowa2].setObecny(pom);
			pozycja1 = nowa1;
			pozycja2 = nowa2;
			max++;

		}
		JOptionPane.showMessageDialog(null, "Prosze uzywac strzalek do poruszania obiektem.");
		Progres pr = new Progres(this);
		this.setLayout(new BorderLayout());
		this.add(pr.getPr(), BorderLayout.SOUTH);
		pr.start();

	}

	public void ponowne() {
		max = 0;
		boolean zmiana = false;
		while (losowanie()||max<10) {
			zmiana = false;
			while (!zmiana) {
				zmiana((int) (Math.random() * 4) + 1);
				if (nowa1 < 3 && nowa1 > -1 && nowa2 < 3 && nowa2 > -1) {
					zmiana = true;

				}

			}
			obrazki[pozycja1][pozycja2].setIm(obrazki[nowa1][nowa2].getIm());
			obrazki[nowa1][nowa2].setIm(null);
			int pom = obrazki[pozycja1][pozycja2].getObecny();
			obrazki[pozycja1][pozycja2].setObecny(obrazki[nowa1][nowa2].getObecny());
			obrazki[nowa1][nowa2].setObecny(pom);
			pozycja1 = nowa1;
			pozycja2 = nowa2;
			max++;

		}
		razy = 0;
		repaint();
		synchronized (this) {
			notifyAll();

		}
		trwa = true;

	}

	private void zmiana(int kierunek) {
		switch (kierunek) {
		case 1:
			nowa1 = pozycja1 - 1;
			nowa2 = pozycja2;
			break;
		case 2:
			nowa1 = pozycja1;
			nowa2 = pozycja2 - 1;
			break;
		case 3:
			nowa1 = pozycja1 + 1;
			nowa2 = pozycja2;
			break;
		case 4:
			nowa1 = pozycja1;
			nowa2 = pozycja2 + 1;
			break;
		default:
			System.out.println("Blad");
			break;
		}
	}

	public boolean losowanie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (obrazki[i][j].check()) {
					return true;

				}

			}

		}
		return false;

	}

	public boolean sprawdzanie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!obrazki[i][j].check()) {
					return false;

				}

			}

		}
		return true;

	}

	public int getrazy() {
		return this.razy;

	}

	public void klik(int x) {
		try {
			zmiana(x);
			obrazki[pozycja1][pozycja2].setIm(obrazki[nowa1][nowa2].getIm());
			obrazki[nowa1][nowa2].setIm(null);
			int pom = obrazki[pozycja1][pozycja2].getObecny();
			obrazki[pozycja1][pozycja2].setObecny(obrazki[nowa1][nowa2].getObecny());
			obrazki[nowa1][nowa2].setObecny(pom);
			pozycja1 = nowa1;
			pozycja2 = nowa2;
			razy++;
			this.repaint();
		} catch (IndexOutOfBoundsException e) {

		}

	}

	public void solve() {
		int pom1 = 0;
		int pom2 = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.obrazki[i][j].getObecny() == 1) {
					pom1 = i;
					pom2 = j;
					break;

				}
			}
		}
		move(pom1, pom2, 0, 0, this.FAZA1);

		JOptionPane.showMessageDialog(null, "Obrazek ulozony w " + this.razy + " ruchach.");
		ponowne();

	}

	public void move(int x_ob, int y_ob, int x_doc, int y_doc, boolean faza) {
		// TODO Przesuwanie obrazków

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.drawImage(this.obrazki[i][j].getIm(), i * szer, j * wys, null);

			}

		}
		if (this.razy < this.max) {
			g.setColor(g.getColor().BLUE);
		} else if (this.razy == this.max) {
			g.setColor(g.getColor().ORANGE);
		} else {
			g.setColor(g.getColor().RED);
		}
		Font myfont = new Font("Serif", Font.BOLD, 30);
		g.setFont(myfont);
		g.drawString("Zmiany obrazkow: " + razy, 0, (3 * wys) + g.getFontMetrics().getAscent());
		g.setColor(g.getColor().BLACK);
		g.drawString("Losowanie trawlo: " + max, 0,
				(3 * wys) + g.getFontMetrics().getAscent() + g.getFontMetrics().getAscent());
	}

}
