package org.matsim.project;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler {

    public static void main(String[] args) {

        String inputFile = "output-reduced-nw/output_events.xml.gz";
        String outputFile = "output-reduced-nw/link6volumes.txt";

        EventsManager eventsManager = EventsUtils.createEventsManager(); //

        SimpleEventHandler eventHandler = new SimpleEventHandler();
        eventsManager.addHandler(eventHandler);

        SimpleLinkEventHandlerUE05 linkEventHandler = new SimpleLinkEventHandlerUE05(outputFile);
        eventsManager.addHandler(eventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager); //
        eventsReader.readFile(inputFile);

        linkEventHandler.printResult();

    }
}
