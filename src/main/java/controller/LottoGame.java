package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LottoGame {

    public static void main(String[] args) {
        Money money = new Money(InputView.inputMoneyAmount());
        Lottos lottos = Lottos.issueLottos(money, new RandomNumberGenerator());
        OutputView.printLottos(lottos);
        List<LottoNumber> lottoNumbers = convertNumbersToLottoNumbers(InputView.inputNumbers());
        Lotto lotto = new Lotto(lottoNumbers);
        WinningLotto winningLotto = InputView.inputWinningLotto(lotto);
        Map<Rank, Integer> statistics = lottos.produceStatistics(winningLotto);
        double yield = lottos.calculateYield(winningLotto, money);
        OutputView.printStatistics(statistics, yield);
    }

    private static List<LottoNumber> convertNumbersToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }

}
