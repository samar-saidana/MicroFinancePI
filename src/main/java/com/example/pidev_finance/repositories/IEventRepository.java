package com.example.pidev_finance.repositories;

import com.example.pidev_finance.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IEventRepository extends JpaRepository<Event,Integer> {
    @Query("select e from Event e inner join e.shareHolders shareHolders where shareHolders.lastNameShareholder = ?1 and shareHolders.FirstNameShareholder = ?2")
    List<Event> findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(String lastNameShareholder, String FirstNameShareholder);
    @Query("SELECT SUM(s.investment) FROM ShareHolder s WHERE s.event.idEvent = :eventId")
    Double findByToltalInvestmentEvent(@Param("eventId") int eventId);
}
