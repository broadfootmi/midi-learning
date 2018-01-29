public class Creature implements Comparable<Creature> {

	private Simulation simulation;

	private Population generation;
	private boolean hasPopulation = true;

	private Genome genome;
	private boolean isFitnessDetermined = false;

	private String name;

	private int fitness;
	private boolean isMutated = false;

	private Creature () {

		genome = new Genome( this );

	}

	public Creature ( Simulation simulation ) { //Lone Creature

		this();

		this.simulation = simulation;
		genome.scramble();
		hasPopulation = false;

	}

	public Creature ( Population population ) { //Random Creature

		this();

		generation = population;
		simulation = generation.getSimulation(); 

		genome.scramble();

	}

	public Creature ( Population population, boolean[] genome ) { //Child Creature

		this( population );

		setGenome( genome );

	}

	public Creature ( Population population, char[] genome ) {

		this( population );

		setGenome( genome );

	}

	public void setFitness ( int fitness ) {

		if (!isFitnessDetermined) {

			this.fitness = fitness;
			isFitnessDetermined = true;

		}

	}

	public int getFitness () { 
		
		return fitness; 

	}

	public String getName () { 

		return name;

	}

	public int compareTo( Creature otherCreature ) {

		return ( otherCreature.getFitness() - getFitness() );

	}

	public Simulation getSimulation () {

		return simulation;

	}

	public boolean getIsMutated () {

		return isMutated;

	}

	public void printTraits () {

		genome.printTraits();

	}

	public void printGenome () {

		genome.printData();

	}

	public void setGenome ( Genome genome ) {

		this.genome = genome;

	}

	public void setGenome ( boolean[] newData ) {

		genome.setData( newData );

	}

	public void setGenome ( char[] newTraits ) {

		genome.setData( newTraits );

	}

	public String getSong () {

		String song = new String();

		char[] notes = genome.getDataAsNotes();

		for( char note : notes ) {

			song += note;

		}

		return song;
	}

	public Genome getGenome () {

		return genome;

	}

	public void nameCreature () {

		if( hasPopulation ) {

			try {

				String generationIndex = String.format( "%03d", generation.getGenerationIndex() + 1 ); //Named Indexes start at 1
				String creatureIndex = String.format( "%03d", generation.getCreatureIndex( this ) + 1 );
				String fitness = String.format( "%02d", this.fitness );

				name = "g" + generationIndex + "c" + creatureIndex + "f" + fitness; 

			if( isMutated ) {

				name += "m";

			}

			} catch( Exception e ) {

				e.printStackTrace();
				System.out.println( " Could not format creature name - missing info? " );
				name = "null";

			}


		}

		else {

			name = "solution";

		}

	}

	public void crossover (Creature partner, Creature childOne, Creature childTwo) {

		int crossoverPoint = simulation.getRandomInteger() % genome.getLength();

		for ( int i = 0; i <= crossoverPoint; i++ ) { //Set Bits Before Crossover Point
			
			childOne.getGenome().setDataBit( i, genome.getDataBit( i ) );
			childTwo.getGenome().setDataBit( i, partner.getGenome().getDataBit( i ) );
		} 

		if ( !genome.isLastBit( crossoverPoint ) ) {

			for ( int i = crossoverPoint + 1; i < genome.getLength(); i++ ) { //Set Bits After Crossover Point

				childOne.getGenome().setDataBit( i, partner.getGenome().getDataBit( i ) );
				childTwo.getGenome().setDataBit( i, genome.getDataBit( i ) );

			}

		}

		childOne.mutate();
		childTwo.mutate();

	}

	public void mutate () {

		int range = (int) ( 1 / simulation.getMutationRate() );
		int randomNumber;

		for ( int i = 0; i < genome.getLength(); i++ ) {

			randomNumber = simulation.getRandomInteger() % range;

			if ( randomNumber == 0 ) { // 1 in range chance

				isMutated = true;
				genome.setDataBit( i, !genome.getDataBit( i ) );

			}

		}

		
	}
}

