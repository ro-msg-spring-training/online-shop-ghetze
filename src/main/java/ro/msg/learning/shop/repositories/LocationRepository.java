package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entitites.Location;

import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
