package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nu.nooris.noorisbackend.validation.ValidGuests;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Data transfer object for a booking")
public class BookingDto {

  @Schema(description = "Unique identifier of the booking", example = "a3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID reference;

  @NotBlank(message = "Name is mandatory")
  @Schema(description = "Name of the person who made the booking", example = "John Doe")
  private String name;

  @NotBlank(message = "Phone number is mandatory")
  @Schema(description = "Phone number of the person who made the booking", example = "+46701234567")
  private String phoneNumber;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  @Schema(description = "Email of the person who made the booking")
  private String email;

  @NotBlank(message = "Number of guests is mandatory")
  @ValidGuests
  @Schema(description = "Number of guests of the booking", example = "4")
  private Integer guests;

  @NotBlank(message = "Start time is mandatory")
  @Future(message = "Start time must be in the future")
  @Schema(description = "Reserved time of the booking", example = "2025-08-16T18:00")
  private LocalDateTime startTime;
}
