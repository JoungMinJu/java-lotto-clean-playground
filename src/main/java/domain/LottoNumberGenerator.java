package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator {

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)
        .boxed()
        .collect(Collectors.toList());

    public List<Integer> generate() {
        Collections.shuffle(NUMBERS);
        return new ArrayList<>(NUMBERS.subList(0, Lotto.SIZE));
    }

}
