package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.entity.Fase;
import pe.edu.upc.model.repository.FaseRepository;
import pe.edu.upc.service.FaseBusiness;

@Service
public class FaseBusinessImpl implements FaseBusiness{


	@Autowired
	private FaseRepository bRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Fase> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Fase save(Fase t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Fase update(Fase t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Fase> findById(Integer id) throws Exception {
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
