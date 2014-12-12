import java.util.*;

public class Store
{
    ArrayList<Customer> topCustomer = new ArrayList();
    
    public void addSale(String customerName, double ammount)
    {
        Customer customer  = new Customer(customerName, ammount);
        topCustomer.add(customer);        
    }
    public String nameOfBestCustomer()
    {
       String bestCustomer = topCustomer.get(0);
       for (int i = 0; i<=(topCustomer.size()-1); i ++)
       {
           
       }
    }

}

