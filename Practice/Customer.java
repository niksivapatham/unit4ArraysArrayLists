
public class Customer
{
    /** description of instance variable x (add comment for each instance variable) */
    private String customerName;
    private double ammount;
    
    public Customer(String customerName, double ammount)
    {
        customerName = customerName;
        ammount = ammount;
    }
    
    public String getCustomerName()
    {
        return this.customerName;
    }
    
    public double getAmmount()
    {
        return this.ammount;
    }
}
