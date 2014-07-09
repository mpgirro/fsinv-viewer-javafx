
package fsinv.viewer.javafx;

import java.util.Date;

/**
 *
 * @author Maximilian Irro
 */
public class FileDefinition implements FileStructureEntity{
	
	private final String path;
	private final long bytes;
	private final Date ctime;
	private final Date mtime;
	private final int mimeId;
	private final int kindId;
	
	public FileDefinition(String path, long bytes, Date ctime, Date mtime, int mimeId, int kindId){
		this.path = path;
		this.bytes = bytes;
		this.ctime = ctime;
		this.mtime = mtime;
		this.mimeId = mimeId;
		this.kindId = kindId;
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
