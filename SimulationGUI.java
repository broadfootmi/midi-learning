import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SimulationGUI {

	private Simulation simulation;

	private JFrame frame;

	private JPanel generationPanel;
	private JLabel generationLabel;

	private JScrollPane creatureScrollPane;
	private JPanel creaturePanel;
	private ArrayList< CreatureInfoPanel > creatureInfoPanels;

	private JPanel buttonPanel;
	private JButton stepSimulation;
	private JButton finishSimulation;

	private JSpinner numStepsChooser;

	private int creatureRows = 0;
	private int creatureCols = 1;

	private int buttonRows = 0;
	private int buttonCols = 1;

	private int creatureSpacingX = 16;
	private int creatureSpacingY = 0;

	private int resolutionX = 640;
	private int resolutionY = 480;

	private int defaultStepsPerClick = 1;
	private int minStepsPerClick = 1;
	private int maxStepsPerClick = 20;

	SimulationGUI ( Simulation simulation ) {

		this.simulation = simulation;

		frame = new JFrame( "MIDI Learning" );

		generationPanel = new JPanel();
		generationPanel.setLayout( new BoxLayout(generationPanel, BoxLayout.Y_AXIS) );
		generationLabel = new JLabel();
		creatureScrollPane = new JScrollPane();
		creaturePanel = new JPanel();
 		creatureInfoPanels = new ArrayList< CreatureInfoPanel >();

		generationPanel.add( generationLabel );
		generationPanel.add( creatureScrollPane );

		numStepsChooser = new JSpinner( new SpinnerNumberModel(defaultStepsPerClick, minStepsPerClick, maxStepsPerClick, 1) );

		buttonPanel = new JPanel( new GridLayout(buttonRows, buttonCols) );
		stepSimulation = new JButton( "Next" );
		finishSimulation = new JButton( "Finish" );

		buttonPanel.add( numStepsChooser );
		buttonPanel.add( stepSimulation );
		buttonPanel.add( finishSimulation );

		frame.setSize( resolutionX, resolutionY );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.getContentPane().add( generationPanel, BorderLayout.WEST );
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

	private int stepsPerClick() {
	
		SpinnerNumberModel model = (SpinnerNumberModel) numStepsChooser.getModel();
		return model.getNumber().intValue();
	}

	public void updateGenerationLabel() {

		generationLabel.setText("Generation #" + (simulation.getCurrentGeneration().getGenerationIndex() + 1));
	
	}

	private class StepListener implements ActionListener {

		public void actionPerformed( ActionEvent e ) {
		
			simulation.step( stepsPerClick() );
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

