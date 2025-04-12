package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Wrapper object for returning a list of time slots")
public class TimeSlotsDto {

  @Schema(description = "List of all time slots")
  private List<LocalDateTime> timeSlots;
}
