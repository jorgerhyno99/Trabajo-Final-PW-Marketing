package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.entity.Campania;
import pe.edu.upc.model.repository.CampaniaRepository;
import pe.edu.upc.service.CampaniaBusiness;

@Service
public class CampaniaBusinessImpl implements CampaniaBusiness {

	@Autowired
	private CampaniaRepository bRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Campania> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Campania save(Campania t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Campania update(Campania t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Campania> findById(Integer id) throws Exception {
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
