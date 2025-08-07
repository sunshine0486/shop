package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService
{
    private final MemberRepository memberRepository;

    public Member saveMember(Member member)
    {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member)
    {
        Member foundmember = memberRepository.findByEmail(member.getEmail());
        if(foundmember != null)
        {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
    }

}
