package view;

import model.Lotto;
import model.Lottos;
import model.Rank;
import model.WinningLotto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GameViewOutput {
    private static final String PLEASE_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String PURCHASED = "개를 구매했습니다.";
    private static final String PLEASE_INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String PLEASE_INPUT_AGAIN = "잘못된 입력입니다. 다시 입력해 주세요.";
    private static final String PLEASE_INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String PARTIONING_LINE = "--------";
    private static final String GET_FIFTH_PLACE_COUNT_IS = "3개 일치 (5000원) - ";
    private static final String GET_FOUTH_PLACE_COUNT_IS = "4개 일치 (50000원) - ";
    private static final String GET_THIRD_PLACE_COUNT_IS = "5개 일치 (1500000원) - ";
    private static final String GET_SECOND_PLACE_COUNT_IS = "5개 일치, 보너스 볼 일치 (30000000원) - ";
    private static final String GET_FIRST_PLACE_COUNT_IS = "6개 일치 (2000000000원) - ";
    private static final String COUNTS = "개";
    private static final String TOTAL_YIELD_IS = "총 수익률은 ";

    public static void printPleaseInputMoney() {
        System.out.println(PLEASE_INPUT_MONEY);
    }

    public static void printPurchased(List<Lotto> lottos) {
        System.out.println(lottos.size() + PURCHASED);
    }

    public static void printPleaseInputWinningNumbers() {
        System.out.println(PLEASE_INPUT_WINNING_NUMBERS);
    }

    public static void printPleaseInputBonusNo() {
        System.out.println(PLEASE_INPUT_BONUS_BALL);
    }

    public static void printPleaseInputAgain() {
        System.out.println(PLEASE_INPUT_AGAIN);
    }

    public static void printStatistics(WinningLotto winningLotto, Lottos lottos) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(PARTIONING_LINE);

        Map<Rank, Long> statistics = lottos.getStatistics(winningLotto);
        printRankCount(statistics);
        printYield(lottos.calculateYield(winningLotto));
    }

    private static void printRankCount(Map<Rank, Long> statistics) {
        List<String> template = setStatisticsTemplate();
        statistics.remove(Rank.NONE);
        Collections.reverse(template);
        IntStream.range(0, template.size())
                .mapToObj(index -> template.get(index) + statistics.getOrDefault(Rank.values()[index], 0L) + COUNTS)
                .forEach(System.out::println);
    }

    private static List<String> setStatisticsTemplate() {
        return Stream.of(GET_FIRST_PLACE_COUNT_IS,
                GET_SECOND_PLACE_COUNT_IS,
                GET_THIRD_PLACE_COUNT_IS,
                GET_FOUTH_PLACE_COUNT_IS,
                GET_FIFTH_PLACE_COUNT_IS)
                .collect(toList());
    }

    private static void printYield(BigDecimal yield) {
        System.out.println(TOTAL_YIELD_IS + yield + "입니다.");
    }

}