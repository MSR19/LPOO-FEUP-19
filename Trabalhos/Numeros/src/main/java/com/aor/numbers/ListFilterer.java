package com.aor.numbers;

import java.util.ArrayList;
import java.util.List;

public class ListFilterer {
    List<Integer> list;

    ListFilterer (List<Integer> list) {
        this.list = list;
    }

    public List<Integer> filter(IListFilter filter) {
        List<Integer> filtered = new ArrayList();
        for(int i = 0; i != list.size(); i++) {
            if(filter.accept(list.get(i)))
                filtered.add(list.get(i));
        }
        return filtered;
    }
}
