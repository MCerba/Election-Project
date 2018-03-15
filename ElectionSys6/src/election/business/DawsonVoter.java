/**
 * @author Simon Guevara-Ponce
 */
/**

 * @author Simon Guevara-Ponce
 *
 */

package election.business;

import election.business.interfaces.Election;
import election.business.interfaces.Voter;
import java.io.Serializable;
import lib.Name;
import lib.Email;
import lib.PostalCode;
import java.time.LocalDate;
import java.util.Objects;


public class DawsonVoter implements Voter, Comparable<Voter> {

  private Name name;
  private Email email;
  private PostalCode postalcode;
  private static final long serialVersionUID = 42031768871L;

  public DawsonVoter(String fname, String lname, String email, String postalcode) {

    this.name = new Name(fname, lname);
    this.email = new Email(email);
    this.postalcode = new PostalCode(postalcode);


  }

  //Geters

  @Override
  /*
   * @name getName()
   * @param 
   * @return
   * 
   * 
   */
  public Name getName() {

    return name;

  }

  @Override
  /*
   * @name getEmail()
   * @param 
   * @return
   * 
   * 
   */
  public Email getEmail() {
    return email;
  }

  @Override
  /*
   * @name getPostalCode()
   * @param 
   * @return
   * 
   * 
   */
  public PostalCode getPostalCode() {
    return postalcode;
  }


  @Override
  /*
   * @name setPostalCode()
   * @param copycode
   * @return
   * 
   * 
   */
  public void setPostalCode(PostalCode copycode) {

    postalcode = new PostalCode(copycode.getCode());
  }


  /*
   * @author Simon Guevara
   * @name isEligible()
   * @param Election
   * @return boolean
   * 
   * 
   */
  public boolean isEligible(Election election) {

    if (LocalDate.now().isBefore(election.getStartDate()) || LocalDate.now()
        .isAfter(election.getEndDate())) {
      return false;
    }

    if (election.isLimitedToPostalRange() && (!this.postalcode
        .inRange(election.getPostalRangeStart(), election.getPostalRangeEnd()))) {
      return false;
    }

    return true;

  }


  /*
   * @author Simon Guevara
   * @name equals()
   * @param Object
   * @return boolean
   *
   *
   */
  final public boolean equals(Object object) {

    if (object == null) {
      return false;
    }

    if (object instanceof DawsonVoter && ((Voter) object).getEmail().getAddress() == this.email
        .getAddress()) {
      return true;
    }

    return false;


  }


  /*
   * @author Simon Guevara
   * @name hashCODE()
   * @param
   * @return int
   *
   *
   */
  final public int hashCODE() {

    return Objects.hash(this.email.getAddress().toUpperCase(), serialVersionUID);

  }

  @Override
       
       /*
        * @author Simon Guevara
        * @name toString()
        * @param 
        * @return String
        * 
        * 
        */
  public String toString() {
    return (email.getAddress() + "*" + this.name.toString() + "*" + this.postalcode.toString());
  }


  @Override
       
       /*
        * @author Simon Guevara
        * @name compareTo()
        * @param Voter
        * @return int
        * @throws IllegalArgumentException
        * 
        * 
        */
  public int compareTo(Voter object) throws IllegalArgumentException {

    if (object == null) {
      throw new IllegalArgumentException("Enter a non-null voter");
    }

    if (object == this) {
      return 0;
    }

    DawsonVoter comp = ((DawsonVoter) object);

    return this.email.compareTo(((DawsonVoter) comp).getEmail());


  }


}
