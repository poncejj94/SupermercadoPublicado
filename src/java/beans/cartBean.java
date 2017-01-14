/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Producto;
import model.Usuario;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "sp")
@RequestScoped
public class cartBean implements Serializable {

    private List<Item> cart = new ArrayList<Item>();
    private float total;
private Item selectedItem = new Item();

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }


    public cartBean() {

    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        total = 0;
        for (Item item : cart) {
            total += item.getValorTotal();
        }
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Item> addtoCart(Producto p, Integer cantidad) {
        HttpSession session = HibernateUtil.getSession();

        if ((List<Item>) session.getAttribute("carrito") != null) {
            cart = (List<Item>) session.getAttribute("carrito");
        }

        for (Item item : cart) {
            if (item.getProducto().getIdProducto().equals(p.getIdProducto())) {
                item.setCantidad(cantidad + item.getCantidad());
                item.setId(p.getIdProducto());
                return cart;
            }

        }
        Item item1 = new Item();
        item1.setCantidad(cantidad);
        item1.setProducto(p);
        item1.setValorTotal(cantidad * p.getPrecio());
        item1.setId(p.getIdProducto());
        cart.add(item1);
        session.setAttribute("carrito", this.cart);
        return cart;
    }

     public void remove(){
        String msg;
         HttpSession session = HibernateUtil.getSession();
         List<Item> lista = (List<Item>) session.getAttribute("carrito");
            lista = (List<Item>) session.getAttribute("carrito");

        for (Item item : lista) {
            if(item.getId().equals(selectedItem.getId()))
            {
                lista.remove(selectedItem);
                
                msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
         session.setAttribute("carrito", lista);

    }
     
     public String payment()
     {
         return "pago";
     }

}
