
package fsinv.viewer.javafx;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maximilian Irro
 */
public class FsInventory {
    
    private LookupTable kindTab;
    private LookupTable mimeTab;
    private DirectoryDefinition fileStructure;
    
    public void fromJSON(String inputJsonString){
        
        JsonParserFactory factory = JsonParserFactory.getInstance(); 
        JSONParser parser = factory.newJsonParser();
  
        Map fsinvData = parser.parseJson(inputJsonString);

        List kindTabJsonList = (List) fsinvData.get("kind_tab");
        kindTab = new LookupTable();
        kindTab.fromJSON(kindTabJsonList);
        
        List mimeTabJsonList = (List) fsinvData.get("mime_tab");
        mimeTab = new LookupTable();
        mimeTab.fromJSON(mimeTabJsonList);
        
        Map fstructTabMap = (Map) fsinvData.get("file_structure");
        
    }
    
}
