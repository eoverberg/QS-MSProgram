// Erik Overberg
// 10/24/22
// CS 321 Fall 2022
// This program reads an input file of strings given by the user and produces 2 outputs
// The first is sorted with quicksort and the second with mergesort
// Also prints to console the time elapsed for each sort
import java.io.*;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {
    /**
     * Compares the values of strings to the chosen pivot value until i > pivot and j < pivot, then
     * switches i and j
     * @param arr array of strings
     * @param lo lower bound of array
     * @param hi upper bound of array
     * @return returns index of partition
     */
    public static int myPartition(String[] arr, int lo, int hi) {
        String pivotValue = arr[lo];
        //char[] pivotArray = pivotValue.toLowerCase().toCharArray();
        int i = lo - 1;
        int j = hi + 1;
        while (true) {
            do {
                i++;
            } while (arr[i].compareToIgnoreCase(pivotValue) < 0);
            do {
                j--;
            } while (arr[j].compareToIgnoreCase(pivotValue) > 0);
            if (i >=j) {
                return j; // returns the pivot value when i and j switch
            }
            String swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
        }
//        do {
////            char[] arrayLinei = arr[i].toCharArray();
////            for (int k = 0; k < arrayLinei.length; k++) {
////                arrayLinei[k] = Character.toLowerCase(pivotArray[k]);
////            }
////            char[] arrayLinej = arr[j].toCharArray();
////            for (int k = 0; k < arrayLinej.length; k++) {
////                arrayLinej[k] = Character.toLowerCase(arrayLinej[k]);
////            }
//            if (arr[i].compareToIgnoreCase(pivotValue) < 0) {
//                i++;
//            }
//            if (arr[j].compareToIgnoreCase(pivotValue) > 0) {
//                j--;
//            }
//            String swap = arr[i];
//            arr[i] = arr[j];
//            arr[j] = swap;
//        } while (i <= j);
//        String swap = arr[i];
//        arr[i] = arr[j];
//        arr[j] = swap;
//        String temp = arr[i];
//        arr[i] = arr[lo];
//        arr[lo] = temp;
        // return j
    }

    /**
     * Partitions the array into halves and recursively calls itself to perform the quicksort algorithm
     * @param arr array of stings
     * @param lo lower bound of array
     * @param hi upper bound of array
     */
    static void qs(String[] arr, int lo, int hi) {
        if (lo < hi) {
            int partIndex = myPartition(arr, lo, hi);

            qs(arr, lo, partIndex - 1);
            qs(arr, partIndex + 1, hi);
        }
    }

    /**
     * Splits the array into two halves after comparing the values of the two strings passed in
     * @param arr array of strings
     * @param lo lower bound
     * @param mid middle of array (calculated in ms function)
     * @param hi upper bound of array
     */
    static void merge(String[] arr, int lo, int mid, int hi)  {
        int left = mid - lo + 1;
        int right = hi - mid;
        String[] Left_arr = new String [left];
        String[] Right_arr = new String [right];

        System.arraycopy(arr, lo, Left_arr, 0, left);
        // the splitting
        for (int j = 0; j < right; ++j)
            Right_arr[j] = arr[mid + 1+ j];
        //increments through and compares the strings
        int i = 0, j = 0;
        int k = lo;
        while (i < left && j < right) {
//            char[] leftchar = Left_arr[i].toLowerCase().toCharArray();
//            char[] rightchar = Right_arr[j].toLowerCase().toCharArray();
            if (Left_arr[i].compareToIgnoreCase(Right_arr[j]) <= 0)  {
                arr[k] = Left_arr[i];
                i++;
            }
            else {
                arr[k] = Right_arr[j];
                j++;
            }
            k++;
        }
        while (i < left)  {
            arr[k] = Left_arr[i];
            i++;
            k++;
        }
        while (j < right){
            arr[k] = Right_arr[j];
            j++;
            k++;
        }
    }

    /**
     * Recursively calls itself until the base array is reached, then merges all arrays in order
     * @param arr array of strings to be passed in
     * @param lo lower bound of array
     * @param hi upper bound of array (n-1)
     */
    static void ms(String[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = floorDiv(lo+hi, 2);
            ms(arr, lo, mid);
            ms(arr, mid+1, hi);
            merge(arr, lo, mid, hi);
        }
    }
    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        //Reads in a filename to take the data from
        Scanner input = new Scanner(System.in);
        //String fileIn = "sample10.txt";
        //String fileOut = "output.txt";
        System.out.println ("Enter a file to be read: ");
        String fileIn = input.nextLine();

         System.out.println ("Enter a name for the first output file: ");
         String fileOut = input.nextLine();
        System.out.println ("Enter a name for the second output file: ");
        String fileOut2 = input.nextLine();
        //String fileOut2 = "output2.txt";
        File myFile2 = new File(fileOut2);

        //Creates a reader to read the number of lines in the file
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileIn));
        String line = reader.readLine();
        int numlines = 0;
        while (line != null) {
            numlines++;
            line = reader.readLine();
         }
        reader.close();

        //Initializes two arrays of strings based on the file input
        // lines for quicksort and mslines for merge sort
        String[] lines = new String[numlines];
        String[] mslines = new String[numlines];
        BufferedReader reader2;
        reader2 = new BufferedReader(new FileReader(fileIn));
        for (int i = 0; i < numlines; i++) {
            lines[i] = reader2.readLine();
            mslines[i] = lines[i];
        }
        reader2.close();


//        for (int i = 0; i < numlines; i++) {
//            System.out.println(mslines[i]);
//        }
        int length = lines.length - 1;

        //times how long the quicksort function takes
        long qsStartTime = System.nanoTime();
        qs(lines, 0, length);
        long qsEndTime = System.nanoTime();
        long qsDuration = (qsEndTime-qsStartTime); // time taken to execute quicksort in nanoseconds

        //times how long the mergesort function takes
        long msStartTime = System.nanoTime();
        ms(mslines, 0 , numlines-1);
        long msEndTime = System.nanoTime();
        long msDuration = (msEndTime-msStartTime); //time taken to execute merge sort in nanoseconds

//         for (int i = 0; i < numlines; i++) {
//             System.out.println(mslines[i]);
//         }

        //creates a file for output and writes the sorted array from quicksort
        File myFile = new File(fileOut);
        BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileOut));
        for (int i = 0; i < numlines; i++) {
            myWriter.write(lines[i] + "\n");
            //myWriter.newLine();
        }
         myWriter.flush(); //resets the writer
        System.out.print("Time taken to execute Quick Sort (in nanoseconds): ");
        System.out.println(qsDuration);
        // writes to second file
         myWriter = new BufferedWriter(new FileWriter(fileOut2));
        for (int i = 0; i  < numlines; i++) {
            myWriter.write(mslines[i] + "\n");
            //myWriter2.newLine();
        }
        System.out.print("Time taken to execute Merge Sort (in nanoseconds): ");
        System.out.print(msDuration);
        myWriter.close();
     }
}

