package com.services;

import java.util.List;

import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;

public interface AvailableService {
	ZauzetostJedinice findOne(Long id);
	List<ZauzetostJedinice> findAll();
	ZauzetostJedinice save(ZauzetostJedinice t);
	void remove(Long id) throws IllegalArgumentException;
	void deleteAll();
}
