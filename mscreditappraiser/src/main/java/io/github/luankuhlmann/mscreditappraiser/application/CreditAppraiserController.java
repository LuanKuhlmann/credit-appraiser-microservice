package io.github.luankuhlmann.mscreditappraiser.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-appraiser")
public class CreditAppraiserController {

    @GetMapping
    public String status() {
        return "ok";
    }
}
