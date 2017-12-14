import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	private JFrame frame;
	private JScrollPane creatureScrollPane;
	private JPanel creaturePanel;

	private ArrayList< CreatureInfoPanel > creatureInfoPanels;

	private int creatureRows = 0;
	private int creatureColumns = 8;

	private int creatureSpacingX = 16;
	private int creatureSpacingY = 0;

	private int resolutionX = 640;
	private int resolutionY = 480;

	SimulationGUI () {

		frame = new JFrame( "MIDI Learning" );
		creatureScrollPane = new JScrollPane();
		creaturePanel = new JPanel();
 		creatureInfoPanels = new ArrayList< CreatureInfoPanel >();

		frame.setSize( resolutionX, resolutionY );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add( creatureScrollPane, BorderLayout.WEST );

		creatureScrollPane.createVerticalScrollBar();
		creatureScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		creatureScrollPane.getViewport().add( creaturePanel );
		
		creaturePanel.setLayout( new GridLayout( 0, 1 ) );
		
	}

	public void start () {

		frame.setVisible( true );

	}

	public void displayPopulation ( Population pop ) {

		creatureInfoPanels.clear();

		ArrayList< Creature > creatures = pop.getCreatures();

		for( Creature c : creatures ) {
		
			CreatureInfoPanel p =  new CreatureInfoPanel( c ); 
			creatureInfoPanels.add( p );

			

		}

		for( CreatureInfoPanel p : creatureInfoPanels ){

			creaturePanel.add( p );

			p.setAlignmentX( Component.LEFT_ALIGNMENT );

		}

		frame.revalidate();
		frame.repaint();

	}

}

