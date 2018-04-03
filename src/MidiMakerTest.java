public class MidiMakerTest {

	public static void main ( String[] args ) {

		//MidiMaker midiMaker = new MidiMaker();
 		//	'C', 'd', 'D', 'e', 'E', 'F', 'g', 'G', 'a', 'A', 'b', 'B', 'r'
		String songString = "AAABCCBBAAGGAABBCCBBBBABCCBBrrAAGGrr";
		//String songString =   "CdDeEFgGaAbBr";
		//String songString = "AB";
		//String songString = "BAAB";

		char[] song = songString.toCharArray();



		MidiMaker.INSTANCE.makeSong( song );
	}

}

