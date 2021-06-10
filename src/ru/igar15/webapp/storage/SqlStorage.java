package ru.igar15.webapp.storage;

import ru.igar15.webapp.exception.ExistInStorageException;
import ru.igar15.webapp.exception.NotExistInStorageException;
import ru.igar15.webapp.exception.StorageException;
import ru.igar15.webapp.model.Resume;
import ru.igar15.webapp.sql.ConnectionFactory;
import ru.igar15.webapp.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        get(resume.getUuid());
        sqlHelper.execute("UPDATE resume r set full_name =? WHERE r.uuid =?", resume.getFullName(), resume.getUuid());
    }

    @Override
    public Resume get(String uuid) {
        try {
            ResultSet rs = sqlHelper.executeQuery("SELECT * FROM resume r WHERE r.uuid =?", uuid);
            if (!rs.next()) {
                throw new NotExistInStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        get(uuid);
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid =?", uuid);
    }

    @Override
    public Resume[] getAll() {
        try {
            ResultSet rs = sqlHelper.executeQuery("SELECT * FROM resume");
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                String fullName = rs.getString("full_name");
                resumes.add(new Resume(uuid, fullName));
            }
            return resumes.toArray(new Resume[0]);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        Resume exist = null;
        try {
            exist = get(resume.getUuid());
        } catch (NotExistInStorageException e) {
            // all is good
        } finally {
            if (exist != null) {
                throw new ExistInStorageException(resume.getUuid());
            }
            sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", resume.getUuid(), resume.getFullName());
        }
    }

    @Override
    public int size() {
        try {
            ResultSet resultSet = sqlHelper.executeQuery("SELECT COUNT(*) FROM resume");
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
