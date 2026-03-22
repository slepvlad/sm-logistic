package com.shipping.calc.controller;

import com.shipping.calc.model.ShippingRequest;
import com.shipping.calc.model.ShippingResponse;
import com.shipping.calc.service.ShippingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shipping")
@CrossOrigin(origins = "*")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<ShippingResponse> calculate(@Valid @RequestBody ShippingRequest request) {
        ShippingResponse response = shippingService.calculate(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "Shipping Cost Calculator");
        return ResponseEntity.ok(status);
    }
}
