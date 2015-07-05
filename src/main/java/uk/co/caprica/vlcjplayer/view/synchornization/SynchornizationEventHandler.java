package uk.co.caprica.vlcjplayer.view.synchornization;

import com.google.common.eventbus.Subscribe;
import uk.co.caprica.vlcj.player.MediaPlayer;

import uk.co.caprica.vlcjplayer.Application;
import uk.co.caprica.vlcjplayer.synchronizationEvents.PlayingEvent;
import uk.co.caprica.vlcjplayer.synchronizationEvents.TickEvent;

/**
 * Created by kozo on 7/3/15.
 */
public class SynchornizationEventHandler {
    MediaPlayer mPlayer;
    public SynchornizationEventHandler(MediaPlayer mediaPlayer){
        mPlayer=mediaPlayer;
    }
    @Subscribe
    public void OnPausedEvent(uk.co.caprica.vlcjplayer.synchronizationEvents.PausedEvent e){

        mPlayer.pause();

    }

    @Subscribe
    public void onPlayingEvent(PlayingEvent e){

        mPlayer.play();
    }
    @Subscribe
    public void OnTickEvent(TickEvent e){
        mPlayer.setPosition(e.getValue() / 1000.0f);
        System.out.println("Tick");
    }
}
