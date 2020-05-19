package org.matsim.project;

import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;

import java.util.HashMap;
import java.util.Map;

public class SimpleLinkEventHandlerUE05 implements LinkEnterEventHandler {

    private Map<Integer, Integer> vehiclesPerHour = new HashMap<>();

    public SimpleLinkEventHandlerUE05(){
        for(int i=0;i<24;i++){
            vehiclesPerHour.put(i, 0);
        }
    }

    //not executed yet in RunEventsHandler class

    @Override
    public void handleEvent(LinkEnterEvent linkEnterEvent) {
        if(linkEnterEvent.getLinkId().toString().equals("6")) {
            int hour = (int) Math.ceil(linkEnterEvent.getTime() / 3600);
            vehiclesPerHour.put(hour, vehiclesPerHour.get(hour)+1);
        }
    }

    public void print(){
        for(Integer i : vehiclesPerHour.keySet()){
            System.out.println("During hour " + i + " : " + vehiclesPerHour.get(i) + " vehicles");
        }
    }
}
