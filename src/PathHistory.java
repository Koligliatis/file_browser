import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
// Keeps the paths to a file
// return last path from file
// and write path to file
public class PathHistory {
    private File file;
    private long seekPosition;
    private long truncatePosition;
    private boolean truncate;

    public PathHistory() {
        file = new File("history.txt");
        if (file.exists()) {
            file.delete();
        }
        truncate = false;
    }
    // read line from file and return it to string
    public String getLine() {
        truncate = true;
        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            long filePointer;
            StringBuilder sb = new StringBuilder();

            for(filePointer = seekPosition; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();
                // new line
                if( readByte == 0xA ) {
                    if( filePointer == seekPosition ) {
                        continue;
                    }
                    break;
                // carriage return
                } else if( readByte == 0xD ) {
                    if( filePointer == seekPosition - 1 ) {
                        continue;
                    }
                    break;
                }
                sb.append( ( char ) readByte );
            }
            truncatePosition = seekPosition;
            seekPosition = filePointer;
            String lastLine = sb.reverse().toString();
            return lastLine;
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        }
    }
    // add to tail of file the line
    // if before has called getLine
    // truncate the file
    public void addTail(String line) {
        RandomAccessFile fileHandler = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileHandler = new RandomAccessFile( file, "rw" );
            long fileLength = fileHandler.length();
            if (truncate) {
                if (seekPosition != -1) {
                    fileHandler.getChannel().truncate(truncatePosition + 1);
                    fileLength = fileHandler.length();
                    truncate = false;
                }
            }
            seekPosition = fileLength - 1;

            fileHandler.seek( fileLength );
            fileHandler.write(line.getBytes());
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
        } catch( java.io.IOException e ) {
            e.printStackTrace();
        }
    }
    // delete history
    public void deleteFile() {
        file.delete();
    }
}
