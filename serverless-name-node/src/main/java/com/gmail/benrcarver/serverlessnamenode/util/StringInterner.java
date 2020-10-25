package com.gmail.benrcarver.serverlessnamenode.util;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

/**
 * Provides string interning utility methods. For weak interning,
 * we use the standard String.intern() call, that performs very well
 * (no problems with PermGen overflowing, etc.) starting from JDK 7.
 */
public class StringInterner {
    /**
     * Retains a strong reference to each string instance it has interned.
     */
    private final static Interner<String> STRONG_INTERNER =
            Interners.newStrongInterner();

    /**
     * Interns and returns a reference to the representative instance
     * for any of a collection of string instances that are equal to each other.
     * Retains strong reference to the instance,
     * thus preventing it from being garbage-collected.
     *
     * @param sample string instance to be interned
     * @return strong reference to interned string instance
     */
    public static String strongIntern(String sample) {
        if (sample == null) {
            return null;
        }
        return STRONG_INTERNER.intern(sample);
    }

    /**
     * Interns and returns a reference to the representative instance
     * for any of a collection of string instances that are equal to each other.
     * Retains weak reference to the instance,
     * and so does not prevent it from being garbage-collected.
     *
     * @param sample string instance to be interned
     * @return weak reference to interned string instance
     */
    public static String weakIntern(String sample) {
        if (sample == null) {
            return null;
        }
        return sample.intern();
    }

    /**
     * Interns all the strings in the given array in place,
     * returning the same array.
     */
    public static String[] internStringsInArray(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = weakIntern(strings[i]);
        }
        return strings;
    }

}