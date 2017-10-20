/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.konrad.project1.ntd.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author PC
 */
@Entity
@Table(name="detalle_pedido")
public class DetallePedidoEntity implements Serializable{
    /**
     * llave primaria del pedido
     */
    @Id
    @Column(name="id_pedido", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * fecha del pedido
     */
    @Column(name="fecha_pedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    /**
     * llave foranea del pedido hacia el producto
     */
    @ManyToOne
    @JoinColumn(name="id_producto")
    private ProductoEntity producto;
    /**
     * llave foranea del pedido hacia la factura
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

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
}
