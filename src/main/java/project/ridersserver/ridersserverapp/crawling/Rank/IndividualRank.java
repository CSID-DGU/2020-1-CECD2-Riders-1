package project.ridersserver.ridersserverapp.crawling.Rank;

import lombok.Data;

@Data
public class IndividualRank {
	String 	name;
	String	gameCount;
	String	score;
	String	assist;
	String	reBound;
	String 	steal;
	String  block;
	String 	yatu;
	String	threePtn;
	String 	freeDraw;
	String	yatuSuccessRate;
	String	threePtnSucessRate;
	String	freeDrawSucessRate;
	
	public IndividualRank() {}; 	
	public IndividualRank(String name_, String gameCount_, String score_,
			String assist_, String reBound_, String steal_, String block_,
			String yatu_, String threePtn_, String freeDraw_, String yatuSuccessRate_,
			String threePtnSucessRate_, String freeDrawSucessRate_) {
		name = name_;
		gameCount = gameCount_;
		score = score_;
		assist = assist_;
		reBound = reBound_;
		steal = steal_;
		block = block_;
		yatu = yatu_;
		threePtn = threePtn_;
		freeDraw = freeDraw_;
		yatuSuccessRate = yatuSuccessRate_;
		threePtnSucessRate = threePtnSucessRate_;
		freeDrawSucessRate = freeDrawSucessRate_;
	}
	
	
}
