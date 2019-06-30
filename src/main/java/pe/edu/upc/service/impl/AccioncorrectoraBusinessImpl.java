package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.AccionCorrectora;
import pe.edu.upc.model.repository.AccioncorrectoraRepository;
import pe.edu.upc.service.AccioncorrectoraBusiness;

@Service
public class AccioncorrectoraBusinessImpl implements AccioncorrectoraBusiness {

	
	
	@Autowired
	private AccioncorrectoraRepository bRepository;
	
	
	@Override
	@Transactional
	public List<AccionCorrectora> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public AccionCorrectora save(AccionCorrectora t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public AccionCorrectora update(AccionCorrectora t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<AccionCorrectora> findById(Integer id) throws Exception {
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
