package view;

import model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class OutputView {
    private static final String PLEASE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String HAS_PURCHASED = "개를 구매했습니다.";
    private static final String PLEASE_INPUT_LAST_WEEK_WINNING_LOTTO_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PLEASE_INPUT_BONUS_BALL_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String SEPARATION_LINE = "---------";
    private static final String MATCHES = "개 일치 (";
    private static final String MATCHES_AND_BONUS_NUMBER_MATCH = "개 일치, 보너스 볼 일치 (";
    private static final String COUNT_IS = "원) - ";
    private static final String PIECES = "개";
    private static final String TOTAL_YIELD_IS = "총 수익률은 ";

    public static void printPleaseInputPurchaseMoney() {
        System.out.println(PLEASE_INPUT_PURCHASE_MONEY);
    }

    public static void printHasPurchased(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + HAS_PURCHASED);
    }

    public static void printLottos(Lottos lottos) {
        printHasPurchased(lottos);
        lottos.getLottos().stream()
                .forEach(OutputView::printLotto);
    }

    private static void printLotto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .collect(toList());
        System.out.println(numbers);
    }

    public static void printPleaseInputLastWeekWinningLottoNumber() {
        System.out.println(PLEASE_INPUT_LAST_WEEK_WINNING_LOTTO_NUMBER);
    }

    public static void printPleaseInputBonusBallNumber() {
        System.out.println(PLEASE_INPUT_BONUS_BALL_NUMBER);
    }

    public static void printWinningStatistics() {
        System.out.println(WINNING_STATISTICS);
    }

    public static void printSeparationLine() {
        System.out.println(SEPARATION_LINE);
    }

    public static void printStatistics(Map<Rank, Integer> statistics, double yield) {
        printWinningStatistics();
        printSeparationLine();
        printWinningCounts(statistics);
        printYield(yield);
    }

    private static void printWinningCounts(Map<Rank, Integer> statistics) {
        statistics.keySet().stream()
                .filter(rank -> !rank.equals(Rank.NONE))
                .sorted(comparing(rank -> rank.getWinningMoney().getAmount()))
                .map(rank -> convertToResultMessage(statistics, rank))
                .forEach(System.out::println);
    }

    private static String convertToResultMessage(Map<Rank, Integer> statistics, Rank rank) {
        int matchCount = rank.getMatchCount();
        BigDecimal winningMoneyAmount = rank.getWinningMoney().getAmount();
        Integer winningCount = statistics.get(rank);
        if (rank.equals(Rank.SECOND)) {
            return matchCount + MATCHES_AND_BONUS_NUMBER_MATCH + winningMoneyAmount + COUNT_IS + winningCount + PIECES;
        }
        return matchCount + MATCHES + winningMoneyAmount + COUNT_IS + winningCount + PIECES;
    }

    private static void printYield(double yield) {
        System.out.println(TOTAL_YIELD_IS + yield + "입니다.");
    }
}
