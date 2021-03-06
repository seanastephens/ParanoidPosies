package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class SoundManager {

	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "music" + System.getProperty("file.separator");

	private List<String> songs;

	// Music
	private static String Booty = baseDir + "BootySong.mp3";
	private static String Bee = baseDir + "FlightOfTheBumblebee.mp3";
	private static String Tulips = baseDir + "tiptoeThroughTheTulips.mp3";

	// End of game
	private static String GabeOver = baseDir + "GabeOver.mp3";

	// Sound Effects
	private static String Chomp = baseDir + "chomp.mp3";
	private static String Siren = baseDir + "Siren.mp3";

	public SoundManager() {
		songs = new ArrayList<String>();
		songs.add(Bee);
		songs.add(Booty);
		songs.add(Tulips);

		go();
	}

	private void go() {
		Collections.shuffle(songs);
		SongPlayer.playFile(new LoopListener(), songs.get(0));
	}

	public static void playSiren() {
		SongPlayer.playFile(Siren);
	}

	public static void playChomp() {
		SongPlayer.playFile(Chomp);
	}

	public static void GabeOver() {
		SongPlayer.playFile(GabeOver);
	}

	private class LoopListener implements EndOfSongListener {

		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
			// mainSong.playFile(new LoopListener(), Booty);
			go();
		}

	}
}
