package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.MemberConverter;
import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.dto.MemberDto;
import nst.springboot.domaci.model.Member;
import nst.springboot.domaci.repository.MemberRepository;
import nst.springboot.domaci.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private MemberConverter memberConverter;

    public MemberServiceImpl(MemberRepository memberRepository, MemberConverter memberConverter) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
    }

    @Override
    public MemberDto save(MemberDto memberDto) throws Exception {
        return memberConverter.toDto(memberRepository.save(memberConverter.toEntity(memberDto)));
    }

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(e -> memberConverter.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable).stream().map(e -> memberConverter.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) throw new Exception("Member does not exist!");
        memberRepository.delete(member.get());
    }

    @Override
    public void update(MemberDto memberDto) throws Exception {

    }

    @Override
    public MemberDto findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) throw new Exception("Member does not exist!");
        return memberConverter.toDto(member.get());
    }
}
