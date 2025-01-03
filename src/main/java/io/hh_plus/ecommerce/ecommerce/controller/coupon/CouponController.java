package io.hh_plus.ecommerce.ecommerce.controller.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.service.coupon.CouponService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coupons")
public class CouponController {
    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

}
