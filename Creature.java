public class Creature implements GenomeSequencer, Comparable<Creature> {

	private Simulation simulation;

	private boolean[] genome;
	private int bitsPerGene;
	private int fitness;
	private boolean isFitnessDetermined;

	public Creature (Simulation sim) {

		simulation = sim; 
		genome = new boolean[108];
		bitsPerGene = 3;

		scrambleGenome();

	}

	public void setFitness (int fit) {

		if (!isFitnessDetermined) {

			this.fitness = fit;
			isFitnessDetermined = true;

		}

	}

	public int getFitness () { return fitness; }

	public int compareTo( Creature otherCreature ) {

		return ( otherCreature.getFitness() - this.getFitness() );

	}

	public int getGenomeLength() {

		return genome.length;

	}

	public boolean[] getGenome() {

		return genome;

	}

	public void printGenome () {

		for (int i = 0; i < genome.length; i++) {

			if ( genome[i] == true ) {
				System.out.print(1);
			}

			else {
				System.out.print(0);
			}

		}
		
		System.out.println();

	}

	public void setGenome ( boolean[] newGenome ) {

		if (newGenome.length == genome.length) {
			System.arraycopy( newGenome, 0, genome, 0, genome.length);
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
		for(int i = 0; i < genome.length; i += bitsPerGene ) {

			boolean[] note = { genome[i], genome[i+1], genome[i+2] };
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
		
		for (int i = 0; i < genome.length; i++) {
			boolean b = simulation.getRandomBoolean();
			genome[i] = b;
		}

	}
}
