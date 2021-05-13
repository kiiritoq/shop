package shop;

import ds.DynArray;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Basket {
    //---------------------
    //      ATRIBUTOS
    //---------------------
    DynArray<BasketEntry> entryList = new DynArray<BasketEntry>();


    //---------------------
    //      CONSTRUCTOR
    //---------------------

    /**
     * Referencia ás entradas do carrito
     * @return a compra toda
     */
    public DynArray<BasketEntry> getEntryList(){ return entryList; }

    //---------------------
    //      METODOS
    //---------------------

    /**
     * Devolve se está vacío o carrito ou non. O array
     * dinámico ten un método co que podemos saber se
     * a nosa entryList está vacía ou non.
     * @return boolean entryList.isEmpty()
     */
    public boolean isEmpty(){
        return entryList.isEmpty();
    }

    /**
     * Método que devolve o número total de items que hai no carro.
     * @return numEntries = número de entradas.
     */
    public int getNumEntries(){
        return getEntryList().size();
    }

    public void clear(){
        while (!getEntryList().isEmpty()){
            getEntryList().delete(getEntryList().get(0));
        }
    }
    /**
     * Crea unha entrada no carrito para o producto e o número de unidades indicadas.
     * Se o producto xa se atopa no carro, actualízase o número de
     * entradas correspondentes
     * @param p producto que se engade na cesta
     * @param items número de unidades dese producto
     */
    public void add(Product p, double items){
        boolean exists = false;
        for(int i =0; i< entryList.size(); i++){
            if(entryList.get(i).getProduct().toString().equals(p.toString())) {
                BasketEntry be = getEntryList().get(i);
                be.add(items);
                exists = true;
                break;
            }
        }
        if (!exists){
            BasketEntry entry = new BasketEntry(p,items);
            entryList.add(entry);
        }
    }

    /**
     * Método que resta o número de unidades idicadas do producto da cesta.
     * Se na cesta non está dito producto, lanzase a excepción ProductNotInBasketException
     * @param p producto
     * @param items unidades do producto
     * @throws ProductNotInBasketException excepción de eliminar un producto que non está la cesta.
     */
    public void sub(Product p, double items) throws ProductNotInBasketException{
        boolean exist =false;
        for (int i = 0; i < getEntryList().size(); i++) {
            if (getEntryList().get(i).getProduct().toString().equals(p.toString())) {
                getEntryList().get(i).sub(items);
                exist=true;
                break;
            }
        }
        if (!exist){
              throw new ProductNotInBasketException();
        }


    }

    /**
     * Elimina da cesta a entrada do producto indicado. Se non existe
     * lanza unha excepción.
     * @param p producto
     * @throws ProductNotInBasketException excepcion
     */
    public void remove(Product p) throws ProductNotInBasketException{
        boolean exist = false;
        for (int i = 0; i<getEntryList().size();i++){
            if (getEntryList().get(i).getProduct().toString().equals(p.toString())){
                getEntryList().delete(getEntryList().get(i));
                exist = true;
                break;
            }
        }
        if(!exist){
            throw new ProductNotInBasketException();
        }
    }

    /**
     * Devolve un array co total de impostos e o total do carro.
     * @return data con impostos[0] e o total[1]
     */
    public BigDecimal[] getTotal(){

        BigDecimal[] data = new BigDecimal[2];
        data[0] = BigDecimal.ZERO;
        data[1] = BigDecimal.ZERO;
        for(int i = 0; i<getNumEntries(); i++){
            BigDecimal impuestos = getEntryList().get(i).getTotal()[0];
            BigDecimal total = getEntryList().get(i).getTotal()[1];
            data[0] = data[0].add(impuestos);
            data[1] = data[1].add(total);
        }
        return data;
    }

    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i<getNumEntries();i++){
            str = str + (i+1)+":"+this.getEntryList().get(i).getProduct()+"["+this.getEntryList().get(i).getItems()+"]["+this.getEntryList().get(i).getTotal()[0]+
                    ", " +this.getEntryList().get(i).getTotal()[1] +"]\n";
        }
        str = str + "#:[" + this.getTotal()[0]+", "+ this.getTotal()[1]+"]";
        return str;
    }


}
