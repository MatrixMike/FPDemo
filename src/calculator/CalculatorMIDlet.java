/*
 *
 *
 */
package calculator;
// import java.util.*;  // using this causes 'invalid application' error on the device
// import java.lang.*;  // removed due to error on phone screen
import java.util.Date;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

// import java.util.List;

// import java.text.SimpleDateFormat;
// import java.util.Properties;

/**
 * The calculator demo is a simple floating point calculator
 * which powered by floating point support available in cldc1.1.
 *
 * @version
 */
 /* String buffers are used by the compiler to implement the binary string concatenation operator +. For example, the code:

         x = "a" + 4 + "c"
     

is compiled to the equivalent of:

         x = new StringBuffer().append("a").append(4).append("c")
                               .toString()
                               */
     
 class LongestCommonSubsequence {
     // Compute length of LCS for all subproblems.
    public static String lcs(String x, String y) {
        int m = x.length(), n = y.length();
        int[][] opt = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (x.charAt(i) == y.charAt(j)) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                }
                else {
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                }
            }
        }

        // Recover LCS itself.
        String lcs = "";
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (x.charAt(i) == y.charAt(j)) {
                lcs += x.charAt(i);
                i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) i++;
            else                                 j++;
        }
        return lcs;
    }
}
 
public final class CalculatorMIDlet extends MIDlet implements CommandListener {
	private Date today = new Date(System.currentTimeMillis());
    /** The number of characters in numeric text field. */
    private static final int NUM_SIZE = 20;

    /** Soft button for exiting the game. */
    private final Command exitCmd = new Command("Exit", Command.EXIT, 2);

    /** Menu item for changing game levels. */
    private final Command calcCmd = new Command("Calc", Command.SCREEN, 1);

    /** A text field to keep the first argument. */
    private final TextField t1 = new TextField(null, "", NUM_SIZE, TextField.DECIMAL);// "t time"
    //      System.getProperty("user.name");

    /** A text field to keep the second argument. */
    private final TextField t2 = new TextField(null, "", NUM_SIZE, TextField.DECIMAL);// "u initial velocity"
    /** A text field to keep the second argument. */
    private final TextField t3 = new TextField(null, "", NUM_SIZE, TextField.DECIMAL);// "a acceleration"
    private final TextField t4 = new TextField(null, "", NUM_SIZE, TextField.DECIMAL);// "v velocity at time t"
    /** A text field to keep the result of calculation. */
    private final TextField tr = new TextField("displacement s ", "", NUM_SIZE, TextField.UNEDITABLE); // "s=" 
    private final TextField v  = new TextField("velocity at time t ", "", NUM_SIZE, TextField.UNEDITABLE); // "v=" 
    private final TextField sysprop  = new TextField(System.getProperty("microedition.configuration"), "", NUM_SIZE, TextField.UNEDITABLE); // "v="   
    private final TextField texttest  = new TextField("xxx ", "", NUM_SIZE, TextField.UNEDITABLE); // "v="   
// here for the 3 strings to support lcs
   
    private final TextField lcs_in1 = new TextField("lcsIN1", "", NUM_SIZE, TextField.DECIMAL);		//  
    private final TextField lcs_in2 = new TextField("lcsIN2", "", NUM_SIZE, TextField.DECIMAL);		//  
    private final TextField lcs_out = new TextField("lcsOUT", "", NUM_SIZE, TextField.UNEDITABLE);	//   
    
    /** A choice group with ava1ailable operations. */
    private final ChoiceGroup cg =
        new ChoiceGroup("", ChoiceGroup.POPUP,
            new String[] { "add", "subtract", "multiply", "divide", "displacement" }, null);

    /** An alert to be reused for different errors. */
    private final Alert alert = new Alert("Error", "", null, AlertType.ERROR);

    /** Indicates if the application is initialized. */
    private boolean isInitialized = false;
//            private Date date ;
            
 //       long aboutNow = date.getTime();
 //       Calendar calendar = Calendar.getInstance();  // removed due to error on device
 //       double DOM = calendar.get(Calendar.DAY_OF_MONTH);
    /**
     * Creates the calculator view and action buttons.
     */

