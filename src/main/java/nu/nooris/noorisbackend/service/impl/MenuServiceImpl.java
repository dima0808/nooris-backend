package nu.nooris.noorisbackend.service.impl;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.repository.MenuItemRepository;
import nu.nooris.noorisbackend.repository.entity.MenuItem;
import nu.nooris.noorisbackend.service.MenuService;
import nu.nooris.noorisbackend.service.exception.MenuItemNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

  private final MenuItemRepository menuItemRepository;

  @Override
  public List<MenuItem> getAllMenuItems() {
    return menuItemRepository.findAll();
  }

  @Override
  public MenuItem getMenuItemByReference(UUID reference) {
    return menuItemRepository.findById(reference)
        .orElseThrow(() -> new MenuItemNotFoundException(reference));
  }

  @Override
  public MenuItem createMenuItem(MenuItem menuItem) {
    return menuItemRepository.save(menuItem);
  }

  @Override
  public MenuItem updateMenuItem(UUID reference, MenuItem menuItem) {
    menuItem.setReference(reference);
    return menuItemRepository.save(menuItem);
  }

  @Override
  public void deleteMenuItemByReference(UUID reference) {
    menuItemRepository.deleteById(reference);
  }
}
