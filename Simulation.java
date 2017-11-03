import java.util.Random;
import java.util.ArrayList;

public class Simulation {

	/*Settings*/
	private double mutationRate;
	private double populationSize;
	private int maxNumGenerations;
	private Creature desiredCreature;

	/*State*/
	private int numGenerations;
	private Population lastGeneration;
	private Population currentGeneration;

	/*Data*/
	private ArrayList< Population > previousGenerations;

	/*RNG*/
	private Random rng;


	public Simulation() {

		/*Initialize Settings*/
		mutationRate = 0.001;

		populationSize = 200;
		maxNumGenerations = 3;
		/*Initialize State*/
		numGenerations = 0;

		/*Init RNG*/
		rng = new Random();

		/*Init Solution*/
		String desiredGenomeString = "AAABCCBBAAGGAABBCCBBBBABCCBBrrAAGGrr";
		char[] desiredGenome = desiredGenomeString.toCharArray();

		desiredCreature = new Creature( this );
		desiredCreature.setGenome( desiredGenome );

		System.out.println("This is the creature we want:");

		desiredCreature.printGenome();
		desiredCreature.printNotes();

	}	

	public void start () {
		/*(Test Code)*/

		/*Initialize first Population*/
		int populationSize = 200;
		currentGeneration = new Population(this, populationSize);
		this.numGenerations++;

		/*Evaluate Fitnesses*/
		currentGeneration.testCreatures();

		/*nextGeneration()*/
	}

	public boolean getRandomBoolean () {

		return rng.nextBoolean();

	}

	private void nextGeneration() {

		/*Save Previous Generation*/
		/*Create New Population using Previous's fitness data*/
		/*Evaluate Fitness of current generation*/
		/*If (solution reached OR numGens < maxNumGens)
		 * nextGeneration()*/
	}

	public void testCreature( Creature c ) {

		/*Compare bits with desired creature*/
		boolean[] genomeUnderTest = c.getGenome();
		boolean[] desiredGenome = this.desiredCreature.getGenome();

		int fitness = 0;
		int index = 0;
		for (boolean b : genomeUnderTest) {
			if (b == desiredGenome[index]) {
				fitness++;
			}
			index++;
		}

		c.setFitness( fitness );
		

	}

}
