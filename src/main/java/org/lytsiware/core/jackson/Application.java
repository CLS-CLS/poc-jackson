package org.lytsiware.core.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lytsiware.core.jackson.component.Card;
import org.lytsiware.core.jackson.component.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.List;

public class Application {

    private static Logger log = LoggerFactory.getLogger(Application.class);
    public static ObjectMapper objectMapper = objectMapper();

    private static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) throws JsonProcessingException {
        log.debug("Starting applications at {}", LocalTime.now());

        State state = SampleData.state();
        /*
         * use pretty print with the help of 'writerWithDefaultPrettyPrinter'
         */
        String stateAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(state);
        log.info("State at time {} is {}", LocalTime.now(), stateAsString);

        State deserializedState = objectMapper.readValue(stateAsString, State.class);
        log.info("Deserialized state {}", deserializedState);

        sampleWithLists();

        log.debug("Application end");
    }

    private static void sampleWithLists() throws JsonProcessingException {
        log.debug("START sampleWithLists");
        List<Card> cards = SampleData.someCards();

        log.info("card {}", objectMapper.writeValueAsString(cards.get(0)));

        /**
         * needs {@link ObjectMapper#writerFor(Class)} when de/serializing lists due genetics
         * erasure
         * https://github.com/FasterXML/jackson-databind/issues/1816
         */
        String cardsAsString = objectMapper.writerFor(new TypeReference<List<Card>>() {
        }).writeValueAsString(cards);

        log.info("The Cards are {}", cardsAsString);

        List<Card> cards2 = objectMapper.readValue(cardsAsString, new TypeReference<List<Card>>() {
        });

        log.debug("END sampleWithLists");
    }


}
