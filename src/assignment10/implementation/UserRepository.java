package assignment10.implementation;

import assignment10.ConnectorDB;
import assignment10.dtos.GroupDTO;
import assignment10.dtos.UserDTO;
import assignment10.repositories.IUserRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String T_USERS = "Users",
                                T_USERGROUP = "UserGroup",
                                G_ID = "gId",
                                C_ID = "uId",
                                C_LOGIN = "uLogin",
                                C_PASSWORD = "uPassword";

    private Connection connection;
    private Savepoint savepoint;

    public UserRepository() {
        getConnection();
    }

    @Override
    public Connection getConnection() {
        if (connection == null)
            connection = ConnectorDB.connect();
        return connection;
    }

    @Override
    public void add(UserDTO dto) {
        try {
            PreparedStatement statement;
            String query =
                    "INSERT INTO " + T_USERS + "(" + C_ID + ", " + C_LOGIN + ", " + C_PASSWORD + ")" +
                    "VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        try {
            PreparedStatement statement;
            String query =
                    "UPDATE " + T_USERS + " SET " +
                    C_LOGIN + " = ? ," +
                    C_PASSWORD + " = ? " +
                    "WHERE " + C_ID + " = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, dto.getLogin());
            statement.setString(2, dto.getPassword());
            statement.setInt(3, dto.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        if (exists(dto))
            update(dto);
        else
            add(dto);
    }

    @Override
    public void delete(UserDTO dto) {
        try {
            String query = "DELETE FROM " + T_USERS +
                    " WHERE " + C_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        try {
            String query = "SELECT * FROM " + T_USERS +
                    " WHERE " + C_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new UserDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            savepoint = getConnection().setSavepoint();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public int getCount() {
        try {
            String query = "SELECT COUNT (*) FROM " + T_USERS;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            int result = 0;
            while (resultSet.next())
                result = resultSet.getInt(1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean exists(UserDTO dto) {
        UserDTO dto2 = findById(dto.getId());
        return dto2 != null;
    }

    @Override
    public List<UserDTO> findByName(String username) {
        try {
            List<UserDTO> list = new LinkedList<>();
            String query = "SELECT * FROM " + T_USERS +
                    " WHERE " + C_LOGIN + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new UserDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addToUserGroup(UserDTO udto, GroupDTO gdto) {
        try {
            addToGroup(gdto);
            PreparedStatement statement;
            String query = "INSERT INTO " + T_USERGROUP + "(" + C_ID + ", " + G_ID + ")" +
                    "VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, udto.getId());
            statement.setInt(2, gdto.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void addToGroup(GroupDTO dto) {
        try {
            PreparedStatement statement;
            String query = "INSERT INTO GROUPS (gId, gName, gDesc)" +
                    "VALUES (?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
