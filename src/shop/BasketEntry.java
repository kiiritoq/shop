package shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Bidi;
import java.text.DecimalFormat;

public class BasketEntry {

    //---------------------
    //      ATRIBUTOS
    //---------------------
    private Product producto; // Producto da entrada do carrito
    private int items; //Número de unidades  (en positivo)

    //---------------------
    //     CONSTRUCTORES
    //---------------------

    /**
     * Constructor que se inicializa con un producto e un precio inicial
     * @param p Producto da entrada do carro
     * @param items número de unidades
     */
    public BasketEntry(Product p, int items){
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
    public int getItems(){ return this.items;}

    /**
     * Engade o número de unidades indicado e devolve
     * o valor actualizado.
     * @param cantidad número de items que engadimos
     * @return número de unidades que hai en total.
     */
    public int add(int cantidad) {
        this.items += cantidad;
        return this.items;
    }

    /**
     * Resta as unidades indicadas e devolve o valor actualizado.
     * @param cantidad número de items a eliminar
     * @return número de unidades que quedan en total.
     */
    public int sub(int cantidad){
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
     * @return
     */
    public BigDecimal[] getTotal(){
        BigDecimal price = new BigDecimal(producto.getPrice());
        BigDecimal items = BigDecimal.valueOf(this.items);

        BigDecimal base = items.multiply(price); // base
        BigDecimal rdBase = base.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal tasaProductos = new BigDecimal(producto.getTax());

        BigDecimal impuestos = rdBase.multiply(tasaProductos); //impuestos
        BigDecimal rdImpuestos = impuestos.setScale(2,RoundingMode.HALF_EVEN);

        BigDecimal total = rdBase.add(rdImpuestos);
        BigDecimal[] data = new BigDecimal[2];
        data[0]= rdImpuestos;
        data[1] = total;
        return data;
    }

    /**
     * Devolve a representación en texto do carro, según o formato
     * indicado.
     * @return Cadana de Caracteres coa representación en texto do carro
     */
    @Override
    public String toString(){
        return producto.getName()+"["+this.getItems()+"]["+getTotal()[0]+", "+getTotal()[1]+"]";
    }
}
