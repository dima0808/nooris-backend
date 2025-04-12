package nu.nooris.noorisbackend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import nu.nooris.noorisbackend.repository.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

  @Query("""
      SELECT COUNT(b) FROM Booking b
      WHERE b.guests = :guests
        AND b.startTime < :end
        AND b.startTime >= :start
      """)
  int countOverlappingBookings(@Param("guests") int guests,
      @Param("start") LocalDateTime start,
      @Param("end") LocalDateTime end);

  List<Booking> findAllByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);

  List<Booking> findAllByStartTimeBetweenAndStartTimeAfterOrderByStartTimeAsc(
      LocalDateTime startOfDay, LocalDateTime endOfDay, LocalDateTime now);
}
