public class ShippableProduct extends Product implements Shippable{
    private double _weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this._weight = weight;
    }

    public double getWeight(){
        return this._weight;
    }
}
