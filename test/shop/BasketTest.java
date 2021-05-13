//-----------------------------------------------------------------------------------------------
//  Unit Tests:
//-----------------------------------------------------------------------------------------------
// Test 1: carrito vacío
// Test 2: añadir un producto al carrito
// Test 3: añadir más unidades de un producto
// Test 4: añadir más unidades del mismo producto
// Test 5: añadir más unidades de los mismos productos
// Test 6: eliminar un producto del carrito
// Test 7: eliminar un producto de un carrito con varios productos
// Test 8: eliminar producto que no está en el carrito, lanza excepción
// Test 9: eliminar unidades de un producto del carrito
// Test 10: eliminar unidades de un producto que no está en el carrito, lanza excepción
// Test 11: Vaciar el carrito
// Test 12: getTotal() de carrito vacío
// Test 13: getTotal() de carrito con un producto
// Test 14: getTotal() de carrito con varias unidades de un producto
// Test 15: getTotal() de carrito con varias unidades de un varios productos
// Test 16: toString() con múltiples entradas
//-----------------------------------------------------------------------------------------------

package shop;

import org.junit.*;
import java.math.BigDecimal;
import shop.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BasketTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    //-----------------------------------------------------------------------------------------------
    // Set-Up

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    //-----------------------------------------------------------------------------------------------
    // Tests

    // Test 1: carrito vacío
    @Test
    public void test_empty_basket() {
        Basket basket = new Basket();               // Creamos el carrito

        assertTrue(basket.isEmpty());               // Comprobamos vacío =true
        assertEquals(0, basket.getNumEntries());    // Comprobamos entradas =0
    }

    // Test 2: añadir un producto al carrito
    @Test
    public void test_add_one() {
        Basket basket = new Basket();                         // Creamos el carrito

        basket.add(new Product("R1","P1","0.1","100.0"), 1);   // Añadimos producto

        assertFalse(basket.isEmpty());                          // Comprobamos vacío =false
        assertEquals(1, basket.getNumEntries());                // Comprobamos entradas =1
    }

    // Test 3: añadir más unidades de un producto
    @Test
    public void test_add_multiple() {
        Basket basket = new Basket();                         // Creamos el carrito

        basket.add(new Product("R1","P1","0.1","100.0"), 1);   // Añadimos producto
        basket.add(new Product("R2","P2","0.1","100.0"), 1);   // Añadimos producto

        assertFalse(basket.isEmpty());                          // Comprobamos vacío =false
        assertEquals(2, basket.getNumEntries());                // Comprobamos entradas =2
    }

    // Test 4: añadir más unidades del mismo producto
    @Test
    public void test_add_same() {
        Basket basket = new Basket();                               // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");         // Creamos un producto

        basket.add(p1, 1);   // Añadimos producto
        basket.add(p1, 2);   // Añadimos producto

        assertFalse(basket.isEmpty());                              // Comprobamos vacío =false
        assertEquals(basket.getNumEntries(), 1);                    // Comprobamos entradas =1

        // Comprobamos items =3.0
        assertEquals(3.0, basket.getEntryList().get(0).getItems(), 0.0);
    }

    // Test 5: añadir más unidades de los mismos productos
    @Test
    public void test_add_same_multiple() {
        Basket basket = new Basket();                         // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto
        Product p2 = new Product("R2","P2","0.1","100.0");     // Creamos un producto

        basket.add(p1, 1);   // Añadimos producto
        basket.add(p2, 2);   // Añadimos producto
        basket.add(p1, 2);   // Añadimos producto
        basket.add(p2, 4);   // Añadimos producto

        assertFalse(basket.isEmpty());                          // Comprobamos vacío =false
        assertEquals(basket.getNumEntries(), 2);                // Comprobamos entradas =2

        // Comprobamos items =3.0 de p1
        assertEquals(3.0, basket.getEntryList().get(0).getItems(), 0.0);

        // Comprobamos items =6.0 de p2
        assertEquals(6.0, basket.getEntryList().get(1).getItems(), 0.0);
    }

    // Test 6: eliminar un producto del carrito
    @Test
    public void test_remove_one() throws ProductNotInBasketException {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto

        basket.add(p1, 2);                                      // Añadimos producto
        basket.remove(p1);                                      // Eliminamos el producto

        assertTrue(basket.isEmpty());                           // Comprobamos vacío =true
        assertEquals(0, basket.getNumEntries());                // Comprobamos entradas =0
    }

    // Test 7: eliminar un producto de un carrito con varios productos
    @Test
    public void test_remove_one_from_many() throws ProductNotInBasketException {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto
        Product p2 = new Product("R2","P2","0.1","100.0");     // Creamos un producto

        basket.add(p1, 1);   // Añadimos producto
        basket.add(p2, 1);   // Añadimos producto
        basket.add(p1, 2);   // Añadimos producto
        basket.add(p2, 2);   // Añadimos producto
        basket.add(p1, 1);                                      // Añadimos producto
        basket.remove(p1);                                      // Eliminamos el producto

        assertFalse(basket.isEmpty());                          // Comprobamos vacío =false
        assertEquals(1, basket.getNumEntries());                // Comprobamos entradas =1
    }

    // Test 8: eliminar producto que no está en el carrito, lanza excepción
    @Test(expected = ProductNotInBasketException.class)
    public void test_remove_exception() throws ProductNotInBasketException {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto

        basket.remove(p1);                                      // Debe lanzar excepción
    }

    // Test 9: eliminar unidades de un producto del carrito
    @Test
    public void test_sub_one() throws ProductNotInBasketException {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto

        basket.add(p1, 3);                                      // Añadimos producto
        basket.sub(p1, 1);                                      // Eliminamos el producto

        assertFalse(basket.isEmpty());                          // Comprobamos vacío =false
        assertEquals(basket.getNumEntries(), 1);                // Comprobamos entradas =1

        // Comprobamos items =2.0 de p1
        assertEquals(2.0, basket.getEntryList().get(0).getItems(), 0.0);
    }

    // Test 10: eliminar unidades de un producto que no está en el carrito, lanza excepción
    @Test(expected = ProductNotInBasketException.class)
    public void test_sub_exception() throws ProductNotInBasketException {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto

        basket.sub(p1, 1);                                      // Debe lanzar excepción
    }

    // Test 11: Vaciar el carrito
    @Test
    public void test_clear() {
        Basket basket = new Basket();                           // Creamos el carrito

        Product p1 = new Product("R1","P1","0.1","100.0");     // Creamos un producto
        Product p2 = new Product("R2","P2","0.1","100.0");     // Creamos un producto

        basket.add(p1, 1);   // Añadimos producto
        basket.add(p2, 1);   // Añadimos producto

        basket.clear();                                         // Vaciamos el carrito

        assertTrue(basket.isEmpty());                           // Comprobamos vacío =true
        assertEquals(0, basket.getNumEntries());                // Comprobamos entradas =0
    }

    // Test 12: getTotal() de carrito vacío
    @Test
    public void test_getTotal_empty_basket() {
        Basket basket = new Basket();               // Creamos el carrito

        assertTrue(basket.isEmpty());               // Comprobamos vacío =true
        assertEquals(basket.getNumEntries(), 0);    // Comprobamos entradas =0

        BigDecimal[] total = basket.getTotal();

        assertEquals("0.00", total[0].toString());     // Comprobamos totalTax = 0.0
        assertEquals("0.00", total[1].toString());     // Comprobamos totalImporte = 0.0
    }

    // Test 13: getTotal() de carrito con un producto
    @Test
    public void test_getTotal_one_in_basket() {
        Basket basket = new Basket();               // Creamos el carrito

        Product p1 = new Product("R1","P1","0.21","55.23");     // Creamos un producto

        basket.add(p1, 1);

        BigDecimal[] total = basket.getTotal();

        assertEquals("11.60", total[0].toString());     // Comprobamos totalTax = 10.0
        assertEquals("66.83", total[1].toString());    // Comprobamos totalImporte = 110.0
    }

    // Test 14: getTotal() de carrito con varias unidades de un producto
    @Test
    public void test_getTotal_several_units_in_basket() {
        Basket basket = new Basket();                       // Creamos el carrito

        Product p1 = new Product("R1","P1","0.21","55.23"); // Creamos un producto

        basket.add(p1, 2);

        BigDecimal[] total = basket.getTotal();

        assertEquals("23.20", total[0].toString());     // Comprobamos totalTax = 23.20
        assertEquals("133.66", total[1].toString());    // Comprobamos totalImporte = 133.66
    }

    // Test 15: getTotal() de carrito con varias unidades de un varios productos
    @Test
    public void test_getTotal_multiple_in_basket() {
        Basket basket = new Basket();               // Creamos el carrito

        Product p1 = new Product("R1","P1","0.21","55.23");     // Creamos un producto
        Product p2 = new Product("R2","P2","0.04","12.55");     // Creamos un producto

        basket.add(p1, 2);
        basket.add(p2, 3);

        BigDecimal[] total = basket.getTotal();

        assertEquals("24.71", total[0].toString());     // Comprobamos totalTax = 24.70
        assertEquals("172.82", total[1].toString());    // Comprobamos totalImporte = 172.82
    }

    // Test 16: toString() con múltiples entradas
    @Test
    public void test_toString_multiple() {
        String testset_prt =    "1:[R001; Producto 1; 0.04; 21.25][3.0][2.55, 66.30]\n" +
                "2:[R002; Producto 2; 0.10; 32.50][1.0][3.25, 35.75]\n" +
                "3:[R003; Producto 3; 0.21; 112.18][2.0][47.12, 271.48]\n" +
                "#:[52.92, 373.53]";

        Basket basket = new Basket();                   // Creamos el carrito

        Product p1 = new Product("R001", "Producto 1", "0.04", "21.25");
        Product p2 = new Product("R002", "Producto 2", "0.10", "32.50");
        Product p3 = new Product("R003", "Producto 3", "0.21", "112.18");

        basket.add(p1, 1);
        basket.add(p2, 1);
        basket.add(p3, 2);
        basket.add(p1, 2);

        assertEquals(testset_prt, basket.toString());   // Comprobamos el valor devuelto por el método toString()
    }
}