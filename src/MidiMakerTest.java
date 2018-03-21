public class MidiMakerTest {

	public static void main ( String[] args ) {

		MidiMaker midiMaker = new MidiMaker();

		String songString = "AAABCCBBAAGGAABBCCBBBBABCCBBrrAAGGrr";
		char[] song = songString.toCharArray();

		midiMaker.makeSong( song );
	}

}

