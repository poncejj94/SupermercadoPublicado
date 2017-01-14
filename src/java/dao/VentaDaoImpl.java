/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Cliente;
import model.Venta;
import model.Venta;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class VentaDaoImpl implements VentaDao {

    @Override
    public List<Venta> selectItems() {
        List<Venta> listado = null;
        String sql = "from model.Venta";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
    public List<Venta> findAll() {
        List<Venta> listado = null;
        String sql = "from model.Venta u";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
    public List<Venta> findAllByDate(Date date) {
        List<Venta> listado = null;
        String sql = "from model.Venta where date ='" + date + "'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
    public boolean create(Venta venta) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.persist(venta);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;

    }

    @Override
    public boolean update(Venta venta) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            session.update(venta);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public Venta findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta lastVenta(int idCliente) {
        List<Venta> venta = null;
        String sql = "from model.Venta v where cliente='" + idCliente + "' ORDER BY v.idVenta desc";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            venta = session.createQuery(sql).setMaxResults(1).list();
            

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return venta.get(0);
    }

    @Override
    public List<Venta> findAllByCliente(Cliente cliente) {
        List<Venta> venta = null;
        String sql = "from model.Venta v where cliente like'%"+cliente+"%'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            venta = session.createQuery(sql).list();
            

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return venta;
    }

}
