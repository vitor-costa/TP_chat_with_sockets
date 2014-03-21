import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Chat extends JFrame implements ActionListener {
	
	JLabel title;
	public static JButton enviar;
	public static JButton sair;
	JTextArea display;
	JScrollPane sp_display;
    JTextField input;
    TCPClient client;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    String nickname;
    String host;
    int port;
    Socket clientSocket;
    
	
	public Chat(String host, int port, final String nickname) throws UnknownHostException, IOException {
		
		
		SwingLayout customLayout = new SwingLayout(); //Inicializa a inteface gr�fica
        getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12)); //Seta fonte padr�o
        getContentPane().setLayout(customLayout); 

        title = new JLabel("Chat TCP"); //Texto inicial
        getContentPane().add(title);                
        
        display = new JTextArea();
        sp_display = new JScrollPane(display);
        display.setEditable(false);
        display.append("Chat - Ola! Sua sessao ja esta iniciada."); //Mensagem de sauda��o
        getContentPane().add(sp_display);
        
        title = new JLabel("Escreva sua mensagem abaixo:"); //Titulo antes do campo de mensagem
        getContentPane().add(title);
        
        input = new JTextField(""); //Campo de mensagem         
        getContentPane().add(input);
        
        enviar = new JButton("Enviar");//Bot�o de enviar
        getContentPane().add(enviar); 
        
        this.nickname = nickname;
        this.host = host;
        this.port = port;
        this.clientSocket = new Socket(host, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        new ServerListener().start();
        
        //Ouvinte do bot�o de enviar
		enviar.addActionListener(this);//new ActionListener(){
//		        public void actionPerformed(ActionEvent e) {
//		        	if(e.getSource() == enviar && !input.getText().equals("")){
//		        		try {
//							outToServer.writeBytes(nickname + ": " + input.getText() + "\n\n");
////							restartSocket();
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
////		        		display.append("\nVoc� - "+input.getText()); //Insere o texto digitado no campo do chat
//						input.setText("");   //Apaga o texto digitado				
//		        	}
//		        }
//
//				
//		});
		
	    //Encerra o programa quando aperta o bot�o fechar do Windows
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            System.exit(0);
	        }
	    });		
	}
	
	public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == enviar && !input.getText().equals("")){
    		try {
				outToServer.writeBytes(nickname + ": " + input.getText() + "\n\n");
				clientSocket = new Socket(host, port);
		        outToServer = new DataOutputStream(clientSocket.getOutputStream());
		        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				restartSocket();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
//    		display.append("\nVoc� - "+input.getText()); //Insere o texto digitado no campo do chat
			input.setText("");   //Apaga o texto digitado				
    	}
    }

	
	
	private void restartSocket() throws IOException {
		clientSocket.close();
		clientSocket = new Socket(host, port);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public void print(String msg) {
		display.append("\n - "+ msg);
	}
	
	public void liveChat() {
		while(true) {
			try {
				String modifiedSentence = inFromServer.readLine();
				display.append("\n");
				display.append(modifiedSentence);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class ServerListener extends Thread {
        public void run() {
            while(true) {
                try {
                	String modifiedSentence = inFromServer.readLine();
    				display.append("\n");
    				display.append(modifiedSentence);
                }
                catch(IOException e) {
                    break;
                }
            }
        }
    }
	
}