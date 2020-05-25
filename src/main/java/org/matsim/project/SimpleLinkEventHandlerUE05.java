package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimpleLinkEventHandlerUE05 implements LinkEnterEventHandler {

    private final BufferedWriter bufferedWriter;

    public SimpleLinkEventHandlerUE05(String outputFile) {
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }

    private double[] volumeLink6 = new double[24] ;

    private int getSlot(double time) {
        return (int) time/3600; //casting to int, cutting the remainder
    }

    //not executed yet in RunEventsHandler class

    @Override
    public void handleEvent(LinkEnterEvent event) {
        if(event.getLinkId().equals(Id.createLinkId("6"))) { //not with == sign, because we don't want to compare exact object but content; needs to
            int slot = getSlot(event.getTime());
            this.volumeLink6[slot]++; // takes number from hour slot in array and increases it by one
        }
    }

    public void printResult() {
        try {
            bufferedWriter.write("Hour \t Volume");
            bufferedWriter.newLine();
            for (int i=0; i<24; i++) {
                double volume = this.volumeLink6[i];
                bufferedWriter.write( i + "\t" + volume);
                bufferedWriter.newLine();
                System.out.println("Volume on link 6 from " + i + " to " + (i + 1) + " o'clock = " + volume);
            }
            bufferedWriter.close();
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }
}
