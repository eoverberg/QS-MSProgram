
import java.io.*;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {

    public static int myPartition(String[] arr, int lo, int hi) {
        String pivotValue = arr[lo];
        //char[] pivotArray = pivotValue.toLowerCase().toCharArray();
        int i = lo;
        int j = hi;
        do {
//            char[] arrayLinei = arr[i].toCharArray();
//            for (int k = 0; k < arrayLinei.length; k++) {
//                arrayLinei[k] = Character.toLowerCase(pivotArray[k]);
//            }
//            char[] arrayLinej = arr[j].toCharArray();
//            for (int k = 0; k < arrayLinej.length; k++) {
//                arrayLinej[k] = Character.toLowerCase(arrayLinej[k]);
//            }
            if (arr[i].compareToIgnoreCase(pivotValue) < 0) {
                i++;
            }
            if (arr[j].compareToIgnoreCase(pivotValue) > 0) {
                j++;
            }
            String swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
        } while (i >= j);
        String swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
        String temp = arr[i];
        arr[i] = arr[lo];
        arr[lo] = temp;
        return j;
    }
    static void qs(String[] arr, int lo, int hi) {
        if (lo < hi) {
            int partIndex = myPartition(arr, lo, hi);

            qs(arr, lo, partIndex - 1);
            qs(arr, partIndex + 1, hi);
        }
    }
    static void merge(String[] arr, int lo, int mid, int hi)  {
        int left = mid - lo + 1;
        int right = hi - mid;
        String[] Left_arr = new String [left];
        String[] Right_arr = new String [right];

        System.arraycopy(arr, lo, Left_arr, 0, left);

        for (int j = 0; j < right; ++j)
            Right_arr[j] = arr[mid + 1+ j];

        int i = 0, j = 0;
        int k = lo;
        while (i < left && j < right) {
            System.out.println(Left_arr[i]);
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
    static void ms(String[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = floorDiv(lo+hi, 2);
            ms(arr, lo, mid);
            ms(arr, mid+1, hi);
            merge(arr, lo, mid, hi);
        }
    }
    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {
        Scanner input = new Scanner(System.in);
        //String fileIn = "sample10.txt";
        String fileOut = "output.txt";
        System.out.println ("Enter a file to be read: ");
        String fileIn = input.nextLine();
         //System.out.println ("Enter a name for the first output file: ");
         //String fileOut = input.nextLine();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileIn));
        String line = reader.readLine();
        int numlines = 0;
        while (line != null) {
            numlines++;
            line = reader.readLine();
         }
        reader.close();
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
        long qsStartTime = System.nanoTime();
        //qs(lines, 0, length);
        long qsEndTime = System.nanoTime();
        long qsDuration = (qsEndTime-qsStartTime); // time taken to execute quicksort in nanoseconds
        long msStartTime = System.nanoTime();
        ms(mslines, 0 , numlines-1);
        long msEndTime = System.nanoTime();
        long msDuration = (msEndTime-msStartTime); //time taken to execute merge sort in nanoseconds
         for (int i = 0; i < numlines; i++) {
             System.out.println(mslines[i]);
         }
        File myFile = new File(fileOut);
        BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileOut));
        for (int i = 0; i < numlines; i++) {
            myWriter.write(lines[i] + "\n");
            //myWriter.newLine();
        }
         myWriter.flush();
        System.out.print("Time taken to execute Quick Sort (in nanoseconds): ");
        System.out.println(qsDuration);
        //System.out.println ("Enter a name for the second output file: ");
        //String fileOut2 = input.nextLine();
        String fileOut2 = "output2.txt";
        File myFile2 = new File(fileOut2);
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

