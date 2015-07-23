package uk.co.caprica.vlcjplayer.view.synchornization;

import uk.co.caprica.vlcjplayer.Application;
import uk.co.caprica.vlcjplayer.synchronizationEvents.SynchronizedEvent;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

/**
 * Created by kozo on 7/3/15.
 */
public class Client extends Thread{
    private String ip;
    private int port;
    private Socket mSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private static Client INSTANCE;
    DatagramSocket timeSocket;
    public static Client makeNewConnection(String address, int port){

        INSTANCE=new Client(address,port);
        return INSTANCE;
    }
    public static void disconnect(){
        INSTANCE=null;
    }
    private Client(String ip,int port){
        this.ip=ip;
        this.port=port;
        try {
            mSocket=new Socket(ip,port);
            mSocket.setTcpNoDelay(true);
            inputStream=new ObjectInputStream(mSocket.getInputStream());
            outputStream=new ObjectOutputStream(mSocket.getOutputStream());
            Application.isOnSynchronization=true;
             timeSocket=new DatagramSocket();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
      Object msg;
        while (true){
            try {
                msg=inputStream.readObject();
                Application.application().post(msg);
            }catch (EOFException e){
                //No event been sent on the stream
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void broadcastEvent( SynchronizedEvent event){
        try {
            outputStream.writeObject(event);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getServerTime(){
        byte [] buff=new byte[250];
        try {
            DatagramPacket packet=new DatagramPacket(buff,buff.length, InetAddress.getByName(ip),port-1);
            timeSocket.send(packet);
            packet=new DatagramPacket(buff,buff.length);
            timeSocket.receive(packet);
            return ByteBuffer.wrap(packet.getData()).getLong();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Client getClient() {
        return INSTANCE;
    }
}
