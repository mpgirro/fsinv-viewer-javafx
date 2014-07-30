
package fsinv.viewer.javafx;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximilian Irro
 */
public class DirectoryDescription implements BaseDescription{
	
    public final String path;
    public final long bytes;
    public final Date ctime;
    public final Date mtime;
    public final long fileCount;
    public final long itemCount;
    public final BaseDescription[] fileList;

    private DirectoryDescription(String path, long bytes, Date ctime, Date mtime, long fileCount, long itemCount, BaseDescription[] fileList){
        this.path = path;
        this.bytes = bytes;
        this.ctime = ctime;
        this.mtime = mtime;
        this.fileCount = fileCount;
        this.itemCount = itemCount;
        this.fileList = fileList;
    }

    public static DirectoryDescription fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static DirectoryDescription fromJSON(Map jsonMap) {
        String path = (String) jsonMap.get("path");
        long bytes = (Long) jsonMap.get("bytes");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date ctime = null;
        try {
            ctime = formatter.parse((String) jsonMap.get("ctime"));
        } catch (ParseException ex) {
            Logger.getLogger(DirectoryDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date mtime = null;
        try {
            mtime = formatter.parse((String) jsonMap.get("mtime"));
        } catch (ParseException ex) {
            Logger.getLogger(DirectoryDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        long fileCount = (Long) jsonMap.get("file_count");
        long itemCount = (Long) jsonMap.get("item_count");
        BaseDescription[] fileList = parseJSONFileList((List) jsonMap.get("file_list"));
        return new DirectoryDescription(path, bytes, ctime, mtime, fileCount, itemCount, fileList);
    }

    public static DirectoryDescription fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static DirectoryDescription fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private static BaseDescription[] parseJSONFileList(List jsonList){
        List<BaseDescription> fileList = new ArrayList<>();
        for( Object item : jsonList){
            switch((String) ((Map)item).get("type")){
                case "directory":
                    fileList.add(DirectoryDescription.fromJSON((Map)item));
                    break;
                case "file":
                    fileList.add(FileDescription.fromJSON((Map)item));
                    break;
                default:
                    // chaos and madness
            }
        }
        Collections.sort( fileList );
        return (BaseDescription[]) fileList.toArray( new BaseDescription[0]);
    }

    @Override
    public String getName() {
        String[] pathParts = path.split("/");
        return pathParts[pathParts.length-1];
    }

    @Override
    public long getSize() {
        return bytes;
    }

    @Override
    public Date getModificationTime() {
        return mtime;
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo( ((BaseDescription)o).getName() );
    }
	
}
