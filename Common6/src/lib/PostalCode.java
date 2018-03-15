/**
 *
 */
package lib;

import java.io.Serializable;

/**
 * @author Saad
 */
public class PostalCode implements Serializable, Comparable<PostalCode> {

    private static final long serialVersionUID = 4203172017L;
    private final String code;

    /**
     * @return Void
     * @author Saad Khan
     */
    public PostalCode(String code) {
        this.code = validate(code);
    }

    /**
     * //compares code with input postalCodes object returns -1,0,1
     *
     * @return int
     * @author Saad Khan
     */
    @Override
    public int compareTo(PostalCode postal) {
        return this.code.compareTo(postal.getCode());
    }


    /**
     * checks if object is valid and not empty then change to UpperCase then return true or false if
     * hey are equal
     *
     * @return boolean
     * @author Saad Khan
     */
    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            PostalCode objectNew = (PostalCode) object;
            String objectString = objectNew.getCode();
            if (objectString.equals(this.getCode())) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Wronge");
        }
    }


    /**
     * returns the hash code of code
     *
     * @return int
     * @author Saad Khan
     */
    @Override
    public final int hashCode() {
        return this.code.toUpperCase().hashCode();
    }

    /*returns code
     * @author	Saad Khan
     * @param	void
     * @return	String
     * @throws	N/A
     */
    public String getCode() {
        return code;

    }

    /**
     * checks if postal code is in range of the start and end string
     *
     * @return boolean
     * @author Saad Khan
     */
    public boolean inRange(String start, String end) {

        if (start.length() == 7) {
            start = SevenToSix(start);
        }
        start = start.toUpperCase();
        if (start.length() < 6) {
            start = changeToUpperPostalCode(start);
        }

        if (end.length() == 7) {
            end = SevenToSix(end);
        }
        end = end.toUpperCase();
        if (end.length() < 6) {
            end = changeToLowerPostalCode(end);
        }
        if (start.compareTo(code) < 0 && end.compareTo(code) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds padding to incomplete Strings
     *
     * @return String
     * @author Saad Khan
     */
    private String changeToLowerPostalCode(String end) {
        int size = 6 - end.length();
        String endPhrase = "Z9Z9Z9";
        switch (size) {
            case 1:
                end = end.substring(0, 5) + endPhrase.substring(size, 2);
                break;
            case 2:
                end = end.substring(0, 4) + endPhrase.substring(size - 1, 3);
                break;
            case 3:
                end = end.substring(0, 3) + endPhrase.substring(size - 2, 4);
                break;
            case 4:
                end = end.substring(0, 2) + endPhrase.substring(size - 3, 5);
                break;
            case 5:
                end = end.substring(0, 1) + endPhrase.substring(size - 4, 6);
                break;
        }
        return end;
    }

    /**
     * Adds padding to incomplete Strings
     *
     * @return String
     * @author Saad Khan
     */
    private String changeToUpperPostalCode(String start) {
        int size = 6 - start.length();
        String startPhrase = "A0A0A0";
        switch (size) {
            case 1:
                start = start.substring(0, 5) + startPhrase.substring(size, 2);
                break;
            case 2:
                start = start.substring(0, 4) + startPhrase.substring(size - 1, 3);
                break;
            case 3:
                start = start.substring(0, 3) + startPhrase.substring(size - 2, 4);
                break;
            case 4:
                start = start.substring(0, 2) + startPhrase.substring(size - 3, 5);
                break;
            case 5:
                start = start.substring(0, 1) + startPhrase.substring(size - 4, 6);
                break;
        }
        return start;
    }

    //returns string as uppercode
    @Override
    public String toString() {
        return code.toUpperCase();
    }

    /*Validates postal code and if invalid throws an exception
     *Adds padding to incomplete Strings
     *  @author	Saad Khan
     * @param	String
     * @return	code
     * @throws	IllegalArgumentException
     */
    private static String validate(String code) throws IllegalArgumentException {
        if (code.length() > 7 || code.length() < 6) {
            throw new IllegalArgumentException("validate: String too long or too short");
        }
        if (code.length() == 7) {
            code = SevenToSix(code);
        }
        code = ChangeCaseUpper(code);
        // D, F,I, O, Q and U are NOT permitted. Also, the first position cannot be W or Z.
        if (Character.isLetter(code.charAt(0)) && Character.isDigit(code.charAt(1)) && Character
                .isLetter(code.charAt(2)) && Character.isDigit(code.charAt(3)) && Character
                .isLetter(code.charAt(4)) && Character.isDigit(code.charAt(5))
                && code.charAt(0) != 'D' && code.charAt(0) != 'F' && code.charAt(0) != 'I'
                && code.charAt(0) != 'O' && code.charAt(0) != 'Q' && code.charAt(0) != 'U'
                && code.charAt(0) != 'W' && code.charAt(0) != 'Z'
                && code.charAt(2) != 'D' && code.charAt(2) != 'F' && code.charAt(2) != 'I'
                && code.charAt(2) != 'O' && code.charAt(2) != 'Q' && code.charAt(2) != 'U'
                && code.charAt(4) != 'D' && code.charAt(4) != 'F' && code.charAt(4) != 'I'
                && code.charAt(4) != 'O' && code.charAt(4) != 'Q' && code.charAt(4) != 'U') {
            return code;
        } else {
            throw new IllegalArgumentException("Validation: Somthing went wrong with Postal Code");
        }
    }

    /**
     * Change Postal Code from lower case to upper case
     *
     * @return String
     * @author Saad Khan
     */
    private static String ChangeCaseUpper(String toChange) {
        toChange = toChange.toUpperCase();
        return toChange;

    }

    /* Convert postal code with space to without space
     * @author	Saad Khan
     * @param	String
     * @return	String
     * @throws	N/A
     */
    private static String SevenToSix(String codespace) {
        return codespace.substring(0, 3) + codespace.substring(4, 7);
    }
}