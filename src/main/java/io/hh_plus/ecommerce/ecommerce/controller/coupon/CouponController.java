package io.hh_plus.ecommerce.ecommerce.controller.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.service.coupon.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("coupons")
public class CouponController {
    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // 쿠폰 발급 기능 API
    @PostMapping()
    public ResponseEntity<String> issueCoupon() {
        return ResponseEntity.ok("결제 API");
    }

    // 보유 쿠폰 목록 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<String> getMyCoupons(@PathVariable long userId) {
        return ResponseEntity.ok("결제 API");
    }

}
