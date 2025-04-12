package nu.nooris.noorisbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.BookingDto;
import nu.nooris.noorisbackend.dto.BookingListDto;
import nu.nooris.noorisbackend.service.BookingService;
import nu.nooris.noorisbackend.service.mapper.BookingMapper;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/booking")
@RequiredArgsConstructor
@Tag(name = "Booking Management", description = "API for managing bookings")
public class BookingManagementController {

  private final BookingService bookingService;
  private final BookingMapper bookingMapper;

  @Operation(summary = "Get all bookings")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of bookings",
          content = @Content(schema = @Schema(implementation = BookingListDto.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<BookingListDto> getAllBookings() {
    return ResponseEntity.ok(bookingMapper.toBookingListDto(bookingService.getAllBookings()));
  }

  @Operation(summary = "Get all bookings for a given date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of bookings for a given date",
          content = @Content(schema = @Schema(implementation = BookingListDto.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping("/by-date")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<BookingListDto> getBookingsByDate(@RequestParam LocalDate date) {
    return ResponseEntity.ok(bookingMapper.toBookingListDto(
        bookingService.getAllBookingsByDate(date))
    );
  }

  @Operation(summary = "Get a booking by UUID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Booking found",
          content = @Content(schema = @Schema(implementation = BookingDto.class))),
      @ApiResponse(responseCode = "404", description = "Booking not found",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping("/{reference}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<BookingDto> getBookingByReference(@PathVariable UUID reference) {
    return ResponseEntity.ok(bookingMapper.toBookingDto(
        bookingService.getBookingByReference(reference)));
  }

  @Operation(summary = "Delete a booking by UUID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Booking deleted"),
      @ApiResponse(responseCode = "401", description = "Unauthorized to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @DeleteMapping("/{reference}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteBookingByReference(@PathVariable UUID reference) {
    bookingService.deleteBookingByReference(reference);
    return ResponseEntity.noContent().build();
  }
}
