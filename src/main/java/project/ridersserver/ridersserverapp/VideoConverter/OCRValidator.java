package project.ridersserver.ridersserverapp.VideoConverter;

import javafx.util.Pair;
import org.springframework.stereotype.Component;


//강화된 올바른 문장 검증 방법
@Component
public class OCRValidator {

    private double Max(double n1, double n2, double n3, double n4){
        double max = 0;
        max = (n1 > n2)? n1: n2;
        max = (max > n3)? max : n3;
        max = (max > n4)? max: n4;
        return max;
    }

    //구분자를 빈칸으로 쓴다.
    public Boolean IsRightString(String ocrStr) {
        int idx = ocrStr.indexOf(' ');
        if(idx >=0)
            return true;
        else
            return false;
    }

    //ocrStr의 키워드 점수 계산 함수
    public double CalKeyWordSocre(String ocrStr){
        if(!IsRightString(ocrStr))  //키워드가 아닌 문자 걸러내기 => 공백이 없다면 그냥 0점
            return 0;

        double score = 0;
        String[] compontents = ocrStr.split(" ");
        String ocrTeamStr = compontents[0];
        String ocrActionStr = ocrStr.substring(ocrStr.indexOf(' '));
        double home = 0, away = 0;
        double two = 0, three = 0, dunk = 0 , block = 0;

        if(ocrTeamStr.indexOf('H') >=0) home++;
        if(ocrTeamStr.indexOf('o') >=0) home++;
        if(ocrTeamStr.indexOf('m') >=0) home++;
        if(ocrTeamStr.indexOf('e') >=0) home++;

        if(ocrTeamStr.indexOf('A') >=0) away++;
        if(ocrTeamStr.indexOf('w') >=0) away++;
        if(ocrTeamStr.indexOf('a') >=0) away++;
        if(ocrTeamStr.indexOf('y') >=0) away++;

        if(ocrActionStr.indexOf('T') >=0) two++;
        if(ocrActionStr.indexOf('w') >=0) two++;
        if(ocrActionStr.indexOf('o') >=0) two++;
        if(ocrActionStr.indexOf('p') >=0) two++;
        if(ocrActionStr.indexOf('o') >=0) two++;
        if(ocrActionStr.indexOf('i') >=0) two++;
        if(ocrActionStr.indexOf('n') >=0) two++;
        if(ocrActionStr.indexOf('t') >=0) two++;

        if(ocrActionStr.indexOf('T') >=0) three++;
        if(ocrActionStr.indexOf('h') >=0) three++;
        if(ocrActionStr.indexOf('r') >=0) three++;
        if(ocrActionStr.indexOf('e') >=0) three++;
        if(ocrActionStr.indexOf('e') >=0) three++;
        if(ocrActionStr.indexOf('p') >=0) three++;
        if(ocrActionStr.indexOf('o') >=0) three++;
        if(ocrActionStr.indexOf('i') >=0) three++;
        if(ocrActionStr.indexOf('n') >=0) three++;
        if(ocrActionStr.indexOf('t') >=0) three++;

        if(ocrActionStr.indexOf('D') >=0) dunk++;
        if(ocrActionStr.indexOf('u') >=0) dunk++;
        if(ocrActionStr.indexOf('n') >=0) dunk++;
        if(ocrActionStr.indexOf('k') >=0) dunk++;

        if(ocrActionStr.indexOf('B') >=0) block++;
        if(ocrActionStr.indexOf('l') >=0) block++;
        if(ocrActionStr.indexOf('o') >=0) block++;
        if(ocrActionStr.indexOf('c') >=0) block++;
        if(ocrActionStr.indexOf('k') >=0) block++;
        //정규화
        home = home/4;
        away = away/4;
        block = block/5;
        dunk = dunk/4;
        two = two/8;
        three = three/10;
        //점수 계산
        score = (home > away)? home : away;
        score = score + Max(block,dunk,two,three);
        return score;
    }

