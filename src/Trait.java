public class Trait {

    protected static int numBits = 0;
    protected boolean[] data;


    Trait(){
        data = new boolean[numBits];
    }

    Trait(boolean[] data){
        this();
        try {
            System.arraycopy(data, 0, this.data, 0, this.data.length);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Wrong length of data to construct Trait");
        }
    }

    boolean[] getData(){
        return data;
    }

    int size(){
        return numBits;
    }

    char asChar(){
       return '0';
    }

}
