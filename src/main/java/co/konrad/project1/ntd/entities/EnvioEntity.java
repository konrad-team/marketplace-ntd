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
@Table(name="envio")
public class EnvioEntity implements Serializable{

    /**
     * llave primaria del envio
     */
    @Id
    @Column(name="id_envio", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * dirección del envio
     */
    @Column(name="direccion_envio")
    private String direccion;
    /**
     * ciudad del envio
     */
    @Column(name="ciudad_envio")
    private String ciudad;
    /**
     * pais del envio
     */
    @Column(name="pais_envio")
    private String pais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
