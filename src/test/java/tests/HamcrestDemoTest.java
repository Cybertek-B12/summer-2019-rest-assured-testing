package tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class HamcrestDemoTest {

    @Test
    public void hamcrestTests() {
        String one = "text";
        String two = "text";
        String three = " text";

        assertThat(one, equalTo(two));

        assertThat(one, is(two));

        assertThat(one, is(equalToCompressingWhiteSpace(three)));

        // verify values are not equal
        assertThat(one, is(not(three)));

        assertThat(12, greaterThan(11));
        assertThat(12, lessThanOrEqualTo(13));

        List<Integer> list = Arrays.asList(1, 2, 4, 5);

        assertThat(list, hasSize(4));
        assertThat(list, contains(1,2,4,5));

        assertThat(list, hasItem(5));

        assertThat(list, everyItem(greaterThan(0)));

    }
}
