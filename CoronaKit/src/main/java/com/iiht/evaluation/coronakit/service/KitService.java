package com.iiht.evaluation.coronakit.service;

import java.util.List;

import com.iiht.evaluation.coronakit.exception.CoronaKitException;
import com.iiht.evaluation.coronakit.model.CoronaKit;

public interface KitService {
	CoronaKit validateAndAdd(CoronaKit coronaKit) throws CoronaKitException;
	List<CoronaKit> getAllOrders() throws CoronaKitException;
}
