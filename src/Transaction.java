/**
 * @author Johan Rickardo Roxas
 * @version 1.00 (2023/06/07)
 * TODO: Documentation
 */
public class Transaction {

    /**
     * Type of Transaction (Deposit or Withdrawal)
     */
    private String type;

    /**
     * Amount of Transaction
     */
    private double amount;

    /**
     * Date of Transaction
     */
    private String date;

    /**
     * Constructs an object of Transaction with default values.
     */
    public Transaction() {
        type = "Deposit";
        amount = 1000.00;
        date = "2023-06-07 13:25:05";
    } // end of Transaction default constructor

    /**
     * Constructs an object of Transaction with user-defined values
     * @param type given type
     * @param amount given amount
     * @param date given date
     */
    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    } // end of Transaction constructor

    /**
     * Mutates the state of the type attribute
     * @param type state/value of type attribute
     */
    public void setType(String type) {
        this.type = type;
    } // end of setType method

    /**
     * Mutates the state of the amount attribute
     * @param amount state/value of amount attribute
     */
    public void setAmount(double amount) {
        this.amount = amount;
    } // end of setAmount mutator method

    /**
     * Mutates the state of the date attribute
     * @param date state/value of date attribute
     */
    public void setDate(String date) {
        this.date = date;
    } // end of setDate mutator method

    /**
     * Accesses the state of the type attribute
     * @return state/value of type attribute
     */
    public String getType() {
        return type;
    } // end of getType accessor method

    /**
     * Accesses the state of the amount attribute
     * @return state/value of amount attribute
     */
    public double getAmount() {
        return amount;
    } // end of getAmount accessor method

    /**
     * Accesses the state of the date attribute
     * @return state/value of date attribute
     */
    public String getDate() {
        return date;
    } // end of getDate accessor method

    /**
     * Concatenates the attribute states of Transaction
     * @return states as comma-separated values
     */
    public String toString() {
        return type + "," +
                amount + "," +
                date;
    } // end of toString method
} // end of class Transaction
