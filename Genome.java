public class Genome {

	public static final char[] Notes = { 'C', 'D', 'E', 'F', 'G', 'A', 'B', 'r' };

	private Creature owner;

	private boolean[] data;
	private int bitsPerGene = 3;

	public Genome ( Creature owner, int length ) {

		this.owner = owner;
		data = new boolean[length];

	}

	public void scramble () {

		for (int i = 0; i < this.data.length; i++) {

			boolean b = this.owner.getSimulation().getRandomBoolean();
			this.data[i] = b;

		}

	}

	public void setData ( boolean[] newData ) {

		if (newData.length == this.data.length) {
			System.arraycopy( newData, 0, this.data, 0, this.data.length);
		}

		else {
			System.out.println("Wrong genome for this creature!");
		}

	}

	public void setData ( char[] newTraits ) {

		boolean[] newData = new boolean[this.data.length];

		int binaryIndex = 0;

		for ( char note : newTraits ) {

			boolean[] gene = new boolean[this.bitsPerGene];
			gene = getGene( note );

			System.arraycopy( gene, 0, newData, binaryIndex, gene.length );

			binaryIndex += this.bitsPerGene;

		}

		this.setData( newData );

	}

	public void setDataBit ( int index, boolean value ) {

		this.data[index] = value;

	}

	public boolean getDataBit ( int index ) {

		return this.data[index];

	}

	public boolean[] getData () {

		return this.data;

	}

	public char[] getDataAsNotes () {

		String notes = new String();

		for( int i = 0; i < data.length; i += bitsPerGene ) {

			boolean[] note = { data[i], data[i+1], data[i+2] };
			notes += getNote( note );

		}

		return notes.toCharArray();

	}

	public int getLength () {

		return this.data.length;

	}

	public int getBitsPerGene() {

		return this.bitsPerGene;

	}

	public void printTraits() {

		char[] notes = getDataAsNotes();
		System.out.println( String.valueOf( notes ) );

	}

	public void printData () {

		for (int i = 0; i < this.data.length; i++) {

			if ( this.data[i] == true ) {
				System.out.print(1);
			}

			else {
				System.out.print(0);
			}

		}
		
		System.out.println();

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

}


