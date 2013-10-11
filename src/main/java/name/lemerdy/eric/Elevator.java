package name.lemerdy.eric;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Lists.newArrayList;

public class Elevator {
    private Integer[] numberOfPersonThatWantToOpenDoorsPerFloor;
    private Integer currentFloor;

    public Elevator() {
        this(0);
    }

    @VisibleForTesting
    protected Elevator(Integer currentFloor) {
        this.currentFloor = currentFloor;
        this.numberOfPersonThatWantToOpenDoorsPerFloor = new Integer[]{0, 0, 0, 0, 0, 0};
    }

    public Integer getNumberOfPersonThatWantToOpenDoorsHere() {
        return numberOfPersonThatWantToOpenDoorsPerFloor[currentFloor];
    }

    public void openedDoorsHere() {
        numberOfPersonThatWantToOpenDoorsPerFloor[currentFloor] = 0;
    }

    public void oneMorePersonWantToOpenDoorsAt(Integer floor) {
        numberOfPersonThatWantToOpenDoorsPerFloor[floor]++;
    }

    public Boolean isWaitedElsewhere() {
        return any(newArrayList(numberOfPersonThatWantToOpenDoorsPerFloor),
                new Predicate<Integer>() {
                    @Override
                    public boolean apply(Integer input) {
                        return input > 0;
                    }
                });
    }

    public String moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors() {
        Integer maximumNumberOfPersonThatWantToOpenDoors = 0;
        Integer desiredFloor = 0;
        for (int i = 0; i < numberOfPersonThatWantToOpenDoorsPerFloor.length; i++) {
            Integer numberOfPersonThatWantToOpenDoorsAtFloor = numberOfPersonThatWantToOpenDoorsPerFloor[i];
            if (numberOfPersonThatWantToOpenDoorsAtFloor > maximumNumberOfPersonThatWantToOpenDoors) {
                desiredFloor = i;
                maximumNumberOfPersonThatWantToOpenDoors = numberOfPersonThatWantToOpenDoorsPerFloor[i];
            }
        }
        String direction;
        if (desiredFloor < currentFloor) {
            currentFloor = currentFloor + -1;
            direction = "DOWN";
        } else {
            currentFloor = currentFloor + 1;
            direction = "UP";
        }
        return direction;
    }
}
