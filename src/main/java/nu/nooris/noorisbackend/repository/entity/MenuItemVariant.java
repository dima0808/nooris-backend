package nu.nooris.noorisbackend.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class MenuItemVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID reference;

  private String label;
  private Integer price;

  @ManyToOne
  @JoinColumn(nullable = false)
  private MenuItem menuItem;
}
