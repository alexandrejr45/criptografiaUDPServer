package principal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPServer {

    public static void main(String args[]) throws Exception {
        //Cria um servidor UDP na porta 9876
        DatagramSocket serverSocket = new DatagramSocket(5000);
        //Sockets apenas enviam bytes
        byte[] receiveData = new byte[1024];
        byte[] encryptedData = new byte[1024];
        byte[] valor = new byte[1024];
        Criptografia cripto = new Criptografia();
        
        
        while (true) {
            System.out.println("Servidor UDP ouvindo...");
            //Recebe as mensagens dos clientes
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            encryptedData = receivePacket.getData();
            
            byte[] val = new byte[16];
            
            for(int i= 0; i < 16; i++){
                val[i] = encryptedData[i];
            }
            
            
            valor = cripto.decrypt(val);


            //Responde ao mesmo IP e Porta do pacote recebido.
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(valor, valor.length, IPAddress, port);
            serverSocket.send(sendPacket);

            for (int i = 0; i < receiveData.length; i++) {
                receiveData[i] = 0;
            }
        }
    }
}