    //ocrStr을 검증해서 올바른 키워드면 키워드 리턴 / 아니면 null리턴
    public Pair<String,String> OCRValidate(SingleOCRInfo ocrInfo){
        String ocrStr = ocrInfo.getOcrStr();
        if(!IsRightString(ocrStr))  //키워드가 아닌 문자 걸러내기
            return null;
        String[] compontents = ocrStr.split(" ");
        String ocrTeamStr = compontents[0];
        String ocrActionStr = ocrStr.substring(ocrStr.indexOf(' '));

        double home = 0, away = 0;
        double two = 0, three = 0, dunk = 0 , block = 0;

        if(ocrTeamStr.indexOf('H') >=0) home++;
        if(ocrTeamStr.indexOf('o') >=0) home++;
        if(ocrTeamStr.indexOf('m') >=0) home++;
        if(ocrTeamStr.indexOf('e') >=0) home++;

        if(ocrTeamStr.indexOf('A') >=0) away++;
        if(ocrTeamStr.indexOf('w') >=0) away++;
        if(ocrTeamStr.indexOf('a') >=0) away++;
        if(ocrTeamStr.indexOf('y') >=0) away++;

        if(ocrActionStr.indexOf('T') >=0) two++;
        if(ocrActionStr.indexOf('w') >=0) two++;
        if(ocrActionStr.indexOf('o') >=0) two++;
        if(ocrActionStr.indexOf('p') >=0) two++;
        if(ocrActionStr.indexOf('o') >=0) two++;
        if(ocrActionStr.indexOf('i') >=0) two++;
        if(ocrActionStr.indexOf('n') >=0) two++;
        if(ocrActionStr.indexOf('t') >=0) two++;

        if(ocrActionStr.indexOf('T') >=0) three++;
        if(ocrActionStr.indexOf('h') >=0) three++;
        if(ocrActionStr.indexOf('r') >=0) three++;
        if(ocrActionStr.indexOf('e') >=0) three++;
        if(ocrActionStr.indexOf('e') >=0) three++;
        if(ocrActionStr.indexOf('p') >=0) three++;
        if(ocrActionStr.indexOf('o') >=0) three++;
        if(ocrActionStr.indexOf('i') >=0) three++;
        if(ocrActionStr.indexOf('n') >=0) three++;
        if(ocrActionStr.indexOf('t') >=0) three++;

        if(ocrActionStr.indexOf('D') >=0) dunk++;
        if(ocrActionStr.indexOf('u') >=0) dunk++;
        if(ocrActionStr.indexOf('n') >=0) dunk++;
        if(ocrActionStr.indexOf('k') >=0) dunk++;

        if(ocrActionStr.indexOf('B') >=0) block++;
        if(ocrActionStr.indexOf('l') >=0) block++;
        if(ocrActionStr.indexOf('o') >=0) block++;
        if(ocrActionStr.indexOf('c') >=0) block++;
        if(ocrActionStr.indexOf('k') >=0) block++;

        home = home/4;
        away = away/4;
        block = block/5;
        dunk = dunk/4;
        two = two/8;
        three = three/10;
        //점수 계산
        double teamScore = (home > away)? home : away;
        double actionScore = Max(block,dunk,two,three);

        if(teamScore < 0.7 || actionScore < 0.7 ){
            return null;    //키워드가 아닌 문자 걸러내기
        }

        String teamStr = (home > away)? "Home" : "Away";
        String actionStr;
        double maxScore;
        if(two > three)
        {
            maxScore = two;
            actionStr = "2Point";
        }
        else
        {
            maxScore = three;
            actionStr = "3Point";
        }

        if(maxScore < dunk)
        {
            maxScore = dunk;
            actionStr = "Dunk";
        }

        if(maxScore < block)
        {
            maxScore = block;
            actionStr = "Block";
        }
        return new Pair<String,String>(teamStr,actionStr);
    }
}
