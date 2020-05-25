package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetworkModifier { //UE05

    public static void main(String[] args) {
        Path inputNetwork = Paths.get(args[0]); //add first program argument in dropdown menu
        Path outputNetwork = Paths.get(args[1]); //add second program argument in dropdown menu as output file (potentially .gz)

        Network network = NetworkUtils.createNetwork();
        new MatsimNetworkReader(network).readFile(inputNetwork.toString());

        //UE05: network.getLinks().get(Id.createLinkId("6")).setCapacity(120); //link 6 now has super low capacity

        //UE06:

        network.getLinks().get(Id.createLinkId("6")).setCapacity(1); //link 6 now has super low capacity

        new NetworkWriter(network).write(outputNetwork.toString());


    }
}
