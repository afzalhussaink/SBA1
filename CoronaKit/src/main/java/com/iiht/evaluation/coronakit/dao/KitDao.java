package com.iiht.evaluation.coronakit.dao;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaKitException;
import com.iiht.evaluation.coronakit.model.CoronaKit;

public interface KitDao {
	CoronaKit add(CoronaKit coronaKit) throws CoronaKitException;
	List<CoronaKit> getAll() throws CoronaKitException;
}
