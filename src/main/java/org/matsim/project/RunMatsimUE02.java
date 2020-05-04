/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nagel
 *
 */
public class RunMatsimUE02 {

	public static void main(String[] args) {

		Config config = ConfigUtils.loadConfig( args ) ;

		// possibly modify config here

		// ---

		Scenario scenario = ScenarioUtils.loadScenario(config) ;

		// possibly modify scenario here

		// let MATsim output only 1 person

		Id<Person> interestingPersonId = Id.createPersonId(1);
		List<Id<Person>> personstoRemove = new ArrayList<>();

		for (Id<Person> personId : scenario.getPopulation().getPersons().keySet()) {
			if (!personId.equals(interestingPersonId)) {
				personstoRemove.add(personId);
			}
		}

		for (Id<Person> personId : personstoRemove) {
			scenario.getPopulation().removePerson(personId);
		}

		System.out.println("Population size = " + scenario.getPopulation().getPersons().size()); // should output 1

		// create new person with a daily plan

		PopulationFactory populationFactory = scenario.getPopulation().getFactory();

		Person person2 = populationFactory.createPerson(Id.createPersonId("Erik"));

		Plan plan = populationFactory.createPlan();

		Activity homeActivity= populationFactory.createActivityFromLinkId("h", Id.createLinkId(21));
		homeActivity.setEndTime(6*60+60.);
		plan.addActivity(homeActivity);

		Leg leg = populationFactory.createLeg(TransportMode.car);
		plan.addLeg(leg);

		Activity workActivity = populationFactory.createActivityFromLinkId("w", Id.createLinkId(1));
		plan.addActivity(workActivity);

		person2.addPlan(plan);

		scenario.getPopulation().addPerson(person2);

		System.out.println("Population size = " + scenario.getPopulation().getPersons().size()); //should output 2 as we now have 2 persons


		// ---

		Controler controler = new Controler( scenario ) ;

		// possibly modify controler here

		controler.run();
	}
	
}
