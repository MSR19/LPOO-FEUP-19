package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListFilterTest {
    private List<Integer> list;

    @Before
    public  void setUp () {
        this.list = new ArrayList();
        list.add(3);
        list.add(2);
        list.add(0);
        list.add(6);
        list.add(1);
        list.add(4);
        list.add(-2);
        list.add(5);
        list.add(7);
    }

    public class StubIlistFilter implements IListFilter {
        @Override
        public boolean accept(Integer number) {
            if (number > 2) return true;
            else return false;
        }
    }

    @Test
    public void listFilter () {
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(6);
        expected.add(4);
        expected.add(5);
        expected.add(7);

        List<Integer> result = new ListFilterer(list).filter(new StubIlistFilter());

        assertEquals(expected,result);
    }

    @Test
    public  void positiveFilter () {
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(2);
        expected.add(6);
        expected.add(1);
        expected.add(4);
        expected.add(5);
        expected.add(7);

        List<Integer> result = new ListFilterer(list).filter(new PositiveFilter());

        assertEquals(expected,result);
    }

    @Test
    public  void divisible () {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(0);
        expected.add(6);
        expected.add(4);
        expected.add(-2);

        List<Integer> result = new ListFilterer(list).filter(new DivisibleByFilter(2));

        assertEquals(expected,result);
    }

}
