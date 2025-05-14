package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] =getClass().getResource("/sound/And The Journey Begins.wav");
		soundURL[1] =getClass().getResource("/sound/CollectItem.wav");
		soundURL[2] =getClass().getResource("/sound/gameover.wav");
		soundURL[3] =getClass().getResource("/sound/MonsterSword.wav");
		soundURL[4] =getClass().getResource("/sound/player_attack.wav");
		soundURL[5] =getClass().getResource("/sound/recievedamage.wav");
		soundURL[6] =getClass().getResource("/sound/teleport.wav");
	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e);
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
