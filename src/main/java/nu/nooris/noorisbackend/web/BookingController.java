package nu.nooris.noorisbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.BookingDto;
import nu.nooris.noorisbackend.dto.TimeSlotsDto;
import nu.nooris.noorisbackend.service.BookingService;
import nu.nooris.noorisbackend.service.mapper.BookingMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@Tag(name = "Booking", description = "API for booking tables")
public class BookingController {

  private final BookingService bookingService;
  private final BookingMapper bookingMapper;

  @Operation(summary = "Create a new booking")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Booking created",
          content = @Content(schema = @Schema(implementation = BookingDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request data",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PostMapping
  public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingMapper.toBookingDto(
        bookingService.createBooking(bookingMapper.toBooking(bookingDto))));
  }

  @Operation(summary = "Get all available time slots for a given date and number of guests")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved available time slots",
          content = @Content(schema = @Schema(implementation = TimeSlotsDto.class)))
  })
  @GetMapping("/available-times")
  public ResponseEntity<TimeSlotsDto> getAvailableTimeSlots(@RequestParam LocalDate date,
      @RequestParam int guests) {
    return ResponseEntity.ok(bookingMapper.toTimeSlotsDto(
        bookingService.getAvailableTimeSlots(date, guests)));
  }
}
