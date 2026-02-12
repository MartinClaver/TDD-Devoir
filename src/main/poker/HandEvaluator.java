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

        List<Rank> pairs = counts.entrySet().stream()
                .filter(e -> e.getValue() == 2)
                .map(Map.Entry::getKey)
                .sorted((r1, r2) -> Integer.compare(r2.value, r1.value)) // Plus haute paire d'abord
                .collect(Collectors.toList());

        if (pairs.size() >= 2) {
            Rank p1 = pairs.get(0);
            Rank p2 = pairs.get(1);
            List<Card> final5 = new ArrayList<>();

            for(Card c : allCards) if(c.getRank() == p1) final5.add(c);
            for(Card c : allCards) if(c.getRank() == p2) final5.add(c);
            for(Card c : allCards) {
                if(c.getRank() != p1 && c.getRank() != p2 && final5.size() < 5) final5.add(c);
            }
            return new BestHand(HandCategory.TWO_PAIR, final5);
        }

        Rank pairRank = null;

        for (Map.Entry<Rank, Long> entry : counts.entrySet()) {
            if (entry.getValue() == 2) {
                pairRank = entry.getKey();
                break;
            }
        }

        if (pairRank != null) {
            List<Card> final5 = new ArrayList<>();

            for (Card c : allCards) {
                if (c.getRank() == pairRank) final5.add(c);
            }

            for (Card c : allCards) {
                if (c.getRank() != pairRank && final5.size() < 5) {
                    final5.add(c);
                }
            }
            return new BestHand(HandCategory.PAIR, best5);
        }

        return new BestHand(HandCategory.HIGH_CARD, best5);
    }
}
