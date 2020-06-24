package project.ridersserver.ridersserverapp.crawling.Game;

import lombok.Data;

@Data
public class Game {
	String time;
	String leftTeam;
	String rightTeam;
	String score;
	String stadium;
	
	public Game() {}; 	
	public Game(String t, String lf, String rt,String sc,String stad) {
		time = t; leftTeam = lf; rightTeam = rt; score = sc; stadium = stad;
	}
}
