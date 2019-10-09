package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private  List<Integer> list;
    //private  IListDeduplicator deduplicator;
    //private  List<Integer> deduplicated;

    @Before
    public  void setUp () {
        this.list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        list.add(5);

        /*
        this.deduplicated = new ArrayList();
        deduplicated.add(1);
        deduplicated.add(2);
        deduplicated.add(4);
        deduplicated.add(5);
        */
        //deduplicator = Mockito.mock(IListDeduplicator.class);
        //Mockito.when(deduplicator.deduplicate(new ListSorter(list))).thenReturn(deduplicated);
        //Mockito.when(deduplicator.getList()).thenReturn(list);
    }

    @Test
    public void sum() {

        ListAggregator aggregator = new ListAggregator(list);

        int sum = aggregator.sum();

        assertEquals(14, sum);
    }

    @Test
    public  void maxNeg() {
        List<Integer> newList = new ArrayList();
        newList.add(-1);
        newList.add(-4);
        newList.add(-5);

        ListAggregator aggregator = new ListAggregator(newList);

        int max = aggregator.max();

        assertEquals(-1, max);
    }


    @Test
    public void max() {

        ListAggregator aggregator = new ListAggregator(list);

        int max = aggregator.max();

        assertEquals(5, max);
    }

    @Test
    public void min() {

        ListAggregator aggregator = new ListAggregator(list);

        int min = aggregator.min();

        assertEquals(1, min);
    }

    class StubDeduplicator implements IListDeduplicator {
        private  List<Integer> listDedup;

        @Override
        public List<Integer> getList() {
            return listDedup;
        }

        @Override
        public List<Integer> deduplicate(IListSorter listSorter) {
            this.listDedup = new ArrayList();
            listDedup.add(1);
            listDedup.add(2);
            listDedup.add(4);
            return listDedup;
        }
    }



    @Test
    public void distinct() {

        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(new ListDeduplicator(list));

        assertEquals(4, distinct);
    }

    @Test
    public  void distinct2 () {
        this.list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        ListAggregator aggregator = new ListAggregator(list);

        int distinct = aggregator.distinct(new StubDeduplicator());

        assertEquals(3, distinct);
    }

}