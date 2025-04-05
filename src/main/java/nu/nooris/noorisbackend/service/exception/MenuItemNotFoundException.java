package nu.nooris.noorisbackend.service.exception;

import java.util.UUID;

public class MenuItemNotFoundException extends RuntimeException {

  public MenuItemNotFoundException(UUID reference) {
    super("Menu item with reference " + reference + " not found");
  }
}
