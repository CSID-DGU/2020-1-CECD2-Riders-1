package project.ridersserver.ridersserverapp.crawling.Rank;

import lombok.Data;

@Data
public class TeamRank {
	String 		name;
	String 		division;
	String		gameCount;
	String		winCount;
	String		loseCount;
	String		odds;
	String		ride;
	String		homeWinCount;
	String		homeLoseCount;
	String		expeditionWinCount;
	String		expeditionLoseCount;
	String		divisionWinCount;
	String		divisionLoseCount;
	String 		winningStreak;
	
	public TeamRank() {}; 	
	public TeamRank(String name_,String division_, String gameCount_, String winCount_,
			String loseCount_, String odds_, String ride_, String homeWinCount_, String homeLoseCount_,
			String expeditionWinCount_ ,String expeditionLoseCount_, String divisionWinCount_,
			String divisionLoseCount_, String winningStreak_) {
		name = name_;
		division = division_;
		gameCount = gameCount_;
		winCount = winCount_;
		loseCount = loseCount_;
		odds = odds_;
		ride = ride_;
		homeWinCount = homeWinCount_;
		homeLoseCount = homeLoseCount_; 
		expeditionWinCount = expeditionWinCount_;
		expeditionLoseCount = expeditionLoseCount_;
		divisionWinCount = divisionWinCount_;
		divisionLoseCount = divisionLoseCount_;
		winningStreak = winningStreak_;
	}
	
	
}
