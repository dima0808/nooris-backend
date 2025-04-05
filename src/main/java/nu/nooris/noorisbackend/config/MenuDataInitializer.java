package nu.nooris.noorisbackend.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import nu.nooris.noorisbackend.repository.MenuItemRepository;
import nu.nooris.noorisbackend.repository.entity.MenuItem;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MenuDataInitializer implements ApplicationRunner {

  private final MenuItemRepository menuItemRepository;
  private final ObjectMapper objectMapper;

  public MenuDataInitializer(MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (menuItemRepository.count() == 0) {
      System.out.println("Loading menu data from JSON...");
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.json");
      if (inputStream == null) {
        System.out.println("menu.json file not found!");
        return;
      }
      List<MenuItem> menuItems = objectMapper.readValue(inputStream, new TypeReference<>() {});
      menuItemRepository.saveAll(menuItems);
      System.out.println("Basic menu data loaded successfully!");
    } else {
      System.out.println("Menu data already exists, skipping initialization.");
    }
  }
}
