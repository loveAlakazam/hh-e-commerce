package io.hh_plus.ecommerce.ecommerce.domain.service.coupon;

import io.hh_plus.ecommerce.ecommerce.repository.coupon.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
}
