import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Pin on 08-Feb-17.
 */
public class FileMain {
    static ArrayList<String> fileList = new ArrayList<>();

    public static void main(String[] args) {
        // Adding filenames
        fileList.add("C:\\Pinardy\\SUTD\\Term_5\\50.003 - Software Construction\\Week_3\\Homework_Qn4\\file1.txt");
        fileList.add("C:\\Pinardy\\SUTD\\Term_5\\50.003 - Software Construction\\Week_3\\Homework_Qn4\\file2.txt");
        fileList.add("C:\\Pinardy\\SUTD\\Term_5\\50.003 - Software Construction\\Week_3\\Homework_Qn4\\file3.txt");

        // prints out line count for each file in fileList
        fileIterator();
    }

    public static void fileIterator(){

        for (String string : fileList) {
            FileLooper loop = new FileLooper(string);
            try {
                System.out.println(String.format("'%s' contains %d line(s)", string, loop.getLineCount()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
