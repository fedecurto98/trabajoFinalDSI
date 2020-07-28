package clases;

import java.sql.Date;

public class Prestamo {

    private int idPrestamo;
    
    private int idReserva;

    private Date fechaInicio;

    private String estado;    

    public Prestamo() {

    }

    public Prestamo(int idPrestamo, int idReserva, Date fechaInicio, String estado) {
        this.idPrestamo = idPrestamo;
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
                
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    
        
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
