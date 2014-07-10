
package fsinv.viewer.javafx;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximilian Irro
 */
public class FileDefinition implements FileStructureEntity{
	
    public final String path;
    public final long bytes;
    public final Date ctime;
    public final Date mtime;
    public final long mimeId;
    public final long kindId;

    private FileDefinition(String path, long bytes, Date ctime, Date mtime, long mimeId, long kindId){
        this.path = path;
        this.bytes = bytes;
        this.ctime = ctime;
        this.mtime = mtime;
        this.mimeId = mimeId;
        this.kindId = kindId;
    }

    public static FileDefinition fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static FileDefinition fromJSON(Map jsonMap) {
        String path = (String) jsonMap.get("path");
        long bytes = (Long) jsonMap.get("bytes");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date ctime = null;
        try {
            ctime = formatter.parse((String) jsonMap.get("ctime"));
        } catch (ParseException ex) {
            Logger.getLogger(FileDefinition.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date mtime = null;
        try {
            mtime = formatter.parse((String) jsonMap.get("mtime"));
        } catch (ParseException ex) {
            Logger.getLogger(FileDefinition.class.getName()).log(Level.SEVERE, null, ex);
        }
        long mimeId = (Long) jsonMap.get("mime_id");
        long kindId = (Long) jsonMap.get("kind_id");
        return new FileDefinition(path, bytes, ctime, mtime, mimeId, kindId);
    }

    public static FileDefinition fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static FileDefinition fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String name() {
        String[] pathParts = path.split("/");
        return pathParts[pathParts.length-1];
    }
    
}
