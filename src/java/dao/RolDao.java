/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;

/**
 *
 * @author Juan José Ponce
 */
public interface RolDao {
    public List<Rol> selectItems();
    public Rol findByRol(Rol rol);
    public List<Rol> findAll();
    public boolean create(Rol rol);
    public boolean update(Rol rol);
    public boolean delete(Integer id);
    public Rol findById (Long id);
    public Rol rolCliente();
}
