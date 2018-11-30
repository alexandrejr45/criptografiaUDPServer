package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class UDPClient {

    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        byte[] valor = new byte[1024];
        
        Criptografia cripto = new Criptografia();

        while (true) {
            System.out.println("Cliente preparado para enviar: ");
            //A entrada do usuário
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes("UTF-8");
            valor = cripto.encrypt(sendData);
            
            //Cria pacote udp
            DatagramPacket sendPacket = new DatagramPacket(valor, valor.length, IPAddress, 5000);
            //envia ao servidor
            clientSocket.send(sendPacket);
            //Recebe resposta do servidor
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Recebido do servidor UDP:" + modifiedSentence);
            //Fecha conex�o: clientSocket.close();
        }
    }
}
