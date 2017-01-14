/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProductoDao;
import dao.ProductoDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Producto;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "productoBean")
@RequestScoped
public class productoBean {

    private List<SelectItem> selectOneItemsProducto;

    private List<Producto> productos;
    private Producto producto1 = new Producto();
    private Producto selectedProducto = new Producto();
    private Integer categoriaId;

    public List<SelectItem> getSelectOneItemsProducto() {
       this.productos = new ArrayList<Producto>();
        List<Producto> temporal = new ArrayList<Producto>();
        HttpSession session = HibernateUtil.getSession();
        ProductoDao productoDao = new ProductoDaoImpl();
         if((List<Producto>) session.getAttribute("productos")!=null){
            temporal= (List<Producto>) session.getAttribute("productos");
        }else{
         temporal = productoDao.findAll();
        session.setAttribute("productos", temporal);
         }
        for (Producto producto : productos) {
            SelectItem selectItem = new SelectItem(producto.getIdProducto());
            this.selectOneItemsProducto.add(selectItem);
        }
        return selectOneItemsProducto;
    }

    public productoBean() {
        this.productos = new ArrayList<Producto>();
        categoriaBean cat = new categoriaBean();
        this.categoriaId =1;
    }

    public List<Producto> getProductos() {
        this.productos = new ArrayList<Producto>();
        List<Producto> temporal = new ArrayList<Producto>();
        HttpSession session = HibernateUtil.getSession();
        ProductoDao productoDao = new ProductoDaoImpl();
         if((List<Producto>) session.getAttribute("productos")!=null){
            temporal= (List<Producto>) session.getAttribute("productos");
        }else{
         temporal = productoDao.findAll();
        session.setAttribute("productos", temporal);
         }
         for(Producto prod: temporal){
             if(prod.getCategoria().getIdCategoria().equals(this.categoriaId))
                 productos.add(prod);
         }
        return productos;
    }
    
    public void categoriaProductos() {
        List<Producto> temporal = new ArrayList<Producto>();
        HttpSession session = HibernateUtil.getSession();
        ProductoDao productoDao = new ProductoDaoImpl();
         if((List<Producto>) session.getAttribute("productos")!=null){
            temporal= (List<Producto>) session.getAttribute("productos");
        }else{
         temporal = productoDao.findAll();
        session.setAttribute("productos", temporal);
         }
         for(Producto prod: temporal){
             if(prod.getCategoria().getIdCategoria().equals(this.categoriaId))
                 productos.add(prod);
         }
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto) {
        this.producto1 = producto;
    }

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void btnCreateProducto(ActionEvent event) {
        String msg;
        ProductoDao productoDao = new ProductoDaoImpl();

        if (productoDao.create(this.producto1)) {
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Eror al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void btnDeleteProducto(ActionEvent actionEvent) {
        String msg;
        ProductoDao productoDao = new ProductoDaoImpl();
        if (productoDao.delete(this.selectedProducto.getIdProducto())) {
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Eror al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateProducto(ActionEvent actionEvent) {
        String msg;
        ProductoDao productoDao = new ProductoDaoImpl();
        if (productoDao.update(this.selectedProducto)) {
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Eror al actualizar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}