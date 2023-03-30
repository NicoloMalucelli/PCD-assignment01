package assignment1.model;

public interface ModelObserver {
    enum Event {RESULTS_UPDATED, COMPUTATION_ENDED}
    void resultsUpdated();
    void computationEnded();
}
