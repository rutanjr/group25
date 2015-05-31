package edu.chl.ChalmersRisk.utilities;

/**
 * Created by Malin on 2015-05-31.
 *
 * Simple class used for sorting
 */
public class BubbleSort {

    /**
     * Simple bubblesort algorithm
     * @param theInt  the array of integers to sort
     * @return the sorted array of integers
     */
    public static int[] sortInt(int [] theInt){
        for(int i = 0; i < theInt.length-1 ; i++){ // go through the whole list
            for(int j = 0; j< theInt.length-1 - i; j++){ // go through the whole list, excluding the ones that are "locked"
                if(theInt[j] < theInt[j+1]){
                    int temp = theInt[j];
                    theInt[j] = theInt[j+1];
                    theInt[j+1] = temp;
                }

            }

        }

        return theInt;
    }
}
