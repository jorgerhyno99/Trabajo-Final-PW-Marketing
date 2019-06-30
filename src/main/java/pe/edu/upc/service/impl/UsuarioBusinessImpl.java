package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.entity.Usuario;
import pe.edu.upc.model.repository.UsuarioRepository;
import pe.edu.upc.service.UsuarioBusiness;

@Service
public class UsuarioBusinessImpl implements UsuarioBusiness  {

	@Autowired
	private UsuarioRepository bRepository;

	@Override
	@Transactional
	public List<Usuario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return bRepository.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Usuario update(Usuario t) throws Exception {
		
		return bRepository.save(t);
	}

	@Override
	@Transactional
	public Optional<Usuario> findById(Integer id) throws Exception {
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
