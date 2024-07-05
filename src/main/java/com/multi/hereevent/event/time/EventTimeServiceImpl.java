package com.multi.hereevent.event.time;

import com.multi.hereevent.dto.EventTimeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventTimeServiceImpl implements EventTimeService{
    private final EventTimeDAO dao;

    @Override
    public int insertEventTimeList(List<EventTimeDTO> eventTimeList) {
        return dao.insertEventTimeList(eventTimeList);
    }

    @Override
    public List<String> getOperTime(int event_no, String day) {
        EventTimeDTO eventTime = dao.getEventTimeByEventNoAndDay(event_no,day);
        String[] openTime = eventTime.getOpen_time().split(":");
        String[] closeTime = eventTime.getClose_time().split(":");
        //System.out.println(openTime[0]);
        List<String> timeList = new ArrayList<>();
        int openTimeInt = Integer.parseInt(openTime[0]);
        int closeTimeInt = Integer.parseInt(closeTime[0]);
        for(int i=openTimeInt;i<=closeTimeInt;i++){
            timeList.add(String.valueOf(i)+":"+openTime[1]+":"+openTime[2]);
        }
        System.out.println("timeList===>"+timeList);
        return timeList;
    }

    @Override
    public EventTimeDTO getEventTimeByEventNoAndDay(int event_no, String day){
        return dao.getEventTimeByEventNoAndDay(event_no,day);
    }

    @Override
    public List<EventTimeDTO> getEventTime(int event_no){
        return dao.getEventTime(event_no);
    }

    @Override
    public List<String> getHolidayDays(int event_no) {
        return dao.getHolidayDays(event_no);
    }
}