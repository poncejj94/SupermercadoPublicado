/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Producto;

/**
 *
 * @author Juan José Ponce
 */
public interface ProductoDao {
    public List<Producto> selectItems();
    public Producto findByProducto(Producto producto);
    public List<Producto> findAll();
    public List<Producto> findAllByCtegoria(Integer id);
    public boolean create(Producto producto);
    public boolean update(Producto producto);
    public boolean delete(Integer id);
    public Producto findById (Long id);
    public boolean updateCantidad(Producto producto);
}
