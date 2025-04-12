package nu.nooris.noorisbackend.config;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import nu.nooris.noorisbackend.common.TimeRange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.booking")
public class BookingProperties {

  private Map<String, Integer> tables;
  private Map<String, String> openingHours;

  private Integer maxGuests;
  private Integer slotInterval;
  private Integer durationHours;

  public int getTableCountForGuests(int guests) {
    return tables.getOrDefault("for-" + guests, 0);
  }

  public TimeRange getOpeningHoursForDate(LocalDate date) {
    DayOfWeek day = date.getDayOfWeek();
    String raw = openingHours.get(day.name().toLowerCase());
    if (raw == null || !raw.contains("-")) return null;

    String[] parts = raw.split("-");
    LocalTime start = LocalTime.parse(parts[0]);
    LocalTime end = LocalTime.parse(parts[1]);
    return new TimeRange(start, end);
  }

}
