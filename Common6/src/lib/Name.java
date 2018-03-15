package lib;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Simon Guevara
 */
public class Name implements Serializable, Comparable<Name> {


    private String firstname;
    private String lastname;
    private static final long serialVersionUID = 42031768871L;


    /**
     * @param String,String
     * @return void
     * @throws N/A
     * @author Simon Guevara
     */
    public Name(String fname, String lname) {
        validate(fname);
        validate(lname);
        this.firstname = fname;

        this.lastname = lname;

    }


    /**
     * @return void
     * @author Simon Guevara
     */
    public Name(Name name) {

        this.firstname = name.getFirstname();
        this.lastname = name.getLastname();
    }


    /**
     * returns a string with first name
     *
     * @return String
     * @author Simon Guevara
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * returns a string with last name
     *
     * @return String
     * @author Simon Guevara
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * returns a string with fullName
     *
     * @return String
     * @author Simon Guevara
     */
    public String getFullName() {
        return (firstname + " " + lastname);

    }


    /*
     * Validate
     * @param: String name
     * @Return: String
     * Throws: IllegalArgumentException
     *
     * Checks if the name is over characters long
     * Goes through the name and check if it contains a ', a -, or a space
     * if it does, that means that everything before it was the first name, so then it check if the first name is at least 2 characters long
     * if it's not a seperator and not a letter, throw an exception
     * at the end it returns the validated name
     *
     */
    public static String validate(String name) throws IllegalArgumentException {
    	
    	 if (name == null) {
             throw new IllegalArgumentException("You need a non-null name.");
         }
        name = name.trim();
       
       
        if (name.length() < 2) {
            throw new IllegalArgumentException("You need a longer name");
        }
        for (int i = 0; i < name.length(); i++) {
            if ((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= 'a'
                    && name.charAt(i) <= 'z')) {

           
            } else if (name.charAt(i) == 39 || name.charAt(i) == '-' || name.charAt(i) == ' ') {

                if (i == name.length() - 2 || i == 0) {
                    throw new IllegalArgumentException(
                            "You cannot have specials characters at the edges of the name");
                }
            } else if ( i!=0 && name.charAt(i - 1) == 39 || i!=0 && name.charAt(i - 1) == '-'
                    || i!=0 && name.charAt(i - 1) == ' ') {
                throw new IllegalArgumentException("You cannot have specials characters back to back");

            } else {
                throw new IllegalArgumentException(name + " is not a valid name.");
            }
        }
        
        
        return name;
    }

    /**
     * returns a string with last name
     *
     * @return Int
     * @author Simon Guevara
     */
    @Override
    public int compareTo(Name name) throws IllegalArgumentException {

        if (name == null) {
            throw new IllegalArgumentException("Please send non null Name");
        }

        if (name.getLastname().compareToIgnoreCase(this.getLastname()) == 0) {
            return name.getFirstname().compareToIgnoreCase(this.getFirstname());
        }

        return name.getLastname().compareToIgnoreCase(this.getLastname());

    }

    //Overriding methods
 
 /*
  * 
  * equals method
  * @param: Object object
  * @Returns boolean
  * Checks if it's null, if it's the exact same object, if it's a name, and then if it has the same full name
  * if everything all test fail, return false
  */


    @Override
    public boolean equals(Object object) {

        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        return (firstname + " " + lastname).equalsIgnoreCase(((Name) object).getFullName());


    }

    /*
     * hashCODE
     * Param: void
     * Return int
     * generates a hashcode
     *
     */
    @Override
    public int hashCode() {

        return Objects.hash(this.getFullName().toUpperCase(), serialVersionUID);

    }


    /*
     * toStrin()
     * Param:n/a
     * Return: String
     * Formats the name a first name followed by a space then the last name
     */
    @Override
    public String toString() {
        return (this.firstname + "*" + this.lastname);
    }


}