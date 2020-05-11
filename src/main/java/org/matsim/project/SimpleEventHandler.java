package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.PersonEntersVehicleEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.events.handler.PersonEntersVehicleEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimpleEventHandler implements PersonDepartureEventHandler, PersonArrivalEventHandler, PersonEntersVehicleEventHandler {

    //UE04:
    Map<Id<Person>, Double> departureTimeByPersonMap = new HashMap<>();

    @Override
    public void handleEvent(PersonDepartureEvent personDepartureEvent) {
        System.out.println("Departure event; time " + personDepartureEvent.getTime() + " -- linkId: " + personDepartureEvent.getLinkId() + "personId: " + personDepartureEvent.getPersonId());

        departureTimeByPersonMap.put(personDepartureEvent.getPersonId(), personDepartureEvent.getTime());
    }

    @Override
    public void handleEvent(PersonEntersVehicleEvent personEntersVehicleEvent) {
        //Task at home: try and play around with this, i.e. compute travel times (departure minus arrival Time)
    }

    @Override
    public void handleEvent(PersonArrivalEvent personArrivalEvent) {

        System.out.println("Arrival event; time " + personArrivalEvent.getTime() + " -- linkId: " + personArrivalEvent.getLinkId() + "personId: " + personArrivalEvent.getPersonId());

        //UE04:

        double departureTime = departureTimeByPersonMap.get(personArrivalEvent.getPersonId());
        //print same travel time for all agents because it is running the very first simple example project
        System.out.println("travel time = " + (personArrivalEvent.getTime()-departureTime));

    }

}
