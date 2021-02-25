// Sabra Bilodeau
// Project 1
// 2/20/2021
// Determine if a given matching is stable.

// ************************************************************************

import java.util.*;
import java.io.*;

// Class for representing a person in a stable marriage
public class Person {
   public int number;
   public int match;
   public int matchIndex;
   public int[] preferences;

   // Constructor
   public Person(int n, int[] preferences) {
      this.number = n;
      this.preferences = preferences;
      return;
   }

   // Set the match for this person and determine
   // the index of the match in the array of preferences
   public void SetMatch(int n) {
      this.match = n;
      for (int i = 0; i < preferences.length; i++) {
         if (preferences[i] == n) {
            this.matchIndex = i;
         }
      }
   }

   // Get the preference at index n
   public int GetPreference(int n) {
      return preferences[n-1];
   }

   // Get the index in the preferences array of person n
   public int GetPreferenceIndex(int n) {
      for (int i = 0; i < preferences.length; i++) {
         if (preferences[i] == n) {
            return i;
         }
      }
      return -1;
   }
}
