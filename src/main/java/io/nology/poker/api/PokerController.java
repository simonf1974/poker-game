package io.nology.poker.api;

import io.nology.poker.engine.HandScorer;
import io.nology.poker.engine.Holdem;
import io.nology.poker.entity.Table;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/play")
public class PokerController {

    @GetMapping("/score/{hand}")
    public int scoreHand(@PathVariable String hand) {
        return HandScorer.scoreHand(hand);
    }

    @PostMapping("/score/table")
    public Table scoreHand(@RequestBody Table table) {
        return Holdem.scoreTable(table);
    }
}
