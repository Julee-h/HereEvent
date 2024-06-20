package com.multi.hereevent.event;

import com.multi.hereevent.dto.EventDTO;
import com.multi.hereevent.dto.ReservationDTO;

import java.util.Date;
import java.util.List;

public interface EventDAO {
    //관리자페이지
    //행사 등록
    int insertEvent(EventDTO event);
    //행사 수정
    int updateEvent(EventDTO event);
    //행사 삭제
    int deleteEvent(int event_no);

    //행사이름 검색
    List<EventDTO> searchEvent(String keyword);
    //전체 팝업 조회
    List<EventDTO> getAllEvent();

    //별점 높은순 10순위 리스트
    List<EventDTO> getListStarRank();
    //카테고리별 조회
    List<EventDTO> selectEventByCategoryNo(int category_no);

    //오픈예정 행사
    List<EventDTO> getOpenEvent();
    //예약,대기 높은순 10순위 리스트
    List<EventDTO> getPopularEvent();

    //인스타그램 태그

    //세부페이지
    EventDTO getEventDetails(int event_no);  // 전체 데이터 조회
    EventDTO getEventDetails(int event_no, int category_no); // 이벤트 상세 정보 + 회원 관심 여부 조회
    //사진 가져오기
    EventDTO getEventImage(int event_no);
    //예약하기
    int insertReserve(ReservationDTO reservation);
    // 
    int insertCrawlingEvent(EventDTO event); // 크롤링한 이벤트 등록
    String selectEventNoByEventName(String eventName); // 이벤트 이름으로 이벤트 번호 조회
}
