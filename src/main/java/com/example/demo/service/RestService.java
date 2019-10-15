package com.example.demo.service;

import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdInfo;


public interface RestService {

	HerdInfo findHerdById(long id);
	AnimalInfo findCowById(long id);
	Herd herdSave(Herd h);
	Cow cowSave(Cow c);
	CowBcs cowBcsSave(CowBcs c);
}
