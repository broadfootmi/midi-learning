public class Genome {

	public static final char[] Notes = { 'C', 'D', 'E', 'F', 'G', 'A', 'B', 'r' };

	private Creature owner;

	private boolean[] data;

	private int numGenes = 36;
	private int bitsPerGene = 3;

	public Genome ( Creature owner ) {

		this.owner = owner;
		data = new boolean[numGenes * bitsPerGene];

	}

	public void scramble () {

		for ( int i = 0; i < data.length; i++ ) {

			boolean b = owner.getSimulation().getRandomBoolean();
			data[i] = b;

		}

	}

	public void setData ( boolean[] data ) {

		try {
			
			System.arraycopy( data, 0, this.data, 0, this.data.length);
		
		} catch( IndexOutOfBoundsException e ) {

			e.printStackTrace();
			System.out.println( "Can't copy data, mismatched dna length." );
				
		} catch( Exception e ) {

			e.printStackTrace();

		}

	}

	public void setData ( char[] notes ) {

		boolean[] data = new boolean[this.data.length];

		int binaryIndex = 0;

		for ( char note : notes ) {

			boolean[] gene = new boolean[bitsPerGene];
			gene = getGene( note );

			try {

				System.arraycopy( gene, 0, data, binaryIndex, gene.length );

			} catch( Exception e ) {

				e.printStackTrace();

			}

			binaryIndex += bitsPerGene;

		}

		setData( data );

	}

	public void setDataBit ( int index, boolean value ) {

		this.data[index] = value;

	}

	public boolean getDataBit ( int index ) {

		return data[index];

	}

	public boolean[] getData () {

		return data;

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

		return data.length;

	}

	public int getNumGenes () {
	
		return numGenes;

	}

	public boolean isLastBit( int index ) {

		return ( index == data.length - 1 );

	}

	public int getBitsPerGene() {

		return bitsPerGene;

	}

	public void printTraits() {

		char[] notes = getDataAsNotes();
		System.out.println( String.valueOf( notes ) );

	}

	public void printData () {

		System.out.println( DataConverter.getBinaryString( data ) );

	}

	public char getNote( boolean[] gene ) {

		String binaryString = DataConverter.getBinaryString( gene, bitsPerGene ); 

		int base = 2;
		int geneInt = Integer.parseInt( binaryString, base );

		return Notes[ geneInt ];

	}

	public int numMatchingGenes( Genome otherGenome ) {

		int numMatchingGenes = 0;
		boolean[] otherData = otherGenome.getData();

		for ( int i = 0; i < data.length; i += bitsPerGene ) { //For Genes in Genome

			boolean geneMatches = true;

			for ( int j = 0; j < bitsPerGene; j++ ) { //For Bits in Gene

				if ( data[i+j] != otherData[i+j] ) {

					geneMatches = false;
					break;

				}

			}

			if( geneMatches ) {
				numMatchingGenes++;
			}

		}

		return numMatchingGenes;

	}

	public boolean[] getGene( char note ) {

		boolean[] gene = new boolean[bitsPerGene];

		int geneInt = 0;

		for (int i = 0; i < Notes.length; i++) {

			if (Notes[i] == note) {

				geneInt = i;
				break;

			}

		}

		String binaryString = Integer.toBinaryString( geneInt );

		gene = DataConverter.getBooleanArray( binaryString, bitsPerGene );

		return gene;

	}

}


