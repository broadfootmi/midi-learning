import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	private Simulation simulation;

	private JFrame frame;

	private JScrollPane creatureScrollPane;
	private JPanel creaturePanel;
	private ArrayList< CreatureInfoPanel > creatureInfoPanels;

	private JPanel buttonPanel;
	private JButton stepSimulation;
	private JButton finishSimulation;

	private int creatureRows = 0;
	private int creatureCols = 1;

	private int buttonRows = 0;
	private int buttonCols = 1;

	private int creatureSpacingX = 16;
	private int creatureSpacingY = 0;

	private int resolutionX = 640;
	private int resolutionY = 480;

	private int stepsPerClick = 1;

	SimulationGUI ( Simulation simulation ) {

		this.simulation = simulation;

		frame = new JFrame( "MIDI Learning" );

		creatureScrollPane = new JScrollPane();
		creaturePanel = new JPanel();
 		creatureInfoPanels = new ArrayList< CreatureInfoPanel >();

		buttonPanel = new JPanel( new GridLayout(buttonRows, buttonCols) );
		stepSimulation = new JButton( "Next" );
		finishSimulation = new JButton( "Finish" );

		buttonPanel.add( stepSimulation );
		buttonPanel.add( finishSimulation );

		frame.setSize( resolutionX, resolutionY );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add( creatureScrollPane, BorderLayout.WEST );
		frame.getContentPane().add( buttonPanel, BorderLayout.CENTER );

		creatureScrollPane.createVerticalScrollBar();
		creatureScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		creatureScrollPane.getViewport().add( creaturePanel );
		
		creaturePanel.setLayout( new GridLayout( creatureRows, creatureCols ) );

		stepSimulation.addActionListener( new StepListener() );
		finishSimulation.addActionListener( new FinishListener() );
		
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

		creaturePanel.removeAll();

		for( CreatureInfoPanel p : creatureInfoPanels ){

			creaturePanel.add( p );

			p.setAlignmentX( Component.LEFT_ALIGNMENT );

		}

		frame.revalidate();
		frame.repaint();

	}

	private class StepListener implements ActionListener {

		public void actionPerformed( ActionEvent e ) {
		
			simulation.step( stepsPerClick );
			displayPopulation( simulation.getCurrentGeneration() );

		}

	}

	private class FinishListener implements ActionListener {

		public void actionPerformed( ActionEvent e ) {

			while( !simulation.isSimulationComplete() ) {

				simulation.step();

			}

			displayPopulation( simulation.getCurrentGeneration() );

		}

	}

}