    protected void startApp() {
        if (isInitialized) {
            return;
        }
 //       long aboutNow = date.getTime();
//        Calendar calendar = Calendar.getInstance();
//        double DOM = calendar.get(Calendar.DAY_OF_MONTH);

//		int DOM = calendar.get(Calendar.DAY_OF_MONTH);
// in this library appear not to be able to do DAY_OF_WEEK_IN_MONTH but maybe 
//		if   ( (calendar.get(Calendar.DAY_OF_MONTH)==4) && (calendar.get(Calendar.DAY_OF_WEEK)==2) ){}
//	         DateField datefield = new DateField("", DateField.DATE.TIME); 
        Form f = new Form("Kinematics 2");

		f.append(lcs_in1);
	
		f.append(lcs_in2);
			
  		f.append(lcs_out);  // enabling this causes fail with message 'Invalid Application - delete?' 


        f.append(t1); // time
        f.append(cg); 
        f.append(t2); // init velocity
        f.append(t3); // acceleration
        f.append(tr); // displacement
        f.append(v);  // velocity at time t
        f.append(sysprop);
        f.append(texttest);	  // need to append text fields for lcs here
        f.addCommand(exitCmd);
        f.addCommand(calcCmd);
        
// 1          display = Display.getDisplay(this);

 //   datefield.setDate(today);
 // 1   form.append(datefield);
 // 1   form.addCommand(exit);
 // 1   form.setCommandListener(this);
    
        f.append(new DateField("Date1", DateField.DATE));  // 07.02.2017
  // 1          date2.setDate(today);
//        f.append(datefield);  // f.append(new DateField("Date2", DateField.DATE)); //
        // to retrieve date values - maybe assign 
        // DateField date = 
 //           Date currentTime = datefield.getDate();
         String	lcs = LongestCommonSubsequence.lcs("tania", "tonight");
        f.setCommandListener(this);
        Display.getDisplay(this).setCurrent(f);
        alert.addCommand(new Command("Back", Command.SCREEN, 1));
        isInitialized = true;
    }

    /**
     * Does nothing. Redefinition is required by MIDlet class.
     */
    protected void destroyApp(boolean unconditional) {
    }

    /**
     * Does nothing. Redefinition is required by MIDlet class.
     */
    protected void pauseApp() {
    }

    /**
     * Responds to commands issued on CalculatorForm.
     *
     * @param c command object source of action
     * @param d screen object containing the item the action was performed on.
     */
    public void commandAction(Command c, Displayable d) {
        if (c == exitCmd) {
            destroyApp(false);
            notifyDestroyed();

            return;
        }

        double res = 0.0;

        try {
            double n1 = getNumber(t1, "time");
            double n2 = getNumber(t2, "initial velocity");
            double n3 = getNumber(t3, "acceleration");
            double vs;
            String  svs ;

            switch (cg.getSelectedIndex()) {
                case 0:
                res = n1 + n2;
                break;

                case 1:
                res = n1 - n2;
                break;

                case 2:
                res = n1 * n2;
                break;

                case 3:
                res = n1 / n2;

                break;
                case 4:
                res = n2*n1 + ((n1*n1)*n3)/2; // was n1/n2  // n1 / n2; // res = u*t + ((t*t)*a)/2  //
                res = calculations(n1,n2,n3);
                //  cpp s = u * t + ((t * t) * a) / 2;
                //double
                vs = Math.sqrt((n2*n2)+(2*n3*res));  // v2 = u2 + 2as :
                // vs = Math.sqrt(2.5); 
                svs = Double.toString(vs);
      //           svs += Double.toString(DOM);
      String lcs = LongestCommonSubsequence.lcs("tania", "tonight");
             lcs = LongestCommonSubsequence.lcs(lcs_in1.getString(), lcs_in2.getString());   // s= lcs_in1.getString();
                texttest.setString(svs);
                lcs_out.setString(lcs);
                break;
                default:
            }
        } catch (NumberFormatException e) {
            return;
        } catch (ArithmeticException e) {
            alert.setString("Divide by zero.");
            Display.getDisplay(this).setCurrent(alert);
            return;
        }
        /*
         * The resulted string may exceed the text max size.
         * We need to correct last one then.
         */
        String res_str = Double.toString(res);

        if (res_str.length() > tr.getMaxSize()) {
            tr.setMaxSize(res_str.length());
        }

        tr.setString(res_str);
    }

    /**
     * Extracts the double number from text field.
     *
     * @param t the text field to be used.
     * @param type the string with argument number.
     * @throws NumberFormatException is case of wrong input.
     */
    private double getNumber(TextField t, String type)
    throws NumberFormatException {
        String s = t.getString();

        if (s.length() == 0) {
            alert.setString("No " + type + " Argument");
            Display.getDisplay(this).setCurrent(alert);
            throw new NumberFormatException();
        }

        double n;

        try {
            n = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            alert.setString(type + " argument is out of range.");
            Display.getDisplay(this).setCurrent(alert);
            throw e;
        }

        return n;
    }
    
      public static double calculations(double n1,double n2,double n3) {
		  double res;
	    res = n2*n1 + ((n1*n1)*n3)/2; // was n1/n2  // n1 / n2; // res = u*t + ((t*t)*a)/2  //
                //  cpp s = u * t + ((t * t) * a) / 2;
                //double
                return res;
	 }
    
} // end of class 'CalculatorMIDlet' definition

 

	 
