package com.aor.numbers;

import java.util.List;

public interface IListDeduplicator {
    public List<Integer> getList();
    public List<Integer> deduplicate(IListSorter listSorter);
}
