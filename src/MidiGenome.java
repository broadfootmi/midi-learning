import java.util.HashMap;

public class MidiGenome extends Genome{

    static{
        numGenes = 36;
        bitsPerGene = 3;
    }

    public static final char[] Notes = { 'C', 'D', 'E', 'F', 'G', 'A', 'B', 'r' };

    public MidiGenome(Creature owner) {
        super(owner);
        traitMap = new HashMap<boolean[], Note>(Notes.length);

        for(int i = 0; i < Notes.length; i++){
            String binaryString = Integer.toBinaryString(i);
            boolean[] bits = DataConverter.getBooleanArray(binaryString, bitsPerGene);

            traitMap.put(bits, new Note(bits));
        }
    }

    @Override
    public Trait getTrait(boolean[] gene){

        /*
		String binaryString = DataConverter.getBinaryString( gene, bitsPerGene );

		int base = 2;
		int geneInt = Integer.parseInt( binaryString, base );
		*/

		return new Note(gene);

    }

    public static boolean[] charToBinary(char note){
        boolean[] output = new boolean[3];
        for(int i = 0; i < Notes.length; i++){
            if(note == Notes[i]){
                output = DataConverter.getBooleanArray(Integer.toBinaryString(i),3);
            }
        }
        return output;

    }

}
