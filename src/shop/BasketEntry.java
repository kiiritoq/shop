package shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.BlockingQueue;


public class BasketEntry {

    //---------------------
    //      ATRIBUTOS
    //---------------------
    private Product producto; // Producto da entrada do carrito
    private double items; //Número de unidades  (en positivo)

    //---------------------
    //     CONSTRUCTORES
    //---------------------

    /**
     * Constructor que se inicializa con un producto e un precio inicial
     * @param p Producto da entrada do carro
     * @param items número de unidades
     */
    public BasketEntry(Product p, double items){
        this.producto = p;
        this.items = items;
    }

    //---------------------
    //     METODOS
    //---------------------

    /**
     * Devolve o producto da entrada
     * @return Producto da entrada do carro.
     */
    public Product getProduct(){ return this.producto; }

    /**
     * Devolve o número de unidades da entrada
     * @return int. Número de items
     */
    public double getItems(){ return this.items;}

    /**
     * Engade o número de unidades indicado e devolve
     * o valor actualizado.
     * @param cantidad número de items que engadimos
     * @return número de unidades que hai en total.
     */
    public double add(double cantidad) {
        this.items += cantidad;
        return this.items;
    }

    /**
     * Resta as unidades indicadas e devolve o valor actualizado.
     * @param cantidad número de items a eliminar
     * @return número de unidades que quedan en total.
     */
    public double sub(double cantidad){
        if((this.items-cantidad) <0){
            this.items = 0;
        }else{
            this.items -= cantidad;
        }
        return this.items;
    }

    /**
     * Devolve un array co total de impostos e o total  da enrtada do
     * carrito.
     * @return devolve os valores dos impostos e o total do precio
     */
    public BigDecimal[] getTotal(){
        //Creamos array
        BigDecimal[] data = new BigDecimal[2];
        data[0] = BigDecimal.ZERO.setScale(1);
        data[1] = BigDecimal.ZERO.setScale(1);

        //Creamos precio base e unidades
        double dPrice = Double.parseDouble(this.getProduct().getPrice());
        BigDecimal price = new BigDecimal(dPrice);
        BigDecimal units = new BigDecimal(this.getItems());

        //Base = num_unidades * precio producto
        BigDecimal base = new BigDecimal(0);
        base = price.multiply(units);

        //Creamos impostos e tasa producto
        double dTasa = Double.parseDouble(this.getProduct().getTax());
        BigDecimal tasa = new BigDecimal(dTasa);
        BigDecimal imposto = base.multiply(tasa);

        // Total
        BigDecimal total = base.add(imposto);
        data[0] = data[0].add(imposto);
        data[1] = data[1].add(total);
        data[0] = data[0].setScale(2,RoundingMode.HALF_EVEN);
        data[1] = data[1].setScale(2,RoundingMode.HALF_EVEN);

        return data;
    }

    /**
     * Devolve a representación en texto do carro, según o formato
     * indicado.
     * @return Cadana de Caracteres coa representación en texto do carro
     */
    @Override
    public String toString(){
        return getProduct()+"["+this.getItems()+"]["+getTotal()[0]+", "+getTotal()[1]+"]";
    }


}
