package project.ridersserver.ridersserverapp.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;
import project.ridersserver.ridersserverapp.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@Controller
//@AllArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    //회원가입페이지 이동
    @GetMapping("/member/signup")
    public String dispSignup() {
        return "/signup";
    }

    //회원가입 동작
    @PostMapping("/member/signup")
    public String SignupAction(HttpServletRequest request, MemberEntity memberEntity, Model model) {
        Long id = memberService.joinUser(memberEntity);
        return "redirect:/member/login";
    }

    //아이디 중복확인 ajax 메핑 url
    @RequestMapping(value = "/member/idCheck", method = RequestMethod.GET)
    @ResponseBody
    public int idCheck(HttpServletRequest request) {
        String memberId = request.getParameter("memberId");
        Long isPresent = memberService.emailOverlapCheck(memberId);
        if(isPresent == -1)
            return 1;
        else
             return -1;
    }

    // 로그인 페이지
    @RequestMapping(value = "/member/login")
    public String dispLogin(HttpServletRequest request ,Model model) {
        String error = request.getParameter("error");
        if(error != null) {//로그인 실패 상황
            model.addAttribute("loginFailureError", "잘못된 회원정보 입니다");
        }
        return "/login";
    }

    // 접근 거부 페이지
    @GetMapping("/member/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/member/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

}
