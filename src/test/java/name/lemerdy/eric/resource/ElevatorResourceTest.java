package name.lemerdy.eric.resource;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.fest.assertions.Assertions.assertThat;

public class ElevatorResourceTest {

    private ElevatorResource elevator;
    private Response response;

    @Before
    public void setUp() throws Exception {
        elevator = new ElevatorResource();
    }

    private void assertThatAction(Response response, Integer statusCode, String action) {
        assertThat(response.getStatus()).isEqualTo(statusCode);
        assertThat(response.getEntity()).isEqualTo(action);
    }

    @Test
    public void should_respond_to_next_command() {
        response = elevator.nextCommand();
        assertThatAction(response, 200, "NOTHING");
    }

    @Test
    public void with_0_floor_should_accept_up_at_0() throws Exception {
        response = elevator.nextCommand();
        response = elevator.call(0, "UP");
        assertThatAction(response, 200, null);
    }

    @Test
    public void with_0_floor_should_accept_up_at_0_and_open_then_close_when_go_to_1_should_up() throws Exception {
        response = elevator.nextCommand();
        response = elevator.call(0, "UP");
        response = elevator.nextCommand();
        assertThatAction(response, 200, "OPEN");
        response = elevator.userHasEntered();
        response = elevator.go(1);
        response = elevator.nextCommand();
        assertThatAction(response, 200, "CLOSE");
        response = elevator.nextCommand();
        assertThatAction(response, 200, "UP");
        response = elevator.nextCommand();
        assertThatAction(response, 200, "OPEN");
        response = elevator.nextCommand();
        assertThatAction(response, 200, "CLOSE");
    }
}
