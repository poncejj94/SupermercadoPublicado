package model;
// Generated 17/04/2016 11:47:35 by Hibernate Tools 4.3.1


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,catalog="supermercado"
)
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private Rol rol = new Rol();
     private String usuario;
     private String clave;
     private Date fecha;
     private Byte estado;

    public Usuario() {
        
    }

	
    public Usuario(Date fecha) {
        this.fecha = fecha;
        
    }
    public Usuario(Rol rol, String usuario, String clave, Date fecha, Byte estado) {
       this.rol = rol;
       this.usuario = usuario;
       this.clave = clave;
       this.fecha = fecha;
       this.estado = estado;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_usuario", unique=true, nullable=false)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rol_id")
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="usuario", length=30)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="clave", length=32)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", nullable=false, length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="estado")
    public Byte getEstado() {
        return this.estado;
    }
    
    public void setEstado(Byte estado) {
        this.estado = estado;
    }
}

