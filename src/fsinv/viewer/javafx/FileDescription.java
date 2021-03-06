
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
public class FileDescription implements BaseDescription{
	
    public final String path;
    public final long bytes;
    public final Date ctime;
    public final Date mtime;
    public final long mimeId;
    public final long magicId;

    private FileDescription(String path, long bytes, Date ctime, Date mtime, long mimeId, long magicId){
        this.path = path;
        this.bytes = bytes;
        this.ctime = ctime;
        this.mtime = mtime;
        this.mimeId = mimeId;
        this.magicId = magicId;
    }

    public static FileDescription fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static FileDescription fromJSON(Map jsonMap) {
        String path = (String) jsonMap.get("path");
        long bytes = (Long) jsonMap.get("bytes");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date ctime = null;
        try {
            ctime = formatter.parse((String) jsonMap.get("ctime"));
        } catch (ParseException ex) {
            Logger.getLogger(FileDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date mtime = null;
        try {
            mtime = formatter.parse((String) jsonMap.get("mtime"));
        } catch (ParseException ex) {
            Logger.getLogger(FileDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        long mimeId = (Long) jsonMap.get("mimetype");
        long magicId = (Long) jsonMap.get("magicdescr");
        return new FileDescription(path, bytes, ctime, mtime, mimeId, magicId);
    }

    public static FileDescription fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public static FileDescription fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); 
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
