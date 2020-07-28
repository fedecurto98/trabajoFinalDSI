/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import controladores.ControladorReservas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ezequiel Crespo
 */
public class ControladorPrestamosTest {
    
    public ControladorPrestamosTest() {
    }
    
    
    @Test
    public void testValorArribaDeLimiteSuperiorFecha(){
        ControladorReservas controladorPrestamo = new ControladorReservas();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        boolean EsRangoValido;
        
        EsRangoValido = controladorPrestamo.verificarRangoValidoDeFecha(calendar.getTime());
        assertEquals(false, EsRangoValido);
    }
    
        @Test
        public void testValorAbajoDeLimiteInferiorFecha(){
        ControladorReservas controladorPrestamo = new ControladorReservas();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        boolean EsRangoValido;
        
        EsRangoValido = controladorPrestamo.verificarRangoValidoDeFecha(calendar.getTime());
        assertEquals(false, EsRangoValido);
    }
        
        @Test
        public void testValorDentroDeRangoFecha(){
        ControladorReservas controladorPrestamo = new ControladorReservas();
        boolean Bandera=false;
        
        
        for(int i=0;i<30;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            boolean EsRangoValido;
        
            EsRangoValido = controladorPrestamo.verificarRangoValidoDeFecha(calendar.getTime());
            
            if(EsRangoValido == false){
                Bandera=true;  
            }
        }
         assertEquals(false, Bandera);
    }
}
