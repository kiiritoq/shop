package shop;

public class ProductNotInBasketException extends Exception {
    private static final String MESSAGE ="O producto non se atopa na cesta";

    public ProductNotInBasketException(){super(MESSAGE);}
    public ProductNotInBasketException(String msg){super(msg);}

}
