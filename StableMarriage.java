// Sabra Bilodeau
// Project 1
// 2/20/2021
// Determine if a given matching is stable.

// ************************************************************************

import java.util.*;
import java.io.*;

// This class represents an instance of Stable Marriage
public class StableMarriage {
   protected static int numMatches;
   protected static Person[] men;
   protected static Person[] women;
   protected static int[][] matches;

   // Constructor function
   public StableMarriage(int n, int[][] men, int[][] women, int[][] matches) {
      this.numMatches = n;

      this.men = new Person[n];
      InitializeMen(men);

      this.women = new Person[n];
      InitializeWomen(women);

      this.matches = matches;
      SetMatches();
      return;
   }

   // Initialize the array of men
   protected void InitializeMen(int[][] men) {
      for (int i = 0; i < numMatches; i++) {
         Person man = new Person(i+1, men[i]);
         this.men[i] = man;
      }
   }

   // Initialize the array of women
   protected void InitializeWomen(int[][] women) {
      for (int i = 0; i < numMatches; i++) {
         Person woman = new Person(i+1, women[i]);
         this.women[i] = woman;
      }
   }

   // Sets the match value for each pair
   protected void SetMatches() {
      for (int i = 0; i < numMatches; i++) {
         int man = matches[i][0];
         int woman = matches[i][1];
         men[man-1].SetMatch(woman);
         women[woman-1].SetMatch(man);
      }
   }

   // Evaluate the stablility of each match.
   // If man X prefers woman Y over his match,
   // then the match is unstable.
   // If woman Y prefers man X over her match,
   // then the match is unstable.
   // Otherwise, it is stable.
   public void EvaluateMatches() {
      for (int i = 0; i < numMatches; i++) {
         int man = matches[i][0];
         int man_matchIndex = men[man-1].matchIndex;

         int woman = matches[i][1];
         int woman_matchIndex = women[woman-1].matchIndex;

         if (man_matchIndex > 0) {
            // The current man we are on is matched with someome
            // who is *not* their first choice.
            for (int j = man_matchIndex; j >= 1; j--) {
               // Get the woman # that is listed higher ( > man_matchIndex)
               // in the mans list of preferences.
               // Note: GetPreference returns preferences[n-1]
               int morePrefWoman = men[man-1].GetPreference(j);

               // Get this mans index from his more preferred womans
               // list of preferences
               int hisIdx = women[morePrefWoman-1].GetPreferenceIndex(man);

               // Get the index of her match
               int herMatchIdx = women[morePrefWoman-1].matchIndex;

               // If her matches index is greater than the man we
               // are currently working with, then she prefers this
               // man over her match.
               if (herMatchIdx > hisIdx) {
                  System.out.println("Unstable "+man+" "+morePrefWoman);
                  return;
               }
            }
         } else if (woman_matchIndex > 0) {
            // The current woman we are on is matched with someome
            // who is *not* their first choice.
            for (int j = woman_matchIndex; j >= 1; j--) {
               // Get the man # that is listed higher ( > woman_matchIndex)
               // in the womans list of preferences.
               // Note: GetPreference returns preferences[n-1]
               int morePrefMan = women[woman-1].GetPreference(j);

               // Get this womans index from her more preferred mans
               // list of preferences
               int herIdx = men[morePrefMan-1].GetPreferenceIndex(woman);

               // Get the index of his match
               int hisMatchIdx = men[morePrefMan-1].matchIndex;

               // If his matches index is greater than the woman we
               // are currently working with, then her prefers this
               // woman over her match.
               if (hisMatchIdx > herIdx) {
                  System.out.println("Unstable "+man+" "+morePrefMan);
                  return;
               }
            }
         }
         System.out.println("Stable");
         return;
      }
   }
}
