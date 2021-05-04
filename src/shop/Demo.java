package shop;

public class Demo {
    public static void main(String[] args) {
        Product p1 = new Product("R001","Sofa","0.12","11.26");
        Product p2 = new Product("R001","Sofa","0.12","11.26");
        Product p3 = new Product("R003","Porta","0.13","12.26");
        System.out.println("HASH CODE p1: " + p1.hashCode());
        System.out.println("HASH CODE p2: " + p2.hashCode());

        System.out.println(p1.equals(p2));
    }
}
