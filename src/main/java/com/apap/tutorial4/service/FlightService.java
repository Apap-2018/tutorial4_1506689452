package com.apap.tutorial4.service;

import java.util.List;
import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	void updateFlight(FlightModel flightModel);
	void removeFlight(Long id);
	FlightModel flightById(Long id);
	List<FlightModel> getAllFlight();
}
