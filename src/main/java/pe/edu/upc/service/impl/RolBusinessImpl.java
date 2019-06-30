package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.Rol;
import pe.edu.upc.model.repository.RolRepository;
import pe.edu.upc.service.RolBusiness;

@Service
public class RolBusinessImpl implements RolBusiness{


	@Autowired
	private RolRepository bRepository;
	
	@Override
	@Transactional
	public List<Rol> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Rol save(Rol t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Rol update(Rol t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<Rol> findById(Integer id) throws Exception {
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
