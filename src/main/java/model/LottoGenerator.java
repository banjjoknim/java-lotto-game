package model;

@FunctionalInterface
public interface LottoGenerator {

    Lotto generate(NumberGenerator numberGenerator);
}
