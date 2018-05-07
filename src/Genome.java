import java.util.HashMap;

public abstract class Genome {

	protected Creature owner;
	protected boolean[] data;

	protected static int numGenes = 36;
	protected static int bitsPerGene = 3;

	protected HashMap<boolean[], Note> traitMap;

	public Genome ( Creature owner ) {

		this.owner = owner;
		data = new boolean[numGenes * bitsPerGene];

	}

	public void scramble () {

			for (int i = 0; i < data.length; i++) {

					boolean b = owner.getSimulation().getRandomBoolean();
					data[i] = b;

			}
	}

	public void setData (boolean[] newData) {

		try {
			
			System.arraycopy(newData, 0, this.data, 0, this.data.length);
		
		} catch( IndexOutOfBoundsException e ) {

			e.printStackTrace();
			System.out.println( "Can't copy data, mismatched dna length." );
				
		} catch( Exception e ) {

			e.printStackTrace();

		}

	}

	public void setData ( Trait[] traits) {

		boolean[] newData = new boolean[this.data.length];

		int binaryIndex = 0;

		for ( Trait trait: traits) {



			try {
				//System.arraycopy( data, 0, newData, binaryIndex, data.length );
				System.arraycopy( trait.getData(), 0, newData, binaryIndex, trait.size());

			} catch( Exception e ) {

				e.printStackTrace();

			}

			binaryIndex += bitsPerGene;

		}

		setData( newData );

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

	public char[] getDataAsChars() {

		String notes = new String();

		for( int i = 0; i < data.length; i += bitsPerGene ) {

			boolean[] note = { data[i], data[i+1], data[i+2] };
			notes += getTrait(note).asChar();

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

		char[] notes = getDataAsChars();
		System.out.println( String.valueOf( notes ) );

	}

	public void printData () {

		System.out.println( DataConverter.getBinaryString( data ) );

	}

	public Trait getTrait(boolean[] gene) {
		return new Trait(gene);
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

	public boolean[] getGene( Trait trait ) {

		return trait.getData();

	}
}


