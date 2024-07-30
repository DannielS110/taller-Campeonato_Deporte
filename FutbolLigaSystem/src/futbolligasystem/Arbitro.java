/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package futbolligasystem;

/**
 *
 * @author Dannn
 */
public class Arbitro implements IRegistrable {
    private String nombre;
    private String categoria;

    public Arbitro(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    
    public void registrar() {
        System.out.println("Registrando árbitro: " + this.nombre + " (Categoría: " + this.categoria + ")");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}