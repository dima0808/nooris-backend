package nu.nooris.noorisbackend.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nu.nooris.noorisbackend.common.MenuCategory;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class MenuItem {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID reference;

  private String name;
  private String description;
  private MenuCategory category;

  @OneToMany(mappedBy = "menuItem", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<MenuItemVariant> variants;
}
