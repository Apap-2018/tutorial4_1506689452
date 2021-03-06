package com.apap.tutorial4.service;

import java.util.List;
import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
			flightDb.save(flight);
	}
	
	public List<FlightModel> getAllFlight() {
		return flightDb.findAll();
	}
	
	@Override
	public FlightModel flightById(Long id) {
		return flightDb.getOne(id);
	}

	@Override
	public void updateFlight(FlightModel flightModel) {
	    FlightModel flight = flightDb.getOne(flightModel.getId());
	    flight.setDestination(flightModel.getDestination());
	    flight.setFlightNumber(flightModel.getFlightNumber());
	    flight.setOrigin(flightModel.getOrigin());
	    flight.setTime(flightModel.getTime());
	    flightDb.save(flight);
	}
	
	@Override
	public void removeFlight(Long id) {
		flightDb.deleteById(id);
	}
}
