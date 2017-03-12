package Homework_Qn_2;

import java.lang.*;

/** Homework Question 2 */

public class StringBufferThread extends Thread {

    StringBuffer stringBuffer;

    // For our thread objects to share the same object,
    // we need a constructor that accepts a StringBuffer object in the argument
    public StringBufferThread(StringBuffer buffer) {
        stringBuffer = buffer;
    }

    @Override
    public void run() {
        synchronized(stringBuffer){
            for (int i = 0; i < 100; i++) {
                System.out.print(stringBuffer.toString());
            }
            char letter = stringBuffer.charAt(0);
            letter++;
            stringBuffer.setCharAt(0, letter);
        }
    }
}
