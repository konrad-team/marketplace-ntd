/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table(name="proveedor")
public class ProveedorEntity {
    /**
     * llave primaria del proveedor
     */
    @Id
    @Column(name="id_proveedor", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * nombre del proveedor
     */
    @Column(name="nombre_proveedor")
    private String nombre;
    /**
     * dirección del proveedor
     */
    @Column(name="direccion_proveedor")
    private String direccion;
    /**
     * teléfono del proveedor
     */
    @Column(name="telefono_proveedor")
    private String telefono;
    /**
     * correo del proveedor
     */
    @Column(name="email_proveedor")
    private String email;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
