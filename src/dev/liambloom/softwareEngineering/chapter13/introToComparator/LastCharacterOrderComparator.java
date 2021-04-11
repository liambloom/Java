/**
    LastCharacterOrderComparator: "compares" the last letter of 2 Strings.
 */

 package dev.liambloom.softwareEngineering.chapter13.introToComparator;

import java.util.Comparator;

public final class LastCharacterOrderComparator implements Comparator<CharSequence> {
    public int compare(CharSequence s1, CharSequence s2) {
        return s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1);
    }                                                                                                                                                                                                                                                                                                                                                                                                                     
}
