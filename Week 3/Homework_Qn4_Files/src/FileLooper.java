import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLooper {

    private String pathDirectory;
    private int lineCount = 0;

    public FileLooper(String folderPathName) {
        pathDirectory = folderPathName;
    }

    public int getLineCount() throws IOException{
        FileReader fr = new FileReader(pathDirectory);
        BufferedReader br = new BufferedReader(fr);

        // if the BufferedReader is able to read a new line, we increment linecount
        while (br.readLine() != null){
            lineCount++;
        }
        return lineCount;
    }

}
