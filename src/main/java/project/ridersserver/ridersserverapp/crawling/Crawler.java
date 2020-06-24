package project.ridersserver.ridersserverapp.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import project.ridersserver.ridersserverapp.crawling.Game.DayGame;
import project.ridersserver.ridersserverapp.crawling.Game.Game;
import project.ridersserver.ridersserverapp.crawling.Rank.IndividualRank;
import project.ridersserver.ridersserverapp.crawling.Rank.TeamRank;


@Component
public class Crawler {

	public DayGame GetTodayGame() {
		Calendar cal = Calendar.getInstance();
		String url = "https://sports.news.naver.com/basketball/schedule/index.nhn?"
				+ "year=" + cal.get(Calendar.YEAR)
				+"&month=" + (cal.get(Calendar.MONTH) + 1)
				+"&category=nba#";
		Document doc = null;
		List<DayGame> gameList = new ArrayList<DayGame>();
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements todayGame = doc.select("div.selected");
		for(Element eg: todayGame) {
			List<Game> list = new ArrayList<Game>();
			String date = eg.select("span.td_date").text();
			for(Element g: eg.select("tr")) {
				list.add(new Game(g.select("span.td_hour").text(),
						g.select("span.team_lft").text(),
						g.select("span.team_rgt").text(),
						g.select("strong.td_score").text(),
						g.select("span.td_stadium").text()));
			}
			gameList.add(new DayGame(date,list));
		}

		System.out.println(gameList);
		if(gameList.size() == 0)
			return null;
		else
			return gameList.get(0);
	}
	
	public List<DayGame> GetYearAndMonthGame(int year, int month){
        String url = "https://sports.news.naver.com/basketball/schedule/index.nhn?"
        		+ "year=" + year
        		+"&month=" + month
        		+"&category=nba#";
        Document doc = null;
        List<DayGame> gameList = new ArrayList<DayGame>();     
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements oddGame = doc.select("div.sch_tb");
        Elements evenGame = doc.select("div.sch_tb2");
        for(int i = 0; i <oddGame.size() + evenGame.size();i++ ) {
        	List<Game> list = new ArrayList<Game>();
        	if(i%2 == 0) {	//even
        		String date = oddGame.get(i/2).select("span.td_date").text();
        		for(Element g: oddGame.get(i/2).select("tr")) {
            		list.add(new Game(g.select("span.td_hour").text(),
            				g.select("span.team_lft").text(),
            				g.select("span.team_rgt").text(),
            				g.select("strong.td_score").text(),
            				g.select("span.td_stadium").text()));
        		}
            		gameList.add(new DayGame(date,list));
        	}else {			//odd
        		String date = evenGame.get(i/2).select("span.td_date").text();
        		for(Element g: evenGame.get(i/2).select("tr")) {
            		list.add(new Game(g.select("span.td_hour").text(),
            				g.select("span.team_lft").text(),
            				g.select("span.team_rgt").text(),
            				g.select("strong.td_score").text(),
            				g.select("span.td_stadium").text()));
        		}
            		gameList.add(new DayGame(date,list));
        	}
        }
        return gameList;
	}
	
	public Pair<List<TeamRank>,List<IndividualRank>> GetTeamAndIndividualRank(int year, String conference)
	{		
		String url = "https://sports.news.naver.com/basketball/record/index.nhn?category=nba&"
				+ "year=" + year
				+ "&conference=" + conference;
		Document doc = null;
        List<TeamRank> teamRankList = new ArrayList<TeamRank>(); 
        List<IndividualRank> individualRankList = new ArrayList<IndividualRank>();
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements teamRankBox = doc.select("#regularTeamRecordList_table");
    	Elements teams = teamRankBox.select("tr");
        for(int i = 0 ; i < 15;i++) {
        	teamRankList.add(new TeamRank(
					 teams.get(i).select("td").get(0).text(),
					 teams.get(i).select("td").get(1).text(),
					 teams.get(i).select("td").get(2).text(),
					 teams.get(i).select("td").get(3).text(),
					 teams.get(i).select("td").get(4).text(),
					 teams.get(i).select("td").get(5).text(),
					 teams.get(i).select("td").get(6).text(),
					 teams.get(i).select("td").get(7).text(),
					 teams.get(i).select("td").get(8).text(),
					 teams.get(i).select("td").get(9).text(),
					 teams.get(i).select("td").get(10).text(),
					 teams.get(i).select("td").get(11).text(),
					 teams.get(i).select("td").get(12).text(),
					 teams.get(i).select("td").get(13).text()));
        }
        Elements individualRankBox = doc.select("#playerRecordTable");
        Elements individuals = individualRankBox.select("tr");
        for(int i = 0 ; i < 20;i++) {
        	individualRankList.add(new IndividualRank(
        			individuals.get(i).select("td").get(0).text(),
        			individuals.get(i).select("td").get(1).text(),
        			individuals.get(i).select("td").get(2).text(),
        			individuals.get(i).select("td").get(3).text(),
        			individuals.get(i).select("td").get(4).text(),
        			individuals.get(i).select("td").get(5).text(),
        			individuals.get(i).select("td").get(6).text(),
        			individuals.get(i).select("td").get(7).text(),
        			individuals.get(i).select("td").get(8).text(),
        			individuals.get(i).select("td").get(9).text(),
        			individuals.get(i).select("td").get(10).text(),
        			individuals.get(i).select("td").get(11).text(),
        			individuals.get(i).select("td").get(12).text()));
        }
        return Pair.of(teamRankList, individualRankList);
	}
}
