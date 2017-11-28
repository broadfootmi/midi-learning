import javax.sound.midi.*;
import java.util.HashMap;
import java.util.Arrays;

public class MidiMaker {

	private Sequencer sequencer;
	private HashMap< Character, Integer > notePitchMap;

	private int baseDuration = 4;
	private int ticksPerBeat = 2;
	private int noteVelocity = 64;
	private int octave = 5;
	private final int notesPerOctave = 12;
	
	public MidiMaker () {

		try {

			sequencer = MidiSystem.getSequencer();

		} catch ( MidiUnavailableException e ) {

			System.out.println("No Sequencer Available.");

		} finally {

			initNotePitchMap();

		}

	}

	private int getPitch( char note, int octave ) {

		return notePitchMap.get( note ) + notesPerOctave * octave;

	}

	private void initNotePitchMap () {

		notePitchMap = new HashMap< Character, Integer > ();
		char[] allNotes = { 'C', 'd', 'D', 'e', 'E', 'F', 'g', 'G', 'a', 'A', 'b', 'B', 'r' }; // Lower case means flat. 'r' means rest.

		for( int i = 0; i < allNotes.length; i++ ) {

			notePitchMap.put( allNotes[i], i );

		}
	}


	public void makeSong ( char[] notes ) {


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
		long tickOff = tickOn + this.baseDuration - 1;
		char previousNote = ' ';

		for ( char note : notes ) {

			int duration = 0;
			int noteIndex = Arrays.asList( notes ).indexOf( note );

			if( note != previousNote ) {

				for( int i = noteIndex; i < notes.length; i++ ) {

					if( notes[i] == note ) {

						duration += baseDuration;

					}

					else {

						break;

					}

				}

				tickOff = tickOn + duration - 1;

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

				tickOn += duration;

			}

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

