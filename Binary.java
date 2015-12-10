public class Binary implements Comparable{

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_decNum = 0;
	_binNum = "0";
  
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_decNum = n;
	//changes the inputted int to a binary number
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	//changes inputted string of binary to decimal number
	_decNum = binToDec(s);
	_binNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() {
	//returns the string of the binary number
	return _binNum;  
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
        String retStr = "";//creates the string that will hold the binary number
	while (n > 0) {//as long as the number is positive
	    retStr = n % 2 + retStr;//add the remainder to the beginning of the binary number
	    n /= 2;//set the number to the quotient of n and 2
	    //since % by 2 only numbers being added are 0 and 1
	}
	return retStr; //returns the binary number
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) {
	//as long as the number isn't 0 or less
	if ( n != 0) {
	    return decToBinR(n / 2) + (n % 2);//adds the remainder of n and 2 to the recurssed of the quotient of n and 2
	}
	return "";//if n is 0 or less return empty string
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int size = s.length() - 1;//the length of the  binary number 
	int retInt = 0;//integer being returned
	while (size > -1) {//as long as the binary number doesn't reach 0
	    //add the 0th index of the binary multiplied by 2 to the power of the length of the binary
	    retInt += Integer.parseInt( s.substring(0,1) ) * (int) Math.pow(2,size);
	    s = s.substring(1);//shortens the binary
	    size--;//decreases size of binary 
	}
	return retInt;//return the decimal number
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) {
	if ( s.length() == 0 ) {//if the binary has no length
	    return 0;//return 0 because thats the decimal equivalent
	}
	else {
	    //add (the 0th index of the binary multiplied by 2 to the power of the length of the binary)
	    //to (the recurssed method with a shorter binary as the input
	    return Integer.parseInt( s.substring(0,1) ) * (int) Math.pow(2,s.length() - 1) + binToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	if (other instanceof Binary) {//check if input is a Binary
	    return _decNum == ((Binary) other)._decNum;//returns true when the decimal equivalents are equal
	}
	return false;//return false if input is not Binary
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object o ) {
	//throws NullPointerException if the input has not been assigned a value or has a value of null
	if (o.equals(null)) {
	    throw new NullPointerException(
					   "\nObject is null!"
					   +" compareTo() input not assigned a value");
	}
	else if (o instanceof Binary) {//if the input is binary
	    return _decNum -  ((Binary) o)._decNum;//returns the difference of the called numbers decimal eqiv. and the inputs decimal equiv.
	}
	//throws ClassCastException if the input is not Binary
	else {
	    throw new ClassCastException(
					 "\nClasses do not match! " 
					 + " compareTo() input not a Binary");
	}
    }


    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);
	Binary b5 = null;
	
	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

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
	System.out.println( b4.compareTo(b1) ); //should be neg
	//       	System.out.println( b4.compareTo("2") ); //should throw class error
	System.out.println( b4.compareTo(b5) ); //should throw NullPointerException error
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
