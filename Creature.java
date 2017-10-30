public class Creature implements GenomeSequencer {

	/*Simulation*/
	private Simulation simulation;

	/*Genetic Info*/
	private boolean[] genome;
	private int bitsPerGene;
	private int fitness;

	public Creature (Simulation sim) {

		simulation = sim; 
		genome = new boolean[108];
		bitsPerGene = 3;

		scrambleGenome();

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

	public void printNotes () {

		String notes = new String();
		for(int i = 0; i < genome.length; i += bitsPerGene ) {

			boolean[] note = { genome[i], genome[i+1], genome[i+2] };
			notes += getNote( note );

		}
		System.out.println( notes );

	}

	public char getNote( boolean[] gene ) {

		String geneString = new String();
	       	for( int i = 0; i < gene.length; i++ ) {
			geneString += gene[i];
		}

		int base = 2;
		int geneInt = Integer.parseInt( geneString, base );

		System.out.println( Notes[ geneInt ] );	

		return Notes[ geneInt ];
	}

	private void scrambleGenome () {
		
		for (int i = 0; i < genome.length; i++) {
			boolean b = simulation.getRandomBoolean();
			genome[i] = b;
		}

	}
}
