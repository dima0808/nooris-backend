package nu.nooris.noorisbackend.service.mapper;

import java.util.List;
import nu.nooris.noorisbackend.common.MenuCategory;
import nu.nooris.noorisbackend.dto.MenuItemDto;
import nu.nooris.noorisbackend.dto.MenuItemListDto;
import nu.nooris.noorisbackend.dto.MenuItemVariantDto;
import nu.nooris.noorisbackend.repository.entity.MenuItem;
import nu.nooris.noorisbackend.repository.entity.MenuItemVariant;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MenuMapper {

  @Mapping(target = "reference", source = "reference")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryString")
  @Mapping(target = "variants", source = "variants", qualifiedByName = "toMenuItemVariantDto")
  MenuItemDto toMenuItemDto(MenuItem menuItem);

  List<MenuItemDto> toMenuItemDto(List<MenuItem> menuItems);

  @Mapping(target = "label", source = "label")
  @Mapping(target = "price", source = "price")
  @Named("toMenuItemVariantDto")
  MenuItemVariantDto toMenuItemVariantDto(MenuItemVariant menuItemVariant);

  @Named("toCategoryString")
  default String toCategoryString(MenuCategory category) {
    return category.name();
  }

  default MenuItemListDto toMenuItemListDto(List<MenuItem> menuItems) {
    return MenuItemListDto.builder()
        .menuItems(toMenuItemDto(menuItems))
        .build();
  }

  @Mapping(target = "reference", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryType")
  @Mapping(target = "variants", source = "variants", qualifiedByName = "toMenuItemVariant")
  MenuItem toMenuItem(MenuItemDto menuItemDto);

  List<MenuItem> toMenuItem(List<MenuItemDto> menuItemDtos);

  @Mapping(target = "label", source = "label")
  @Mapping(target = "price", source = "price")
  @Named("toMenuItemVariant")
  MenuItemVariant toMenuItemVariant(MenuItemVariantDto menuItemVariantDto);

  @AfterMapping
  default void linkVariants(@MappingTarget MenuItem menuItem) {
    if (menuItem.getVariants() != null) {
      for (MenuItemVariant variant : menuItem.getVariants()) {
        variant.setMenuItem(menuItem);
      }
    }
  }

  @Named("toCategoryType")
  default MenuCategory toCategoryType(String category) {
    return MenuCategory.valueOf(category.toUpperCase());
  }
}
