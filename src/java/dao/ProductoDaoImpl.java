/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class ProductoDaoImpl implements ProductoDao {

    @Override
    public List<Producto> selectItems() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Producto> listado = null;
        String sql = "from model.Producto";

        try {
            session.beginTransaction();
            session.beginTransaction();
            listado = session.createQuery(sql).list();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }

    @Override
    public Producto findByProducto(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Producto model = null;
        String sql = "from model.Producto where producto = '" + producto.getNombre() + "'";

        try {
            session.beginTransaction();
            model = (Producto) session.createQuery(sql).uniqueResult();

            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Producto findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Producto model = null;

        String sql = "from model.Producto where id_producto =" + id;

        try {
            session.beginTransaction();
            model = (Producto) session.createQuery(sql).uniqueResult();

            return model;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Producto> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Producto> listado = null;
        String sql = "from model.Producto u";
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
    public List<Producto> findAllByCtegoria(Integer categoria) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Producto> listado = null;
        String sql = "from model.Producto where categoria ='" + categoria + "'";

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
    public boolean create(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        boolean flag;
        try {
            session.beginTransaction();
            session.persist(producto);

            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;

    }

    @Override
    public boolean update(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        boolean flag;

        try {
            session.beginTransaction();
            Producto productodb = (Producto) session.load(Producto.class, producto.getIdProducto());
            productodb.setNombre(producto.getNombre());
            productodb.setPrecio(producto.getPrecio());
            productodb.setImagen(producto.getImagen());

            session.update(productodb);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }

                
        return flag;
    }

    @Override
    public boolean updateCantidad(Producto producto) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        boolean flag;
        try {
            
            session.beginTransaction();
            session.update(producto);
            session.getTransaction().commit();
   
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
        boolean flag;
        try {
            session.beginTransaction();
            Producto producto = (Producto) session.load(Producto.class, id);
            session.delete(producto);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
  
        return flag;
    }
}
