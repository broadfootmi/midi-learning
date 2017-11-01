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

		boolean[] desiredGenome = {
			/*
			
			1,0,0, 1,0,0, 1,0,0, 1,0,1, 1,1,0, 1,1,0, //AAA BCC
			1,0,1, 1,0,1, 1,0,0, 1,0,0, 0,1,1, 0,1,1, //BBA AGG
			1,0,0, 1,0,0, 1,0,1, 1,0,1, 1,1,0, 1,1,0, // AAB BCC
			1,0,1, 1,0,1, 1,0,1, 1,0,1, 1,0,0, 1,0,1, // BBB BAB
			1,1,0, 1,1,0, 1,0,1, 1,0,1, 0,0,0, 0,0,0, // CCB BRR
			1,0,0, 1,0,0, 0,1,1, 0,1,1, 0,0,0, 0,0,0 //AAG GRR

			*/
		
		};

		desiredCreature = new Creature( this );
		desiredCreature.setGenome( desiredGenome );
	


	}	

	public void start () {
		/*(Test Code)*/
		Creature crea = new Creature( this );
		crea.printGenome();
		crea.printNotes();
		//boolean[] testNote = {false, true, true};
		//crea.getNote(testNote);


		/*Initialize first Population*/
		//currentGeneration = new Population();
		this.numGenerations++;

		/*Evaluate Fitnesses*/
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

	


}
