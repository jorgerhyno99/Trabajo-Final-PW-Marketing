package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

public interface CrudBusiness<T,ID> {
	List<T> findAll() throws Exception;
	T save( T t ) throws Exception;
	T update( T t ) throws Exception;
	Optional<T> findById( ID id ) throws Exception;
	void deleteById( ID id ) throws Exception;
	void deleteAll() throws Exception;

}
