import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	private JFrame frame;

	private ArrayList< CreatureInfoPanel > creatureInfoPanels;

	private int creatureRows = 0;
	private int creatureColumns = 8;

	private int creatureSpacingX = 16;
	private int creatureSpacingY = 0;

	private int resolutionX = 640;
	private int resolutionY = 480;

	SimulationGUI () {

		frame = new JFrame( "MIDI Learning" );

		frame.setSize( resolutionX, resolutionY );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().setLayout( new GridLayout( creatureRows, creatureColumns , creatureSpacingX, creatureSpacingY ) );

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

		frame.revalidate();

	}

}

