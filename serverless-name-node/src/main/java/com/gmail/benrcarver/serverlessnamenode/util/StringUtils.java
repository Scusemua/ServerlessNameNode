package com.gmail.benrcarver.serverlessnamenode.util;

import com.google.common.base.Preconditions;

import java.util.*;

public class StringUtils {

    final public static String[] emptyStringArray = {};
    final public static char COMMA = ',';
    final public static String COMMA_STR = ",";
    final public static char ESCAPE_CHAR = '\\';

    /** The same as String.format(Locale.ENGLISH, format, objects). */
    public static String format(final String format, final Object... objects) {
        return String.format(Locale.ENGLISH, format, objects);
    }

    /**
     * Splits a comma or newline separated value <code>String</code>, trimming
     * leading and trailing whitespace on each value.
     *
     * @param str a comma or newline separated <code>String</code> with values,
     *            may be null
     * @return an array of <code>String</code> values, empty array if null String
     *         input
     */
    public static String[] getTrimmedStrings(String str){
        if (null == str || str.trim().isEmpty()) {
            return emptyStringArray;
        }

        return str.trim().split("\\s*[,\n]\\s*");
    }

    /**
     * Converts all of the characters in this String to lower case with
     * Locale.ENGLISH.
     *
     * @param str  string to be converted
     * @return     the str, converted to lowercase.
     */
    public static String toLowerCase(String str) {
        return str.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Converts all of the characters in this String to upper case with
     * Locale.ENGLISH.
     *
     * @param str  string to be converted
     * @return     the str, converted to uppercase.
     */
    public static String toUpperCase(String str) {
        return str.toUpperCase(Locale.ENGLISH);
    }

    /**
     * <p>Checks if the String contains only unicode letters.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = true
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains letters, and is non-null
     */
    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compare strings locale-freely by using String#equalsIgnoreCase.
     *
     * @param s1  Non-null string to be converted
     * @param s2  string to be converted
     * @return     the str, converted to uppercase.
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        Preconditions.checkNotNull(s1);
        // don't check non-null against s2 to make the semantics same as
        // s1.equals(s2)
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * Splits a comma separated value <code>String</code>, trimming leading and
     * trailing whitespace on each value. Duplicate and empty values are removed.
     *
     * @param str a comma separated <String> with values, may be null
     * @return a <code>Collection</code> of <code>String</code> values, empty
     *         Collection if null String input
     */
    public static Collection<String> getTrimmedStringCollection(String str){
        Set<String> set = new LinkedHashSet<String>(
                Arrays.asList(getTrimmedStrings(str)));
        set.remove("");
        return set;
    }

    /**
     * Given an array of bytes it will convert the bytes to a hex string
     * representation of the bytes
     * @param bytes
     * @param start start index, inclusively
     * @param end end index, exclusively
     * @return hex string representation of the byte array
     */
    public static String byteToHexString(byte[] bytes, int start, int end) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes == null");
        }
        StringBuilder s = new StringBuilder();
        for(int i = start; i < end; i++) {
            s.append(format("%02x", bytes[i]));
        }
        return s.toString();
    }

    /**
     * The traditional binary prefixes, kilo, mega, ..., exa,
     * which can be represented by a 64-bit integer.
     * TraditionalBinaryPrefix symbol are case insensitive.
     */
    public enum TraditionalBinaryPrefix {
        KILO(10),
        MEGA(KILO.bitShift + 10),
        GIGA(MEGA.bitShift + 10),
        TERA(GIGA.bitShift + 10),
        PETA(TERA.bitShift + 10),
        EXA (PETA.bitShift + 10);

        public final long value;
        public final char symbol;
        public final int bitShift;
        public final long bitMask;

        private TraditionalBinaryPrefix(int bitShift) {
            this.bitShift = bitShift;
            this.value = 1L << bitShift;
            this.bitMask = this.value - 1L;
            this.symbol = toString().charAt(0);
        }

        /**
         * @return The TraditionalBinaryPrefix object corresponding to the symbol.
         */
        public static TraditionalBinaryPrefix valueOf(char symbol) {
            symbol = Character.toUpperCase(symbol);
            for(TraditionalBinaryPrefix prefix : TraditionalBinaryPrefix.values()) {
                if (symbol == prefix.symbol) {
                    return prefix;
                }
            }
            throw new IllegalArgumentException("Unknown symbol '" + symbol + "'");
        }

        /**
         * Convert a string to long.
         * The input string is first be trimmed
         * and then it is parsed with traditional binary prefix.
         *
         * For example,
         * "-1230k" will be converted to -1230 * 1024 = -1259520;
         * "891g" will be converted to 891 * 1024^3 = 956703965184;
         *
         * @param s input string
         * @return a long value represented by the input string.
         */
        public static long string2long(String s) {
            s = s.trim();
            final int lastpos = s.length() - 1;
            final char lastchar = s.charAt(lastpos);
            if (Character.isDigit(lastchar))
                return Long.parseLong(s);
            else {
                long prefix;
                try {
                    prefix = TraditionalBinaryPrefix.valueOf(lastchar).value;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid size prefix '" + lastchar
                            + "' in '" + s
                            + "'. Allowed prefixes are k, m, g, t, p, e(case insensitive)");
                }
                long num = Long.parseLong(s.substring(0, lastpos));
                if (num > (Long.MAX_VALUE/prefix) || num < (Long.MIN_VALUE/prefix)) {
                    throw new IllegalArgumentException(s + " does not fit in a Long");
                }
                return num * prefix;
            }
        }

        /**
         * Convert a long integer to a string with traditional binary prefix.
         *
         * @param n the value to be converted
         * @param unit The unit, e.g. "B" for bytes.
         * @param decimalPlaces The number of decimal places.
         * @return a string with traditional binary prefix.
         */
        public static String long2String(long n, String unit, int decimalPlaces) {
            if (unit == null) {
                unit = "";
            }
            //take care a special case
            if (n == Long.MIN_VALUE) {
                return "-8 " + EXA.symbol + unit;
            }

            final StringBuilder b = new StringBuilder();
            //take care negative numbers
            if (n < 0) {
                b.append('-');
                n = -n;
            }
            if (n < KILO.value) {
                //no prefix
                b.append(n);
                return (unit.isEmpty()? b: b.append(" ").append(unit)).toString();
            } else {
                //find traditional binary prefix
                int i = 0;
                for(; i < values().length && n >= values()[i].value; i++);
                TraditionalBinaryPrefix prefix = values()[i - 1];

                if ((n & prefix.bitMask) == 0) {
                    //exact division
                    b.append(n >> prefix.bitShift);
                } else {
                    final String  format = "%." + decimalPlaces + "f";
                    String s = format(format, n/(double)prefix.value);
                    //check a special rounding up case
                    if (s.startsWith("1024")) {
                        prefix = values()[i];
                        s = format(format, n/(double)prefix.value);
                    }
                    b.append(s);
                }
                return b.append(' ').append(prefix.symbol).append(unit).toString();
            }
        }
    }
}
