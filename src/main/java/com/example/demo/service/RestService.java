package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnimalAlert;
import com.example.demo.model.AnimalAlertFired;
import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdAlert;
import com.example.demo.model.HerdAlertFired;
import com.example.demo.model.HerdInfo;


public interface RestService {

	HerdInfo findInfoHerdById(long id);
	
	Herd findHerdById(long id);
	
	AnimalInfo findInfoCowById(long id);
	
	Cow findCowById(long id);
	
	Herd herdSave(Herd h);
	
	Cow cowSave(Cow c);
	
	CowBcs cowBcsSave(CowBcs c);
	
	AnimalAlert animalAlertSave(AnimalAlert c);

	HerdAlert herdAlertSave(HerdAlert h);

	List<AnimalAlertFired> getCowsFiredAlert();

	List<HerdAlertFired> getHerdsFiredAlert();

	Boolean setSession(boolean enable);
	
}
