package project.ridersserver.ridersserverapp.Controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.ridersserver.ridersserverapp.FTP.FTPHostInfo;
import project.ridersserver.ridersserverapp.crawling.Crawler;
import project.ridersserver.ridersserverapp.crawling.Game.DayGame;
import project.ridersserver.ridersserverapp.crawling.Rank.IndividualRank;
import project.ridersserver.ridersserverapp.crawling.Rank.TeamRank;
import project.ridersserver.ridersserverapp.service.VideoService;


@Controller
//@AllArgsConstructor
public class MyController {

	@Autowired
    private Crawler crawler;

	@Autowired
	private VideoService videoService;

	@Autowired
	private FTPHostInfo ftpHostInfo;

    @GetMapping("/")
    public String index(Model model) {
		model.addAttribute("hostIp",ftpHostInfo.getHostIP());

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		model.addAttribute("currentYear",year);

		DayGame dayGame = crawler.GetTodayGame();
		if(dayGame != null){
			model.addAttribute("gameSize", dayGame.getGameList().size() + 1);
			model.addAttribute("todayGame", dayGame);
		}
		else{
			model.addAttribute("gameSize", 2);
//			model.addAttribute("todayGame", dayGame);
		}

		//인기영상 4개, 최신영상 4개 (videoName받아서 넘기면 이걸로 url 메핑해서 보여줄 수 있다)
		model.addAttribute("latestVideoName",videoService.GetLatestVideoName());
		model.addAttribute("mostLikeVideoName",videoService.GetMostLikeVideoName());
		return "/index";
    }

    @RequestMapping("/gameList")
    public String gameListGet(HttpServletRequest request, Model model) {
    	int year,month;
    	int beforeYear,beforeMonth, nextYear,nextMonth;
    	String year_ = request.getParameter("year");
    	String month_ = request.getParameter("month");
    	if(year_ == null && month_==null) {
        	Calendar cal = Calendar.getInstance();
        	year = cal.get(Calendar.YEAR);
        	month = (cal.get(Calendar.MONTH) + 1);
    	}else {
    		year = Integer.parseInt(year_);
    		month = Integer.parseInt(month_);
    	}
        List<DayGame> yearAndMonthGame = crawler.GetYearAndMonthGame(year,month);

        if(month == 1) {
			beforeMonth = 12;
			beforeYear = year-1;
		}else {
			beforeYear = year;
			beforeMonth = month-1;
			if(beforeMonth == 9)
				beforeMonth = 6;
		}
        if(month == 12) {
			nextMonth = 1;
			nextYear = year+1;
		}else {
			nextYear = year;
			nextMonth = month+1;
			if(nextMonth == 7)
				nextMonth = 10;
		}
        
        model.addAttribute("beforeYear", beforeYear);
        model.addAttribute("beforeMonth", beforeMonth);
        model.addAttribute("currentYear", year);
        model.addAttribute("currentMonth", month);
        model.addAttribute("nextYear", nextYear);
        model.addAttribute("nextMonth", nextMonth);
        model.addAttribute("GameList", yearAndMonthGame);
    	return "/calendar";
    }
   
    @RequestMapping("/rank")
    public String rank(HttpServletRequest request, Model model) {
    	int year,beforeYear,nextYear;
    	Calendar cal = Calendar.getInstance();
    	String year_ = request.getParameter("year");
    	String conference = request.getParameter("conference");
    	if(conference == null)
    		conference = "EAST";
    	
    	if(year_ == null)
    		year = cal.get(Calendar.YEAR) + 1;
    	else
    		year = Integer.parseInt(year_);   	
    	beforeYear = year - 1;
    	nextYear = year + 1;
    	if(year == 2013)
    		beforeYear = 0;
    	if(year == cal.get(Calendar.YEAR) + 1)
    		nextYear = 0;
    	
    	Pair<List<TeamRank>,List<IndividualRank>> teamAndIndividualRank
    		= crawler.GetTeamAndIndividualRank(year,conference); 
    	
    	model.addAttribute("currentYear", year);
    	model.addAttribute("beforeYear", beforeYear);
    	model.addAttribute("nextYear", nextYear);
    	model.addAttribute("conference",conference);
    	model.addAttribute("teamRankList", teamAndIndividualRank.getFirst());
    	model.addAttribute("individualRankList", teamAndIndividualRank.getSecond());
    	return "/rank";
    }


}