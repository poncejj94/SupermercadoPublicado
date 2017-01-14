/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.DetalleDao;
import dao.DetalleDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.VentaDao;
import dao.VentaDaoImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.Detalleventa;
import model.Item;
import model.Usuario;
import model.Venta;
import util.HibernateUtil;
import model.Producto;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "ventaBean")
@RequestScoped
public class ventaBean {

    ClienteDao clienteDao = new ClienteDaoImpl();
    private List<Item> cart = new ArrayList<Item>();
    List<Item> carroPayPal = new ArrayList<Item>();
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
    Usuario user = (Usuario) session.getAttribute("usuario");
    Cliente cliente1 = new Cliente();
    private float subtotal;
    private float valorIva;
    private float total;
    private String busqueda;
    private List<Venta> ventas;
    private List<Venta> selectedVentas;
    private List<Detalleventa> detalleVenta;
    private int idVenta;
            
    public ventaBean() 
    {
        if ((List<Item>) session.getAttribute("carrito") != null) {
            cart = (List<Item>) session.getAttribute("carrito");
        }
        cliente1 = clienteDao.findByUserCedula(user.getUsuario());
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public float getSubtotal() {
        subtotal = 0;
        for (Item item : cart) {
            subtotal += item.getValorTotal();
        }

        return subtotal;
    }

    public void setSubtotal(float subtotal) {

        this.subtotal = subtotal;
    }

    public float getValorIva() {
        valorIva = 0;
        for (Item item : cart) {
            valorIva += item.getValorTotal() * 0.14;
        }
        return valorIva;
    }

    public void setValorIva(float valorIva) {
        this.valorIva = valorIva;
    }

    public float getTotal() {
        total = 0;
        for (Item item : cart) {
            total += item.getValorTotal() * 1.14;
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Venta> getVentas() {
        VentaDao ventaDao = new VentaDaoImpl();
        ventas = ventaDao.findAll();
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }


    public List<Detalleventa> getDetalleVenta() {
        detalleVenta = new ArrayList<Detalleventa>();
        DetalleDao detalle = new DetalleDaoImpl();
        //detalleVenta = detalle.findAllByVenta(idVenta);
        return detalleVenta;
    }

    public void setDetalleVenta(List<Detalleventa> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public List<Venta> getSelectedVentas() {
        return selectedVentas;
    }

    public void setSelectedVentas(List<Venta> selectedVentas) {
        this.selectedVentas = selectedVentas;
    }

    public List<Item> getCarroPayPal() {
        if ((List<Item>) session.getAttribute("carrito") != null) {
            carroPayPal = (List<Item>) session.getAttribute("carrito");
        }
        return carroPayPal;
    }

    public void setCarroPayPal(List<Item> carroPayPal) {
        this.carroPayPal = carroPayPal;
    }
    
    

    public void agregarVenta(double totalVenta) {
        VentaDao ventaDao = new VentaDaoImpl();
        
        ProductoDao productoDao = new ProductoDaoImpl();
        Venta venta = new Venta();
        Venta venta2 = new Venta();
        Detalleventa detalle = new Detalleventa();
        Date fecha = new Date();
        Producto prod;

        venta.setCliente(cliente1);
        venta.setFecha(fecha);
        venta.setTotal(totalVenta);
        ventaDao.create(venta);
        venta2 = ventaDao.lastVenta(cliente1.getIdCliente());
        for (Item item : cart) {
            prod = item.getProducto();
            int total = Integer.parseInt(prod.getCantidad())-item.getCantidad();
            prod.setCantidad(total+"");
            //if(productoDao.updateCantidad(item.getProducto())){
            detalle.setProducto(item.getProducto());
            detalle.setCantidad(item.getCantidad());
            detalle.setTotal(item.getValorTotal());
            detalle.setVenta(venta2);
            crearDetalle(detalle);
            
            
            
        }
        
        List<Producto> temporal = new ArrayList<Producto>();
        temporal = productoDao.findAll();
        session.setAttribute("productos", temporal);
       
    }
    public void crearDetalle(Detalleventa detalle){
            DetalleDao detalleDao = new DetalleDaoImpl();
            detalleDao.create(detalle);
        }

    public List<String> completeText(String query) {
        ClienteDao cliente = new ClienteDaoImpl();
        List<String> results = cliente.findByString(query);

        return results;
    }

}
