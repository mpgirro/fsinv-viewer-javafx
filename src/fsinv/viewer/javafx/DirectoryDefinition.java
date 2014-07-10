
package fsinv.viewer.javafx;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximilian Irro
 */
public class DirectoryDefinition implements FileStructureEntity{
	
    private final String path;
    private final long bytes;
    private final Date ctime;
    private final Date mtime;
    private final long fileCount;
    private final long itemCount;
    private final FileStructureEntity[] fileList;

    private DirectoryDefinition(String path, long bytes, Date ctime, Date mtime, long fileCount, long itemCount, FileStructureEntity[] fileList){
        this.path = path;
        this.bytes = bytes;
        this.ctime = ctime;
        this.mtime = mtime;
        this.fileCount = fileCount;
        this.itemCount = itemCount;
        this.fileList = fileList;
    }

    public static DirectoryDefinition fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static DirectoryDefinition fromJSON(Map jsonMap) {
        String path = (String) jsonMap.get("path");
        long bytes = (Long) jsonMap.get("bytes");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date ctime = null;
        try {
            ctime = formatter.parse((String) jsonMap.get("ctime"));
        } catch (ParseException ex) {
            Logger.getLogger(DirectoryDefinition.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date mtime = null;
        try {
            mtime = formatter.parse((String) jsonMap.get("mtime"));
        } catch (ParseException ex) {
            Logger.getLogger(DirectoryDefinition.class.getName()).log(Level.SEVERE, null, ex);
        }
        long fileCount = (Long) jsonMap.get("file_count");
        long itemCount = (Long) jsonMap.get("item_count");
        FileStructureEntity[] fileList = parseJSONFileList((List) jsonMap.get("file_list"));
        return new DirectoryDefinition(path, bytes, ctime, mtime, fileCount, itemCount, fileList);
    }

    public static DirectoryDefinition fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static DirectoryDefinition fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private static FileStructureEntity[] parseJSONFileList(List jsonList){
        List<FileStructureEntity> fileList = new ArrayList<>();
        for( Object item : jsonList){
            switch((String) ((Map)item).get("type")){
                case "directory":
                    fileList.add(DirectoryDefinition.fromJSON((Map)item));
                    break;
                case "file":
                    fileList.add(FileDefinition.fromJSON((Map)item));
                    break;
                default:
                    // chaos and madness
            }
        }
        return (FileStructureEntity[]) fileList.toArray( new FileStructureEntity[0]);
    }
	
}
