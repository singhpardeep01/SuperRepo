// Pardeep Singh
// APCS1 pd9
// HW37 -- Rational Equality
// 2015-11-24

public class Rational implements Comparable{

    // instance vars
    private int numerator;
    private int denominator;

    // default constructor
    public Rational() {
        numerator = 0;
        denominator = 1;
    }

    // constructor
    public Rational(int numerator, int denominator) {
        this();
        if (denominator != 0) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        else {
            System.out.println("Invalid denominator");
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    // overriding toString()
    public String toString() {
        return numerator + "/" + denominator;
    }

    // returns a floating point value of the number
    public double floatValue() {
        return (double) numerator / denominator;
    }

    // takes 1 Rational object as a parameter and multiplies it by this Rational object, modifying this object
    public void multiply(Rational r) {
        numerator *= r.numerator;
        denominator *= r.denominator;
    }

    // Takes 1 Rational object as a paramter and divides this by the parameter, modifying this object
    public void divide(Rational r) {
        numerator /= r.denominator;
        denominator /=  r.numerator;
    }

    // Takes 1 Rational object as a paramter and adds it to this Rational object, modifying this object
    public void add(Rational r) {
        int gcd = new Rational(denominator, r.denominator).gcd();
        denominator *= r.denominator / gcd;
        numerator *= r.denominator / gcd;
        numerator += (r.numerator * (denominator / r.denominator));
    }

    // Subtracts the value of this Rational object by the parameter, modifying this object
    public void subtract(Rational r) {
        int gcd = new Rational(denominator, r.denominator).gcd();
        denominator *= r.denominator / gcd;
        numerator *= r.denominator / gcd;
        numerator -= (r.numerator * (denominator / r.denominator));
    }

    public int gcd() {
        int a = numerator;
        int b = denominator;
        int gcd = 1;

        if (a>b) {
            if (a%b==0) { //done, yay!
                gcd = b;
            } else { //implement algorithm
                a -= b;
                gcd = new Rational(a,b).gcd();
            }
        } else {
            gcd = new Rational(b,a).gcd(); //instead of rewriting code, switch maxes
        }
        return gcd;
    }

    public void reduce() {
        int gcd = gcd();
        // Divide both the numerator and denominator by the gcd
        numerator /= gcd;
        denominator/= gcd;
    }

    // Recursive implementation of gcd
    public static int gcd(int numerator, int denominator) {
        if (denominator == 0) {
            return numerator;
        }
        return gcd(denominator, numerator % denominator);
    }

    // Compares one Rational to another
    // Returns 0 if they are equal
    // Returns 1 if calling number is greater than parameter
    // Returns -1 if calling number is less than parameter
    public int compareTo(Object o) {
	if (this instanceof Rational && o instanceof Rational) {
	    int thisValue = ((Rational) this).numerator * ((Rational) o).denominator;
	    int rValue = ((Rational) o).numerator * ((Rational) this).denominator;
	    return thisValue - rValue;
	}
        return 10000000;
    }
       

    public boolean equals(Rational r) {
        return r instanceof Rational
            && this.compareTo(r) == 0;
    }

    // main method for testing
    public static void main(String[] args) {
        Rational r = new Rational(2, 3); // Stores the rational number 2/3
        Rational s = new Rational(1, 2); // Stores the rational number 1/2
        Rational t = new Rational(2, 4); // Stores the rational number 2/4
        Rational u = new Rational(5, 3); // Stores the rational number 5/3
        Rational v = new Rational(6, 1); // Stores the rational number 6/1
        System.out.println(r.equals(s));//false
        System.out.println(t.equals(s));//true
        System.out.println(s.compareTo(t));//0
        System.out.println(v.compareTo(u));//positive
        System.out.println(r.compareTo(u));//negative
        r.add(s);
        System.out.println(r);
        r.subtract(s);
        System.out.println(r); // Should be the original value of r
        r.reduce();
        System.out.println(r); // Should return 2/3
    }
}
