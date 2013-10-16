package name.lemerdy.eric;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Lists.newArrayList;
import static name.lemerdy.eric.DoorAction.CLOSE;
import static name.lemerdy.eric.DoorAction.NOTHING;

public class Elevator {
    private Integer[] numberOfPersonThatWantToOpenDoorsPerFloor;
    private Integer currentFloor;
    private DoorAction doorAction;

    public Elevator() {
        this(0);
    }

    @VisibleForTesting
    public Elevator(Integer currentFloor) {
        this.currentFloor = currentFloor;
        this.numberOfPersonThatWantToOpenDoorsPerFloor = new Integer[]{0, 0, 0, 0, 0, 0};
        this.doorAction = NOTHING;
    }

    public void oneMorePersonWantToOpenDoorsAt(Integer floor) {
        numberOfPersonThatWantToOpenDoorsPerFloor[floor]++;
    }

    public String performNextCommand() {
        String nextCommand = "";
        if (doorAction.equals(CLOSE)) {
            nextCommand = "CLOSE";
            doorAction = NOTHING;
        } else if (getNumberOfPersonThatWantToOpenDoorsHere() > 0) {
            nextCommand = "OPEN";
            doorAction = CLOSE;
            openedDoorsHere();
        } else if (isWaitedElsewhere()) {
            nextCommand = moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors();
        } else {
            nextCommand = "NOTHING";
        }
        return nextCommand;
    }

    @VisibleForTesting
    protected Boolean isWaitedElsewhere() {
        return any(newArrayList(numberOfPersonThatWantToOpenDoorsPerFloor),
                new Predicate<Integer>() {
                    @Override
                    public boolean apply(Integer input) {
                        return input > 0;
                    }
                });
    }

    @VisibleForTesting
    protected String moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors() {
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

    private Integer getNumberOfPersonThatWantToOpenDoorsHere() {
        return numberOfPersonThatWantToOpenDoorsPerFloor[currentFloor];
    }

    private void openedDoorsHere() {
        numberOfPersonThatWantToOpenDoorsPerFloor[currentFloor] = 0;
    }
}
