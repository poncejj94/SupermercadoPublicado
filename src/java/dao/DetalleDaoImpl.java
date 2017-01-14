/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Detalleventa;
import model.Detalleventa;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Juan José Ponce
 */
public class DetalleDaoImpl implements DetalleDao {

    @Override
    public boolean create(Detalleventa venta) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.merge(venta);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public boolean update(Detalleventa venta) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Detalleventa ventadb = (Detalleventa) session.load(Detalleventa.class, venta.getIdDetalleVenta());
            ventadb.setProducto(venta.getProducto());
            ventadb.setCantidad(venta.getCantidad());
            ventadb.setTotal(venta.getTotal());
            ventadb.setVenta(venta.getVenta());

            session.update(ventadb);
            session.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }

    @Override
    public List<Detalleventa> findAllByVenta(Integer idVenta) {
        List<Detalleventa> listado = null;
        String sql = "from model.Detalleventa where date ='" + idVenta + "'";
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            listado = session.createQuery(sql).list();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        return listado;
    }
}
