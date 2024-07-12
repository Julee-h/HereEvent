package com.multi.hereevent.reserve;

import com.multi.hereevent.dto.MemberDTO;
import com.multi.hereevent.dto.ReserveDTO;
import com.multi.hereevent.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@SessionAttributes("member")
public class ReserveController {
    private final ReserveService reserveService;
    private final MailService mailService;
    // 예약기능
    @PostMapping("/event/reservation")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String reservation(@RequestBody Map<String, String> data, Model model) {
        MemberDTO member = (MemberDTO) model.getAttribute("member");
        if (member == null) {
            JSONObject json = new JSONObject();
            json.put("message", "로그인을 해주세요.");
            return json.toJSONString();
        }
        log.info("Request Data: " + data);

        int event_no = Integer.parseInt(data.get("eventNo"));
        int member_no = member.getMember_no();
        data.put("memberNo", String.valueOf(member_no));
        String reserve_date = data.get("reserveDate");
        String reserve_time = data.get("reserveTime");

        log.info("[reserve] " + event_no + ", " + reserve_date + ", " + reserve_time);
        model.addAttribute("memberNo", member_no);
        model.addAttribute("eventNo", event_no);
        model.addAttribute("reserveDate", reserve_date);
        model.addAttribute("reserveTime", reserve_time);
        String message = reserveService.makeReservation(event_no, member_no, reserve_date, reserve_time);

        // 예약 성공 시 이메일 전송
        if(message.equals("예약되었습니다.")){
            ReserveDTO reserve = reserveService.selectReserve(event_no, member_no, reserve_date, reserve_time);
            mailService.sendReserveSuccessEmail(reserve);
        }

        JSONObject json = new JSONObject();
        json.put("message", message);

        return json.toJSONString();
    }
    @GetMapping("/reservation/delete")
    public String cancelReservation(@RequestParam("event_no") int event_no,
                                    @RequestParam("reserve_date") String reserve_date,
                                    @RequestParam("reserve_time") String reserve_time,
                                    Model model) {
        /*
        log.info("event_no: " + event_no);
        log.info("reserve_date: " + reserve_date);
        log.info("reserve_time: " + reserve_time);*/

        MemberDTO member = (MemberDTO) model.getAttribute("member");
        if (member != null) {
            Map<String, Object> params = new HashMap<>();
            int member_no = member.getMember_no();
            params.put("event_no", event_no);
            params.put("member_no", member_no);
            params.put("reserve_date", reserve_date);
            params.put("reserve_time", reserve_time);

            // 예약 삭제 서비스 호출
            reserveService.cancelReservation(params);
        } else {
            // 회원 정보가 세션에 없는 경우, 예외 처리
            throw new IllegalStateException("Member not found in session");
        }
        return "redirect:/myevent";
    }

    @GetMapping("/reservation/update")
    public String updateReservation(@RequestParam("event_no") int event_no,
                                    @RequestParam("reserve_date") String reserve_date,
                                    @RequestParam("reserve_time") String reserve_time,
                                    Model model) {
        MemberDTO member = (MemberDTO) model.getAttribute("member");
        if (member != null) {
            Map<String, Object> params = new HashMap<>();
            int member_no = member.getMember_no();
            params.put("event_no", event_no);
            params.put("member_no", member_no);
            params.put("reserve_date", reserve_date);
            params.put("reserve_time", reserve_time);

            // 예약 삭제 서비스 호출
            reserveService.updateReservation(params);
        } else {
            // 회원 정보가 세션에 없는 경우, 예외 처리
            throw new IllegalStateException("Member not found in session");
        }
        return "redirect:/myevent";
    }
}
