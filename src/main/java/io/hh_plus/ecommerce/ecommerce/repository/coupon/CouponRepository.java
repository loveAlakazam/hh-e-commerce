package io.hh_plus.ecommerce.ecommerce.repository.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.model.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}