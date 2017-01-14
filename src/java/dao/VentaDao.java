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

/**
 *
 * @author Juan José Ponce
 */
public interface VentaDao {
    public List<Venta> selectItems();
    public List<Venta> findAll();
    public List<Venta> findAllByDate(Date date);
    public boolean create(Venta venta);
    public boolean update(Venta venta);
    public Venta findById (Long id);
    public Venta lastVenta(int idCliente);
    public List<Venta> findAllByCliente(Cliente cliente);
}
