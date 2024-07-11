package com.multi.hereevent.event;

import com.multi.hereevent.dto.*;


import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface EventDAO {
    //관리자페이지
    int insertEvent(EventDTO event); //행사 등록
    int updateEvent(EventDTO event); // 업데이트
    int deleteEvent(List<Integer> eventNo); // 삭제
    List<EventDTO> selectAll(); //이벤트 전체조회

    List<EventDTO> searchEvent(String keyword); //행사 이름 검색

    List<EventDTO> getAllEvent(); //전체 팝업 조회
    List<EventDTO> getListStarRank(); //별점 높은순 10순위 리스트
    List<EventDTO> selectFourEventByCategory(int category_no); //카테고리별로 4개씩 가져오기
    List<EventDTO> getOpenEvent(); //오픈예정 행사
    List<EventDTO> getPopularEvent(); //예약,대기 높은순 10순위 리스트
    List<EventDTO> getOnGoingEvent(); // 진행중인 행사
    // 조건 주고 조회
    List<EventDTO> getAllEventWithCondition(List<String> state, List<String> type); //전체 팝업 조회
    List<EventDTO> getStarEventWithCondition(List<String> state, List<String> type); //별점 높은순 10순위 리스트
    List<EventDTO> getEventByCategoryWithCondition(int category_no, List<String> state, List<String> type); //카테고리별 조회
    List<EventDTO> getOpenEventWithCondition(List<String> type); //오픈예정 행사
    List<EventDTO> getPopularEventWithCondition(List<String> state, List<String> type); //예약,대기 높은순 10순위 리스트

    //인스타그램 태그

    //세부페이지
    EventDTO getEventDetails(int event_no);  // 전체 데이터 조회
    EventDTO getEventDetails(int event_no, int category_no); // 이벤트 상세 정보 + 회원 관심 여부 조회
    //이름으로 조회
    EventDTO getEventDetail(String name);
    //사진 가져오기
    EventDTO getEventImage(int event_no);
    //이벤트 관심 숫자표시
    int getEventInterest(int event_no);

    //예약하기
    int insertReserve(ReserveDTO reservation);
    //예약 순서 체크
    ReserveDTO checkReserveOrder(int event_no, Date reserve_date, Time reserve_time);
    //예약 인원 체크
    int checkReserveLimit(int event_no);

    // 크롤링
    int insertCrawlingEvent(EventDTO event); // 크롤링한 이벤트 등록
    int updateEventImg(int event_no, String img_path);
    String selectEventNoByEventName(String eventName); // 이벤트 이름으로 이벤트 번호 조회

    // 특정 멤버 이벤트 내역 조회
    List<MemberEventDTO> selectMemberEvent(int member_no);
    // 오늘로부터 2주 내에 오픈 예정인 관심 카테고리 이벤트 조회
    List<EventDTO> selectNewEvent(int member_no);

    // 진행 중이고 현장대기 가능한 이벤트 조회
    List<Integer> selectWaitEvent();

    // 페이징 처리
    int countEventWithPage(Map<String, Object> params);
    List<EventDTO> selectEventWithPage(Map<String, Object> params);


    int getWaitLimit(int event_no);

}