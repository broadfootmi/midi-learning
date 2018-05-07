public abstract class Creature implements Comparable<Creature> {

	protected Simulation simulation;

	protected Population generation;
	protected boolean hasPopulation = true;

	protected Genome genome;
	protected boolean isFitnessDetermined = false;

	protected String name;

	protected int fitness;
	protected boolean isMutated = false;

	protected Creature () {

		//genome = new Genome( this );
		initGenome();

	}

	protected void initGenome() {
	    //genome = new MidiGenome(this);
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

	public void setGenome ( Trait[] newTraits ) {

		genome.setData( newTraits );

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

		int crossoverIndex = simulation.getRandomInteger() % genome.getLength();

		for ( int i = 0; i <= crossoverIndex; i++ ) { //Set Bits Before Crossover Point
			
			childOne.getGenome().setDataBit( i, genome.getDataBit( i ) );
			childTwo.getGenome().setDataBit( i, partner.getGenome().getDataBit( i ) );
		} 

		if ( !genome.isLastBit( crossoverIndex ) ) {

			for ( int i = crossoverIndex + 1; i < genome.getLength(); i++ ) { //Set Bits After Crossover Point

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

