/**
 * Created by vitor.costa on 3/14/14.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        String clientSentence;
        String capitalizedSentence;

        ServerThread threads[] = new ServerThread[10];

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(true) {
            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
                for(int i = 0; i < 10; i++) {
                    if(threads[i] == null) {
                        threads[i] = new ServerThread(connectionSocket, threads);
                        threads[i].start();
                        break;
                    }
                }
            } catch (IOException e) {
                connectionSocket.close();
            }

//            Socket connectionSocket = welcomeSocket.accept();
//
//            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//
//            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//
//            clientSentence = inFromClient.readLine();
//
//            capitalizedSentence = clientSentence.toUpperCase() + '\n';
//
//            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
