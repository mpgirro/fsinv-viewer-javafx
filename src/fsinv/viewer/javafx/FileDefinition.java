
package fsinv.viewer.javafx;

import java.util.Date;

/**
 *
 * @author Maximilian Irro
 */
public class FileDefinition {
	
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
    
}
