package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.Well19937a;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.model.AnimalAlert;
import com.example.demo.model.AnimalAlertFired;
import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdAlert;
import com.example.demo.model.HerdAlertFired;
import com.example.demo.model.HerdInfo;
import com.example.demo.repository.AnimalAlertFiredRepository;
import com.example.demo.repository.AnimalAlertRepository;
import com.example.demo.repository.CowBcsRepository;
import com.example.demo.repository.CowRepository;
import com.example.demo.repository.HerdAlertFiredRepository;
import com.example.demo.repository.HerdAlertRepository;
import com.example.demo.repository.HerdRepository;

@EnableScheduling
@Service
public class RestServiceImp implements RestService {

	@Autowired
	HerdRepository herdRepository;

	@Autowired
	CowRepository cowRepository;

	@Autowired
	CowBcsRepository cbsCowRepository;

	@Autowired
	AnimalAlertRepository animalAlertRepository;

	@Autowired
	HerdAlertRepository herdAlertRepository;

	@Autowired
	HerdAlertFiredRepository herdAlertFiredRepository;

	@Autowired
	AnimalAlertFiredRepository animalAlertFiredRepository;
	
	private static boolean sessionEnabled = false;

	// Devuelve la informacion de un herd, que incluye la informacion de cada una de
	// sus vacas
	@Override
	public HerdInfo findInfoHerdById(long id) {

		float prom = 0;
		int divisor = 0;

		HerdInfo he = new HerdInfo();
		he.setId(herdRepository.findById(id).getId());
		he.setLocation(herdRepository.findById(id).getLocation());

		List<AnimalInfo> ai = new ArrayList<AnimalInfo>();
		List<Cow> cows = cowRepository.findAllByherd(herdRepository.findById(id));
		for (Cow cow : cows) {
			AnimalInfo aiAux = findInfoCowById(cow.getId());

			if (aiAux.getFechaBcs() != null) {
				prom += aiAux.getCc();
				divisor++;
			}

			ai.add(aiAux);
		}

		if (prom > 0)
			prom = prom / divisor;

		he.setBcsPromedio(prom);
		he.addAnimalsInfo(ai);

		return he;
	}

	// Devuelve toda la informacion de un animal
	@Override
	public AnimalInfo findInfoCowById(long id) {

		Cow cow = cowRepository.findById(id);

		AnimalInfo aiAux = new AnimalInfo();
		aiAux.setId(cow.getId());
		aiAux.setElectronicId(cow.getElectronicId());
		aiAux.setFechaNacimiento(cow.getFechaNacimiento());
		aiAux.setUltimaFechaParto(cow.getUltimaFechaParto());
		aiAux.setCantidadPartos(cow.getCantidadPartos());
		aiAux.setPeso(cow.getPeso());
		aiAux.setHerdId(cow.getHerdId());

		CowBcs cbrr = cbsCowRepository.findFirstBycowIdOrderByFechaDesc(cow);
		if (cbrr != null) {
			aiAux.setCc(cbrr.getCc());
			aiAux.setCowBcsId(cbrr.getId());
			aiAux.setFechaBcs(cbrr.getFecha());
		}

		return aiAux;
	}

	@Override
	public Herd herdSave(Herd h) {
		return herdRepository.save(h);
	}

	@Override
	public Cow cowSave(Cow c) {
		return cowRepository.save(c);
	}

	@Override
	public CowBcs cowBcsSave(CowBcs c) {

		AnimalAlert animalAlert = animalAlertRepository.findBycow(cowRepository.findById(c.getCowId()));

		if (animalAlert != null)
			if (animalAlert.getBcsThresholdMin() > c.getCc() || animalAlert.getBcsThresholdMax() < c.getCc())
				System.out.print("El animal se encuentra fuera de los limites deseados con un CC de " + c.getCc()
						+ ". Limites de la alerta:  (" + animalAlert.getBcsThresholdMin() + ", "
						+ animalAlert.getBcsThresholdMax() + ") \n");

		// debo cargar vaca antes de hacer control de alerta de rodeo
		CowBcs bcs = cbsCowRepository.save(c);

		HerdInfo hi = findInfoHerdById(cowRepository.findById(c.getCowId()).getHerdId());
		Herd h = herdRepository.findById(hi.getId());

		HerdAlert herdAlert = herdAlertRepository.findByherd(h);

		if (herdAlert != null)
			if (herdAlert.getBcsThresholdMin() > hi.getBcsPromedio()
					|| herdAlert.getBcsThresholdMax() < hi.getBcsPromedio())
				System.out.print("El herd del animal se encuentra fuera de los limites deseados con un CC de "
						+ hi.getBcsPromedio() + ". Limites de la alerta:  (" + herdAlert.getBcsThresholdMin() + ", "
						+ herdAlert.getBcsThresholdMax() + ") \n");

		return bcs;
	}

