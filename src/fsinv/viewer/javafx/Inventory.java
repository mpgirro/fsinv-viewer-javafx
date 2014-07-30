
package fsinv.viewer.javafx;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Maximilian Irro
 */
public class Inventory {
    
    public final LookupTable kindTab;
    public final LookupTable mimeTab;
    public final DirectoryDescription[] fileStructure;
	
    private Inventory(LookupTable kindTab, LookupTable mimeTab, DirectoryDescription[] fileStructure){
        this.kindTab = kindTab;
        this.mimeTab = mimeTab;
        this.fileStructure = fileStructure;
    }
    
    public static Inventory fromJSON(String jsonInputString){
        
        JSONParser parser=new JSONParser();
        Map fsinvData;
        try {
            System.out.println("Parsinf JSON file");
            fsinvData = (Map) parser.parse(jsonInputString);
            
            System.out.println("Building Kind Lookup Table");
            JSONArray kindTabJsonList = (JSONArray) fsinvData.get("kind_tab");
            LookupTable kindTab = LookupTable.fromJSON(kindTabJsonList);

            System.out.println("Building MIME Lookup Table");
            JSONArray mimeTabJsonList = (JSONArray) fsinvData.get("mime_tab");
            LookupTable mimeTab = LookupTable.fromJSON(mimeTabJsonList);

            System.out.println("Building File structure");
            JSONArray fstructArray = (JSONArray) fsinvData.get("file_structure");
            DirectoryDescription[] fileStructure = new DirectoryDescription[fstructArray.size()];
            for( int i = 0; i < fstructArray.size(); i++ ){
                fileStructure[i] = DirectoryDescription.fromJSON((Map)fstructArray.get(i));
            }

            System.out.println("New FsInventory created");
            return new Inventory(kindTab, mimeTab, fileStructure);
        } catch (ParseException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
