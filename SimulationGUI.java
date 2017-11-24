import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	private JFrame frame;

	private ArrayList< CreatureInfoPanel > creatureInfoPanels;
	private int creatureRows = 0;
	private int creatureColumns = 8;

	SimulationGUI () {

		frame = new JFrame( "MIDI Learning" );
		frame.setSize( 640, 480 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().setLayout( new GridLayout( creatureRows, creatureColumns , 16, 0 ) );

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

		int numRows = creatureInfoPanels.size() / creatureColumns;
		int numCols = creatureColumns;

		for( int row = 0; row < numRows; row++ ) {

			for( int col = 0; col < numCols; col++ ) {

				frame.getContentPane().add( creatureInfoPanels.get( row + numRows * col ) );

			}

		}

	}

}

