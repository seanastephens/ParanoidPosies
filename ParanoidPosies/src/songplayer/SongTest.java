package songplayer;

public class SongTest {
	private static  String testSongName = "BootySong.mp3";
	
	public static String baseDir = System.getProperty("user.dir")
		      + System.getProperty("file.separator") + "music"
		      + System.getProperty("file.separator");
	
	public static void main(String[] args){
		String dir = baseDir + testSongName;
		
		SongPlayer.playFile(dir);
	}

}
