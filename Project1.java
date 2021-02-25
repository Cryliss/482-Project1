// Sabra Bilodeau
// Project 1
// 2/20/2021
// Determine if a given matching is stable.

// ************************************************************************

import java.util.*;
import java.io.*;

// This project reads an instance of StableMarriage from a file,
// input.txt, and determines whether or not the matching is stable.
// The first line of the file will be an integer N representing the
// number of men.
// First N lines: preferences of each man
// Second N lines: preferences of each woman
// Third N lines: matchings
// Output:
//    Stable
//    Unstable X Y
//       where man X prefers woman Y to his partner
//       and woman Y prefer man X to her partner
public class Project1 {
   public static void main(String[] args) {
      StableMarriage sm = getInput();
      sm.EvaluateMatches();
   }

   // Reading from input.txt and creating an instance
   // of stable marriage
   private static StableMarriage getInput() {
      // Initialize scanner
      Scanner sc = null;
      try {
         sc = new Scanner(new File("input.txt"));
      }
      catch(FileNotFoundException e) {
         System.out.println("Did you forget the input file?");
         System.exit(1);
      }

      int idx=0, idx2=0, pos=0, n = sc.nextInt();

      int[][] men = new int[n][n];
      int[][] women = new int[n][n];
      int[][] matches = new int[n][2];

      while (sc.hasNextInt()) {
         if (pos < (n*n)) {
            if (idx2 == n) {
               idx2 = 0;
               idx++;
            }
            men[idx][idx2] = sc.nextInt();
            idx2++;
         } else if (pos < (2*(n*n))) {
            if (idx2 == n) {
               idx2 = 0;
               idx++;
            }
            women[idx][idx2] = sc.nextInt();
            idx2++;
         } else {
            if (idx2 == 2) {
               idx2 = 0;
               idx++;
            }
            matches[idx][idx2] = sc.nextInt();
            idx2++;
         }
         pos++;

         if (pos == n*n) {
            idx = 0;
            idx2 = 0;
         } else if (pos == 2*(n*n)) {
            idx = 0;
            idx2 = 0;
         }
      }

      // Initialize new instance of Stable Marriage with the provided
      // input values
      StableMarriage sm = new StableMarriage(n, men, women, matches);
      return sm;
   }
}
