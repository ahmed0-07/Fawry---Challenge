import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Cart {
    private double _total;
    private HashMap<Product, Integer> cart;
    private HashMap<String, Product> name_to_product;

    public Cart(){
        _total = 0.0;
        cart = new HashMap<Product, Integer>();
        name_to_product = new HashMap<String, Product>();
    }

    public double get_total(){
        return _total;
    }

    void add(Product product, int quantity){
        if(quantity > product.get_quantity())
            throw new IllegalStateException("Out of stock");

        if(product instanceof Expirable && ((Expirable) product).isExpired())
            throw new IllegalStateException("Expired");

        _total += (quantity * product.get_price());
        cart.put(product, cart.getOrDefault(product, 0) + quantity);
        name_to_product.put(product.get_name(), product);
    }

    Product search_by_name(String name){
        return name_to_product.get(name);
    }

    void remove_by_name(String product_name){
        Product product = search_by_name(product_name.toLowerCase());
        if(product == null)
            return;

        int product_quantity_in_cart = cart.get(product);
        _total -= (product_quantity_in_cart * product.get_price());
        name_to_product.remove(product_name.toLowerCase());
        cart.remove(product);
    }


    void check_out(){
        if(cart.isEmpty()){
            throw new IllegalStateException("Cart is empty!");
        }

        // PRODUCTS WHICH NEED SHIPPING
        Vector<Product> shipments = new Vector<>();
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product product = entry.getKey();
            if(product instanceof Shippable)
                shipments.add(product);
        }

        if(!shipments.isEmpty()){
            System.out.println("** Shipment notice **");
            double total_weight = 0.0;
            for(Product shipment : shipments){
                int quantity = cart.get(shipment);
                System.out.println(cart.get(shipment) + "x " + shipment.get_name() + "      " + (((Shippable) shipment).getWeight() * quantity));
                total_weight += ((Shippable) shipment).getWeight() * quantity;
            }

            System.out.println("Total package weight " + total_weight +"Kg");
        }

        System.out.println("\n** Checkout receipt **");
        double total_price = 0.0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()){
            Product product = entry.getKey();
            int quantity = cart.get(product);
            System.out.println(cart.get(product) + "x " + product.get_name() + "      " + (quantity * product.get_price()));
            total_price += (quantity * product.get_price());
        }

        System.out.println("\n----------------------");
        System.out.println("Subtotal      " + total_price);
        if(!shipments.isEmpty())
            System.out.println("Shipping      " + 30);
        total_price += (!shipments.isEmpty() ? 30 : 0);
        System.out.println("Total      " + total_price);

        if(!shipments.isEmpty()){
            ShipService shipService = new ShipService();
            shipService.start_shipping(shipments);
        }
    }
}
