package name.lemerdy.eric;

import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Lists.newArrayList;

public class Floors {
    private Integer[] floors = new Integer[]{0,0,0,0,0,0};
    public Integer get(Integer currentFloor) {
        return floors[currentFloor];
    }

    public void emptyFloor(Integer currentFloor) {
        floors[currentFloor] = 0;
    }

    public void addPersonAtFloor(Integer atFloor) {
        floors[atFloor]++;
    }

    public Boolean haveDirection() {
        return any(newArrayList(floors),
                new Predicate<Integer>() {
                    @Override
                    public boolean apply(Integer input) {
                        return input > 0;
                    }
                });
    }

    public String chooseDirection(Integer currentFloor) {
        Integer maxQueue = 0;
        Integer targetFloor = 0;
        for (int i = 0; i < floors.length; i++) {
            Integer queueAtFloor = floors[i];
            if (queueAtFloor > maxQueue) {
                targetFloor = i;
                maxQueue = floors[i];
            }
        }
        return (targetFloor < currentFloor) ? "DOWN" : "UP";
    }
}
