/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author Juan José Ponce
 */
public interface ClienteDao {
    public List<Cliente> selectItems();
    public Cliente findByCliente(Cliente cliente);
    public List<Cliente> findAll();
    public boolean create(Cliente cliente);
    public boolean update(Cliente cliente);
    public boolean delete(Integer id);
    public Cliente findById(Long id);
    public Cliente findByUserCedula(String cedula);
    public List<String> findByString(String busqueda);
}
