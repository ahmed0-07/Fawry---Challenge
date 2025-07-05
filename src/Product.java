public class Product {
    private String _name;
    private double _price;
    private int _quantity;

    public Product(String _name, double _price, int _quantity){
        String new_name = _name.toLowerCase(); // TO CONVERT ALL NAMES TO THE SAME FORMAT
        this._name = new_name;
        this._price = _price;
        this._quantity = _quantity;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }

    public void update_quantity(int add){
        this._quantity += add;
    }

    @Override
    public int hashCode(){
        return _name.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;

        if(!(o instanceof Product))
            return false;

        Product other = (Product) o;
        return other._name.equals(this._name);
    }
}
