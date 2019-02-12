/*
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sudoku by Alex Kowalczuk
University of San Francisco: CS-245
Finished Feb 12, 2019
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

import java.lang.*; 

public class Sudoku 
{ 
  int[] board[]; 
  int N; // number of columns/rows. 
  int SRN; // square root of N 

  // Constructor 
  Sudoku(int N) 
  { 
    this.N = N; 
    Double SRNd = Math.sqrt(N); 
    SRN = SRNd.intValue(); 

    board = new int[N][N]; 
  } 

  public void fillValues() 
  { 
    fillDiagonal(); 
    fillRemaining(0, SRN); // Fill remaining blocks 
  } 

  // Fill the diagonal SRN number of SRN x SRN matrices 
  void fillDiagonal() 
  { 

    for (int i = 0; i<N; i=i+SRN) 
      fillBox(i, i); // for diagonal box, start coordinates (i==j) 
  } 

  // Returns false if given 3 x 3 block contains num. 
  boolean unUsedInBox(int rowStart, int colStart, int num) 
  { 
    for (int i = 0; i<SRN; i++) 
      for (int j = 0; j<SRN; j++) 
        if (board[rowStart+i][colStart+j]==num) 
          return false; 

    return true; 
  } 

  void fillBox(int row,int col) // Fill a 3 x 3 
  { 
    int num; 
    for (int i=0; i<SRN; i++){ 
      for (int j=0; j<SRN; j++){ 
        do{ 
          num = randomGenerator(N); 
        } 
        while (!unUsedInBox(row, col, num)); 

        board[row+i][col+j] = num; 
      } 
    } 
  } 

  int randomGenerator(int num) // Random generator 
  { 
    return (int) Math.floor((Math.random()*num+1)); 
  } 

  boolean CheckIfSafe(int i,int j,int num) // Check if safe to put in cell 
  { 
    return (unUsedInRow(i, num) && 
        unUsedInCol(j, num) && 
        unUsedInBox(i-i%SRN, j-j%SRN, num)); 
  } 

  boolean unUsedInRow(int i,int num) // check in the row for existence 
  { 
    for (int j = 0; j<N; j++) 
    if (board[i][j] == num) 
        return false; 
    return true; 
  } 

  boolean unUsedInCol(int j,int num)  // check in the column for existence 
  { 
    for (int i = 0; i<N; i++) 
      if (board[i][j] == num) 
        return false; 
    return true; 
  } 

  boolean fillRemaining(int i, int j) // A recursive function to fill remaining 
  { 
    if (j>=N && i<N-1){ 
      i = i + 1; 
      j = 0; 
    } 
    if (i>=N && j>=N) 
      return true; 

    if (i < SRN) { 
      if (j < SRN) 
        j = SRN; 
    } 
    else if (i < N-SRN) { 
      if (j==(int)(i/SRN)*SRN) 
        j = j + SRN; 
    } 
    else{ 
      if (j == N-SRN){ 
        i = i + 1; 
        j = 0; 
        if (i>=N) 
          return true; 
      } 
    } 

    for (int num = 1; num<=N; num++){ 
      if (CheckIfSafe(i, j, num)){ 
        board[i][j] = num; 
        if (fillRemaining(i, j+1)) 
          return true; 

        board[i][j] = 0; 
      } 
    } 
    return false; 
  } 

  public void printSudoku() { // Print sudoku 
    System.out.println("======= WELCOME IN MY COMPLETE SUDOKU =======");
    for (int i = 0; i<N; i++){ 
      System.out.println("|-------------||-------------||-------------|");
      for (int j = 0; j<N; j++) 
        System.out.print("| " + board[i][j] + " |"); 
      System.out.println(); 
    } 
    System.out.println("=============================================");
  } 

  public static void main(String[] args) { 
    int N = 9; 
    Sudoku sudoku = new Sudoku(N); 
    sudoku.fillValues(); 
    sudoku.printSudoku(); 
  } 
} 
