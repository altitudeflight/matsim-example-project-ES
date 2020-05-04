package org.matsim.project;

import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.PersonEntersVehicleEvent;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.events.handler.PersonEntersVehicleEventHandler;

public class SimpleEventHandler implements PersonDepartureEventHandler, PersonEntersVehicleEventHandler {
    @Override
    public void handleEvent(PersonDepartureEvent personDepartureEvent) {
        System.out.println("Departure event; time " + personDepartureEvent.getTime() + " -- linkId: " + personDepartureEvent.getLinkId() + "personId: " + personDepartureEvent.getPersonId());
    }

    @Override
    public void handleEvent(PersonEntersVehicleEvent personEntersVehicleEvent) {
        //Task for next week: try and play around with this, i.e. compute travel times (departure minus arrival Time)
    }
}
