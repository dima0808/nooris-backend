package nu.nooris.noorisbackend.service;

import java.util.List;
import java.util.UUID;
import nu.nooris.noorisbackend.repository.entity.MenuItem;

public interface MenuService {

  List<MenuItem> getAllMenuItems();

  MenuItem getMenuItemByReference(UUID reference);

  MenuItem createMenuItem(MenuItem menuItem);

  MenuItem updateMenuItem(UUID reference, MenuItem menuItem);

  void deleteMenuItemByReference(UUID reference);
}
