/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src_DataStructures.GroupProjectBases;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * Code modified by Nick
 */
public class Bases_Nick {

    Stack<Integer> I = new Stack<>();
    Stack<String> S = new Stack<>();
    Queue<Integer> qI = new LinkedList<>();
    Queue<String> qS = new LinkedList<>();

    // This altered method now splits the string into using two seperate methods
public String convertToBaseTen(String str, int baseStart){
       
    
//Input with no Decimal.  Uses your original code.
if(!str.contains("."))
    {return convertWholeNumsToBaseTen(str, baseStart);}



//My new addition to this method.  Splits string into two strings:  One to the
//left of decimal point and one to the right.  Returns a new string that
//reassembles the two return strings from the seperate algorithms.
//It also works if the input has nothing left of the decimal. (i.e. '.313')
else
    {int indexOfDecimal = str.indexOf(".");
    if(indexOfDecimal == 0)
	    {return "0." + convertFractionToBaseTen(str.substring
	            (indexOfDecimal + 1), baseStart);
        }
    else{
	return "" + convertWholeNumsToBaseTen(str.substring
	        (0, indexOfDecimal), baseStart)+ "."
		+ convertFractionToBaseTen(str.substring
		(indexOfDecimal + 1), baseStart);
    }
}

}

    // My new method coverting any base fractions into base ten fractions.
    public String convertFractionToBaseTen(String str, int baseStart) {

        // same for() iteration you had, but implementing a queue
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                qI.offer(10);
            } else if (str.charAt(i) == 'B') {
                qI.offer(11);
            } else if (str.charAt(i) == 'C') {
                qI.offer(12);
            } else if (str.charAt(i) == 'D') {
                qI.offer(13);
            } else if (str.charAt(i) == 'E') {
                qI.offer(14);
            } else if (str.charAt(i) == 'F') {
                qI.offer(15);
            } else if (str.charAt(i) == 'G') {
                qI.offer(16);
            } else if (str.charAt(i) == 'H') {
                qI.offer(17);
            } else if (str.charAt(i) == 'I') {
                qI.offer(18);
            } else if (str.charAt(i) == 'J') {
                qI.offer(19);
            } else {
                qI.offer(Integer.parseInt("" + str.charAt(i)));
            }
        }

        
        // The main purpose of this next part is to calculate each digit.
        // It also skips any leading 0's to help reduce time.
        BigDecimal baseStartBG = BigDecimal.valueOf(baseStart);
        BigDecimal outputInBaseTenBG = BigDecimal.valueOf(0.0);
        int tenthsPlace = -1;
        while (!qI.isEmpty()) {
            BigDecimal removedBG = BigDecimal.valueOf(qI.remove());
            if (Integer.parseInt("" + removedBG) > 0) {
                BigDecimal baseTenCalculationBG = (removedBG
                        .multiply(baseStartBG.pow(tenthsPlace, MathContext.DECIMAL32), MathContext.DECIMAL32));
                outputInBaseTenBG = outputInBaseTenBG.add(baseTenCalculationBG);
            }
            tenthsPlace--;
        }

        // Creates the final return string, which here is no larger than 24 digits.
        // ***NOTE: If your final program has problems with the number of output
        // characters, just reduce the '26' --- (24 = 26 - 'int i = 2') --- within
        // the 3rd next line's condition, and do the same with the
        // convertFractionToBaseTen() method***
        String strDouble = "" + outputInBaseTenBG;
        str = "";
        for (int i = 26; i > 2; i--) {
            if (i < strDouble.length()) {
                str = "" + strDouble.charAt(i) + str;
            }
        }

        return str;

    }

    // The rest of your original method. Only the name is different.
    public String convertWholeNumsToBaseTen(String str, int baseStart) {

        int mult = 0;
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                I.push(10);
            } else if (str.charAt(i) == 'B') {
                I.push(11);
            } else if (str.charAt(i) == 'C') {
                I.push(12);
            } else if (str.charAt(i) == 'D') {
                I.push(13);
            } else if (str.charAt(i) == 'E') {
                I.push(14);
            } else if (str.charAt(i) == 'F') {
                I.push(15);
            } else if (str.charAt(i) == 'G') {
                I.push(16);
            } else if (str.charAt(i) == 'H') {
                I.push(17);
            } else if (str.charAt(i) == 'I') {
                I.push(18);
            } else if (str.charAt(i) == 'J') {
                I.push(19);
            } else {
                I.push(Integer.parseInt("" + str.charAt(i)));
            }
        }

        while (!I.isEmpty()) {
            result += I.pop() * Math.pow(baseStart, mult);
            mult++;
        }

        str = Integer.toString(result);
        return str;
    }

    // This altered method now splits the string into using two seperate methods.
    // Same as above pretty much.
    public String convertToAnyBase(String str, int baseFinal) {

        // Input with no Decimal. Uses your original code.
        if (!str.contains(".")) {
            {
                return convertWholeNumsToAnyBase(str, baseFinal);
            }
        }

        // My new addition to this method. Splits string into two strings: One to the
        // left of decimal point and one to the right. Returns a new string that
        // reassembles the two return strings from the seperate algorithms.
        // Again, it works if you inputed a base10 number with nothing to the left of
        // the decimal point. (i.e. '.131')
        // Same as above pretty much.
        else {
            int indexOfDecimal = str.indexOf(".");
            if (indexOfDecimal == 0) {
                return "0." + convertWholeNumsToBaseTen(str.substring(indexOfDecimal + 1), baseFinal);
            } else {
                return "" + convertWholeNumsToAnyBase(str.substring(0, indexOfDecimal), baseFinal) + "."
                        + convertFractionToAnyBase(str.substring(indexOfDecimal + 1), baseFinal);
            }
        }

    }

    // My new method coverting base-10 fractions into any-base fractions.
    public String convertFractionToAnyBase(String str, int baseFinal) {

        // Reduces the string to a type int with 9 characters
        int inputNum = 0;
        if (str.length() > 9) {
            inputNum = Integer.parseInt(str.substring(0, 8));
        } else {
            inputNum = Integer.parseInt(str);
        }

        // Gets rid of any unnessisary trailing 0's.
        while (inputNum % 10 == 0) {
            inputNum /= 10;
        }
        str = "" + inputNum;
        String strDouble = "0." + str;

        // Loop that calculates conversion.
        while (inputNum != 0 && qS.size() < 24) {

            // Initial calculation using java.math.BigDecimal class.
            BigDecimal inputNumBG = BigDecimal.valueOf(Double.parseDouble(strDouble));
            BigDecimal baseFinalBG = BigDecimal.valueOf(baseFinal);
            BigDecimal calcInputNumBG = inputNumBG.multiply(baseFinalBG, MathContext.DECIMAL32);
            strDouble = "" + calcInputNumBG;

            // Whole numbers get pushed into answer.
            int pushWholeNum = Integer.parseInt(strDouble.substring(0, strDouble.indexOf(".")));
            if (pushWholeNum == 10) {
                qS.offer("A");
            } else if (pushWholeNum == 11) {
                qS.offer("B");
            } else if (pushWholeNum == 12) {
                qS.offer("C");
            } else if (pushWholeNum == 13) {
                qS.offer("D");
            } else if (pushWholeNum == 14) {
                qS.offer("E");
            } else if (pushWholeNum == 15) {
                qS.offer("F");
            } else if (pushWholeNum == 16) {
                qS.offer("G");
            } else if (pushWholeNum == 17) {
                qS.offer("H");
            } else if (pushWholeNum == 18) {
                qS.offer("I");
            } else if (pushWholeNum == 19) {
                qS.offer("J");
            } else if (pushWholeNum == -1) {
                qS.offer("" + 0);
            } else {
                qS.offer("" + pushWholeNum);
            }

            // Here I assign the inputNum variable to apply to this current
            // while-condition by first removing the whole number used previously for
            // the offer() method.
            // Also these next lines round the value to 0, incase the output is
            // < than '0.00000'. This hopefully avoids any extremely small values from
            // being unintentially created during the float/double calculations earlier
            // that unfortunately weren't prevented by the use of class BigDecimal.
            str = strDouble.substring(strDouble.indexOf(".") + 1);
            if (str.length() > 4 && Integer.parseInt(str.substring(0, 4)) == 0) {
                str = "00000";
            }
            inputNum = Integer.parseInt(str);
            strDouble = "0." + str;
        }

        // And finally, I create the return string from the Stack.
        str = "";
        while (!qS.isEmpty()) {
            str += qS.remove();
        }

        return str;

    }

    // Again, the rest of your other original method. Only the name is different.
    public String convertWholeNumsToAnyBase(String str, int baseFinal) {

        int remainder;
        int inputNum = Integer.parseInt(str);
        str = "";

        do {
            remainder = inputNum % baseFinal;
            if (remainder == 10) {
                S.push("A");
            } else if (remainder == 11) {
                S.push("B");
            } else if (remainder == 12) {
                S.push("C");
            } else if (remainder == 13) {
                S.push("D");
            } else if (remainder == 14) {
                S.push("E");
            } else if (remainder == 15) {
                S.push("F");
            } else if (remainder == 16) {
                S.push("G");
            } else if (remainder == 17) {
                S.push("H");
            } else if (remainder == 18) {
                S.push("I");
            } else if (remainder == 19) {
                S.push("J");
            } else {
                S.push("" + remainder);
            }

            inputNum = inputNum / baseFinal;

        } while (inputNum != 0);

        while (!S.isEmpty()) {
            str += S.pop();
        }

        return str;

    }

}
