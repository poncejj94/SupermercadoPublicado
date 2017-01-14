/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static com.sun.faces.facelets.tag.composite.ImplementationHandler.Name;
import dao.*;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.HibernateUtil;
import util.MyUtil;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Integer rol;
    //private static loginBean INSTANCIA = null;
    
  
    public loginBean(){
       
    this.usuarioDao = new UsuarioDaoImpl();
    if(this.usuario==null){
        this.usuario = new Usuario();
        }
    //patron de diseño singleton
    }
   // public static loginBean getInstance(){
     //   if(INSTANCIA == null){
       //     INSTANCIA = new loginBean();
        //}
        //return INSTANCIA;
    //}
    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    
    
        public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        boolean loggedIn;
        
        String ruta = MyUtil.baspathlogin()+"views/inicio1.xhtml";
        this.usuario = this.usuarioDao.login(this.usuario);        
        if(this.usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            
            //menuBean menu = menuBean.getInstance();
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            
            loggedIn = true;
            HttpSession hs = HibernateUtil.getSession();
            hs.setAttribute("usuario", this.usuario);
            ruta = MyUtil.baspathlogin()+"views/inicio.xhtml";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Usuario y/o contraseña incorrecta.");
            
            if(this.usuario==null){
                this.usuario = new Usuario();
                
            }
        }         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }   
        public void logout()
        {
            String ruta = MyUtil.baspathlogin()+"login.xhtml";
            RequestContext context = RequestContext.getCurrentInstance();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            HttpSession session = HibernateUtil.getSession();
            session.invalidate();
            
            context.addCallbackParam("loggetOut", true);
            context.addCallbackParam("ruta", ruta);
            System.out.println(org.hibernate.Version.getVersionString());
        }
        
        public void registro(ActionEvent event){
            RequestContext context = RequestContext.getCurrentInstance();
            
            String ruta = MyUtil.baspathlogin()+"views/registro/cambioclave.xhtml";
            context.addCallbackParam("ruta", ruta);
        }
}

