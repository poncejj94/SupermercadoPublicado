/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import model.Categoria;
import model.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "categoriaBean")
@RequestScoped
public class categoriaBean {

    private List<SelectItem> selectOneItemsCategoria;
    private List<Categoria> categorias;
    private Categoria categoria1 = new Categoria();
    private Categoria selectedCategoria = new Categoria();
    private MenuModel model;

    public MenuModel getModel() {
        
        CategoriaDao categoriadao = new CategoriaDaoImpl();
        DefaultSubMenu submenu = new DefaultSubMenu("Categoria");
        List<Categoria> cat = categoriadao.findAll();
        for (Categoria c : cat) {
        model = new DefaultMenuModel();
        appBean app = new appBean();
        productoBean producto = new productoBean();
        MenuItem item = new DefaultMenuItem(c.getNombre()); 
        //item.setActionCommand("#{productoBean.getProductosPorCategoria(item.getName())}");
        submenu.addElement((MenuElement) item);
        model.addElement(submenu);
        
        }
    return model ;
}

public List<SelectItem> getSelectOneItemsCategoria(){
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        categorias = categoriaDao.findAll();
        for (Categoria categoria : categorias){
            SelectItem selectItem = new SelectItem(categoria.getIdCategoria(),categoria.getNombre());
            this.selectOneItemsCategoria.add(selectItem);
        }
        return selectOneItemsCategoria;
    }
    public categoriaBean() {
        this.categorias = new ArrayList<Categoria>();
    }

    public List<Categoria> getCategorias() {
       
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
         categorias = categoriaDao.findAll();
        
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(Categoria categoria) {
        this.categoria1 = categoria;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }
    
    
     
    public void btnCreateCategoria(ActionEvent event){
        String msg;
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        
        if(categoriaDao.create(this.categoria1)){
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public void btnDeleteCategoria(ActionEvent actionEvent){
        String msg;
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        if(categoriaDao.delete(this.selectedCategoria.getIdCategoria())){
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public void btnUpdateCategoria(ActionEvent actionEvent){
        String msg;
        CategoriaDao categoriaDao = new CategoriaDaoImpl();
        if(categoriaDao.update(this.selectedCategoria)){
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al actualizar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    
    public void mostrarProductosCategoria(ActionEvent event){
        
    }
}
