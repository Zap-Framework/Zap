/**
 *  This file is part of Zap Framework.
 * 
 *  Zap is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Zap is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Zap.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zap.util;

/**
 *
 * @author Faris
 */
public class NameUtilities {
    
    /**
     *  An array containing allowed valid chars from generic keyboard for the transmission data
     */
    public static final char VALID_CHARS[] = {'_', 'a', 'b', 'c', 'd', 'e',
        'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&', '*',
        '(', ')', '-', '+', '=', ':', ';', '.', '>', '<', ',', '"', '[',
        ']', '|', '?', '/', '`'};

    /**
     * sets a random amount in the range specified
     * @param range
     * @return
     */
    public static int random(int range) {
        if (range <= 0) {
            return 0;
        }
        return (int) (Math.random() * (range + 1));
    }

    /**
     * Converts long to name string.
     * @param l the long value
     * @return the name string
     */
    public static String longToName(long l) {
        int i = 0;
        char ac[] = new char[12];
        while (l != 0L) {
            long l1 = l;
            l /= 37L;
            ac[11 - i++] = VALID_CHARS[(int) (l1 - l * 37L)];
        }
        return new String(ac, 12 - i, i);
    }

    /**
     * Converts name to long number.
     * @param s the name
     * @return the long value of the name
     */
    public static long nameToLong(String s) {
        long l = 0L;
        for (int i = 0; i < s.length() && i < 12; i++) {
            char c = s.charAt(i);
            l *= 37L;
            if (c >= 'A' && c <= 'Z') {
                l += (1 + c) - 65;
            } else if (c >= 'a' && c <= 'z') {
                l += (1 + c) - 97;
            } else if (c >= '0' && c <= '9') {
                l += (27 + c) - 48;
            }
        }
        while (l % 37L == 0L && l != 0L) {
            l /= 37L;
        }
        return l;
    }

}
