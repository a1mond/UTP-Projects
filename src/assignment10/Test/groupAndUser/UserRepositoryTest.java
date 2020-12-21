package groupAndUser;

import assignment10.dtos.UserDTO;
import assignment10.implementation.UserRepository;
import assignment10.repositories.IUserRepository;
import base.RepositoryTestBase;
import org.junit.Assert;
import org.junit.Test;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

	@Test
	public void add() {
		UserDTO udto = new UserDTO(1, "a1mond", "123123");
		get_repository().add(udto);

		Assert.assertEquals(1, get_repository().getCount());
		Assert.assertTrue(get_repository().exists(udto));

		Assert.assertEquals(udto, get_repository().findById(1));
	}

	@Test
	public void update() {
		UserDTO udto = new UserDTO(1, "a1mond", "123123");
		UserDTO udto2 = new UserDTO(1, "nie a1mond", "312312");
		get_repository().add(udto);

		get_repository().update(udto2);
		Assert.assertTrue(get_repository().exists(udto2));
		Assert.assertEquals(udto2, get_repository().findById(1));
	}
	
	@Test
	public void addOrUpdate() {
		UserDTO udtoADD = new UserDTO(1, "a1mond", "123123");
		UserDTO udtoUPD = new UserDTO(1, "nie a1mond", "312312");
		get_repository().addOrUpdate(udtoADD);
		Assert.assertTrue(get_repository().exists(udtoADD));
		get_repository().addOrUpdate(udtoUPD);
		Assert.assertEquals(udtoUPD, get_repository().findById(1));
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
	
	@Override
	protected IUserRepository Create() {
		return new UserRepository();
	}
}