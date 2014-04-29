/**
 * This file is part of Zap Framework.
 *
 * Zap is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Zap is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Zap. If not, see <http://www.gnu.org/licenses/>.
 */
package com.zap.util;

import com.zap.Zap;
import com.zap.game.map.Location;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public class GameUtilities {

    /**
     * A logger used to report error messages.
     */
    private static final Logger logger = Logger.getLogger(Zap.class.getName());

    /**
     * Prints any required message to the command prompt
     *
     * @param msg the input field
     */
    public static void print(String msg) {
        logger.info(msg);
    }

    /**
     * Reads RuneScape protocol string from the buffer. RuneScape protocol
     * strings are split by character 10 while by default strings tend to be
     * split by character 0.
     *
     * @param buffer the byte buffer to read string from
     * @return the RuneScape protocol string
     */
    public static String getRSString(ByteBuffer buffer) {
        byte data;
        StringBuilder builder = new StringBuilder();
        while ((data = buffer.get()) != 10) {
            builder.append((char) data);
        }
        return builder.toString();
    }

    /**
     * Gets long value from the specified string.
     *
     * @param str the string for long conversation
     *
     * @return the long value
     */
    public static long getLongString(String str) {
        long l = 0L;
        for (int i = 0; i < str.length() && i < 12; i++) {
            char c = str.charAt(i);
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

    public static int getDistance(Location a, Location b) {
        int deltaX = b.getX() - a.getX();
        int deltaY = b.getY() - a.getY();
        return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    /**
     * Calculates the direction between the two coordinates.
     *
     * @param dx the first coordinate
     * @param dy the second coordinate
     *
     * @return the direction
     */
    public static int getDirection(int dx, int dy) {
        if (dx < 0) {
            if (dy < 0) {
                return 5;
            } else if (dy > 0) {
                return 0;
            } else {
                return 3;
            }
        } else if (dx > 0) {
            if (dy < 0) {
                return 7;
            } else if (dy > 0) {
                return 2;
            } else {
                return 4;
            }
        } else {
            if (dy < 0) {
                return 6;
            } else if (dy > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * Formats the RuneScape string.
     *
     * @param string the string
     *
     * @return the formatted string
     */
    public static String formatString(String string) {
        if (!string.isEmpty()) {
            String result = "";
            for (String part : string.toLowerCase().split(" ")) {
                result += part.substring(0, 1).toUpperCase()
                        + part.substring(1) + " ";
            }
            return result.trim();
        }
        return "";
    }

    /**
     * Converts name to long number.
     *
     * @param s the name
     *
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

    /**
     * Converts long to name string.
     *
     * @param l the long value
     *
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
     * Unpacks the RuneScape text.
     *
     * @param text the text bytes
     *
     * @param length the text length
     *
     * @return the unpacked RuneScape text
     */
    public static String unpackRSText(byte text[], int length) {
        byte[] reversed = new byte[length];
        for (int i = 0; i < length; i++) {
            reversed[i] = text[length - 1 - i];
        }
        byte[] decodeBuf = new byte[4096];
        int idx = 0, highNibble = -1;
        for (int i = 0; i < length * 2; i++) {
            int val = reversed[i / 2] >> (4 - 4 * (i % 2)) & 0xf;
            if (highNibble == -1) {
                if (val < 13) {
                    decodeBuf[idx++] = (byte) XLATE_TABLE[val];
                } else {
                    highNibble = val;
                }
            } else {
                decodeBuf[idx++] = (byte) XLATE_TABLE[((highNibble << 4) + val) - 195];
                highNibble = -1;
            }
        }
        return new String(decodeBuf, 0, idx);
    }
    public static final char XLATE_TABLE[] = {' ', 'e', 't', 'a', 'o', 'i',
        'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g',
        'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(',
        ')', '-', '&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$',
        '%', '"', '[', ']'};
    public static final char VALID_CHARS[] = {'_', 'a', 'b', 'c', 'd', 'e',
        'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&', '*',
        '(', ')', '-', '+', '=', ':', ';', '.', '>', '<', ',', '"', '[',
        ']', '|', '?', '/', '`'};
    public static final byte[] DIRECTION_DELTA_X = new byte[]{-1, 0, 1, -1,
        1, -1, 0, 1};
    public static final byte[] DIRECTION_DELTA_Y = new byte[]{1, 1, 1, 0, 0,
        -1, -1, -1};

    /**
     * sets a random amount in the range specified
     *
     * @param range
     * @return
     */
    public static int random(int range) {
        if (range <= 0) {
            return 0;
        }
        return (int) (Math.random() * (range + 1));
    }
}
