package Homework_Qn_2;

import java.lang.*;
import java.util.ArrayList;

/** Homework Question 2 */

/** Discussion:
 * A String object is immutable. If we try to alter its values, another object gets created.
 * However, a StringBuffer object is mutable so it can change their values.
 * Also, usage of StringBuffer is thread-safe as it is synchronous.
 */


public class StringBuff {

    public static void main(String[] args) throws InterruptedException {

        int numOfThreads = 3;

        StringBuffer stringBuffer = new StringBuffer("A");
        ArrayList<StringBufferThread> threads = new ArrayList<>();

        for (int i = 0; i < numOfThreads; i++){
            StringBufferThread thread = new StringBufferThread(stringBuffer);
            threads.add(thread);
            thread.start();
        }

    }
}
