public interface GenomeSequencer {

	public static final char[] Notes = { 'r', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};

	public char getNote( boolean[] gene );
	public boolean[] getGene( char note );

}
