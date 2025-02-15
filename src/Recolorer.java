import java.awt.*;
import java.awt.image.*;

public class Recolorer {

	public static void recolor ( BufferedImage image, Color color ) {

			for( int w = 0; w < image.getWidth(); w++ ) {

				for( int h = 0; h < image.getHeight(); h++ ) { 

					if( image.getRGB( w, h ) == Color.white.getRGB() ) {

						image.setRGB( w, h, color.getRGB() );

					}

				}

			}

	}

	public static Color encode( Genome genome ) { //For now it just takes the first 24 bits of the genome.

		int[] valuesRGB = new int[3];

		boolean[] data = genome.getData();
		int bitsPerColor = 8;
		
		for( int component = 0; component < 3; component++ ) {

			String binary = new String();

			for( int bit = 0; bit < bitsPerColor; bit++ ) {
				boolean isOne = false;

				try{
					isOne = data[bit + component * bitsPerColor] == true;
				} catch(ArrayIndexOutOfBoundsException e){
				    //array out of bounds
					break;
				}
				if(isOne) {

					binary += "1";

				}

				else {

					binary += "0";

				}

			}

				int base = 2;
			try {
				valuesRGB[component] = Integer.parseInt(binary, base);
			} catch(NumberFormatException e){
				System.out.println("Creature has no genetic code");
			}

		}

		return( new Color( valuesRGB[0], valuesRGB[1], valuesRGB[2] ) );

	}

}

