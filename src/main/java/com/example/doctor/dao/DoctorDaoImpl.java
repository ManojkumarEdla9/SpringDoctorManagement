package com.example.doctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.doctor.modal.Doctor;

@Repository
public class DoctorDaoImpl implements DoctorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class DoctorMapper implements RowMapper<Doctor> {
        @Override
        public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt("id"));
            doctor.setName(rs.getString("name"));
            doctor.setSpecification(rs.getString("specialization"));
            doctor.setSalary(rs.getDouble("salary"));
            doctor.setDepartment(rs.getString("department"));
            return doctor;
        }
    }

    @Override
    public void save(Doctor doctor) throws Exception {
        String sql = "INSERT INTO doctors (id, name, specialization, salary, department) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctor.getId(), doctor.getName(), doctor.getSpecification(), doctor.getSalary(), doctor.getDepartment());
    }

    @Override
    public List<Doctor> findAll() throws Exception {
        String sql = "SELECT * FROM doctors";
        return jdbcTemplate.query(sql, new DoctorMapper());
    }

    @Override
    public Doctor findById(int id) throws Exception {
        String sql = "SELECT * FROM doctors WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DoctorMapper());
    }

    @Override
    public void update(Doctor doctor) throws Exception {
        String sql = "UPDATE doctors SET name=?, specialization=?, salary=?, department=? WHERE id=?";
        jdbcTemplate.update(sql, doctor.getName(), doctor.getSpecification(), doctor.getSalary(), doctor.getDepartment(), doctor.getId());
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM doctors WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
