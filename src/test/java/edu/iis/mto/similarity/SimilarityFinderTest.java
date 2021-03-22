package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    public void shouldReturnOneWhenBothSequencesAreEqual() {
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
    public void shouldReturnZeroWhenSequencesHaveNoCommonElements() {
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

    @Test
    public void shouldReturnHalfWhenGivenIntersectingSequencesAndEqualSequencesLengths() {
        // give
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> {
            if(Arrays.equals(sequence, new int[] {5, 8, 7}) == false) {
                return null;
            }

            if(elem == 9) {
                return SearchResult.builder().withFound(false).build();
            } else if (elem == 5) {
                return SearchResult.builder().withFound(true).build();
            } else if (elem == 7) {
                return SearchResult.builder().withFound(true).build();
            }

            return null;
        });

        int[] seq1 = {9, 5, 7};
        int[] seq2 = {5, 8, 7};
        double expectedResult = 0.5;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    public void shouldReturnZeroPointTwentyFiveWhenGivenIntersectingSequencesAndDifferentSequenceLengths() {
        // give
        SimilarityFinder similarityFinder = new SimilarityFinder((elem, sequence) -> {
            if(Arrays.equals(sequence, new int[] {10, 4, 7, 22, 123, 0}) == false) {
                return null;
            }

            if(elem == 4) {
                return SearchResult.builder().withFound(true).build();
            } else if (elem == 22) {
                return SearchResult.builder().withFound(true).build();
            } else if (elem == 88) {
                return SearchResult.builder().withFound(false).build();
            } else if (elem == 99) {
                return SearchResult.builder().withFound(false).build();
            }

            return null;
        });

        int[] seq1 = {4, 22, 88, 99};
        int[] seq2 = {10, 4, 7, 22, 123, 0};
        double expectedResult = 0.25;

        // when
        double result = similarityFinder.calculateJackardSimilarity(seq1, seq2);

        // then
        assertEquals(expectedResult, result);
    }
}
