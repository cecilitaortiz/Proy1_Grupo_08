/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cecy.proyecto1grupo8;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class Auto {
    private String tipo;
    private String marca;
    private String modelo;
    private String color;
    private Integer kilometraje;
    private double precio;
    private int anio;
    private String imagen;
    private String descripcion;

    public Auto(String tipo, String marca, String modelo, String color, Integer kilometraje, double precio, Integer anio, String imagen, String descripcion) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.kilometraje = kilometraje;
        this.precio = precio;
        this.anio = anio;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public double getPrecio() {
        return precio;
    }

    public Integer getAnio() {
        return anio;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Auto{" + "tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", kilometraje=" + kilometraje + ", precio=" + precio + ", anio=" + anio + ", imagen=" + imagen + ", descripcion=" + descripcion + '}';
    }
}
