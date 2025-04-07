package nu.nooris.noorisbackend.repository;

import java.util.List;
import java.util.UUID;
import nu.nooris.noorisbackend.common.MenuCategory;
import nu.nooris.noorisbackend.repository.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

  List<MenuItem> findAllByCategory(MenuCategory category);
}
