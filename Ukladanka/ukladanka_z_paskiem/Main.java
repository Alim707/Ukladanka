package ukladanka_z_paskiem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Main {

	public static void main(String[] args) {
		JFrame ramka= new JFrame("Ukladanka");
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File("Chomik.jpg"));
		} catch (IOException e) {
			System.out.println("Nie ma obrazka.");
		}
		Obrazek o= new Obrazek(img);
		ramka.addKeyListener(new Action(o));
		ramka.add(o);
		
		
		
		
		ramka.setSize(565, 700);
		ramka.setLocationRelativeTo(null);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setVisible(true);
		ramka.setResizable(false);
		

	}

}
