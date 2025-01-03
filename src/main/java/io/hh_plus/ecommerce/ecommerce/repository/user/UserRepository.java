package io.hh_plus.ecommerce.ecommerce.repository.user;

import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
