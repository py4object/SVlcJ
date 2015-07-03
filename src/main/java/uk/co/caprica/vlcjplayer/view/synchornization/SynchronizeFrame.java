package uk.co.caprica.vlcjplayer.view.synchornization;

import com.google.common.eventbus.Subscribe;
import uk.co.caprica.vlcjplayer.ShowSynchronizeFrameEvent;
import uk.co.caprica.vlcjplayer.view.BaseFrame;

import javax.swing.*;
import java.awt.*;


public class SynchronizeFrame extends BaseFrame {
    private JButton jbConnect;
    private JTextField jtServer;
    private JTextField jtPort;
    public SynchronizeFrame(){
        super("Synchronize");
        JPanel panel=new JPanel();
        jbConnect =new JButton("Connect");
        jtServer=new JTextField(10);
        jtPort =new JTextField(5);
        panel.setLayout(new GridLayout(3,2));
        panel.add(new JLabel("Sever:"));
        panel.add(new JLabel("Port:"));
        panel.add(jtServer);
        panel.add(jtPort);
        panel.add(jbConnect);
        add(panel);
        pack();
    }
    @Subscribe
    public void ShowFrame(ShowSynchronizeFrameEvent e){
        setVisible(true);
    }

}
