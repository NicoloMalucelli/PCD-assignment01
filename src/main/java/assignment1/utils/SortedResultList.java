package assignment1.utils;

import assignment1.model.Result;

import java.util.List;

public interface SortedResultList{
    List<Result> getFirstN(int nElems);
    void add(Result item);
}
