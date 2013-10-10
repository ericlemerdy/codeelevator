package name.lemerdy.eric;

import name.lemerdy.eric.Floors;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FloorsTest {

    private Floors floors;

    @Before
    public void createFloors() throws Exception {
        floors = new Floors();
    }

    @Test
    public void with_no_direction_should_not_have_direction() {
        assertThat(floors.haveDirection()).isFalse();
    }

    @Test
    public void with_some_direction_should_have_direction() {
        floors.addPersonAtFloor(1);
        floors.addPersonAtFloor(1);
        floors.addPersonAtFloor(4);
        assertThat(floors.haveDirection()).isTrue();
    }

    @Test
    public void with_one_upper_direction_should_goes_up() throws Exception {
        floors.addPersonAtFloor(3);
        assertThat(floors.chooseDirection(2)).isEqualTo("UP");
    }

    @Test
    public void with_one_lower_direction_should_goes_down() throws Exception {
        floors.addPersonAtFloor(1);
        assertThat(floors.chooseDirection(4)).isEqualTo("DOWN");
    }
}
