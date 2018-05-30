package com.services;

import java.util.List;

import com.model.Agent;
import com.model.ZauzetostJedinice;

public interface AvailabilityService {
	ZauzetostJedinice findOne(Long id);
	List<ZauzetostJedinice> findAll();
	ZauzetostJedinice save(ZauzetostJedinice t);
	void remove(Long id) throws IllegalArgumentException;
}
