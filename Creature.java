public class Creature implements GenomeSequencer, Comparable<Creature> {

	private Simulation mSimulation;

	private boolean[] mGenome;
	private int mBitsPerGene = 3;
	private int mGenomeLength = 108;
	private int mFitness;
	private boolean mIsMutated = false;
	private boolean mIsFitnessDetermined = false;

	public Creature (Simulation sim) {

		mSimulation = sim; 

		mGenome = new boolean[mGenomeLength];
		scrambleGenome();

	}

	public Creature (Simulation sim, boolean[] genome) {

		mSimulation = sim;

		mGenome = new boolean[mGenomeLength];
		setGenome( genome );
	}

	public Creature (Simulation sim, char[] genome) {

		mSimulation = sim;

		mGenome = new boolean[mGenomeLength];
		setGenome( genome );
	}

	public void setFitness (int fit) {

		if (!mIsFitnessDetermined) {

			this.mFitness = fit;
			mIsFitnessDetermined = true;

		}

	}

	public int getFitness () { return mFitness; }

	public int compareTo( Creature otherCreature ) {

		return ( otherCreature.getFitness() - this.getFitness() );

	}

	public int getGenomeLength() {

		return mGenome.length;

	}

	public boolean[] getGenome() {

		return mGenome;

	}

	public int getBitsPerGene() {

		return mBitsPerGene;

	}

	public boolean getIsMutated() {

		return mIsMutated;

	}

	public void printGenome () {

		for (int i = 0; i < mGenome.length; i++) {

			if ( mGenome[i] == true ) {
				System.out.print(1);
			}

			else {
				System.out.print(0);
			}

		}
		
		System.out.println();

	}

	public void setGenome ( boolean[] newGenome ) {

		if (newGenome.length == mGenome.length) {
			System.arraycopy( newGenome, 0, mGenome, 0, mGenome.length);
		}

		else {
			System.out.println("Wrong genome for this creature!");
		}

	}

	public void setGenome ( char[] newTraits ) {

		boolean[] newGenome = new boolean[this.mGenome.length];

		int binaryIndex = 0;

		for (char note : newTraits ) {

			boolean[] gene = new boolean[this.mBitsPerGene];
			gene = getGene( note );

			System.arraycopy( gene, 0, newGenome, binaryIndex, gene.length);

			binaryIndex += this.mBitsPerGene;

		}

		setGenome( newGenome );

	}

	public void printNotes () {

		String notes = new String();
		for(int i = 0; i < mGenome.length; i += mBitsPerGene ) {

			boolean[] note = { mGenome[i], mGenome[i+1], mGenome[i+2] };
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
		boolean[] gene = new boolean[this.mBitsPerGene];

		for (int i = 0; i < Notes.length; i++) {

			if (Notes[i] == note) {
				geneInt = i;
				break;
			}

		}

		String binary = Integer.toBinaryString( geneInt );

		if (binary.length() < this.mBitsPerGene) { //Pad bits

			int padBits = Math.abs( binary.length() - this.mBitsPerGene );


			String pad = new String();
			for (int i = 0; i < padBits; i++ ) {

				pad = pad.concat("0");
			
			}

			binary = pad + binary;
		}

		binary.substring( binary.length() - this.mBitsPerGene ); //Cut bits

		
		for (int i = 0; i < this.mBitsPerGene; i++) {

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
		
		for (int i = 0; i < mGenome.length; i++) {
			boolean b = mSimulation.getRandomBoolean();
			mGenome[i] = b;
		}

	}

	public void crossover (Creature partner, Creature childOne, Creature childTwo) {

		int crossoverPoint = this.mSimulation.getRandomInteger() % mGenomeLength;
		boolean[] partnerGenome = partner.getGenome();

		boolean[] childOneGenome = new boolean[this.mGenome.length];
		boolean[] childTwoGenome = new boolean[this.mGenome.length];
		
		for ( int i = 0; i <= crossoverPoint; i++ ) {
			
			childOneGenome[i] = this.mGenome[i];	
			childTwoGenome[i] = partnerGenome[i];
		}

		if ( crossoverPoint != (this.mGenome.length - 1) ) {

			for ( int i = crossoverPoint + 1; i < this.mGenome.length; i++ ) {

				childOneGenome[i] = partnerGenome[i];
				childTwoGenome[i] = this.mGenome[i];

			}

		}

		childOne.setGenome( childOneGenome );
		childTwo.setGenome( childTwoGenome );

		childOne.mutate();
		childTwo.mutate();

	}

	public void mutate () {

		int range = (int) ( 1 / mSimulation.getMutationRate() );
		int randomNumber = mSimulation.getRandomInteger() % range;

		if ( randomNumber == 0 ) {

			this.mIsMutated = true;

		}

		if ( this.mIsMutated ) {

			randomNumber = mSimulation.getRandomInteger() % mGenomeLength;

			System.out.println("Creature mutated!");
			//System.out.println("Changed bit " + randomNumber + " from " + mGenome[randomNumber] + " to " + !mGenome[randomNumber] ); 

			mGenome[randomNumber] = !mGenome[randomNumber];


		}
		
	}
}










