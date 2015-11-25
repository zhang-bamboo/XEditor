package music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.FileChooserUI;

import xEditorUI.XEditorFrame;

public class MusicDialog extends JDialog {
    private XEditorFrame faFrame;
    private LinkedList<String> musiclist;
    private int currentIndex;
    private JFileChooser fileChooser;
    private JLabel musicTitle;
    private JButton chooseMusicButn;
    private JButton startMusicButn;
    private JButton stopMusicButn;
    private JButton lastMusicButn;
    private JButton nextMusicButn;
    private AudioClip audioClip;

    public MusicDialog(Frame frame) throws MalformedURLException {
	faFrame = (XEditorFrame) frame;
	musiclist = new LinkedList<>();
	currentIndex = 0;
	fileChooser = null;
	Container container = getContentPane();
	initChooseMusicButn();
	container.add(chooseMusicButn, BorderLayout.NORTH);
	musicTitle = new JLabel();
	musicTitle.setHorizontalAlignment(SwingConstants.CENTER);
	container.add(musicTitle, BorderLayout.CENTER);
	initStartMusicButn();
	initStopMusicButn();
	initLastMusicButn();
	initNextMusicButn();
	JPanel southPanel = new JPanel();
	southPanel.setLayout(new FlowLayout());
	southPanel.add(startMusicButn);
	southPanel.add(stopMusicButn);
	southPanel.add(lastMusicButn);
	southPanel.add(nextMusicButn);
	container.add(southPanel, BorderLayout.SOUTH);
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setBounds(300, 300, 300, 160);
    }

    public void initChooseMusicButn() {
	chooseMusicButn = new JButton("choose music");
	chooseMusicButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = chooser.showOpenDialog(faFrame);
		if (i == JFileChooser.APPROVE_OPTION) {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    musiclist.add(path);
		    musicTitle.setText(path);
		    currentIndex=musiclist.size()-1;
		}
	    }
	});

    }

    public void initStartMusicButn() throws MalformedURLException {
	startMusicButn = new JButton("start");
	startMusicButn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    startMusic();
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		}

	    }
	});
    }

    public void initStopMusicButn() {
	stopMusicButn = new JButton("stop");
	stopMusicButn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (audioClip != null)
		    audioClip.stop();
	    }
	});

    }

    public void initLastMusicButn() {
	lastMusicButn = new JButton("last");
	lastMusicButn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    if (--currentIndex < 0)
			currentIndex = musiclist.size() - 1;
		    startMusic();
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		}
	    }
	});

    }

    public void initNextMusicButn() {
	nextMusicButn = new JButton("next");
	nextMusicButn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    if (++currentIndex >= musiclist.size())
			currentIndex = 0;
		    startMusic();
		} catch (MalformedURLException e1) {
		    e1.printStackTrace();
		}
	    }
	});
    }

    private void startMusic() throws MalformedURLException {
	if (audioClip != null)
	    audioClip.stop();
	String curMusicPath = musiclist.get(currentIndex);
	musicTitle.setText(curMusicPath);
	audioClip = Applet.newAudioClip(new URL("file:///" + curMusicPath));
	audioClip.play();
    }

    public static void main(String[] args) {

	try {
	    JFrame frame = new JFrame();
	    new MusicDialog(frame).setVisible(true);

	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}

    }
}
