import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by vitor.costa on 3/14/14.
 */
public class ServerThread extends Thread {
    Socket connectionSocket;
    String clientSentence;
    String capitalizedSentence;
    DataOutputStream outToClient;
    ServerThread threads[];

    public ServerThread(Socket connectionSocket, ServerThread[] threads) {
        this.connectionSocket = connectionSocket;
        this.threads = threads;
    }

    public void run() {
        try{
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
//            capitalizedSentence = clientSentence.toUpperCase() + '\n';

//            outToClient.writeBytes(capitalizedSentence);
            for(int i = 0; i < 10; i++) {
                if(threads[i] != null) {
                    threads[i].outToClient.writeBytes(clientSentence);
                }
            }

            for(int i = 0; i < 10; i++) {
                if(threads[i] == this) {
                    threads[i] = null;
                }
            }


            connectionSocket.close();
        } catch(Exception e) {

        }
    }
}
