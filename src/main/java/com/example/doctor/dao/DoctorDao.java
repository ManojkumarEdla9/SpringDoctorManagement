package com.example.doctor.dao;

import java.util.List;

import com.example.doctor.modal.Doctor;

public interface DoctorDao {
void save(Doctor doctor)throws Exception;
List<Doctor>findAll()throws Exception;
Doctor findById(int id)throws Exception;
void update (Doctor doctor)throws Exception;
void delete (int id)throws Exception;
}
