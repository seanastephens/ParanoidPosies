package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import songplayer.*;

public class SoundManager {
	
	public static String baseDir = System.getProperty("user.dir")
		      + System.getProperty("file.separator") + "music"
		      + System.getProperty("file.separator");
	
	private List<String> songs;
	
	//Music
	private static String Booty = baseDir +  "BootySong.mp3";
	private static String Bee = baseDir + "FlightOfTheBumblebee.mp3";
	
	
	//Sound Effects
	private static String Chomp = baseDir + "chomp.mp3";
	
	private SongPlayer mainSong;
	
	public SoundManager(){
		songs = new ArrayList<String>();
		songs.add(Bee);
		songs.add(Booty);
		
		go();
	}
	
	private void go(){
		Collections.shuffle(songs);
		mainSong.playFile(new LoopListener(), songs.get(0));
	}
	
	public static void playChomp(){
		SongPlayer.playFile(Chomp);
	}
	
	
	private class LoopListener implements EndOfSongListener{

		@Override
		public void songFinishedPlaying(
				EndOfSongEvent eventWithFileNameAndDateFinished) {
			//mainSong.playFile(new LoopListener(), Booty);
			go();
		}
		
	}

}
