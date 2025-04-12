package nu.nooris.noorisbackend.service.mapper;

import java.time.LocalDateTime;
import java.util.List;
import nu.nooris.noorisbackend.dto.BookingDto;
import nu.nooris.noorisbackend.dto.BookingListDto;
import nu.nooris.noorisbackend.dto.TimeSlotsDto;
import nu.nooris.noorisbackend.repository.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

  @Mapping(target = "reference", source = "reference")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "phoneNumber", source = "phoneNumber")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "guests", source = "guests")
  @Mapping(target = "startTime", source = "startTime")
  BookingDto toBookingDto(Booking booking);

  List<BookingDto> toBookingDto(List<Booking> bookings);

  default BookingListDto toBookingListDto(List<Booking> bookings) {
    return BookingListDto.builder()
        .bookings(toBookingDto(bookings))
        .build();
  }

  @Mapping(target = "reference", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "phoneNumber", source = "phoneNumber")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "guests", source = "guests")
  @Mapping(target = "startTime", source = "startTime")
  Booking toBooking(BookingDto bookingDto);

  List<Booking> toBooking(List<BookingDto> bookingDtos);

  default TimeSlotsDto toTimeSlotsDto(List<LocalDateTime> timeSlots) {
    return TimeSlotsDto.builder()
        .timeSlots(timeSlots)
        .build();
  }
}
