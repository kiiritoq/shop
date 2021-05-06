package shop;

public class Demo {
    public static void main(String[] args) {
        Basket b = new Basket();
        Product p = new Product("R001","MESA  GAMING", "0.02", "100.99");
        Product p1 = new Product("R002","SILLA  GAMING", "0.12", "200.99");


        b.add(p,2);
        b.add(p,5);
        b.add(p1,2);
        System.out.println(b.entryList.toString());

    }
}
