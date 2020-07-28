/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import clases.Recurso;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ezequiel Crespo
 */
public class RecursoTest {
    
    public RecursoTest() {
    }
    
    @Test
    public void testNombre(){
        Recurso recurso = new Recurso(1,"Proyector","Color Negro","Habilitado","Objeto");
        assertEquals("Proyector", recurso.getNombre());
    }
    
    @Test
    public void testDescripcion(){
        Recurso recurso = new Recurso(1,"Proyector","Color Negro","Habilitado","Objeto");
        assertEquals("Color Negro", recurso.getDescripcion());
    }
    
    @Test
    public void testEstado(){
        Recurso recurso = new Recurso(1,"Proyector","Color Negro","Habilitado","Objeto");
        assertEquals("Habilitado", recurso.getEstado());
    }
}
