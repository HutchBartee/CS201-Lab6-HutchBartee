import java.io.PrintWriter;
import java.util.ArrayList;

public class ArrayFunctions{
    //PRE:  accepts a string
    //POST: determines that each character in the string is a digit
    //      returns true if so, false if not
    public static boolean isDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i))))
                return false;
        }
        return true;
    }

    //PRE: accepts a string
    //POST: verifies that the string only contains 0s and 1s
    //      returns true if so, false if not
    public static boolean isBinary(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0' || str.charAt(i) == '1')
                continue;
            else
                return false;
        }
        return true;
    }

    //PRE: This function will accept the input string, an empty 2-D ArrayList, and the output file
    //POST: if ‘valid’ return 1, if not print a message and return -1
	//      if ‘valid’ load the ArrayList with the values in the input string and return 1

    public static int verifyArrayList(String inputLine, 
                                      ArrayList<ArrayList<Integer>> arr1, 
                                      PrintWriter outFile) {
        String[] tokens = inputLine.trim().split(" ");

        //test to make sure there are at least 2 tokens for row & col
        if(inputLine.length() < 2){
            outFile.println("\tInvalid matrix size.");
            return -1;
        }
        
        //use position [0] for number of rows and position[1] for number of cols
        int row, col;
        try {
            row = Integer.parseInt(tokens[0]);
            col = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            outFile.println("\tMatrix contains invalid row/col values.");
            return -1;
        }

        //PART 2:  check the row & col values are between 1 and 5 (inclusive) 
        //PART 2:  check that tokens.length has enough data for the matrix
        if((row >= 1 && row <= 5) && (col >= 1 && col <= 5)){
            if((row * col) != (tokens.length - 2)){
                outFile.println("\tLength of matrix mismatch.");
                return -1;
            }
        }
        else{
            outFile.println("\tInvalid matrix size.");
            return -1;
        }

        //starting at position 2 in tokens, read remaining characters
        //see lecture example for loading individual entries in tokens into the ArrayList
        //PART 2:  verfiy that each value in token is isBinary
        for(int i = 2; i < tokens.length; i++){
            if(isBinary(tokens[i]) == false){
                outFile.println("\tMatrix not binary.");
                return -1;
            }
        }

        for(int tempR = 0; tempR < row; tempR++){
            ArrayList<Integer> rowVals = new ArrayList<Integer>();
            for(int tempC = 0; tempC < col; tempC++){
                rowVals.add(Integer.parseInt(tokens[2 + (tempR * col) + tempC]));
            }
            arr1.add(rowVals);
        }

        return 1;
    }


    //PRE: accepts a 2-D ArrayList that has been loaded with proper values and the output file
    //POST: prints the 2-D ArrayList to the output file
    public static void printArrayList(ArrayList<ArrayList<Integer>> arr1, 
                                      PrintWriter outFile) {
        //print 2-D ArrayList
        for(ArrayList<Integer> row : arr1){
            outFile.print("\t");
            for(Integer value : row){
                outFile.print(value + " ");
            }
            outFile.println();
        }
    }

    //PRE:  Accepts one 2 dimensional ArrayList that has been loaded with proper values
    //      and the output file
    //POST: Deterimines if the number of rows = number of columns for this ArrayList
    //   	if not, prints the message: "Cannot determine reflexive/symmetric: Invalid dimensions"
    //  	if so, determines if the ArrayList is reflexive or symmetric and prints out a message for each  

    public static void processArrayList(ArrayList<ArrayList<Integer>>  arr1, 
                                                  PrintWriter outFile) {
   
        //If the number of rows != number of columns, print message & return
        int rows = arr1.size();
        int cols = arr1.get(0).size();
        if(rows != cols){
            outFile.print("\tCannot determine reflexive/symmetric: Invalid dimensions\n");
            return;
        }
        //Check for Reflexive
        int reflexive = 0;
        int ver = 0;
        for(int i = 0; i < arr1.size(); i++){
            if(arr1.get(i).get(i) == 1){
                ver += 1;
            }
        }
        if(ver == rows){
            reflexive = 1;
        }
        //Check for Symmetric
        int symmetric = 0;
        int ver2 = 0;
        for(int i = 0; i < arr1.size(); i++){
            for(int j = 0; j < arr1.size(); j++){
                if(arr1.get(i).get(j) == arr1.get(j).get(i)){
                    ver2 += 1;
                }
            }
        }
        if(ver2 == rows * cols){
            symmetric = 1;
        }

        //Print results
        if(reflexive == 1 && symmetric == 1){
            outFile.print("\tBoth reflexive and symmetric.\n");
        }
        else if(reflexive == 1){
            outFile.print("\tReflexive, not symmetric.\n");
        }
        else if(symmetric == 1){
            outFile.print("\tSymmetric, not reflexive.\n");
        }
        else{
            outFile.print("\tNeither reflexive nor symmetric.\n");
        }
    }

    //PRE:  Accepts two 2 dimensional ArrayLists that have been loaded with proper values
    //      and the output file
    //POST: Determine if the the ArrayLists are the same size
    //       if not, print the message: "Unable to add Matrices: Matrices have different sizes." to the output file & return
    //       if so, add the corresponding elements (row,col) from each ArrayList and print to output file 
    //
    public static void addTwoMatrices(ArrayList<ArrayList<Integer>>  arr1, 
                                      ArrayList<ArrayList<Integer>>  arr2,
                                      PrintWriter outFile) {
        //verify if matrices can be added 
        //If the matrix sizes do not match print message & return
        int rows1 = arr1.size();
        int cols1 = arr1.get(0).size();
        int rows2 = arr2.size();
        int cols2 = arr2.get(0).size();
        if((rows1 != rows2) || (cols1 != cols2)){
            outFile.println("\tUnable to add matrices: size mismatch.");
            return;
        }
        //add corresponding elements & prints the result to the output file
        for(int i = 0; i < rows1; i++){
            outFile.print("\t");
            for(int j = 0; j < cols1; j++){
                int added = arr1.get(i).get(j) + arr2.get(i).get(j);
                outFile.print(added + " ");
            }
            outFile.println();
        }
    }
}