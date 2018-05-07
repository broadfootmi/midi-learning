public class Note extends Trait{

    static{numBits = 3;}

    private char note;

    Note(boolean[] bits){
        super();
        this.data = bits;
        this.note = MidiGenome.Notes[Integer.parseInt(DataConverter.getBinaryString(bits), 2)];
    }

    public Note(char note) {
        this(MidiGenome.charToBinary(note));
    }

    @Override
    char asChar(){
       return note;
    }

}
