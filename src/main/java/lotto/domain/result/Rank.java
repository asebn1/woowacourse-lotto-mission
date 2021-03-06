package lotto.domain.result;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NO_MATCH(0, 0);

    public static final int MATCH_BONUSH_NUMBER = 5;

    private int matchCount;
    private int money;

    Rank(final int matchCount, final int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank matchRank(final int matchCount, final boolean hasBonusNumber) {
        if (matchCount == MATCH_BONUSH_NUMBER && hasBonusNumber) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .filter(rank -> rank != Rank.SECOND)
                .findFirst()
                .orElse(NO_MATCH);
    }

    private boolean isSameMatchCount(final int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMoney() {
        return money;
    }
}
