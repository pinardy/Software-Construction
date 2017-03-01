package Homework_Qn_3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FileTransfer {

    /** Instructions:
     * 1) Change the directory of the string fileName in FileTransferClient.java
     * 2) Change the directory of the FileWriter fw in FileTransfer.java
     */

	private static ServerSocket serverSocket;

	public static void main(String[] args) throws Exception {
		FileTransfer ft = new FileTransfer();
		ft.getClient();
	}

	public void getClient() throws Exception{
		serverSocket = new ServerSocket(4321);
		Thread clientListener = new Thread(new Runnable() {
			private int ID = 0;
			@Override
			public void run() {
				while(true){
					try {
						Socket clientSocket = serverSocket.accept();
						ID++;
						PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
						BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						new Thread(new Runnable(){
							final int threadID = ID;
							@Override
							public void run(){
								FileWriter fw = null;
								try {
                                    fw = new FileWriter(String.format("C:\\Pinardy\\SUTD\\Term_5\\50.003 - Software Construction\\Week_5\\PS4\\src\\Homework_Qn_3\\FileResult%d.txt", threadID), true);

								} catch (IOException e1) {
									e1.printStackTrace();
								}
								String textFromFile;
								try {
									while ((textFromFile = br.readLine()) != null){
										// 1) Readline to get string
										// 2) Send a feedback to client
										// 3) Write to file

										pw.println("Received");
										pw.flush();
                                        fw.write(textFromFile);
                                        fw.flush();
									}
                                    fw.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		clientListener.start();
	}
}
