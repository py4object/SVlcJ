package uk.co.caprica.vlcjplayer.view.synchornization;

import uk.co.caprica.vlcjplayer.Application;
import uk.co.caprica.vlcjplayer.synchronizationEvents.SynchronizedEvent;

import java.io.*;
import java.net.Socket;

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
    public static Client getClient(String address,int port){
            if(INSTANCE ==null){
                INSTANCE=new Client(address,port);
            }
        return INSTANCE;
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


    public static Client getClient() {
        return INSTANCE;
    }
}
