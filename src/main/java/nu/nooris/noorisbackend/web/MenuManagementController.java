package nu.nooris.noorisbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.dto.MenuItemDto;
import nu.nooris.noorisbackend.service.MenuService;
import nu.nooris.noorisbackend.service.mapper.MenuMapper;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/menu")
@RequiredArgsConstructor
@Tag(name = "Menu Management", description = "API for managing menu items")
public class MenuManagementController {

  private final MenuService menuService;
  private final MenuMapper menuMapper;

  @Operation(summary = "Create a new menu item")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Menu item created",
          content = @Content(schema = @Schema(implementation = MenuItemDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request data",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to create menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody @Valid MenuItemDto menuItemDto) {
    return ResponseEntity.ok(menuMapper.toMenuItemDto(
        menuService.createMenuItem(menuMapper.toMenuItem(menuItemDto))));
  }

  @Operation(summary = "Update an existing menu item")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Menu item updated",
          content = @Content(schema = @Schema(implementation = MenuItemDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request data",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "401", description = "Unauthorized to update menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to update menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "Menu item not found",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PutMapping("/{reference}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable UUID reference,
      @RequestBody @Valid MenuItemDto menuItemDto) {
    return ResponseEntity.ok(menuMapper.toMenuItemDto(
        menuService.updateMenuItem(reference, menuMapper.toMenuItem(menuItemDto))));
  }

  @Operation(summary = "Delete a menu item by UUID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Menu item deleted"),
      @ApiResponse(responseCode = "401", description = "Unauthorized to delete menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden to delete menu item",
          content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
  })
  @DeleteMapping("/{reference}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteMenuItemByReference(@PathVariable UUID reference) {
    menuService.deleteMenuItemByReference(reference);
    return ResponseEntity.noContent().build();
  }
}
