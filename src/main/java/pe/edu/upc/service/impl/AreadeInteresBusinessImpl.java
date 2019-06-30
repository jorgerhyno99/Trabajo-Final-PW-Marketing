package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.AreaDeInteres;
import pe.edu.upc.model.repository.AreadeInteresRepository;
import pe.edu.upc.service.AreadeInteresBusiness;

@Service
public class AreadeInteresBusinessImpl implements AreadeInteresBusiness{

	
	@Autowired
	private AreadeInteresRepository bRepository;
	
	
	@Override
	@Transactional
	public List<AreaDeInteres> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public AreaDeInteres save(AreaDeInteres t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public AreaDeInteres update(AreaDeInteres t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<AreaDeInteres> findById(Integer id) throws Exception {
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
