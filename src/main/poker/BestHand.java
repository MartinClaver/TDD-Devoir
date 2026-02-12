package main.poker;


import java.util.List;

public class BestHand {
    private final HandCategory category;
    private final List<Card> chosen5;

    public BestHand(HandCategory category, List<Card> chosen5) {
        this.category = category;
        this.chosen5 = chosen5;
    }

    public HandCategory getCategory() {
        return category;
    }

    public List<Card> getChosen5() {
        return chosen5;
    }
}