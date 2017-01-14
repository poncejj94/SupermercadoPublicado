/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "menuBean")
@RequestScoped
public class menuBean {

    private MenuModel model;

        public menuBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        Usuario user = (Usuario) session.getAttribute("usuario");
                 //loginBean login = loginBean.getInstance();
        Integer rol1 = user.getRol().getIdPerfil();
        model = new DefaultMenuModel();
        appBean app = new appBean();

            if(rol1==1){
                DefaultSubMenu submenu = new DefaultSubMenu("SuperAdministrador"); 
                String url = app.getBasePath()+"seguridad/rol/index.xhtml";
                MenuItem item = new DefaultMenuItem("Rol", "", url);  
                submenu.addElement(item);
                //model.addElement(submenu);
                url = app.getBasePath()+"seguridad/usuario/index.xhtml";
                item = new DefaultMenuItem("Usuario", "", url);  
                submenu.addElement(item);
                model.addElement(submenu);
                
            }
            if(rol1==2){
             DefaultSubMenu submenu = new DefaultSubMenu("Administrador"); 
                String url = app.getBasePath()+"administrador/categoria/index.xhtml";
                MenuItem item = new DefaultMenuItem("Categoria", "", url);  
                submenu.addElement(item);
                //model.addElement(submenu);
                url = app.getBasePath()+"administrador/cliente/index.xhtml";
                item = new DefaultMenuItem("Cliente", "", url);  
                submenu.addElement(item);
                url = app.getBasePath()+"administrador/producto/index.xhtml";
                item = new DefaultMenuItem("Producto", "", url);  
                submenu.addElement(item);
                //model.addElement(submenu);
                url = app.getBasePath()+"administrador/venta/index.xhtml";
                item = new DefaultMenuItem("Venta", "", url);  
                submenu.addElement(item);
               model.addElement(submenu);   
            }
            if(rol1==3){
                DefaultSubMenu submenu = new DefaultSubMenu("Cliente"); 
                String url = app.getBasePath()+"cliente/index.xhtml";
                MenuItem item = new DefaultMenuItem("Comprar", "", url);  
                submenu.addElement(item);
                model.addElement(submenu);
                
            }
        }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    private Integer rol;

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
        
    //public static menuBean getInstance(){
        //if(INSTANCIA == null){
          //  INSTANCIA = new menuBean();
        //}
        //return INSTANCIA;
    //}
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }  
}
