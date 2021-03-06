package model;
// Generated 17/04/2016 11:47:35 by Hibernate Tools 4.3.1


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

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name="producto"
    ,catalog="supermercado"
)
public class Producto  implements java.io.Serializable {


     private Integer idProducto;
     private Categoria categoria;
     private String nombre;
     private double precio;
     private String imagen;
     private String cantidad;
     private Set detalleventas = new HashSet(0);

    public Producto() {
    }

	
    public Producto(String nombre, double precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }
    public Producto(Categoria categoria, String nombre, double precio, String imagen, String cantidad, Set detalleventas) {
       this.categoria = categoria;
       this.nombre = nombre;
       this.precio = precio;
       this.imagen = imagen;
       this.cantidad = cantidad;
       this.detalleventas = detalleventas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_Producto", unique=true, nullable=false)
    public Integer getIdProducto() {
        return this.idProducto;
    }
    
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoria")
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    @Column(name="nombre", nullable=false, length=100)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="precio", nullable=false, precision=22, scale=0)
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    @Column(name="imagen", nullable=false, length=45)
    public String getImagen() {
        return this.imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    @Column(name="cantidad")
    public String getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="producto")
    public Set getDetalleventas() {
        return this.detalleventas;
    }
    
    public void setDetalleventas(Set detalleventas) {
        this.detalleventas = detalleventas;
    }




}


