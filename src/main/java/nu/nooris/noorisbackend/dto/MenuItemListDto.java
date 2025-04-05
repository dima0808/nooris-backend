package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Wrapper object for returning a list of menu items")
public class MenuItemListDto {

  @Schema(description = "List of all menu items")
  private List<MenuItemDto> menuItems;
}
