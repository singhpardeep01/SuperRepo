
public class Hexadecimal {

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF"; 



    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
  
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHexR(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative Hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_decNum = hexToDecR(s);
	_hexNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of HEXDIGITS representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;  
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "2"
      decToHex(3) -> "3"
      decToHex(14) -> "E"
      =====================================*/
    public static String decToHex( int n ) {
        String retStr = "";
	while (n > 0) {
	    retStr = HEXDIGITS.substring(n % 16, (n % 16) + 1) + retStr;
	    n /= 16;
	}
	return retStr; 
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "2"
      decToHexR(3) -> "3"
      decToHexR(14) -> "E"
      =====================================*/
    public static String decToHexR( int n ) {
	if ( n != 0) {
	    return decToHexR(n / 16) +  HEXDIGITS.substring(n % 16, (n % 16) + 1);
	}
	return "";
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 16
      hexToDec("11") -> 17
      hexToDec("1110") -> 4368
      =====================================*/
    public static int hexToDec( String s ) {
	int size = s.length() - 1;
	int retInt = 0;
	while (size > -1) {
	    retInt += HEXDIGITS.indexOf( s.substring(0,1) ) * (int) Math.pow(16,size);
	    s = s.substring(1);
	    size--;
	}
	return retInt;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("10") -> 16
      hexToDecR("11") -> 17
      hexToDecR("1110") -> 4368
      =====================================*/
    public static int hexToDecR( String s ) {
	if ( s.length() == 0 ) {
	    return 0;
	}
	else {
	    return HEXDIGITS.indexOf( s.substring(0,1) ) * (int) Math.pow(16,s.length() - 1) + hexToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal Hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
	if (other instanceof Hexadecimal) {
	    return _decNum == ((Hexadecimal) other)._decNum;
	}
	return false;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other instanceof Hexadecimal) {
	    return _decNum -  ((Hexadecimal) other)._decNum;
	}
	else {
	    throw new ClassCastException(
					 "\nClasses do not match! " 
					 + " compareTo() input not a Hexadecimal");
	}
    }
    

    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(192345);
	Hexadecimal b2 = new Hexadecimal(192345);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(4321421);
	
	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\nhexToDec..." );
	System.out.println( hexToDec(b1.toString()) );
	System.out.println( hexToDec(b2.toString()) );
	System.out.println( hexToDec(b3.toString()) );       
	System.out.println( hexToDec(b4.toString()) );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
