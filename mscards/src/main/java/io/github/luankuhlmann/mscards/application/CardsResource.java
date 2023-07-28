package io.github.luankuhlmann.mscards.application;

import io.github.luankuhlmann.mscards.application.representation.CardSaveRequestDto;
import io.github.luankuhlmann.mscards.application.representation.CardsPerClientResponseDto;
import io.github.luankuhlmann.mscards.domain.Card;
import io.github.luankuhlmann.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;

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
    public ResponseEntity<List<Card>> getCardIncomeUntil(@RequestParam("income") Long income) {
        List<Card> list = cardService.getCardIncomeLowerEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerClientResponseDto>> getCardsByClient(@RequestParam("cpf") String cpf) {
        List<ClientCard> list = clientCardService.listCardsByCpf(cpf);
        List<CardsPerClientResponseDto> resultList = list.stream()
                .map(CardsPerClientResponseDto::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
