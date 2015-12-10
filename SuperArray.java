// Pardeep Singh
// APCS1 pd9
// HW42 -- Array of Titanium
// 2015-12-06

/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array.
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray{

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() {
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;
    }

    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() {
	String foo = "[";
	for (int i = 0; i < _size; i++) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if (foo.length() > 1)
	    foo = foo.substring(0, foo.length() - 1);
	foo += "]";
	return foo;
    }

    //double capacity of this SuperArray
    private void expand() {
	Comparable[] temp = new Comparable[_data.length * 2];
	for (int i = 0; i < _data.length; i++)
	    temp[i] = _data[i];
	_data = temp;
    }

    //accessor -- return value at specified index
    public Comparable get(int index) {
	return _data[index];
    }

    //mutator -- set value at index to newVal,
    //           return old value at index
    public Comparable set(int index, Comparable newVal) {
	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }

    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add(Comparable newVal) {
	if (_lastPos == _size - 1) {
	    expand();
	}
	_lastPos++;
	_size++;
	this.set(_lastPos, newVal);
    }
    
    //inserts an item at index
    //shifts existing elements to the right
    public void add(int index, Comparable newVal) {
	if (index < _size) {
	    for (int x = _lastPos + 1; x > index; x--) {
		_data[x]= _data[x-1];
	    }
	    _data[index] = newVal;
	    _lastPos++;
	    _size++;
	}
    }

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove(int index) {
	for (int x = index; x < _lastPos; x++) {
	    _data[x]= _data[x+1];
	}
	_lastPos--;
	_size--;
    }
    
    //return number of meaningful items in _data
    public int size() {
	return _size;
    }

    //searches for first occurance of Comparable input
    public int linSearch(Comparable find) {
	for (int x = 0; x <= _lastPos; x++) {	    
	    if (_data[x].equals(find)) {
		return x;
	    }
	}
	return -1;
    }
    
    //checks if _data is sorted
    public boolean isSorted() {
	for (int x = 1; x <= _lastPos; x++) {
	    try {
		if (_data[x].compareTo(_data[x-1]) > 0) {
		    return true;
		}
	    }
	    catch (Exception e) {}
	}
	return false;
    }
    
    //main method for testing
    public static void main(String[] args) {
	
	SuperArray curtis = new SuperArray();
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);	    
	for (int i = 0; i < curtis._data.length; i++) {
	    curtis.set(i, new Binary(i * 2));
	    curtis._size++; //necessary bc no add() method yet
	}
	    
	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);
	    
	System.out.println("testing get()...");
	for (int i = 0; i < curtis._size; i++) {
	    System.out.print("item at index" + i + ":\t");
	    System.out.println(curtis.get(i));
	}
	    
	System.out.println("Expanded SuperArray curtis:");
	curtis.expand();
	System.out.println(curtis);
	    
	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.add(new Binary(5));
	mayfield.add(new Hexadecimal(4));
	mayfield.add(new Binary("1110"));
	mayfield.add(new Hexadecimal("F"));
	mayfield.add(new Rational(1,2));

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);

	System.out.println("\nTesting linSearch() mayfield...");
	System.out.println(mayfield.linSearch(new Binary(5)));//expects 0
	System.out.println(mayfield.linSearch(new Hexadecimal(4)));//expects 1
	System.out.println(mayfield.linSearch(new Binary("1110")));//expects 2
	System.out.println(mayfield.linSearch(new Hexadecimal("F")));//expects 3
	System.out.println(mayfield.linSearch(new Binary(0)));//expects -1
		
	System.out.println("\nTesting isSorted() mayfield...");
	System.out.println(mayfield.isSorted());//expects false
	
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	    
	mayfield.add(3,new Rational(99,100));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(2,new Binary(88));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(1,new Hexadecimal(77));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);

    } //end main

} //end class
