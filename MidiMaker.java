import javax.sound.midi.*;

public class MidiMaker {

	private Sequencer sequencer;
	
	public MidiMaker () {

		try {

			sequencer = MidiSystem.getSequencer();

		} catch ( MidiUnavailableException e ) {

			System.out.println("No Sequencer Available.");

		}

	}

	public void makeSong () {

		Sequence sequence = null;
		int ticksPerBeat = 2;
		try {

			sequence = new Sequence( Sequence.PPQ, ticksPerBeat );

		} catch  ( InvalidMidiDataException e ) {

			System.out.println( "Could not make sequence for some reason." );

		}

		sequence.createTrack();

		ShortMessage myNoteOn = null;
		ShortMessage myNoteOff = null;

		try {
			myNoteOn = new ShortMessage( ShortMessage.NOTE_ON, 64, 64 );
			myNoteOff = new ShortMessage( ShortMessage.NOTE_OFF, 64, 0 );

		} catch ( InvalidMidiDataException e ) {

			System.out.println( "Could not make messages." );

		}

		long tickOn = 0;
		long tickOff = 7;

		MidiEvent myEventOn = new MidiEvent( myNoteOn, tickOn );
		MidiEvent myEventOff = new MidiEvent( myNoteOff, tickOff );


		sequence.getTracks()[0].add( myEventOn );
		sequence.getTracks()[0].add( myEventOff );

		try {
			this.sequencer.setSequence( sequence );
		} catch ( InvalidMidiDataException e ) {
			System.out.println("could not set sequence");
		}

		try {
			this.sequencer.open();
		} catch ( MidiUnavailableException e ) {
			System.out.println("Sequencer unavailable");
		}

		this.sequencer.start();

	}

}

