package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.model.repository.PlandemarketingRepository;
import pe.edu.upc.service.PlandemarketingBusiness;

@Service
public class PlandemarketingBusinessImp implements PlandemarketingBusiness {

	
	@Autowired
	private PlandemarketingRepository bRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PlanDeMarketing> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public PlanDeMarketing save(PlanDeMarketing t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public PlanDeMarketing update(PlanDeMarketing t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PlanDeMarketing> findById(Integer id) throws Exception {
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
