package name.lemerdy.eric;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ElevatorTest {

    private Elevator elevator;

    @Before
    public void createFloors() throws Exception {
        elevator = new Elevator();
    }

    @Test
    public void with_no_direction_should_not_have_direction() {
        assertThat(elevator.isWaitedElsewhere()).isFalse();
    }

    @Test
    public void with_some_direction_should_have_direction() {
        elevator.oneMorePersonWantToOpenDoorsAt(1);
        elevator.oneMorePersonWantToOpenDoorsAt(1);
        elevator.oneMorePersonWantToOpenDoorsAt(4);
        assertThat(elevator.isWaitedElsewhere()).isTrue();
    }

    @Test
    public void with_one_upper_direction_should_goes_up() throws Exception {
        elevator = new Elevator(2);
        elevator.oneMorePersonWantToOpenDoorsAt(3);
        assertThat(elevator.moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors()).isEqualTo("UP");
    }

    @Test
    public void with_one_lower_direction_should_goes_down() throws Exception {
        elevator = new Elevator(4);
        elevator.oneMorePersonWantToOpenDoorsAt(1);
        assertThat(elevator.moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors()).isEqualTo("DOWN");
    }
}
