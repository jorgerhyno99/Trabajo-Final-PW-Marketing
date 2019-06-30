package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.Estrategia;
import pe.edu.upc.model.repository.EstrategiaRepository;
import pe.edu.upc.service.EstrategiaBusiness;

@Service
public class EstrategiaBusinessImpl  implements EstrategiaBusiness{

	
	
	@Autowired
	private EstrategiaRepository bRepository;
	
	@Override
	@Transactional
	public List<Estrategia> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Estrategia save(Estrategia t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Estrategia update(Estrategia t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<Estrategia> findById(Integer id) throws Exception {
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
