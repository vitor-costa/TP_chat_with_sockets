import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
	static Chat chat;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
        String nick;

        Scanner scan = new Scanner(System.in);
        nick = scan.nextLine();

        Chat chat = new Chat("127.0.0.1", 6789, nick);
        
    	chat.setResizable(false);
    	chat.setTitle("Chat TCP"); //T�tulo no cabe�alho do Windows
    	chat.pack();
    	chat.setVisible(true);
	}

}
