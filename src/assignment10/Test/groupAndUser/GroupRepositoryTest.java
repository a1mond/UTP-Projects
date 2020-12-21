package groupAndUser;

import assignment10.dtos.GroupDTO;
import assignment10.implementation.GroupRepository;
import assignment10.repositories.IGroupRepository;
import base.RepositoryTestBase;
import org.junit.Test;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	@Test
	public void add() {

	}

	@Test
	public void update() {
	}
	
	@Test
	public void addOrUpdate() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void findById() {
	}
	
	@Test
	public void findByName() {
	}

	protected IGroupRepository Create() {
		return new GroupRepository();
	}
}