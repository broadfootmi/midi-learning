import java.util.Random;
import java.util.ArrayList;
import java.awt.Frame;

public class Simulation {

	/*Settings*/
	private double mutationRate = 0.01;
	private int populationSize = 192;
	private int maxNumGenerations = 1000;
	private Creature desiredCreature;

	/*State*/
	private int numGenerations = 0;
	private Population lastGeneration;
	private Population currentGeneration;
	private boolean isSimulationComplete = false;

	/*Data*/
	private ArrayList< Population > previousGenerations;

	/*RNG*/
	private Random rng;
	private CreatureRoulette roulette;

	/*GUI*/
	private SimulationGUI gui;

	public Simulation() {

		/*Init RNG*/
		rng = new Random();
		roulette = new CreatureRoulette();

		/*Init Solution*/
		String desiredGenomeString = "AAABCCBBAAGGAABBCCBBBBABCCBBrrAAGGrr";
		char[] desiredGenome = desiredGenomeString.toCharArray();

		desiredCreature = new Creature( this );
		desiredCreature.setGenome( desiredGenome );

		/*Init Data*/
		previousGenerations = new ArrayList< Population > ();

		/*Init GUI*/
		gui = new SimulationGUI( this );
		gui.start();

	}	

	public double getMutationRate () {
		
		return mutationRate;

	}

	public int getCurrentGenerationIndex () {

		return numGenerations;

	}

	public Population getCurrentGeneration () {
		
		return currentGeneration;

	}

	public void start () {

		/*Desired Creature*/
		System.out.println( "This is the creature we want:" );

		desiredCreature.printGenome();
		desiredCreature.printTraits();

		step();
		
	}

	public boolean getRandomBoolean () {

		return rng.nextBoolean();

	}

	public int getRandomInteger () {

		return Math.abs( rng.nextInt() );

	}

	public Creature chooseCreature ( Population pop ) {

		return roulette.chooseCreature( pop );

	}

	private void nextGeneration() {


		if( numGenerations == 0 ) {

			currentGeneration = new Population( this, populationSize );
			gui.displayPopulation( currentGeneration );

		}
		
		else if( numGenerations > 0 ) {
	
			if ( (numGenerations == maxNumGenerations) || (currentGeneration.getContainsPerfectCreature()) ) {
				this.isSimulationComplete = true;
				displayResults();
				return;
			}
			lastGeneration = currentGeneration;
			currentGeneration = new Population( lastGeneration );
		}

		numGenerations++;

		currentGeneration.testCreatures();
		currentGeneration.setGenerationIndex( previousGenerations.size() );
		currentGeneration.nameCreatures();

		previousGenerations.add( currentGeneration );

		

		gui.updateGenerationLabel();

	}

	public void step() {
		
		step(1);

	}

	public void step( int numSteps ) {

		for( int i = 0; i < numSteps; i++ ) {

			if( isSimulationComplete ) {

				break;

			}

			else {

				nextGeneration();

			}

		}
	
	}

	public boolean isSimulationComplete() {
		
		return isSimulationComplete;

	}

	private void displayResults(){

		System.out.println("Done!");

		if ( numGenerations == maxNumGenerations ) {

			System.out.println ( "Generation limit reached." );

		}

		if ( currentGeneration.getContainsPerfectCreature() ) {

			System.out.println ( "Desired Creature achieved. Here it is! :");
			currentGeneration.getTopCreature().printTraits();
			System.out.println ( "Compared to what we wanted : " );
			desiredCreature.printTraits();
			System.out.println ( "It only took " + numGenerations + " generations." );

		}

	}

	public boolean testCreature( Creature c ) {

		boolean isPerfectSolution;

		int fitness = c.getGenome().numMatchingGenes( desiredCreature.getGenome() );
		c.setFitness( fitness );

		boolean allGenesMatch = ( fitness == c.getGenome().getNumGenes() );

		isPerfectSolution = allGenesMatch;

		return isPerfectSolution;

	}

}

