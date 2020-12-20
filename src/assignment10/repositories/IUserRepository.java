package assignment10.repositories;

import assignment10.dtos.UserDTO;

import java.util.List;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByName(String username);
}