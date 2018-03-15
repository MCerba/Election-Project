/**
 * Defines an Address type.
 */

package lib;


public class Address {

    private String city = "";
    private String civicNumber = "";
    private String province = "";
    private String code = "";
    private String streetName = "";
    String hi = "hi";

    /*
     * @author	Mihail Cebra
     * @param	String, String, string
     * @return	void
     * @throws	N/A
     */
    public Address(String civicNumber, String streetName, String city) {
        this.civicNumber = validateExistence("civic number", civicNumber);
        this.streetName = validateExistence("street name", streetName);
        this.city = validateExistence("city", city);
    }

    /*
     * @author	Mihail Cebra
     * @param	Address
     * @return	void
     * @throws	N/A
     */
    public Address(Address address) {
        this.civicNumber = address.civicNumber;
        this.streetName = address.streetName;
        this.city = address.city;
        this.province = address.province;
        this.code = address.code;
    }

    /*
     * @author	Mihail Cebra
     * @param	Void
     * @return	Void
     * @throws	N/A
     */
    public Address() {
    }

    /**
     * Returns a String representation of the address.
     *
     * @param Void
     * @return String
     * @throws N/A
     * @author Mihail Cebra
     */
    public String getAddress() {
        String address = civicNumber + " " + streetName + "\n" + city;
        address += (province.equals("") ? "" : (", " + province)) +
                (code.equals("") ? "" : ("\n" + code));
        return address;
    }


    /**
     * Returns a String representation the city.
     *
     * @param Void
     * @return String.
     * @throws N/A
     * @author Mihail Cebra
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns a String representation of the Civic Number.
     *
     * @param Void
     * @return String
     * @throws N/A
     * @author Mihail Cebra
     */
    public String getCivicNumber() {
        return civicNumber;
    }

    /**
     * Returns a String representation of the province.
     *
     * @param void
     * @return String
     * @throws N/A
     * @author Mihail Cebra
     */
    public String getProvince() {
        return province;
    }

    /**
     * Returns the code.
     *
     * @param void
     * @return String
     * @throws N/A
     * @author Mihail Cebra
     */
    public String getCode() {
        return code;
    }

    /*
     * Returns a String representation of the StreetName.
     * @author	Mihail Cebra
     * @param	void
      * @return	String.
      * @throws	N/A
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * changes the city variable
     *
     * @param String
     * @return void
     * @throws N/A
     * @author Mihail Cebra
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * sets the civic number
     *
     * @param String
     * @return void
     * @throws N/A
     * @author Mihail Cebra
     */
    public void setCivicNumber(String civicNumber) {
        this.civicNumber = validateExistence("civicNumber", civicNumber);
    }

    /**
     * sets the province
     *
     * @param String
     * @return void
     * @throws N/A
     * @author Mihail Cebra
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * sets the code
     *
     * @param String
     * @return void
     * @throws N/A
     * @author Mihail Cebra
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * sets the Street Name
     *
     * @param String
     * @return void
     * @throws N/A
     * @author Mihail Cebra
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * returns a string with the civic number, street name, city, province, code
     *
     * @param void
     * @return String
     * @throws N/A
     * @author Mihail Cebra
     */
    @Override
    public String toString() {
        return (civicNumber + "*" + streetName + "*" +
                city + "*" + province + "*" + code);
    }

    /**
     * validates fields inputed
     *
     * @param String, String
     * @return String
     * @throws IllegalArguementException
     * @author Mihail Cebra
     */
    private String validateExistence(String fieldName, String fieldValue) {
        if (fieldValue == null) {
            throw new IllegalArgumentException("Address Error - " + fieldName
                    + " must exist. Invalid value = " + fieldValue);
        }

        String trimmedString = fieldValue.trim();

        if (trimmedString.isEmpty()) {
            throw new IllegalArgumentException("Address Error - " +
                    fieldName + " must exist. Invalid value = " + fieldValue);
        }
        return trimmedString;
    }

}
