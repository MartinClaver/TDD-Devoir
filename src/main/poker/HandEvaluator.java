package main.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandEvaluator {
    public BestHand getBestHand(List<Card> holeCards, List<Card> board) {
        List<Card> allCards = new ArrayList<>(holeCards);
        allCards.addAll(board);
        allCards.sort((c1, c2) -> Integer.compare(c2.getRank().value, c1.getRank().value));
        List<Card> best5 = allCards.subList(0, 5);

        Map<Rank, Long> counts = allCards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        Rank pairRank = null;

        for (Map.Entry<Rank, Long> entry : counts.entrySet()) {
            if (entry.getValue() == 2) {
                pairRank = entry.getKey();
                break;
            }
        }

        if (pairRank != null) {
            // C'est ici que tu devras construire ta liste de 5 cartes
            // (Les 2 cartes de la paire + les 3 meilleures autres)
            return new BestHand(HandCategory.PAIR, best5);
        }

        return new BestHand(HandCategory.HIGH_CARD, best5);
    }
}
