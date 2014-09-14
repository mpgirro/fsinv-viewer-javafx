
package fsinv.viewer.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;

/**
 *
 * @author Maximilian Irro
 */
public class LookupTable {
    
    private long idCursor;
    private HashMap<Long,String> valueMap;
    
    private LookupTable(long idCursor, HashMap<Long,String> descriptionMap){
        this.idCursor = idCursor;
        this.valueMap = descriptionMap;
    }
    
    public String getValue(long id){
        return (String) valueMap.get(id);
    }
    
    public static LookupTable fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static LookupTable fromJSON(JSONArray jsonList) {
	HashMap<Long,String> valueMap = new HashMap<>();
        long idCursor = 0;
        for( Object item : jsonList){
            long id = (Long) ((Map)item).get("id");
            String descr = (String) ((Map)item).get("value");
            valueMap.put(id,descr);
            if (id > idCursor)
                idCursor = id;
        }
	return new LookupTable(idCursor,valueMap);
    }

    public static LookupTable fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static LookupTable fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
