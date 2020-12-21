package assignment10.implementation;

import assignment10.ConnectorDB;
import assignment10.dtos.GroupDTO;
import assignment10.repositories.IGroupRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupRepository implements IGroupRepository {
    private static final String T_GROUP = "Groups",
                               C_ID = "gId",
                               C_NAME = "gName",
                               C_DESC = "gDesc";
    private Connection connection;
    private Savepoint savepoint;

    @Override
    public List<GroupDTO> findByName(String name) {
        try {
            List<GroupDTO> list = new LinkedList<>();
            String query = "SELECT * FROM " + T_GROUP +
                    " WHERE " + C_NAME + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new GroupDTO(
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

    public GroupRepository() {
        getConnection();
    }

    @Override
    public Connection getConnection() {
        if (connection == null)
            connection = ConnectorDB.connect();
        return connection;
    }

    @Override
    public void add(GroupDTO dto) {
        try {
            PreparedStatement statement;
            String query =
                    "INSERT INTO " + T_GROUP + "(" + C_ID + ", " + C_NAME + ", " + C_DESC + ")" +
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

    @Override
    public void update(GroupDTO dto) {
        try {
            PreparedStatement statement;
            String query =
                    "UPDATE " + T_GROUP + " SET " +
                    C_NAME + " = ? ," +
                    C_DESC + " = ? " +
                    "WHERE " + C_ID + " = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getDescription());
            statement.setInt(3, dto.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        if (exists(dto))
            update(dto);
        else
            add(dto);
    }

    @Override
    public void delete(GroupDTO dto) {
        try {
            String query =
                    "DELETE FROM " + T_GROUP +
                    " WHERE " + C_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupDTO findById(int id) {
        try {
            String query =
                    "SELECT * FROM " + T_GROUP +
                    " WHERE " + C_ID + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return new GroupDTO(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
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
            String query = "SELECT COUNT (*) FROM " + T_GROUP;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean exists(GroupDTO dto) {
        GroupDTO dto2 = findById(dto.getId());
        return dto2 != null;
    }
}
