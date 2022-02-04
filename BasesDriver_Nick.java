/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src_DataStructures.GroupProjectBases;

import java.util.Scanner;

/**
 *
 * @author Nick
 */
public class BasesDriver_Nick {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Bases_Nick test = new Bases_Nick();

        System.out.println("Enter Any Number: ");
        String inputNum = input.next();

        System.out.println("Enter the base: ");
        int baseStart = input.nextInt();

        System.out.println("Enter the base for the number to be converted to: ");
        int baseFinal = input.nextInt();

        if (baseStart == 10) {
            String str = test.convertToAnyBase(inputNum, baseFinal);
            System.out.println("Your converted number to base " + baseFinal + " is " + str + ".");
        } else {
            String str = test.convertToBaseTen(inputNum, baseStart);
            System.out.println("TEST POINT: between conversions. base10 str = " + str);
            String str1 = test.convertToAnyBase(str, baseFinal);
            System.out.println("Your converted number to base " + baseFinal + " is " + str1 + ".");
        }
        input.close();

    }

}
