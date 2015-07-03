package uk.co.caprica.vlcjplayer.view.synchornization;

import uk.co.caprica.vlcjplayer.Application;
import uk.co.caprica.vlcjplayer.synchronizationEvents.SynchronizationEvent;

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
    private Client(String ip,int port){
        this.ip=ip;
        this.port=port;
        try {
            mSocket=new Socket(ip,port);
            inputStream=new ObjectInputStream(mSocket.getInputStream());
            outputStream=new ObjectOutputStream(mSocket.getOutputStream());

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
    public void broadcastEvent(SynchronizationEvent event){
        try {
            outputStream.writeObject(event);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
