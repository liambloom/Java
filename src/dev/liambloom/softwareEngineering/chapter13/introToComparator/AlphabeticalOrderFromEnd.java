/**
    YOURComparator:
    This sorts them by alphabetical order starting from the end. If the
    two {@code CharSequence}s are the same for the last n characters,
    where n is the length of the shorter one, the shorter string comes
    first. It will only return 0 if the CharSequences are identical
 */

 package dev.liambloom.softwareEngineering.chapter13.introToComparator;

import java.util.Comparator;

public final class AlphabeticalOrderFromEnd implements Comparator<CharSequence> {
    public int compare(CharSequence s1, CharSequence s2) {
        final int l1 = s1.length() - 1;
        final int l2 = s2.length() - 1;
        final int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++) {
            final int diff = s1.charAt(l1 - i) - s2.charAt(l2 - i);
            if (diff != 0)
                return diff;
        }
        return l1 - l2;
    }
}