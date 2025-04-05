package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nu.nooris.noorisbackend.common.MenuCategory;
import nu.nooris.noorisbackend.validation.ValidVariants;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Data transfer object for a single menu item")
public class MenuItemDto {

  @Schema(description = "Unique identifier of the menu item", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID reference;

  @NotBlank(message = "Name is mandatory")
  @Schema(description = "Name of the menu item", example = "Flamberade räkor")
  private String name;

  @Schema(description = "Optional description of the menu item", example = "Flamberade räkor, vitlök, chili, bröd.")
  private String description;

  @NotBlank(message = "Category is mandatory")
  @Schema(description = "Category name the item belongs to", implementation = MenuCategory.class)
  private String category;

  @NotEmpty(message = "At least one variant is required")
  @ValidVariants
  @Valid
  @Schema(description = "List of available variants for this menu item")
  private List<MenuItemVariantDto> variants;
}
