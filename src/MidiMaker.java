import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Arrays;

public class MidiMaker {

	public final static MidiMaker INSTANCE = new MidiMaker();

	private Sequencer sequencer;
	private HashMap< Character, Integer > notePitchMap;

	private int baseDuration = 4;
	private int ticksPerBeat = 2;
	private int noteVelocity = 64;
	private int defaultOctave = 5;
	private final int notesPerOctave = 12;

	private MidiMaker () {

		initNotePitchMap();

		try {

			sequencer = MidiSystem.getSequencer();

		} catch ( MidiUnavailableException e ) {

			System.out.println("No Sequencer Available.");

		}



	}

	private int getPitch( char note, int octave ) {

		return notePitchMap.get( note ) + notesPerOctave * octave;

	}

	private void initNotePitchMap () {

		notePitchMap = new HashMap< Character, Integer > ();
		char[] allNotes = { 'C', 'd', 'D', 'e', 'E', 'F', 'g', 'G', 'a', 'A', 'b', 'B', 'r' }; // Lower case means flat. 'r' means rest.

		for( int i = 0; i < allNotes.length; i++ ) {

			notePitchMap.put( allNotes[i], i);

		}

	}

	public void makeSong( char[] notes ){

		int[] mask = new int[notes.length];
		for(int i = 0; i < mask.length; i++){
			mask[i] = defaultOctave;
			if(notes[i] == 'C'){
		    	mask[i] = 6;
			}
		}
		makeSong(notes, mask);
	}

	public synchronized void makeSong ( char[] notes, int[] octaveMask) {

		Sequence sequence = null;

		try {

			sequence = new Sequence( Sequence.PPQ, this.ticksPerBeat );

		} catch  ( InvalidMidiDataException e ) {

			System.out.println( "Could not make sequence for some reason." );

		}

		sequence.createTrack();

		ShortMessage noteOn = null;
		ShortMessage noteOff = null;

		long tickOn = 0;
		long tickOff;

		int duration = 1;

		char previousNote = ' ';

		for ( int i = 0; i < notes.length; i++ ) {

			char note = notes[i];
			int octave = octaveMask[i];

			if( ( note != previousNote ) && (note != 'r') ) { //New Note
				//set tick off and end previous note
				//New Note
				tickOff = tickOn + duration; //- 1;

				int pitch = getPitch( note, octave );

				try {

					noteOn = new ShortMessage( ShortMessage.NOTE_ON, pitch, noteVelocity );
					noteOff = new ShortMessage( ShortMessage.NOTE_OFF, pitch, 0);

				} catch ( InvalidMidiDataException e ) {

					System.out.println( "Could not make messages." );

				}

				MidiEvent noteOnEvent = new MidiEvent( noteOn, tickOn );
				MidiEvent noteOffEvent = new MidiEvent( noteOff, tickOff );

				sequence.getTracks()[0].add( noteOnEvent );
				sequence.getTracks()[0].add( noteOffEvent );


			}

			tickOn += duration;
			previousNote = note;

		}

		try {

			sequencer.setSequence( sequence );

		} catch ( InvalidMidiDataException e ) {

			System.out.println("could not set sequence");

		}

		try {

			sequencer.open();

		} catch ( MidiUnavailableException e ) {

			System.out.println("Sequencer unavailable");

		}

		sequencer.start();

	}

}

