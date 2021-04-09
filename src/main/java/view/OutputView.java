package view;

import model.*;

import java.util.Map;

public class OutputView {
    private static final String PLEASE_INPUT_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
    private static final String HAS_PURCHASED = "개를 구매했습니다.";
    private static final String PLEASE_INPUT_LAST_WEEK_WINNING_LOTTO_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PLEASE_INPUT_BONUS_BALL_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String SEPARATION_LINE = "---------";
    private static final String FIFTH_WINNING_COUNT_IS = "3개 일치 (5000원) - ";
    private static final String FOURTH_WINNING_COUNT_IS = "4개 일치 (50000원) - ";
    private static final String THIRD_WINNING_COUNT_IS = "5개 일치 (1500000원) - ";
    private static final String SECOND_WINNING_COUNT_IS = "5개 일치, 보너스 볼 일치 (30000000원) - ";
    private static final String FIRST_WINNING_COUNT_IS = "6개 일치 (2000000000원) - ";
    private static final String PIECES = "개";
    private static final String TOTAL_YIELD_IS = "총 수익률은 ";

    public static void printPleaseInputPurchaseMoney() {
        System.out.println(PLEASE_INPUT_PURCHASE_MONEY);
    }

    public static void printHasPurchased(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + HAS_PURCHASED);
    }

    public static void printLottos(Lottos lottos) {
        lottos.getLottos().stream()
                .map(Lotto::toString)
                .forEach(System.out::println);
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

    public static void printStatistics(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = lottos.getStatistics(winningLotto);
        printFifthWinningCountIs(statistics.get(Rank.FIFTH));
        printFourthWinningCountIs(statistics.get(Rank.FOURTH));
        printThirdWinningCountIs(statistics.get(Rank.THIRD));
        printSecondWinningCountIs(statistics.get(Rank.SECOND));
        printFirstWinningCountIs(statistics.get(Rank.FIRST));
        printTotalYieldIs(lottos.calculateYield(winningLotto));
    }

    private static void printFifthWinningCountIs(int count) {
        System.out.println(FIFTH_WINNING_COUNT_IS + count + PIECES);
    }

    private static void printFourthWinningCountIs(int count) {
        System.out.println(FOURTH_WINNING_COUNT_IS + count + PIECES);
    }

    private static void printThirdWinningCountIs(int count) {
        System.out.println(THIRD_WINNING_COUNT_IS + count + PIECES);
    }

    private static void printSecondWinningCountIs(int count) {
        System.out.println(SECOND_WINNING_COUNT_IS + count + PIECES);
    }

    private static void printFirstWinningCountIs(int count) {
        System.out.println(FIRST_WINNING_COUNT_IS + count + PIECES);
    }

    public static void printTotalYieldIs(double yield) {
        System.out.println(TOTAL_YIELD_IS + yield + "입니다.");
    }
}
