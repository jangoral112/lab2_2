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

    @Test
    public void shouldReturnZeroWhenFirstArgumentSequenceIsEmpty() {
        // give

        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> null);

        int[] seq1 = {};
        int[] seq2 = {1, 4};
        double expectedResult = 0;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnZeroWhenSecondArgumentSequenceIsEmpty() {
        // give
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> SearchResult.builder().withFound(false).build());

        int[] seq1 = {120, 99};
        int[] seq2 = {};
        double expectedResult = 0;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnOneWhenBothSequencesAreEqual() {
        // give
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> SearchResult.builder().withFound(true).build());

        int[] seq1 = {44, 88};
        int[] seq2 = {44, 88};
        double expectedResult = 1;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnZeroWhenSequencesHaveNoCommonElements() {
        // give
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> SearchResult.builder().withFound(false).build());

        int[] seq1 = {1, 2, 3};
        int[] seq2 = {4, 5, 6};
        double expectedResult = 0;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }

}
