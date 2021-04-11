/** Pg 827
    LengthComparator: "compares" the length of 2 Strings.
 */

package dev.liambloom.softwareEngineering.chapter13.introToComparator;

import java.util.Comparator;

public final class LengthComparator implements Comparator<String> {
   public int compare(String s1, String s2) {
       return s1.length() - s2.length();
   } 
}  // LengthComparator