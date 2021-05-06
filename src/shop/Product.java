package shop;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Clase Producto, representa os diferentes productos que pueden ser añadidos
 * al carrito.
 * @author kirito - the shadow programmer
 * @version 1.02052021
 */
public class Product {

    //---------------------
    //      ATRIBUTOS
    //---------------------
    private String ref; //Referencia única do producto
    private String name; //Descripción do producto
    private String tax; //Porcentaje de impuestos sobre o producto
    private String price; //precio do producto

    //---------------------
    //     CONSTRUCTORES
    //---------------------

    /**
     * Constructor que se inicia con todos os valores
     * @param ref Referencia única do producto
     * @param name Descripción do producto
     * @param tax Porcentaje de impuestos sobre o producto
     * @param price precio do producto
     */
    public Product(String ref, String name, String tax, String price){
        this.ref = ref;
        this.name = name;
        this.tax = tax;
        this.price = price;
    }
    //---------------------
    //     METODOS
    //---------------------

    /**
     * Devolve a referencia do producto
     * @return String da referencia do producto
     */
    public String getRef(){return this.ref;}

    /**
     * Devolve o nome do producto
     * @return String do nome do producto
     */
    public String getName(){return this.name;}

    /**
     * Devolve o porcentaxe de impostos sobre o producto
     * @return String da porcentaxe se impostos
     */
    public String getTax(){return this.tax;}

    /**
     * Devolve o precio do producto
     * @return String do producto
     */
    public String getPrice(){return this.price;}

    /**
     * Compara un objecto con outro
     * @param prod Producto que se compara
     * @return Devolve true se son iguals e false se son distintos.
     */
    public boolean equals(Product prod){
        BigDecimal b1 = new BigDecimal(this.hashCode());
        BigDecimal b2 = new BigDecimal(prod.hashCode());
        return b1.equals(b2);
    }
    /**
     * Devolve o hash do producto.
     * @return int co valor hash do producto.
     */
    @Override
    public int hashCode(){
        int hash = ref.hashCode();
        hash = hash*31 + name.hashCode();
        hash = hash*31 + tax.hashCode();
        hash = hash*31 +price.hashCode();
        return hash;
    }

    /**
     * Devolve true o false cando
     * @param p
     * @return
     */
    @Override
    public boolean equals(Object p){
        return  (this.hashCode() == p.hashCode());
    }

    /**
     * Devolde a representación no texto segun o formato.
     * @return Cadena de Caracterese coa representación do texto
     */
    @Override
    public String toString(){
        return "["+this.getRef()+";"+this.getName()+";"+this.getTax()+";"+this.getPrice()+"]";
    }
}
