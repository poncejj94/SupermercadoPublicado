/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Detalleventa;

/**
 *
 * @author Juan José Ponce
 */
public interface DetalleDao {
    public List<Detalleventa> findAllByVenta(Integer idVenta);
    public boolean create(Detalleventa detalleVenta);
    public boolean update(Detalleventa detalleVenta);
}
