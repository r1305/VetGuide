package clases;

import java.util.List;

/**
 * Created by itlab on 11/5/15.
 */
public class Veterinaria {
    private String nombre;
    private String direccion;
    private long numero;
    private String horasAtencion;
    private String diasAtencion;
    private List<String> mascotasAAtender;
    private List<String> serviciosBrindados;
    private double longitud;
    private double latitud;

    public Veterinaria(String nombre, String direccion, long numero, String horasAtencion, String diasAtencion, List<String> mascotasAAtender, List<String> serviciosBrindados, double longitud, double latitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.horasAtencion = horasAtencion;
        this.diasAtencion = diasAtencion;
        this.mascotasAAtender = mascotasAAtender;
        this.serviciosBrindados = serviciosBrindados;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getHorasAtencion() {
        return horasAtencion;
    }

    public void setHorasAtencion(String horasAtencion) {
        this.horasAtencion = horasAtencion;
    }

    public String getDiasAtencion() {
        return diasAtencion;
    }

    public void setDiasAtencion(String diasAtencion) {
        this.diasAtencion = diasAtencion;
    }

    public List<String> getMascotasAAtender() {
        return mascotasAAtender;
    }

    public void setMascotasAAtender(List<String> mascotasAAtender) {
        this.mascotasAAtender = mascotasAAtender;
    }

    public List<String> getServiciosBrindados() {
        return serviciosBrindados;
    }

    public void setServiciosBrindados(List<String> serviciosBrindados) {
        this.serviciosBrindados = serviciosBrindados;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
