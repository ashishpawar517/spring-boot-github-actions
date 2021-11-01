package com.example.springapptest.database;

import com.example.springapptest.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
 class StudentRepository implements IStudentRepository{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String GET_COUNT_OF_STUDENTS = "SELECT COUNT(*) FROM STUDENT";
    private final static String INSERT_STUDENT = "Insert into Student values(:id,:name,:cgpa)";
    private final static String GET_STUDENT_BY_ID = "Select * from student where id=:id";

    public Optional<Integer> getCount() {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();

        Integer count;
        try {
            count = namedParameterJdbcTemplate
                .queryForObject(GET_COUNT_OF_STUDENTS, parameters, (rs, rowNum) -> convert(rs));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.of(count);
    }

    private Integer convert(ResultSet rs) throws SQLException {
        return rs.getInt("COUNT");
    }

    public Optional<Student> getStudent(Long studentId) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(Student.COLUMN_ID, studentId);

        Student student;
        try {
            student = namedParameterJdbcTemplate
                .queryForObject(GET_STUDENT_BY_ID, parameters, (rs, rowNum) -> translateStudent(rs));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.of(student);
    }

    private Student translateStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong(Student.COLUMN_ID));
        student.setName(rs.getString(Student.COLUMN_NAME));
        student.setCgpa(rs.getFloat(student.COLUMN_CGPA));
        return student;
    }

    public void insertStudent(Student student) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(Student.COLUMN_ID, student.getId());
        parameters.addValue(Student.COLUMN_NAME, student.getName());
        parameters.addValue(Student.COLUMN_CGPA, student.getCgpa());

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_STUDENT, parameters, holder);
        System.out.println(holder.getKeys().get(Student.COLUMN_ID));
    }
}
