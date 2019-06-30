package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.Equipo;
import pe.edu.upc.model.repository.EquipoRepository;
import pe.edu.upc.service.EquipoBusiness;

@Service
public class EquipoBusinessImpl  implements EquipoBusiness{

	
	
	@Autowired
	private EquipoRepository bRepository;
	
	@Override
	@Transactional
	public List<Equipo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Equipo save(Equipo t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Equipo update(Equipo t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<Equipo> findById(Integer id) throws Exception {
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
