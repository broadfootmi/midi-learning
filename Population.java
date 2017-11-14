import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private Simulation simulation;
	private ArrayList< Creature > creatures;
	private boolean containsPerfectCreature = false;


	public Population (Simulation sim, int size) {

		this.simulation = sim;
		this.creatures = new ArrayList< Creature >();


		for (int i = 0; i < size; i++) {
			
			Creature c = new Creature(this);
			this.creatures.add(c);

		}


	}

	public Population (Population parentGeneration) {

		this.simulation = parentGeneration.getSimulation();
		this.creatures = new ArrayList< Creature >();

		int numPairs = parentGeneration.getSize() / 2;
		for (int i = 0; i < numPairs; i++) {
			
			Creature mateOne = this.simulation.chooseCreature( parentGeneration );
			Creature mateTwo = this.simulation.chooseCreature( parentGeneration );

			Creature childOne = new Creature( this );
			Creature childTwo = new Creature( this );

			mateOne.crossover( mateTwo, childOne, childTwo );

			this.creatures.add( childOne );
			this.creatures.add( childTwo );
		}

	}

	public int getSize () { 
		
		return this.creatures.size(); 

	}

	public Creature getTopCreature () {

		return this.creatures.get(0);

	}

	public boolean getContainsPerfectCreature () {

		return this.containsPerfectCreature;

	}

	public Simulation getSimulation () {
		
		return this.simulation;

	}

	public ArrayList< Creature > getCreatures () {

		return this.creatures;

	}

	public void testCreatures () {

		for (Creature c : this.creatures) {

			boolean perfectCreature = this.simulation.testCreature( c );

			if ( perfectCreature ) {

				this.containsPerfectCreature = true;

			}

		}

		/*Sort Population by Fitness*/
		Collections.sort( this.creatures );
		printFitnesses();
		//printStatistics();

	}

	public void printFitnesses () {

		System.out.println( "These are the population's fitness levels: " );
		for (Creature c : this.creatures) {

			String info = new String();
			if ( c.getIsMutated() ) {
				
				info += ( "m" + c.getFitness() + " ");

			}

			else {

				info += c.getFitness() + " ";

			}



			System.out.print( info );

		}

		System.out.println();
	}

	public void printStatistics () {

		int totalFitness = 0;
		int maxFitness = 0;
		for (Creature c : this.creatures) {
			totalFitness += c.getFitness();
			if ( c.getFitness() > maxFitness ) {
				maxFitness = c.getFitness();
			}
		}
		int averageFitness = totalFitness / this.creatures.size();
		System.out.println( "Average fitness of this generation is: " + averageFitness );
		System.out.println( "Maximum fitness is: " + maxFitness );
	}

	

}
