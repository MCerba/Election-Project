/**
 *
 */
package election.business;


import election.business.interfaces.BallotItem;

/**
 * @author Saad Khan
 */
public class DawsonBallotItem implements BallotItem {

    private static final long serialVersionUID = 42031768871L;
    public final int maxValue;
    public String choice;
    public int value = 0;

    /**
     * @param maxValue, choice
     * @return void
     * @author Saad Khan
     */
    public DawsonBallotItem(String choice, int maxValue) throws IllegalArgumentException {  // int-String
        if (maxValue < 1 || choice.equals(null) || choice.equals("")) {
            throw new IllegalArgumentException(
                    "BallotItem:Somthing went wrong" + maxValue + " " + choice);
        } else {
            this.maxValue = maxValue;
            this.choice = choice;
        }
    }  // problem is here

    /**
     * @return Void
     * @author Saad Khan
     */
    public DawsonBallotItem(BallotItem item) {
        if (item instanceof DawsonBallotItem) {
            item = (DawsonBallotItem) item;
        }
        this.maxValue = item.getMaxValue();
        this.choice = item.getChoice();
    }

    /**
     * @return String
     * @author Saad Khan
     */
    @Override
    public String toString() {
        return choice + "*" + value;
    }

    /**
     * @return int
     * @author Saad Khan
     */
    @Override
    public final int hashCode() {
        return this.choice.toUpperCase().hashCode();
    }

    /**
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
        DawsonBallotItem item = new DawsonBallotItem((DawsonBallotItem) object);
        if (choice.equalsIgnoreCase(item.getChoice())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return int
     * @author Saad Khan
     */
    @Override
    public int compareTo(BallotItem item) {
        return this.choice.compareTo(item.getChoice());
    }

    /**
     * @return int
     * @author Saad Khan
     */
    @Override
    public int getMaxValue() {
        return this.maxValue;
    }

    /**
     * @return String
     * @author Saad Khan
     */
    @Override
    public String getChoice() {
        return this.choice;
    }

    /**
     * @return int
     * @author Saad Khan
     */
    public int getValue() {
        return this.value;
    }

    /**
     * set the value
     *
     * @return void
     * @author Saad Khan
     */
    public void setValue(int toSet) {
        if (toSet >= 0 && toSet <= maxValue) {
            this.value = toSet;
        } else {
            throw new IllegalArgumentException("setValue: to set not valid");
        }
    }

}
