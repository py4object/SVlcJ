package uk.co.caprica.vlcjplayer.synchronizationEvents;

/**
 * Created by kozo on 6/30/15.
 */
public class PausedEvent extends SynchronizedEvent{
    public static PausedEvent INSTANCE = new PausedEvent();
    Long time;

}
