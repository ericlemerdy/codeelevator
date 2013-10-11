package name.lemerdy.eric.resource;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.abs;
import static javax.ws.rs.core.Response.ok;
import static name.lemerdy.eric.DoorAction.NOTHING;
import static name.lemerdy.eric.DoorAction.CLOSE;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import name.lemerdy.eric.DoorAction;
import name.lemerdy.eric.Elevator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/")
public class ElevatorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElevatorResource.class);
    private DoorAction doorAction;
    private Elevator elevator;

    public ElevatorResource() {
        init();
    }

    private void init() {
        doorAction = NOTHING;
        elevator = new Elevator();
    }

    @GET()
    @Path("/nextCommand")
    public Response nextCommand() {
        LOGGER.info("> nextCommand ");
        String nextCommand = "";
        if (doorAction.equals(CLOSE)) {
            nextCommand = "CLOSE";
            doorAction = NOTHING;
        } else if (elevator.getNumberOfPersonThatWantToOpenDoorsHere() > 0) {
            nextCommand = "OPEN";
            doorAction = CLOSE;
            elevator.openedDoorsHere();
        } else if (elevator.isWaitedElsewhere()) {
            nextCommand = elevator.moveThroughFloorWhereAMaximumOfPersonWantToOpenDoors();
        } else {
            nextCommand = "NOTHING";
        }
        LOGGER.info("< " + nextCommand);
        return ok(nextCommand).build();
    }

    @GET()
    @Path("/call")
    public Response call(@QueryParam("atFloor") Integer atFloor, @QueryParam("to") String to) {
        LOGGER.info("> call atFloor=" + atFloor + " to=" + to);
        elevator.oneMorePersonWantToOpenDoorsAt(atFloor);
        LOGGER.info("< ");
        return ok().build();
    }

    @GET()
    @Path("/go")
    public Response go(@QueryParam("floorToGo") Integer floor) {
        LOGGER.info("> go floorToGo=" + floor);
        elevator.oneMorePersonWantToOpenDoorsAt(floor);
        LOGGER.info("< ");
        return ok().build();
    }

    @GET()
    @Path("/userHasExited")
    public Response userHasExited() {
        LOGGER.info("> userHasExited");
        LOGGER.info("< ");
        return ok().build();
    }

    @GET()
    @Path("/userHasEntered")
    public Response userHasEntered() {
        LOGGER.info("> userHasEntered");
        LOGGER.info("< ");
        return ok().build();
    }

    @GET()
    @Path("/reset")
    public Response reset(@QueryParam("cause") String cause) {
        LOGGER.info("> reset cause=" + cause);
        init();
        LOGGER.info("< ");
        return ok().build();
    }
}
