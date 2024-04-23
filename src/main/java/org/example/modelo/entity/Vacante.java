
package org.example.modelo.entity;


import java.sql.Date;

public class Vacante {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private double salario;
    private String estatus;
    private int destacado;
    private String imagen;
    private String detalles;
    private Categoria categoria;

    public Vacante() {
    }

    public Vacante(int id, String nombre, String descripcion, Date fecha, double salario, String estatus, int destacado, String imagen, String detalles, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.salario = salario;
        this.estatus = estatus;
        this.destacado = destacado;
        this.imagen = imagen;
        this.detalles = detalles;
        this.categoria = categoria;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getDestacado() {
        return destacado;
    }

    public void setDestacado(int destacado) {
        this.destacado = destacado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Vacante{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + ", salario=" + salario + ", estatus=" + estatus + ", destacado=" + destacado + ", imagen=" + imagen + ", detalles=" + detalles + ", categoria=" + categoria + '}';
    }
    
    
}
