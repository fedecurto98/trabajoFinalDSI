package clases;

import java.sql.Date;
import java.util.ArrayList;

public class Reserva {
    
    private int idReserva;

    private Date fechaDesde;

    private Date fechaHasta;

    private String dia;

    private String horaInicio;

    private String horaFin;

    private int idRecurso;

    private int idUsuario;

    private String observaciones;

    public Reserva(int idReserva, Date fechaDesde, Date fechaHasta, String dia, String horaInicio, String horaFin, int idRecurso, int idUsuario, String observaciones) {
        this.idReserva = idReserva;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idRecurso = idRecurso;
        this.idUsuario = idUsuario;
        this.observaciones = observaciones;
    }

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}

