
package fsinv.viewer.javafx;

import java.util.Date;

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

    public DirectoryDefinition(String path, long bytes, Date ctime, Date mtime, long fileCount, long itemCount, FileStructureEntity[] fileList){
        this.path = path;
        this.bytes = bytes;
        this.ctime = ctime;
        this.mtime = mtime;
        this.fileCount = fileCount;
        this.itemCount = itemCount;
        this.fileList = fileList;
    }

    @Override
    public void fromDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fromJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fromXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fromYAML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
