/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table(name="categoria")
public class CategoriaEntity implements Serializable{
    /**
     * llave primaria de la categoría
     */
    @Id
    @Column(name="id_categoria", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * nombre de la categoría
     */
    @Column(name="nombre_categoria")
    private String nombre;
    /**
     * descripción de la categoría
     */
    @Column(name="descripcion_categoria")
    private String descripcion;
    /**
     *  de la categoría
     */
    @Column(name="categoriacol")
    private String categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
