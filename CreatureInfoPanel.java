import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CreatureInfoPanel extends JPanel { 

	Creature creature;

	JLabel creatureSprite;
	JLabel songLabel;
	//image for image
	//textlabel for notes
	//panel for fitness
	//	textlabel FIT
	//	textlabel 36

	CreatureInfoPanel( Creature creature ) {
		
		this.creature = creature;

		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		songLabel = new JLabel( creature.getSong() ); 
		creatureSprite = new JLabel( new ImageIcon( "creature.png" ) ); 

		add( creatureSprite );
		add( songLabel );


		this.setBackground( Color.gray );
		this.setBorder( new LineBorder( Color.black, 2, false ) );

	}
	
	//	songLabel.repaint();
		//image.set("crea");
		//image.repaint();
		//text.setText("FIT")
		//text2.setText(c.getFitness());
	//}
	
	//image.paintComponent() {

		//setColor( c.sequenceAsColor() );

}

