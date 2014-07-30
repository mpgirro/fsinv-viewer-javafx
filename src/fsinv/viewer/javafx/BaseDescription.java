
package fsinv.viewer.javafx;

import java.util.Date;

/**
 *
 * @author Maximilian Irro
 */
public interface BaseDescription extends Comparable{
    
    public String getName();
    
    public long getSize();
    
    public Date getModificationTime();
    
}
