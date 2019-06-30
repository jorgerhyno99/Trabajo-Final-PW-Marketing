package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.Prioridad;
import pe.edu.upc.model.repository.PrioridadRepository;
import pe.edu.upc.service.PrioridadBusiness;

@Service
public class PrioridadBusinessImpl implements PrioridadBusiness {

	
	
	@Autowired
	private PrioridadRepository bRepository;
	
	@Override
	@Transactional
	public List<Prioridad> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Prioridad save(Prioridad t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Prioridad update(Prioridad t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<Prioridad> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		bRepository.deleteById(id);

	}

	@Override
	@Transactional
	public void deleteAll() throws Exception {
		bRepository.deleteAll();

	}


}
