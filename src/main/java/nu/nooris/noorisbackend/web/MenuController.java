package nu.nooris.noorisbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.MenuItemDto;
import nu.nooris.noorisbackend.dto.MenuItemListDto;
import nu.nooris.noorisbackend.service.MenuService;
import nu.nooris.noorisbackend.service.mapper.MenuMapper;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
@Tag(name = "Menu", description = "API for getting menu items")
public class MenuController {

  private final MenuService menuService;
  private final MenuMapper menuMapper;

  @Operation(summary = "Get all menu items")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of menu items",
          content = @Content(schema = @Schema(implementation = MenuItemListDto.class)))
  })
  @GetMapping
  public ResponseEntity<MenuItemListDto> getAllMenuItems() {
    return ResponseEntity.ok(menuMapper.toMenuItemListDto(menuService.getAllMenuItems()));
  }

  @Operation(summary = "Get a menu item by UUID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Menu item found",
          content = @Content(schema = @Schema(implementation = MenuItemDto.class))),
      @ApiResponse(responseCode = "404", description = "Menu item not found",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping("/{reference}")
  public ResponseEntity<MenuItemDto> getMenuItemByReference(@PathVariable UUID reference) {
    return ResponseEntity.ok(menuMapper.toMenuItemDto(
        menuService.getMenuItemByReference(reference)));
  }
}
