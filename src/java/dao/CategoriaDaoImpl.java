/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class CategoriaDaoImpl implements CategoriaDao{

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
    @Override
    public List<Categoria> selectItems() {
        List<Categoria> listado = null;
        String sql ="from model.Categoria";
        
       
        try {
            session.beginTransaction();
            listado = session.createQuery(sql).list();
            
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public Categoria findByCategoria(Categoria categoria) {
        Categoria model = null;        
        
        String sql ="from model.Categoria where categoria = '"+categoria.getNombre()+"'";
        
        try {
            session.beginTransaction();
            model=(Categoria) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public Categoria findById(Long id) {
        Categoria model = null;        
        
        String sql ="from model.Categoria where id_categoria ="+id;
        
       
        try {
            session.beginTransaction();
            model=(Categoria) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> listado = null;
        String sql ="from model.Categoria";
        
       
        try {
            session.beginTransaction();
            listado = session.createQuery(sql).list();
            
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public boolean create(Categoria categoria) {
        boolean flag;
        
       
        try {
            session.beginTransaction();
            session.persist(categoria);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Categoria categoria) {
         boolean flag;
        
       
        try {
            session.beginTransaction();
            session.update(categoria);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        
        
       
        try {
            session.beginTransaction();
            Categoria categoria = (Categoria) session.load(Categoria.class, id);
            session.delete(categoria);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }
}
