package agh.cs.lab2;

public interface IPositionChangedPublisher {

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);
}
