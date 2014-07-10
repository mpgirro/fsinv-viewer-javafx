
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
	
    private FsInventory(LookupTable kindTab, LookupTable mimeTab, DirectoryDefinition fileStructure){
        this.kindTab = kindTab;
        this.mimeTab = mimeTab;
        this.fileStructure = fileStructure;
    }
    
    public void fromJSON(String inputJsonString){
        
        JsonParserFactory factory = JsonParserFactory.getInstance(); 
        JSONParser parser = factory.newJsonParser();
  
        Map fsinvData = parser.parseJson(inputJsonString);

        List kindTabJsonList = (List) fsinvData.get("kind_tab");
        LookupTable kindTab = LookupTable.fromJSON(kindTabJsonList);
        
        List mimeTabJsonList = (List) fsinvData.get("mime_tab");
        LookupTable mimeTab = LookupTable.fromJSON(mimeTabJsonList);
        
        Map fstructTabMap = (Map) fsinvData.get("file_structure");
        DirectoryDefinition fileStructure = DirectoryDefinition.fromJSON(fstructTabMap);
        
    }
    
}
