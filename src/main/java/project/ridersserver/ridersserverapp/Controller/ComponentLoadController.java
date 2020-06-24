package project.ridersserver.ridersserverapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//j쿼리 요청으로 불러오는 html url 메핑담당
@Controller
public class ComponentLoadController {

    @RequestMapping("/leftSidebar.html")
    public String loadSidebar() {
        return "/leftSidebar";
    }

    @RequestMapping("/headers.html")
    public String loadHeader() {
        return "/headers";
    }

    @RequestMapping("/admin/leftSidebar.html")
    public String loadAdminSidebar() {
        return "/leftSidebar";
    }

    @RequestMapping("/admin/headers.html")
    public String loadAdminHeader() {
        return "/headers";
    }

    @RequestMapping("/member/leftSidebar.html")
    public String loadMemberSidebar() {
        return "/leftSidebar";
    }

    @RequestMapping("/member/headers.html")
    public String loadMemberHeader() {
        return "/headers";
    }

}
