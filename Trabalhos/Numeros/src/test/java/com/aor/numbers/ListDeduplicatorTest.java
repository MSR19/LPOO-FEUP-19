package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListDeduplicatorTest {
    private  List<Integer> list;

    class StubListSorter implements IListSorter {
        List<Integer> listSorted;

        @Override
        public List<Integer> sort() {
            this.listSorted = new ArrayList();
            listSorted.add(1);
            listSorted.add(2);
            listSorted.add(2);
            listSorted.add(4);

            return listSorted;
        }
    }


    @Before
    public  void setUp () {
        this.list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);
    }



    @Test
    public void deduplicate() {

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(4);

        ListDeduplicator deduplicator = new ListDeduplicator(list);
        List<Integer> distinct = deduplicator.deduplicate(new StubListSorter());

        assertEquals(expected, distinct);
    }
}