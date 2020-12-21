package base;

import assignment10.dtos.DTOBase;
import assignment10.repositories.IRepository;
import org.junit.After;
import org.junit.Before;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {

	private TRepository _repository;

	@Before
	public void before() {
		_repository = Create();
		if (_repository != null) {
			_repository.beginTransaction();
		}
	}

	@After
	public void after() {
		if (_repository != null) {
			_repository.rollbackTransaction();
		}
	}

	public TRepository get_repository() {
		return _repository;
	}

	public void addToRepository(TDTO tdto) {
		_repository.add(tdto);
	}

	protected abstract TRepository Create();
}