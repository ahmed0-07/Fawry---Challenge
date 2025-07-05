import java.time.LocalDate;

public class ShippableExpirableProduct extends ShippableProduct implements Expirable{
    private LocalDate _expireDate;

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDate expireDate){
        super(name, price, quantity, weight);
        this._expireDate = expireDate;
    }

    public boolean isExpired(){
        return _expireDate.isBefore(LocalDate.now());
    }
}
