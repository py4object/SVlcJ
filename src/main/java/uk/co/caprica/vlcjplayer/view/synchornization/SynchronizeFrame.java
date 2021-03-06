package uk.co.caprica.vlcjplayer.view.synchornization;

import com.google.common.eventbus.Subscribe;
import uk.co.caprica.vlcjplayer.ShowSynchronizeFrameEvent;
import uk.co.caprica.vlcjplayer.view.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        JPanel root=new JPanel();
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(2,2));
        panel.add(new JLabel("Sever:"));
        panel.add(new JLabel("Port:"));
        panel.add(jtServer);
        panel.add(jtPort);;
        JPanel panel1=new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(jbConnect);
        root.add(panel);
        root.add(panel1);
        jbConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.makeNewConnection(jtServer.getText(), Integer.valueOf(jtPort.getText())).start();
                setVisible(false);
            }
        });
        add(root);
        pack();

    }
    @Subscribe
    public void ShowFrame(ShowSynchronizeFrameEvent e){
        jtServer.setText("");
        jtPort.setText("");
        setVisible(true);
    }

}
