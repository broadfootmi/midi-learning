public class Creature implements Comparable<Creature> {

	private Simulation simulation;

	private Population generation;
	private boolean hasPopulation = true;

	private Genome genome;
	private int genomeStartingLength = 108;
	private boolean isFitnessDetermined = false;

	private String name;

	private int fitness;
	private boolean isMutated = false;

	private Creature () {

		this.genome = new Genome( this, this.genomeStartingLength );

	}

	public Creature ( Simulation sim ) { //Lone Creature

		this();
		this.simulation = sim;
		this.genome.scramble();
		this.hasPopulation = false;

	}

	public Creature ( Population pop ) { //Random Creature

		this();
		this.generation = pop;
		this.simulation = this.generation.getSimulation(); 

		this.genome.scramble();

	}

	public Creature ( Population pop, boolean[] genome ) { //Child Creature

		this( pop );
		this.setGenome( genome );

	}

	public Creature ( Population pop, char[] genome ) {

		this( pop );
		this.setGenome( genome );
	}

	public void setFitness (int fit) {

		if (!this.isFitnessDetermined) {

			this.fitness = fit;
			this.isFitnessDetermined = true;

		}

	}

	public int getFitness () { return this.fitness; }

	public String getName () { 

		try {

			String name = this.name;

		} catch( NullPointerException e ) {

			e.printStackTrace();
			String name = "No Name";

		} finally {

			return name;

		}

	}

	public int compareTo( Creature otherCreature ) {

		return ( otherCreature.getFitness() - this.getFitness() );

	}

	public Simulation getSimulation () {

		return this.simulation;

	}

	public boolean getIsMutated () {

		return this.isMutated;

	}

	public void printTraits () {

		this.genome.printTraits();

	}

	public void printGenome () {

		this.genome.printData();

	}

	public void setGenome ( Genome newGenome ) {

		this.genome = newGenome;

	}

	public void setGenome ( boolean[] newData ) {

		this.genome.setData( newData );

	}

	public void setGenome ( char[] newTraits ) {

		this.genome.setData( newTraits );

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

		return this.genome;

	}

	public void nameCreature () {

		if( this.hasPopulation ) {

			try {

				String generationIndex = String.format( "%03d", this.generation.getGenerationIndex() + 1 ); //Indexing starts at 1!!! (For naming purposes only)
				String creatureIndex = String.format( "%03d", this.generation.getCreatureIndex( this ) + 1 );
				String fitness = String.format( "%02d", this.fitness );

				this.name = "g" + generationIndex + "c" + creatureIndex + "f" + fitness; 

			} catch( NullPointerException e ) {

				e.printStackTrace();
				System.out.println("Could not name creature - missing generation or fitness info.");
				this.name = "null";

			}

			if( this.isMutated ) {

				this.name += "m";

			}

		}

		else {

			this.name = "solution";

		}

	}

	public void crossover (Creature partner, Creature childOne, Creature childTwo) {

		int crossoverPoint = this.simulation.getRandomInteger() % this.genome.getLength();
		Genome partnerGenome = partner.getGenome();

		Genome childOneGenome = new Genome( childOne, this.genome.getLength() );
		Genome childTwoGenome = new Genome( childTwo, this.genome.getLength() ); 
		
		for ( int i = 0; i <= crossoverPoint; i++ ) {
			
			childOneGenome.setDataBit( i, this.genome.getDataBit( i ) );
			childTwoGenome.setDataBit( i, partnerGenome.getDataBit( i ) );
		}

		if ( crossoverPoint != (this.genome.getLength() - 1) ) {

			for ( int i = crossoverPoint + 1; i < this.genome.getLength(); i++ ) {

				childOneGenome.setDataBit( i, partnerGenome.getDataBit( i ) );
				childTwoGenome.setDataBit( i, this.genome.getDataBit( i ) );

			}

		}

		childOne.setGenome( childOneGenome );
		childTwo.setGenome( childTwoGenome );

		childOne.mutate();
		childTwo.mutate();

	}

	public void mutate () {

		int range = (int) ( 1 / this.simulation.getMutationRate() );
		int randomNumber;

		for ( int i = 0; i < this.genome.getLength(); i++ ) {

			randomNumber = this.simulation.getRandomInteger() % range;

			if ( randomNumber == 0 ) { // 1 in range chance

				this.isMutated = true;
				this.genome.setDataBit( i, !this.genome.getDataBit( i ) );

			}
		}

		
	}
}

