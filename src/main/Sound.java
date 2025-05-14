package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound(){
		soundURL[0] =getClass().getResource("/sounds/player_attack.wav");
		//soundURL[1] =getClass().getResource("res/sounds/CollectItem.wav");
		//soundURL[2] =getClass().getResource("res/sounds/gameover.wav");
		//soundURL[3] =getClass().getResource("/sounds/MonsterSword.mp3");
		//soundURL[4] =getClass().getResource("res/sounds/player_attack.wav");
		//soundURL[5] =getClass().getResource("res/sounds/receivedamage.wav");
		//soundURL[6] =getClass().getResource("res/sounds/teleport.wav");
	}
	public void setFile(int i) {
		try {
			if(soundURL[i] == null) {
				System.out.println("wrong");
			}
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
