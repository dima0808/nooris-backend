package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Wrapper object for returning a list of bookings")
public class BookingListDto {

  private List<BookingDto> bookings;
}
