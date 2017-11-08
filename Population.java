import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private Simulation mSimulation;
	private ArrayList< Creature > mCreatures;
	private boolean mContainsPerfectCreature = false;


	public Population (Simulation sim, int size) {

		mSimulation = sim;
		mCreatures = new ArrayList< Creature >();


		for (int i = 0; i < size; i++) {
			
			Creature c = new Creature(mSimulation);
			mCreatures.add(c);

		}


	}

	public Population (Population parentGeneration) {

		mSimulation = parentGeneration.getSimulation();
		mCreatures = new ArrayList< Creature >();

		int numPairs = parentGeneration.getSize() / 2;
		for (int i = 0; i < numPairs; i++) {
			
			Creature mateOne = mSimulation.chooseCreature( parentGeneration );
			Creature mateTwo = mSimulation.chooseCreature( parentGeneration );

			Creature childOne = new Creature( this.mSimulation );
			Creature childTwo = new Creature( this.mSimulation );

			mateOne.crossover( mateTwo, childOne, childTwo );

			mCreatures.add( childOne );
			mCreatures.add( childTwo );
		}

	}

	public int getSize () { 
		
		return mCreatures.size(); 

	}

	public boolean getContainsPerfectCreature () {

		return mContainsPerfectCreature;

	}

	public Creature getTopCreature () {

		return mCreatures.get(0);

	}

	public Simulation getSimulation () {
		
		return mSimulation;

	}

	public ArrayList< Creature > getCreatures () {

		return mCreatures;

	}

	public void testCreatures () {

		for (Creature c : this.mCreatures) {

			boolean perfectCreature = mSimulation.testCreature( c );

			if ( perfectCreature ) {

				mContainsPerfectCreature = true;

			}

		}

		/*Sort Population by Fitness*/
		Collections.sort( this.mCreatures );
		printFitnesses();
		//printStatistics();

	}

	public void printFitnesses () {

		System.out.println( "These are the population's fitness levels: " );
		for (Creature c : this.mCreatures) {

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
		for (Creature c : this.mCreatures) {
			totalFitness += c.getFitness();
			if ( c.getFitness() > maxFitness ) {
				maxFitness = c.getFitness();
			}
		}
		int averageFitness = (int) (totalFitness / this.mCreatures.size());
		System.out.println( "Average fitness of this generation is: " + averageFitness );
		System.out.println( "Maximum fitness is: " + maxFitness );
	}

	

}
