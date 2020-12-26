package org.lytsiware.core.jackson;

import org.lytsiware.core.jackson.component.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleData {

    public static State state() {
        return new State(){{
            setCards(someCards());
            setTurn("this is a turn?");
            setScore(100);
        }};
    }

    public static List<Card> someCards() {
        return Stream.of(new NoOpCard(0, Type.HEART),
                new NoOpCard(1, Type.SPADE),
                new AnotherCard(2, Type.SPADE),
                new AnotherCard(3, Type.SPADE)
        ).collect(Collectors.toList());

    }
}
