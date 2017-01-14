/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import util.MyUtil;

/**
 *
 * @author Juan José Ponce
 */
@Named(value = "appBean")
@ApplicationScoped
public class appBean {

    /**
     * Creates a new instance of appBean
     */
    public appBean() {
    }
    
    public String getBaseUrl()
    {
        return MyUtil.basurl();
    }
    
    public String getBasePath()
    {
        return MyUtil.basepath();
    }
}
