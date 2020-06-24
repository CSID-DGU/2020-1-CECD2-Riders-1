package project.ridersserver.ridersserverapp.VideoConverter.Old;

import org.springframework.stereotype.Component;

@Component
public class NameConverter {

    public boolean IsRightString(String ocrStr)
    {
        int idx = ocrStr.indexOf('/');
        if(idx >=0)
            return true;
        else
            return false;
    }

    public String WhatTeam(String ocrTeamStr)
    {
        int homeCount = 0, awayCount = 0;
        if(ocrTeamStr.indexOf('H') >=0) homeCount++;
        if(ocrTeamStr.indexOf('o') >=0) homeCount++;
        if(ocrTeamStr.indexOf('m') >=0) homeCount++;
        if(ocrTeamStr.indexOf('e') >=0) homeCount++;

        if(ocrTeamStr.indexOf('A') >=0) awayCount++;
        if(ocrTeamStr.indexOf('w') >=0) awayCount++;
        if(ocrTeamStr.indexOf('a') >=0) awayCount++;
        if(ocrTeamStr.indexOf('y') >=0) awayCount++;

        if(homeCount > awayCount)
            return "Home";
        else
            return "Away";
    }

    public String WhatAction(String ocrActionStr)
    {
        int two = 0, three = 0, dunk = 0 , block = 0;
        if(ocrActionStr.indexOf('2') >=0) two++;
        if(ocrActionStr.indexOf('P') >=0) two++;
        if(ocrActionStr.indexOf('o') >=0) two++;
        if(ocrActionStr.indexOf('i') >=0) two++;
        if(ocrActionStr.indexOf('n') >=0) two++;
        if(ocrActionStr.indexOf('t') >=0) two++;

        if(ocrActionStr.indexOf('3') >=0) three++;
        if(ocrActionStr.indexOf('P') >=0) three++;
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
        if(ocrActionStr.indexOf('k') >=0) block++;

        String ans;
        int maxCount;
        if(two > three)
        {
            maxCount = two;
            ans = "2Point";
        }
        else
        {
            maxCount = three;
            ans = "3Point";
        }

        if(maxCount < dunk)
        {
            maxCount = dunk;
            ans = "Dunk";
        }

        if(maxCount < block)
        {
            maxCount = block;
            ans = "Block";
        }



        return ans;
    }
}
