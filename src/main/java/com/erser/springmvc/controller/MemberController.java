package com.erser.springmvc.controller;

import com.erser.springmvc.dto.MemberForm;
import com.erser.springmvc.entity.Member;
import com.erser.springmvc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member/new")
    public String register(){
        return "members/new";
    }

    @PostMapping("/member/join")
    public String join(MemberForm dto){
        // 1. DTO를 도메인 Entity로 변환
        Member member = dto.toEntity();
        // 2. Repository (DAO)로 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        return "";
    }

    // READ
    @GetMapping("/member/{id}")
    public String view(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    // READALL
    @GetMapping("/member/all")
    public String viewAll(Model model){
        List<Member> all = memberRepository.findAll();
        model.addAttribute("memberList", all);
        return "members/showAll";
    }
}
