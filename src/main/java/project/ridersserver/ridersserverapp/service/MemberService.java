package project.ridersserver.ridersserverapp.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;
import project.ridersserver.ridersserverapp.domain.Member.MemberEntity;
import project.ridersserver.ridersserverapp.domain.Member.MemberRepository;
import project.ridersserver.ridersserverapp.domain.Role;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public MemberEntity findMemberByMemberName(String memberName){
        Optional<MemberEntity> memberEntitywrpper = memberRepository.findByEmail(memberName);
        if(!memberEntitywrpper.isPresent()){
            System.out.println("회원을 찾을수 없습니다!");
            return null;
        }
        else
            return memberEntitywrpper.get();
    }

    @Transactional
    public Long emailOverlapCheck(String email) {
        if(memberRepository.findByEmail(email).isPresent())
            return new Long(-1);
        else
            return new Long(1);
    }

    //회원가입
    @Transactional
    public Long joinUser(MemberEntity memberEntity) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));

        //아이디 중복시  -1반환
        if(memberRepository.findByEmail(memberEntity.getEmail()).isPresent())
            return new Long(-1);

        //성공적으로 회원가입 완료시 그 회원의 아이디 반환(>=1)
        return memberRepository.save(memberEntity).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
        if(!userEntityWrapper.isPresent())
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");
        MemberEntity userEntity = userEntityWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)||("admin").equals(userEmail)||("admin@1").equals(userEmail)) {	//비밀번호 admin으로 저장
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}