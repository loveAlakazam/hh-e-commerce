package io.hh_plus.ecommerce.ecommerce.controller.point;

import io.hh_plus.ecommerce.ecommerce.domain.service.point.PointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("points")
public class PointController {
    private final PointService pointService;
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    // 포인트 충전 API
    @PutMapping("/{userId}")
    public ResponseEntity<String> chargePoint(@PathVariable Long userId, @RequestBody Integer amount) {
        pointService.chargePoint(userId, amount);
        return ResponseEntity.ok("충전완료");
    }

    // 포인트 조회 API
    @GetMapping("/{userId}/points")
    public ResponseEntity<Integer> getPoint(@PathVariable long userId) {
        return ResponseEntity.ok(pointService.getCurrentPoint(userId));
    }
}
