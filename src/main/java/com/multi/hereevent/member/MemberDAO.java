package com.multi.hereevent.member;

import com.multi.hereevent.dto.MemberDTO;

import java.util.List;

public interface MemberDAO {
    MemberDTO memberLogin(MemberDTO member);
    int memberInsert(MemberDTO member);
    List<MemberDTO> memberList();
    MemberDTO memberDetail(int member_no);
    int memberUpdate(int member_no);
    int memberDelete(int member_no);
    boolean memberCheckNick(String nick);
}
