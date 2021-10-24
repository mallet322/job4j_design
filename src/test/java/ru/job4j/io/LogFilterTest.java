package ru.job4j.io;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class LogFilterTest {

    @Test
    public void whenGet404Error() {
        var file = "input.txt";
        var expected = List.of(
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113",
                "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\" 404 1110"
        );
        var actual = LogFilter.filter(file);
        Assert.assertThat(expected, Matchers.is(actual));
    }

}