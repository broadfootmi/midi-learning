public class Creature implements Comparable<Creature> {

	private Simulation simulation;
	private Population residence;

	private Genome genome;
	private int genomeStartingLength = 108;
	private boolean isFitnessDetermined = false;

	private String name;

	private int generationIndex;
	private int fitness;
	private boolean isMutated = false;

	private Creature () {

		this.genome = new Genome( this, this.genomeStartingLength );

	}

	public Creature ( Simulation sim ) { 

		this();
		this.simulation = sim;
		this.genome.scramble();
	}

	public Creature ( Population pop ) {

		this();
		this.residence = pop;
		this.simulation = this.residence.getSimulation(); 

		this.genome.scramble();
		this.generationIndex = this.simulation.getCurrentGenerationIndex();

	}

	public Creature ( Population pop, boolean[] genome ) {

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
			this.nameCreature();
		}

	}

	public int getFitness () { return this.fitness; }

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

	public Genome getGenome () {

		return this.genome;

	}

	private void nameCreature () {

		try {

			this.name = "g" + this.generationIndex + "c" + this.residence.getCreatureIndex( this ) + "f" + this.fitness; 

			if( this.isMutated ) {

				this.name += "m";

			}

		} catch( NullPointerException e ) {

			e.printStackTrace();
			System.out.println("Could not name creature - missing generation or fitness info.");
			this.name = "null";

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

