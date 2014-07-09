
package fsinv.viewer.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void fromJSON(List jsonList) {
        idCursor = 0;
        for( Object item : jsonList){
            long id = (Long) ((Map)item).get("id");
            String descr = (String) ((Map)item).get("description");
            descriptionMap.put(id,descr);
            if (id > idCursor)
                idCursor = id;
        }
    }

    public void fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
