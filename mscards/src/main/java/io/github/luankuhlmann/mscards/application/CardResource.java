package io.github.luankuhlmann.mscards.application;

import io.github.luankuhlmann.mscards.application.representation.CardSaveRequestDto;
import io.github.luankuhlmann.mscards.application.representation.CardsPerCustomerResponseDto;
import io.github.luankuhlmann.mscards.domain.Card;
import io.github.luankuhlmann.mscards.domain.CustomerCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService cardService;
    private final CustomerCardService customerCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequestDto request) {
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardMaxIncome(@RequestParam("income") Long income) {
        List<Card> list = cardService.getCardIncomeLowerEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerCustomerResponseDto>> getCardsByCustomer(@RequestParam("cpf") String cpf) {
        List<CustomerCard> list = customerCardService.listCardsByCpf(cpf);
        List<CardsPerCustomerResponseDto> resultList = list.stream()
                .map(CardsPerCustomerResponseDto::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
