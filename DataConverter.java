public class DataConverter {

	public static boolean[] getBooleanArray( String binaryString, int numBits ) {

		boolean[] booleanArray = new boolean[numBits];

		binaryString = padBinaryString( binaryString, numBits );

		for ( int i = 0; i < booleanArray.length; i++ ) {

			if ( binaryString.charAt( i ) == '1' ) {

				booleanArray[i] = true; 

			}

			else {

				booleanArray[i] = false;

			}

		}

		return booleanArray;

	}

	public static boolean[] getBooleanArray( String binaryString ) {

		int numBits = binaryString.length();
		return getBooleanArray( binaryString, numBits);

	}

	public static String padBinaryString ( String binaryString, int numBits ) { //Use formatter instead?

		if ( binaryString.length() < numBits ) { //Pad bits. 
			int padBits = Math.abs( binaryString.length() - numBits );

			String pad = new String();
			for ( int i = 0; i < padBits; i++ ) {

				pad = pad.concat("0");
			
			}

			binaryString = pad + binaryString;
		}

		return binaryString.substring( binaryString.length() - numBits ); //Cut bits

	}

	public static String getBinaryString( boolean[] booleanArray ) {

		int numBits = booleanArray.length;
		return getBinaryString( booleanArray, numBits );

	}

	public static String getBinaryString( boolean[] booleanArray, int numBits ) {

		String binaryString = new String();

	       	for( int i = 0; i < booleanArray.length; i++ ) {

			if( booleanArray[i] ) {
				
				binaryString += "1";

			}

			else {

				binaryString += "0";

			}

		}

		binaryString = padBinaryString( binaryString, numBits );

		return binaryString;

	}

}


