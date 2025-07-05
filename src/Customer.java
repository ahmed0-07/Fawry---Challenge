public class Customer {
    private String _name;
    private String _phone_number;
    private double _balance;
    private Cart _cart;

    Customer(String _name, String _phone_number, double _balance){
        this._name = _name;
        this._phone_number = _phone_number;
        this._balance = _balance;
        this._cart = new Cart();
    }

    void add_to_cart(Product product, int quantity){
        try{
            _cart.add(product, quantity);
        }catch (IllegalStateException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    void remove_from_cart(String product_name){
        _cart.remove_by_name(product_name);
    }

    void check_out(){
        double total = _cart.get_total();
        if(_balance < total){
            System.out.println("Error: Not enough balance");
            return;
        }

        try{
            _cart.check_out();
            _balance -= total;
        }catch (IllegalStateException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}