/*
 * pythagoreantriplet.java
 * 
 * Copyright 2017 Mike Hewitt <mikeh@mikeh-Inspiron-1501>
*/
package calculator;
// added to FPDemo 23.02.2017
public class pythagoreantriplet
{
    // instance variables - replace the example below with your own
    private int x;  // keep to remind constructor mechanism
    private static int a,b,c;
    /**
     * Constructor for objects of class pythagoreantriplet
     *
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public void pythagoreantriplet()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * Set shortest side of triangle, a 
     *
     * @param a the shortest side of the triangle
     */
    public static void seta(int len) {  // was void and compiled 
        a= len;
    }

    /**
     * Get shortest side of triangle, a
     * @return a the shortest side of the triangle
     */
    public static int geta() {
        //  a= len;
        return a;
    }

    /**
     * Set b value of middle length side of triangle
     *
     * @param len b value of middle length side of triangle
     */
    public static void setb(int len) {
        b = len;
        //  return b;
    }

    /**
     * Get middle length side of triangle, b
     * @return b value of middle length side of triangle
     */
    public static int getb() {
        return b;
    }

    /**
     * Set longest length side of triangle, c
     *
     * @param  c longest side of triangle
     */
    public static void setc(int len) {
        c= len;
        //  return c;
    }

    /**
     * Get longest length side of triangle, c
     * @return c longest length side of triangle
     */
    public static int getc() {
        //  a= len;
        return c;
    }

    /**
     * Test whether shortest side of triangle is odd
     *
     * @return     logical value of whether 'a is odd'
     */
    public static boolean a_is_odd()
    { if (a%2 == 0) {
            return false;}
        else return true;

    }

    /**
     * produces b when a is known to be odd
     * @return b 
     */
    public static int oddb() {
        b = ((a * a) - 1) / 2;
        return b;
    }

    /**
     * produces b when a is known to be even
     *
     * @return b
     */
    public static int evenb() {
        b = ((a / 2) * (a / 2)) - 1;
        return b;
    }

    /**
     * Find b without knowing whether b is odd or even
     *
     * @return     size of middle length side of triangle
     */
    public static int answerb()
    {
        if (a_is_odd()) {
            return oddb();
        }
        else {return evenb();}

    }

    /**
     * Find c without knowing whether b is odd or even
     *
     * @return     size of longest side of triangle
     */
    public static int answerc()
    {
        if (a_is_odd()) {
            return oddc();
        }
        else {return evenc();}

    }

    /**
     * produces c when a is known to be odd

     * @return  c longest side of triangle
     */
    public static   int oddc() {
        c = b + 1;
        return c;
    }

    /**
     * produces c when a is known to be even
     * @return c longest side of triangle
     */
    public static   int evenc() {
        c = b + 2;
        return c;
    }
}


