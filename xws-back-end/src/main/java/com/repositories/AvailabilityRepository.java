package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Agent;
import com.model.ZauzetostJedinice;

@Repository
@Transactional
public interface AvailabilityRepository extends JpaRepository<ZauzetostJedinice, Long> {

}
