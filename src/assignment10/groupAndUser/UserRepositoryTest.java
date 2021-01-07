package assignment10.groupAndUser;

import assignment10.base.RepositoryTestBase;
import assignment10.dtos.GroupDTO;
import assignment10.dtos.UserDTO;
import assignment10.implementation.UserRepository;
import assignment10.repositories.IUserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		UserDTO udto = new UserDTO(1, "a1mond", "123123");
		get_repository().add(udto);
		Assert.assertTrue(get_repository().exists(udto));
		get_repository().delete(udto);
		Assert.assertFalse(get_repository().exists(udto));
	}

	@Test
	public void findById() {
		UserDTO udto = new UserDTO(1, "a1mond", "123123");
		get_repository().add(udto);
		Assert.assertTrue(get_repository().exists(udto));
		Assert.assertEquals(udto, get_repository().findById(1));
	}
	
	@Test
	public void findByName() {
		UserDTO udto1 = new UserDTO(1, "a1mond", "123123");
		UserDTO udto2 = new UserDTO(2, "a1mond", "312312");

		get_repository().add(udto1);
		get_repository().add(udto2);
		Assert.assertEquals(2, get_repository().findByName("a1mond").size());
		Assert.assertEquals(List.of(udto1, udto2), get_repository().findByName("a1mond"));
	}

	@Test
	public void addToUserGroup() {
		UserDTO udto = new UserDTO(10, "a1mond", "123123");
		GroupDTO gdto = new GroupDTO(1, "16c", "best group ever");

		get_repository().add(udto);
		get_repository().addToUserGroup(udto, gdto);

		try {
			PreparedStatement statement = get_repository().getConnection()
					.prepareStatement("SELECT * FROM UserGroup WHERE uId = ?");
			statement.setInt(1, udto.getId());
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			Assert.assertEquals(10, resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected IUserRepository Create() {
		return new UserRepository();
	}
}