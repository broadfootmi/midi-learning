import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;

public class CreatureInfoPanel extends JPanel { 

	Creature creature;

	JLabel creatureSprite;
	JLabel songLabel;
	//panel for fitness
	//	textlabel FIT
	//	textlabel 36

	CreatureInfoPanel( Creature creature ) {
		
		this.creature = creature;

		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		songLabel = new JLabel( creature.getSong() ); 

		try {

			BufferedImage creatureImage = ImageIO.read( new File( "creature.png" ) );
			Color creatureColor = Recolorer.encode( creature.getGenome() );
			Recolorer.recolor( creatureImage, creatureColor );

			creatureSprite = new JLabel( new ImageIcon( creatureImage ) ); 

		} catch( IOException e ) {

			e.printStackTrace();
			System.out.println( "No file creature.png" );

		}

		


		add( creatureSprite );
		add( songLabel );

		this.setBackground( Color.gray );
		this.setBorder( new LineBorder( Color.black, 2, false ) );

	}
	
}

