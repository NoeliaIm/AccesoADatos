/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

/**
 *
 * @author Fernando
 */
public class MiNeodatis {
    
    private final ODB odb;

    public MiNeodatis(String pNombre){
        
        odb=ODBFactory.open(pNombre);
        
    }
    
    public MiNeodatis(String pNombre, String pUsuario, String pPassword){
        
        odb=ODBFactory.open(pNombre, pUsuario, pPassword);
        
    }
    
    public void guardarObjeto(Object objeto){
        
        odb.store(objeto);
        
    }
    
    //Probar cpn Class.forName
    public OID obtenerOID(Object objeto){
        return odb.getObjectId(objeto);
    }
    
    public ODB getOdb() {
        return odb;
    }
    
    public void commit(){
        
    }
    
    public void cerrarODB(){
        
        odb.close();
        
    }
    
}
