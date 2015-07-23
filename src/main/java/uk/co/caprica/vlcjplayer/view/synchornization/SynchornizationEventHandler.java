package uk.co.caprica.vlcjplayer.view.synchornization;

import com.google.common.eventbus.Subscribe;
import uk.co.caprica.vlcj.player.MediaPlayer;

import uk.co.caprica.vlcjplayer.synchronizationEvents.PlayingEvent;
import uk.co.caprica.vlcjplayer.synchronizationEvents.TickEvent;

/**
 * This class is used to handle events sent by the server.
 */
public class SynchornizationEventHandler {
    MediaPlayer mPlayer;
    public SynchornizationEventHandler(MediaPlayer mediaPlayer){
        mPlayer=mediaPlayer;
    }
    @Subscribe
    public void OnPausedEvent(uk.co.caprica.vlcjplayer.synchronizationEvents.PausedEvent e){
        long i;
        float elay=calculateDelay((i=Client.getClient().getServerTime() - e.getTime()));
        mPlayer.setPosition(elay);
        mPlayer.pause();
    }

    @Subscribe
    public void onPlayingEvent(PlayingEvent e)

    {
        long i;
        float elay=calculateDelay((i=Client.getClient().getServerTime() - e.getTime()));
        mPlayer.setPosition(elay);
        mPlayer.play();
    }
    @Subscribe
    public void OnTickEvent(TickEvent e){
        long i=(Client.getClient().getServerTime() - e.getTime());

       float postion=(e.getValue() / 1000.0f);
        mPlayer.setPosition((postion)+i/mPlayer.getLength());;

    }
    public float calculateDelay(long delay){
        float postion=mPlayer.getPosition();
        float length=mPlayer.getLength();
        float currentPostion=(postion*length)+delay;
        return currentPostion/length;

    }
}
