package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Variant of a menu item")
public class MenuItemVariantDto {

  @Schema(description = "Label for the variant. If multiple variants are available, this field must be set", example = "180g")
  private String label;

  @NotNull(message = "Price is mandatory")
  @Min(value = 1, message = "Price must be greater than 0")
  @Schema(description = "Price of the variant in SEK", example = "110")
  private Integer price;
}
