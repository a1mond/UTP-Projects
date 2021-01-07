package assignment10.groupAndUser;

import assignment10.base.RepositoryTestBase;
import assignment10.dtos.GroupDTO;
import assignment10.implementation.GroupRepository;
import assignment10.repositories.IGroupRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	@Test
	public void add() {
		GroupDTO gdto = new GroupDTO(1, "16c", "best group ever");
		get_repository().add(gdto);

		Assert.assertEquals(1, get_repository().getCount());
		Assert.assertTrue(get_repository().exists(gdto));

		Assert.assertEquals(gdto, get_repository().findById(1));
	}

	@Test
	public void update() {
		GroupDTO gdto = new GroupDTO(1, "16c", "just a group");
		GroupDTO gdtou = new GroupDTO(1, "16c", "best group");
		get_repository().add(gdto);

		get_repository().update(gdtou);
		Assert.assertTrue(get_repository().exists(gdtou));
		Assert.assertEquals(gdtou, get_repository().findById(1));
	}
	
	@Test
	public void addOrUpdate() {
		GroupDTO gdtoADD = new GroupDTO(1, "16c", "best group ever");
		GroupDTO gdtoUPD = new GroupDTO(1, "15c", "group");
		get_repository().addOrUpdate(gdtoADD);
		Assert.assertTrue(get_repository().exists(gdtoADD));
		get_repository().addOrUpdate(gdtoUPD);
		Assert.assertEquals(gdtoUPD, get_repository().findById(1));
	}

	@Test
	public void delete() {
		GroupDTO gdto = new GroupDTO(1, "16c", "best group ever");
		get_repository().add(gdto);
		Assert.assertTrue(get_repository().exists(gdto));
		get_repository().delete(gdto);
		Assert.assertFalse(get_repository().exists(gdto));
	}

	@Test
	public void findById() {
		GroupDTO gdto = new GroupDTO(1, "16c", "best group ever");
		get_repository().add(gdto);
		Assert.assertTrue(get_repository().exists(gdto));
		Assert.assertEquals(gdto, get_repository().findById(1));
	}
	
	@Test
	public void findByName() {
		GroupDTO gdto1 = new GroupDTO(1, "16c", "best group ever");
		GroupDTO gdto2 = new GroupDTO(2, "16c", "just a group");

		get_repository().add(gdto1);
		get_repository().add(gdto2);
		Assert.assertEquals(2, get_repository().findByName("16c").size());
		Assert.assertEquals(List.of(gdto1, gdto2), get_repository().findByName("16c"));
	}

	protected IGroupRepository Create() {
		return new GroupRepository();
	}
}