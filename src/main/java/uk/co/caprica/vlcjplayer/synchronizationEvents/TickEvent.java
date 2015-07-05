package uk.co.caprica.vlcjplayer.synchronizationEvents;

/**
 * Created by kozo on 7/3/15.
 */
public class TickEvent extends SynchronizedEvent{
    int  Value;
    public TickEvent(int value){
        Value=value;
    }
    public int getValue(){
        return Value;
    }

}

