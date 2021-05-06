package shop;

public class Demo {
    public static void main(String[] args) {
        Basket b = new Basket();

        b.add(new Product("R001","MESA  GAMING", "0.02", "100.99"),2);
        System.out.println(b.entryList.toString());

        b.add(new Product("R001", "MESA GAMING", "0.02", "100.99"), 4);
        b.add(new Product("R0003", "MESA GAMING", "0.02", "99.99"), 2);
        b.add(new Product("R0004", "MESA GAMING", "0.02", "99.99"), 2);
        System.out.println(b.entryList.toString());

    }
}
