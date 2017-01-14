/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class RolDaoImpl implements RolDao{

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        String sql ="from model.Rol";
        session.beginTransaction();
        try {
            
            listado = session.createQuery(sql).list();
            
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public Rol findByRol(Rol rol) {
        Rol model = null;        
        
        String sql ="from model.Rol where rol = '"+rol.getNombre()+"'";
        
        session.beginTransaction();
        try {
            
            model=(Rol) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public Rol findById(Long id) {
        Rol model = null;        
        
        String sql ="from model.Rol where id_rol ="+id;
        session.beginTransaction();
        
        try {
            
            model=(Rol) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Rol> findAll() {
        List<Rol> listado = null;
        String sql ="from model.Rol u";
        session.beginTransaction();
        
        try {
            
            listado = session.createQuery(sql).list();
            
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public boolean create(Rol rol) {
        boolean flag;
        
        session.beginTransaction();
        try {
            session.persist(rol);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Rol rol) {
         boolean flag;
        session.beginTransaction();
        
        try {
            Rol roldb = (Rol) session.load(Rol.class, rol.getIdPerfil());
            roldb.setEstado(rol.getEstado());
            roldb.setDescripcion(rol.getDescripcion());
            roldb.setNombre(rol.getNombre());
            
            session.update(roldb);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        
        session.beginTransaction();
        
        try {
            Rol rol = (Rol) session.load(Rol.class, id);
            session.delete(rol);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
    
    public Rol rolCliente() {
        Rol model = null;        
        
        String sql ="from model.Rol where nombre='Cliente' or idPerfil=3";
        
        session.beginTransaction();
        try {
            
            model=(Rol) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }    
    }
}
