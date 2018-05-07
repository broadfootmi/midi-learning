public class MidiCreature extends Creature{
    public MidiCreature(Simulation simulation) {
        super(simulation);
    }

    public MidiCreature(Population population) {
        super(population);
    }

    public MidiCreature(Population population, boolean[] genome) {
        super(population, genome);
    }

    @Override
    public void nameCreature() {
		String song = new String();

		char[] notes = genome.getDataAsChars();

		for( char note : notes ) {

			song += note;

		}

		name = song;
    }

    @Override
    protected void initGenome(){
        genome = new MidiGenome(this);
    }

	public void playSong() {
		MidiMaker.INSTANCE.makeSong(getName().toCharArray());
    }
}
