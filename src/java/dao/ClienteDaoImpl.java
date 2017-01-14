/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class ClienteDaoImpl implements ClienteDao{

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    
    @Override
    public List<Cliente> selectItems() {
        List<Cliente> listado = null;
        String sql ="from model.Cliente";
        
        
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
    public Cliente findByCliente(Cliente cliente) {
        Cliente model = null;        
        
        String sql ="from model.Cliente where cliente = '"+cliente.getNombre()+"'";
        
        
        try {
            session.beginTransaction();
            model=(Cliente) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
    
    @Override
    public Cliente findById(Long id) {
        Cliente model = null;        
        
        String sql ="from model.Cliente where id_cliente ="+id;
        
        
        try {
            session.beginTransaction();
            model=(Cliente) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Cliente findByUserCedula(String cedula) {
        Cliente model = null;        
        
        String sql ="from model.Cliente where cedula='"+cedula+"'";
        
        
        try {
            session.beginTransaction();
            model=(Cliente) session.createQuery(sql).uniqueResult();
            
            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> listado = null;
        String sql ="from model.Cliente u";
        
        
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
    public boolean create(Cliente cliente) {
        boolean flag;
        
        
        try {
            session.beginTransaction();
            session.persist(cliente);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    
    }

    @Override
    public boolean update(Cliente cliente) {
         boolean flag;
        
        
        try {
            session.beginTransaction();
            Cliente clientedb = (Cliente) session.load(Cliente.class, cliente.getIdCliente());
            clientedb.setCedula(cliente.getCedula());
            clientedb.setNombre(cliente.getNombre());
            clientedb.setEmail(cliente.getEmail());
            clientedb.setDireccion(cliente.getDireccion());
            
            session.update(clientedb);
            
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
            Cliente cliente = (Cliente) session.load(Cliente.class, id);
            session.delete(cliente);
            
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;    
    }

    @Override
    public List<String> findByString(String busqueda) {
        List<String> listado = new ArrayList<String>();
        
        String sql ="select nombre from model.Cliente u where nombre like'%"+busqueda+"%' or cedula like '%"+busqueda+"%'";
        
        
        try {
            session.beginTransaction();
            listado = session.createQuery(sql).list();
            
            
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }
}
