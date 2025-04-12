package nu.nooris.noorisbackend.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID reference;

  private String name;
  private String phoneNumber;
  private String email;
  private Integer guests;
  private LocalDateTime startTime;
}
