public class Creature implements GenomeSequencer, Comparable<Creature> {

	private Simulation simulation;
	private Population residence;

	private boolean[] genome;
	private int bitsPerGene = 3;
	private int genomeStartingLength = 108;
	private int fitness;
	private boolean isMutated = false;
	private boolean isFitnessDetermined = false;

	public Creature ( Simulation sim ) {

		this.simulation = sim;

		this.genome = new boolean[this.genomeStartingLength];
		scrambleGenome();

	}

	public Creature ( Population pop ) {


		this.residence = pop;
		this.simulation = this.residence.getSimulation(); 

		this.genome = new boolean[this.genomeStartingLength];
		scrambleGenome();

	}

	public Creature ( Population pop, boolean[] genome ) {

		this.residence = pop;
		this.simulation = this.residence.getSimulation();

		this.genome = new boolean[this.genomeStartingLength];
		setGenome( genome );
	}

	public Creature ( Population pop, char[] genome ) {

		this.residence = pop;
		this.simulation = this.residence.getSimulation();

		this.genome = new boolean[this.genomeStartingLength];
		setGenome( genome );
	}

	public void setFitness (int fit) {

		if (!this.isFitnessDetermined) {

			this.fitness = fit;
			this.isFitnessDetermined = true;

		}

	}

	public int getFitness () { return this.fitness; }

	public int compareTo( Creature otherCreature ) {

		return ( otherCreature.getFitness() - this.getFitness() );

	}

	public int getGenomeLength() {

		return this.genome.length;

	}

	public boolean[] getGenome() {

		return this.genome;

	}

	public int getBitsPerGene() {

		return this.bitsPerGene;

	}

	public boolean getIsMutated() {

		return this.isMutated;

	}

	public void printGenome () {

		for (int i = 0; i < this.genome.length; i++) {

			if ( this.genome[i] == true ) {
				System.out.print(1);
			}

			else {
				System.out.print(0);
			}

		}
		
		System.out.println();

	}

	public void setGenome ( boolean[] newGenome ) {

		if (newGenome.length == this.genome.length) {
			System.arraycopy( newGenome, 0, this.genome, 0, this.genome.length);
		}

		else {
			System.out.println("Wrong genome for this creature!");
		}

	}

	public void setGenome ( char[] newTraits ) {

		boolean[] newGenome = new boolean[this.genome.length];

		int binaryIndex = 0;

		for (char note : newTraits ) {

			boolean[] gene = new boolean[this.bitsPerGene];
			gene = getGene( note );

			System.arraycopy( gene, 0, newGenome, binaryIndex, gene.length);

			binaryIndex += this.bitsPerGene;

		}

		setGenome( newGenome );

	}

	public void printNotes () {

		String notes = new String();
		for(int i = 0; i < this.genome.length; i += this.bitsPerGene ) {

			boolean[] note = { this.genome[i], this.genome[i+1], this.genome[i+2] };
			notes += getNote( note );

		}
		System.out.println( notes );

	}

	public char getNote( boolean[] gene ) {
		/*Gene -> Binary String -> Char*/
		String binary = new String();

	       	for( int i = 0; i < gene.length; i++ ) {

			if( gene[i] ) {
				binary += "1";
			}

			else {
				binary += "0";
			}
		}

		int base = 2;
		int geneInt = Integer.parseInt( binary, base );

		return Notes[ geneInt ];
	}


	public boolean[] getGene( char note ) {
		/*Char -> Binary String -> Gene*/
		int geneInt = 0;
		boolean[] gene = new boolean[this.bitsPerGene];

		for (int i = 0; i < Notes.length; i++) {

			if (Notes[i] == note) {
				geneInt = i;
				break;
			}

		}

		String binary = Integer.toBinaryString( geneInt );

		if (binary.length() < this.bitsPerGene) { //Pad bits

			int padBits = Math.abs( binary.length() - this.bitsPerGene );


			String pad = new String();
			for (int i = 0; i < padBits; i++ ) {

				pad = pad.concat("0");
			
			}

			binary = pad + binary;
		}

		binary.substring( binary.length() - this.bitsPerGene ); //Cut bits

		
		for (int i = 0; i < this.bitsPerGene; i++) {

			if (binary.charAt(i) == '1') {
				gene[i] = true; 
			}

			else {
				gene[i] = false;
			}

		}

		return gene;

	}

	private void scrambleGenome () {
		
		for (int i = 0; i < this.genome.length; i++) {
			boolean b = this.simulation.getRandomBoolean();
			this.genome[i] = b;
		}

	}

	public void crossover (Creature partner, Creature childOne, Creature childTwo) {

		int crossoverPoint = this.simulation.getRandomInteger() % this.genome.length;
		boolean[] partnerGenome = partner.getGenome();

		boolean[] childOneGenome = new boolean[this.genome.length];
		boolean[] childTwoGenome = new boolean[this.genome.length];
		
		for ( int i = 0; i <= crossoverPoint; i++ ) {
			
			childOneGenome[i] = this.genome[i];	
			childTwoGenome[i] = partnerGenome[i];
		}

		if ( crossoverPoint != (this.genome.length - 1) ) {

			for ( int i = crossoverPoint + 1; i < this.genome.length; i++ ) {

				childOneGenome[i] = partnerGenome[i];
				childTwoGenome[i] = this.genome[i];

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

		for ( int i = 0; i < this.genome.length; i++ ) {

			randomNumber = this.simulation.getRandomInteger() % range;

			if ( randomNumber == 0 ) {

				this.isMutated = true;
				this.genome[i] = !this.genome[i];

			}
		}

		
	}
}










