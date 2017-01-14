package model;
// Generated 17/04/2016 11:47:35 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Venta generated by hbm2java
 */
@Entity
@Table(name="venta"
    ,catalog="supermercado"
)
public class Venta  implements java.io.Serializable {


     private Integer idVenta;
     private Cliente cliente;
     private Double total;
     private Date fecha;
     private Set detalleventas = new HashSet(0);

    public Venta() {
    }

    public Venta(Cliente cliente, Double total, Date fecha, Set detalleventas) {
       this.cliente = cliente;
       this.total = total;
       this.fecha = fecha;
       this.detalleventas = detalleventas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_venta", unique=true, nullable=false)
    public Integer getIdVenta() {
        return this.idVenta;
    }
    
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente")
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Column(name="total", precision=22, scale=0)
    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="venta")
    public Set getDetalleventas() {
        return this.detalleventas;
    }
    
    public void setDetalleventas(Set detalleventas) {
        this.detalleventas = detalleventas;
    }




}


