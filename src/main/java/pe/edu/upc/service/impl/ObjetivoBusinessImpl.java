package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.entity.Objetivo;
import pe.edu.upc.model.repository.ObjetivoRepository;
import pe.edu.upc.service.ObjetivoBusiness;

@Service
public class ObjetivoBusinessImpl implements ObjetivoBusiness{


	@Autowired
	private ObjetivoRepository bRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Objetivo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Objetivo save(Objetivo t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Objetivo update(Objetivo t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Objetivo> findById(Integer id) throws Exception {
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
