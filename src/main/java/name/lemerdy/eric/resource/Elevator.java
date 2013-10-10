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
import name.lemerdy.eric.Floors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/")
public class Elevator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Elevator.class);
    private DoorAction doorAction;
    private Floors mustGo;
    private Integer currentFloor;

    public Elevator() {
        init();
    }

    private void init() {
        doorAction = NOTHING;
        mustGo = new Floors();
        currentFloor = 0;
    }

    @GET()
    @Path("/nextCommand")
    public Response nextCommand() {
        LOGGER.info("> nextCommand ");
        String command = "";
        if (doorAction.equals(CLOSE)) {
            command = "CLOSE";
            doorAction = NOTHING;
        } else if (mustGo.get(currentFloor) > 0) {
            command = "OPEN";
            doorAction = CLOSE;
            mustGo.emptyFloor(currentFloor);
        } else if (mustGo.haveDirection()) {
            command = mustGo.chooseDirection(currentFloor);
            currentFloor = currentFloor + (command.equals("UP") ? 1 : -1);
        } else {
            command = "NOTHING";
        }
        LOGGER.info("< " + command);
        return ok(command).build();
    }

    @GET()
    @Path("/call")
    public Response call(@QueryParam("atFloor") Integer atFloor, @QueryParam("to") String to) {
        LOGGER.info("> call atFloor=" + atFloor + " to=" + to);
        mustGo.addPersonAtFloor(atFloor);
        LOGGER.info("< ");
        return ok().build();
    }

    @GET()
    @Path("/go")
    public Response go(@QueryParam("floorToGo") Integer floorToGo) {
        LOGGER.info("> go floorToGo=" + floorToGo);
        mustGo.addPersonAtFloor(floorToGo);
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
