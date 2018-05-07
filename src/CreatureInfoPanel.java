import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;

public class CreatureInfoPanel extends JButton {

	Creature creature;

	JLabel creatureSprite;
	JLabel nameLabel;
	//	textlabel FIT
	//	textlabel 36

	CreatureInfoPanel( Creature creature ) {
		
		this.creature = creature;

		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		nameLabel = new JLabel( creature.getName() );

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
		add(nameLabel);

		setBackground( Color.gray );
		setBorder( new LineBorder( Color.black, 2, false ) );

		addActionListener(new PlaySongListener());
	}

	public class PlaySongListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        	try {
				((MidiCreature)creature).playSong();
			} catch(Exception err){
        		err.printStackTrace();
				System.out.println("Wrong creature type in CreatureInfoPanel");
			}

        }
    }
}

