package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.entity.Control;
import pe.edu.upc.model.repository.ControlRepository;
import pe.edu.upc.service.ControlBusiness;

@Service
public class ControlBusinessImpl implements ControlBusiness{

	
	@Autowired
	private ControlRepository bRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Control> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Control save(Control t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Control update(Control t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Control> findById(Integer id) throws Exception {
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