	@Override
	public Herd findHerdById(long id) {
		return herdRepository.findById(id);
	}

	@Override
	public Cow findCowById(long id) {
		return cowRepository.findById(id);
	}

	@Override
	public AnimalAlert animalAlertSave(AnimalAlert c) {
		AnimalAlert existente = animalAlertRepository.findByCowId(c.getAnimalId());
		if (existente != null)
			animalAlertRepository.delete(existente);
		return animalAlertRepository.save(c);
	}

	@Override
	public HerdAlert herdAlertSave(HerdAlert h) {
		HerdAlert existente = herdAlertRepository.findByherd(
				herdRepository.findById(h.getHerdId()));
		if (existente != null)
			herdAlertRepository.delete(existente);
		return herdAlertRepository.save(h);
	}

	private int[] numsToGenerate = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	double[] discreteProbabilities = new double[] { 0.02, 0.05, 0.15, 0.24, 0.28, 0.24, 0.15, 0.05, 0.02 };
	private EnumeratedIntegerDistribution distribution = new EnumeratedIntegerDistribution(new Well19937a(),
			numsToGenerate, discreteProbabilities);
	private RandomDataGenerator random = new RandomDataGenerator();

	@Scheduled(fixedDelay = 5000)
	public void scheduleFixedDelayTask() {

		if (!sessionEnabled) return;
			
		int count = (int) cowRepository.count();
		int cow_id = random.nextInt(1, count);
		int cc = distribution.sample();
		System.out.println("CC generada: " + cc + " para la vaca: " + cow_id);
		Date today = new Date();
		
		
		CowBcs bcs = new CowBcs();
		bcs.setCc(cc);
		Cow cow = cowRepository.findById(cow_id);
		bcs.setCowId(cow);
		bcs.setFecha(today);

		cbsCowRepository.save(bcs);

		AnimalAlert animalAlert = animalAlertRepository.findBycow(cowRepository.findById(cow.getId()));

		if (animalAlert != null) {
			if (animalAlert.getBcsThresholdMin() >= bcs.getCc() || animalAlert.getBcsThresholdMax() <= bcs.getCc()) {
				System.out.print("El animal se encuentra fuera de los limites deseados con un CC de " + bcs.getCc()
						+ ". Limites de la alerta:  (" + animalAlert.getBcsThresholdMin() + ", "
						+ animalAlert.getBcsThresholdMax() + ") \n");
				AnimalAlertFired aaf = new AnimalAlertFired();
				aaf.setCow(cow);
				aaf.setBcs_fired(cc);
				aaf.setFecha(today);
				animalAlertFiredRepository.save(aaf);
			}
		}

		HerdInfo hi = findInfoHerdById(cowRepository.findById(bcs.getCowId()).getHerdId());
		Herd h = herdRepository.findById(hi.getId());

		HerdAlert herdAlert = herdAlertRepository.findByherd(h);

		if (herdAlert != null) {
			if (herdAlert.getBcsThresholdMin() >= hi.getBcsPromedio()
					|| herdAlert.getBcsThresholdMax() <= hi.getBcsPromedio()) {
				System.out.print("El herd del animal se encuentra fuera de los limites deseados con un CC de "
						+ hi.getBcsPromedio() + ". Limites de la alerta:  (" + herdAlert.getBcsThresholdMin() + ", "
						+ herdAlert.getBcsThresholdMax() + ") \n");
				HerdAlertFired haf = new HerdAlertFired();
				haf.setHerd(h);
				haf.setBcs_fired(hi.getBcsPromedio());
				haf.setFecha(today);
				herdAlertFiredRepository.save(haf);
				
			}
		}

	}

	@Override
	public List<AnimalAlertFired> getCowsFiredAlert() {
		List<AnimalAlertFired> result = new ArrayList<>();
		Iterable<AnimalAlertFired> it = animalAlertFiredRepository.findAll();
		it.forEach(result::add);
		return result;
	}

	@Override
	public List<HerdAlertFired> getHerdsFiredAlert() {
		List<HerdAlertFired> result = new ArrayList<>();
		Iterable<HerdAlertFired> it = herdAlertFiredRepository.findAll();
		it.forEach(result::add);
		return result;
	}

	@Override
	public Boolean setSession(boolean enable) {
		sessionEnabled = enable;
		return sessionEnabled;
	}

}
