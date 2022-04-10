package ukladanka_z_paskiem;

import java.awt.image.BufferedImage;

public class Image {
	private BufferedImage im;
	private int prawidlowy;
	private int obecny;
	private static int stala=1;
	
	public Image(BufferedImage im)
	{
		this.im=im;
		this.prawidlowy=stala;
		this.obecny=stala;
		stala++;
		
	}
	
	public boolean check()
	{
		if(prawidlowy==obecny)
		{
			return true;
			
		}
		return false;
		
	}

	public int getObecny() {
		return obecny;
	}

	public void setObecny(int obecny) {
		this.obecny = obecny;
	}

	public BufferedImage getIm() {
		return im;
	}

	public void setIm(BufferedImage im) {
		this.im = im;
	}

}
