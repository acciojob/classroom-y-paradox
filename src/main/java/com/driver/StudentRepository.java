package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String, Student> studentMap;
    HashMap<String, Teacher> teacherMap;
    HashMap<String, List<String>> studentTeacherMap;

    public StudentRepository() {
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.studentTeacherMap = new HashMap<>();
    }

    public void addStudent(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }


    public void addStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if(studentTeacherMap.containsKey(teacher)){
                List<String> studentList = studentTeacherMap.get(teacher);
                studentList.add(student);
                studentTeacherMap.put(teacher, studentList);
            }
            else{
                List<String> studentList = new ArrayList<>();
                studentList.add(student);
                studentTeacherMap.put(teacher, studentList);
            }
            teacherMap.get(teacher).setNumberOfStudents(studentTeacherMap.get(teacher).size());
        }
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> students = studentTeacherMap.get(teacher);
        return students;
    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        for(String student : studentMap.keySet()) {
            students.add(student);
        }
        return students;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> students = studentTeacherMap.get(teacher);
        for(String student : students){
            studentMap.remove(student);
        }
        studentTeacherMap.remove(teacher);
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String teacher : studentTeacherMap.keySet()){
            List<String> students = studentTeacherMap.get(teacher);
            for(String student : students){
                studentMap.remove(student);
            }
        }
        studentTeacherMap.clear();
        teacherMap.clear();
    }
}
