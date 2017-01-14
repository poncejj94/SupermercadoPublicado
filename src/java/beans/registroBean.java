/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.RolDao;
import dao.RolDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cliente;
import model.Rol;
import model.Usuario;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "registroBean")
@RequestScoped
public class registroBean {

    private Usuario usuario1 = new Usuario();
    private Cliente cliente1 = new Cliente();

    /**
     * Creates a new instance of registroBean
     */
    public registroBean() {
        
    }
    
    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }
    
    public void btnCreateCliente(ActionEvent event){
        String msg;
        ClienteDao clienteDao = new ClienteDaoImpl();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        RolDao rolDao = new RolDaoImpl();
        this.usuario1.setUsuario(this.cliente1.getCedula());
        this.usuario1.setRol(rolDao.rolCliente());
        
        if(clienteDao.create(this.cliente1) && usuarioDao.create(this.usuario1)){
            msg = "Cliente registrado corectamente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al registrar cliente";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }        
    }
    
}
