/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.RolDao;
import dao.RolDaoImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Rol;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "rolBean")
@RequestScoped
public class rolBean {

    private List<SelectItem> selectOneItemsRol;
    
    private List<Rol> roles;
    private Rol rol1 = new Rol();
    private Rol selectedRol = new Rol();
    
    
    public List<SelectItem> getSelectOneItemsRol(){
        this.selectOneItemsRol = new  ArrayList<SelectItem>();
        RolDao roldao = new RolDaoImpl();
        List<Rol> roles = roldao.selectItems();
        for (Rol rol : roles){
            SelectItem selectItem = new SelectItem(rol.getIdPerfil(),rol.getNombre());
            this.selectOneItemsRol.add(selectItem);
        }
        return selectOneItemsRol;
    }
    
    public rolBean() {
        this.roles = new ArrayList<Rol>();
    }

    public List<Rol> getRols() {
        RolDao rolDao = new RolDaoImpl();
        this.roles = rolDao.findAll();
        return roles;
    }

    public void setRols(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol1() {
        return rol1;
    }

    public void setRol1(Rol rol) {
        this.rol1 = rol;
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }
    
    
     
    public void btnCreateRol(ActionEvent event){
        String msg;
        RolDao rolDao = new RolDaoImpl();
        
        if(rolDao.create(this.rol1)){
            msg = "Se creo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al crear el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public void btnDeleteRol(ActionEvent actionEvent){
        String msg;
        RolDao rolDao = new RolDaoImpl();
        if(rolDao.delete(this.selectedRol.getIdPerfil())){
            msg = "Se elimino correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al eliminar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public void btnUpdateRol(ActionEvent actionEvent){
        String msg;
        RolDao rolDao = new RolDaoImpl();
        if(rolDao.update(this.selectedRol)){
            msg = "Se actualizo correctamente el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
            msg = "Eror al actualizar el registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
}
