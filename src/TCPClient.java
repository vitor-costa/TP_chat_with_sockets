import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * Created by vitor.costa on 3/14/14.
 */
public class TCPClient {
    public static void main(String argv[]) throws IOException {
        String nickname;
        String sentence;
        String modifiedSentence;
        Thread listener;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        System.out.println("Type your nickname:");
//        nickname = inFromUser.readLine();
        nickname = in.nextLine();

        Socket clientSocket = new Socket("127.0.0.1", 6789);


        while(true) {
            clientSocket = new Socket("127.0.0.1", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("chat...");
//           sentence = inFromUser.readLine();
             sentence = in.nextLine();
             outToServer.writeBytes(nickname + ": " + sentence + "\n\n");

            modifiedSentence = inFromServer.readLine();

            System.out.println(modifiedSentence);

            clientSocket.close();
        }
    }
}
