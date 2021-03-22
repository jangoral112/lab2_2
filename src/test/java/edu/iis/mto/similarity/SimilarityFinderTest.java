package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Test;

class SimilarityFinderTest {

    @Test
    public void shouldReturnOneWhenBothSequenceAreEmpty() {
        // given

        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> null);

        int[] seq1 = {};
        int[] seq2 = {};
        double expectedValue = 1;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedValue, result);
    }

}
