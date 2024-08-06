package com.Week4HW.Week4.controllers;

import com.Week4HW.Week4.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convertCurrency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("{baseCurrency}/{currency}/{amount}")
    public Double getConvertedCurrency(@PathVariable String baseCurrency,
                                       @PathVariable String currency,
                                       @PathVariable Double amount){
        return currencyService.getConvertedCurrency(baseCurrency, currency, amount);
    }

}
