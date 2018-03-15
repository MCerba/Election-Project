package lib;

/**
 * @author Cerba Mihail
 */
public class Person {
    private Name name;
    private Address address;

    /**
     * @param String, String, Address
     * @return void
     * @throws N/A
     * @author Cerba Mihail
     */
    public Person(String firstName, String lastName, Address address) {
        this.name = new Name(firstName, lastName);
        this.address = new Address(address.getCivicNumber(), address.getStreetName(), address.getCity());
    }

    /**
     * @param String, String
     * @return void
     * @throws N/A
     * @author Cerba Mihail
     */
    public Person(String firstName, String lastName) {
        this(firstName, lastName, new Address("", "", ""));

    }

    /**
     * @param Name, Address
     * @return void
     * @throws N/A
     * @author Cerba Mihail
     */
    public Person(Name n, Address address) {
        this.name = new Name(n.getFirstname(), n.getLastname());
        this.address = new Address(address.getCivicNumber(), address.getStreetName(), address.getCity());
    }

    /**
     * returns a Name
     *
     * @param Void
     * @return Name
     * @throws N/A
     * @author Cerba Mihail
     */
    public Name getName() {
        return new Name(name);
    }

    /**
     * returns a Address
     *
     * @param Void
     * @return Address
     * @throws N/A
     * @author Cerba Mihail
     */
    public Address getAdress() {
        return new Address(address);
    }

    /**
     * @param name the name to set
     */
    //public void setName(String firstName, String lastName) {
    //this.name.setFirstname(firstName);
    //this.name.setLastname(lastName);
    //}

    /**
     * Sets a Name
     *
     * @param Address
     * @return void
     * @throws N/A
     * @author Cerba Mihail
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * returns string representing address
     *
     * @param Void
     * @return String
     * @throws N/A
     * @author Cerba Mihail
     */
    @Override
    public String toString() {

        return name.toString() + "*" +
                (address == null ? "" : address.toString());
    }


}
