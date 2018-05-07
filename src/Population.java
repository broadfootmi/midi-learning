import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

public class Population<CreatureType extends Creature> {

	private Class<CreatureType> creatureTypeClass = (Class<CreatureType>) MidiCreature.class;

	private Simulation simulation;
	private ArrayList< Creature > creatures;
	private boolean containsPerfectCreature = false;
	private int generationIndex;


	private Population () {

		this.creatures = new ArrayList< Creature >();

	}

	public Population ( Simulation simulation, int size ) {

		this();

		this.simulation = simulation;

		for ( int i = 0; i < size; i++ ) {
			creatures.add(getCreatureInstance());
		}

	}

	private Creature getCreatureInstance(){
			Creature c = null;
			try {
				c = creatureTypeClass.getDeclaredConstructor(Population.class).newInstance(this);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			return c;
	}

	public Population ( Population parentGeneration ) {

		this();

		simulation = parentGeneration.getSimulation();

		int numPairs = parentGeneration.getSize() / 2;
		for ( int i = 0; i < numPairs; i++ ) {
			
			Creature mateOne = simulation.chooseCreature( parentGeneration );
			Creature mateTwo = simulation.chooseCreature( parentGeneration );

			Creature childOne = getCreatureInstance();
			Creature childTwo = getCreatureInstance();

			mateOne.crossover( mateTwo, childOne, childTwo );

			creatures.add( childOne );
			creatures.add( childTwo );
		}

	}

	public int getSize () { 
		
		return creatures.size(); 

	}

	public Creature getTopCreature () {

		return creatures.get(0);

	}

	public boolean getContainsPerfectCreature () {

		return containsPerfectCreature;

	}

	public Simulation getSimulation () {
		
		return simulation;

	}

	public ArrayList< Creature > getCreatures () {

		return creatures;

	}

	public int getCreatureIndex( Creature c ) {

		return creatures.indexOf( c );

	}

	public int getGenerationIndex () {

		return generationIndex;

	}
	
	public void setGenerationIndex ( int index ) {

		generationIndex = index;

	}

	public void testCreatures () {

		for ( Creature c : creatures ) {

			boolean perfectCreature = simulation.testCreature( c );

			if ( perfectCreature ) {

				containsPerfectCreature = true;

			}

		}

		Collections.sort( creatures ); //By Fitness

	}

	public void nameCreatures () {

		for (Creature c : creatures) {

			c.nameCreature();

		}

	}

	private void printNames () {

		for( Creature c : creatures ) {

			System.out.print( c.getName() + " " );

		}

		System.out.println();

	}

	public void printFitnesses () {

		System.out.println( "These are the population's fitness levels: " );

		for (Creature c : creatures) {

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

		for (Creature c : creatures) {

			totalFitness += c.getFitness();

			if ( c.getFitness() > maxFitness ) {

				maxFitness = c.getFitness();

			}

		}

		int averageFitness = totalFitness / creatures.size();
		System.out.println( "Average fitness of this generation is: " + averageFitness );
		System.out.println( "Maximum fitness is: " + maxFitness );

	}

}

