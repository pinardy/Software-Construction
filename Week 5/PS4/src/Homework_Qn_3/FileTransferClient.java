package Homework_Qn_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class FileTransferClient {

    /** Instructions:
     * 1) Change the directory of the string fileName in FileTransferClient.java
     * 2) Change the directory of the FileWriter fw in FileTransfer.java
     */

	public static void main(String[] args) throws Exception {

		String fileName = "C:\\Pinardy\\SUTD\\Term_5\\50.003 - Software Construction\\Week_5\\PS4\\src\\Homework_Qn_3\\file1.txt";
		String hostName = "localhost";

		int portNumber = 4321;

		// Client stuff
		Socket echoSocket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		try {
			// Reading from text file
			FileReader textFile = new FileReader(fileName);
			BufferedReader stdIn = new BufferedReader(textFile);
			String userInput;

			while ((userInput = stdIn.readLine())!= null){
				out.println(userInput);
				out.flush();
				try {
					// Checking for timeout
					long timeIn = System.currentTimeMillis();
					while(!in.ready()){
						long duration = System.currentTimeMillis() - timeIn;
						if (duration >= 5000) {
						    throw new SocketTimeoutException();
                        }
					}

					String acknowledgement = in.readLine();
					if (acknowledgement.equals("Received")){
						continue;
					}
				} catch (SocketTimeoutException e){
					// re-transmitting 
					System.out.println("Re-transmitting");
					out.println(userInput);
					out.flush();
					continue;
				}
			}
			out.close();
			in.close();
			stdIn.close();
			echoSocket.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
