import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private Simulation simulation;

	private ArrayList< Creature > population;

	public Population (Simulation sim, int size) {

		simulation = sim;

		population = new ArrayList< Creature >();


		for (int i = 0; i < size; i++) {
			
			Creature c = new Creature(simulation);
			population.add(c);

		}


	}

	public Population (Population parentGeneration) {

		simulation = parentGeneration.simulation;

	}

	public int getSize () {

		return population.size();

	}

	public void testCreatures () {

		System.out.println(this.population.size());

		for (Creature c : this.population) {

			simulation.testCreature( c );

		}

		/*Sort Population by Fitness*/
		Collections.sort( this.population );
		printFitnesses();

	}

	public void printFitnesses () {

		for (Creature c : this.population) {

			System.out.println( c.getFitness() );

		}
	}

	

}
