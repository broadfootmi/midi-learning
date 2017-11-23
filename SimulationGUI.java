import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	JFrame frame;
	ArrayList< CreatureInfoPanel > creatureInfoPanels;

	SimulationGUI () {

		frame = new JFrame( "MIDI Learning" );
		frame.setSize( 640, 480 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().setLayout( new GridLayout( 0, 3 ) );

		creatureInfoPanels = new ArrayList< CreatureInfoPanel >();

	}

	public void start () {

		frame.setVisible( true );

	}

	public void displayPopulation ( Population pop ) {

		creatureInfoPanels.clear();

		ArrayList< Creature > creatures = pop.getCreatures();

		for( Creature c : creatures ) {
		
			creatureInfoPanels.add( new CreatureInfoPanel( c ) );

		}

		for( CreatureInfoPanel panel : creatureInfoPanels ) {

			frame.getContentPane().add( panel );

		}

	}

}

