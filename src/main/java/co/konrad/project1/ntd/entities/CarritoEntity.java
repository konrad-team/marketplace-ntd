/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entidad Carrito de compras
 * @author PC
 */
@Entity
@Table(name="carrito")
public class CarritoEntity implements Serializable{

    /**
     * llave primaria del carrito de compras
     */
    @Id
    @Column(name="id_carrito", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * llave foranea hacia factura
     */
    @ManyToOne
    @JoinColumn(name="id_factura")
    private FacturaEntity factura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
}
