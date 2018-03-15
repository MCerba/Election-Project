package lib;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 4203172017L;
	private final String address;

	/*
	 * @author	Austin Antoine
	 * @param	String
	 * @return	void
	 * @throws	N/A
	 */
	public Email(String address) {
		this.address = validateEmail(address);
	}

	/*
	 * @author	Austin Antoine
	 * @param	N/A
	 * @return	String
	 * @throws 	N/A
	 */
	public String getAddress() {
		return this.address;
	}

	/*
	 * @author	Austin Antoine
	 * @param	N/A
	 * @return	String
	 * @throws	N/A
	 */
	public String getUserID() {
		return this.address.substring(0, address.indexOf('@'));
	}

	/*
	 * @author	Austin Antoine
	 * @param	N/A
	 * @return	String
	 * @throws	N/A
	 */
	public String getHost() {
		return this.address.substring(address.indexOf('@') + 1);
	}

	/*
	 * @author	Austin Antoine
	 * @param	Object
	 * @return	boolean
	 * @throws	N/A
	 */
	@Override
	// Two objects are equal if getClass() returns the same class and if the content is the same.
	public final boolean equals(Object object) {

		if (object == null) {
			return false;
		}

		if (object.getClass() == this.getClass()) {

			if (((Email) object).getAddress().equals(this.address)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * @author	Austin Antoine
	 * @param	Email
	 * @return	int
	 * @throws	N/A
	 */
	@Override
	public int compareTo(Email o) {
		if (this.getHost().compareTo(o.getHost()) != 0)
			return this.getHost().compareTo(o.getHost());

		else if (this.getUserID().compareTo(o.getUserID()) != 0)
			return this.getUserID().compareTo(o.getUserID());

		return 0;
	}

	/*
	 * @author	Austin Antoine
	 * @param	N/A
	 * @return	int
	 * @throws	N/A
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.address, serialVersionUID);
	}

	/*
	 * @author	Austin Antoine
	 * @param	N/A
	 * @return	String
	 * @throws	N/A
	 */
	@Override
	public String toString() {
		return this.address + "";
	}

	/*
	 * @author	Austin Antoine
	 * @param	String
	 * @return	String
	 * @throws	IllegalArgumentException
	 */
	private String validateEmail(String email) throws IllegalArgumentException {
		boolean validOrNot;
		String answer;
		String host = email.substring(email.indexOf('@') + 1);
		String userId;

		if (email.matches(".*@.*"))
			userId = email.substring(0, email.indexOf('@'));

		else
			throw new IllegalArgumentException("Email is invalid");

		// If any host segment is > 32 characters
		if (Pattern.matches(".*\\.[\\w|\\d]{33,}\\..*", host))
			answer = "Invalid email";

		// UID must be <= 32 character and email must have '@' symbol
		if (!Pattern.matches(".*@.*", email) || userId.length() > 32 || email == null) {
			answer = "Invalid email";
		}

		// 
		else if (Pattern.matches("@.*\\..*\\.", email) && !Pattern.matches("\\..*$", email) || Pattern.matches(".*\\.\\..*", email) || !Pattern.matches(".*@[^.].*", email)) {
			answer = "Invalid email";
		}
		
			else
		{
		    String pattern = "[^.].*[0-9|A-z|-|_|.]@[^-]*\\..*[^.|-]";
		    validOrNot = Pattern.matches(pattern, email);
		    answer = (validOrNot == true) ? "Valid email" : "Invalid email";
		}
		
		if (answer.equals("Invalid email"))
		{
			throw new IllegalArgumentException("Email is invalid");
		}
		
		return email;
	}
}
