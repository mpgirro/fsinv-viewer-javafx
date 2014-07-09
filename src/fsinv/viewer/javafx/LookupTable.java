
package fsinv.viewer.javafx;

import java.util.HashMap;

/**
 *
 * @author Maximilian Irro
 */
public class LookupTable {
    
    private long idCursor;
    private HashMap<Long,String> descriptionMap;
    
    public LookupTable(){
    
    }
    
    public String getDescription(long id){
        return (String) descriptionMap.get(id);
    }
    
    public void fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fromJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
