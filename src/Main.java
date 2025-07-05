import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        Customer customer = new Customer("ahmed", "01554092578", 1000);
        Product p = new Product("google card", 20, 3);
        Product p1 = new ShippableProduct("TV", 150, 3, 200);
        Product p2 = new ShippableExpirableProduct("cheese", 2.5, 3, 2.15, LocalDate.of(2025, 7, 8));
        customer.add_to_cart(p, 1);
        customer.add_to_cart(p1, 1);
        customer.add_to_cart(p2, 2);
        customer.check_out();
    }
}