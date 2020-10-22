package com.company;


import java.util.ArrayList;
import java.util.List;

class Classroom{
    Teacher teacher;
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();

    Classroom(Teacher teacher){
        this.teacher = teacher;
    }

    public void addStudent(Student newStudent){
        students.add(newStudent);
        newStudent.joinClass(this);
    }

    public void newAssignment(String name, int maxScore){
        assignments.add(new Assignment(this,name,maxScore));
    }

}

class Assignment{
    Classroom classroom;
    String name;
    int maxScore;
    Grade grade;
    ArrayList<Grade> grades;

    Assignment(Classroom classroom, String name, int maxScore){
        this.classroom = classroom;
        this.maxScore = maxScore;
        this.name = name;
        grades = new ArrayList<>();

    }

    public void gradeAssignment(Student student, int score){
        grades.add(new Grade(student, score));
    }

    public Grade getGrade(Student student){
        for (Grade grade: grades) {
            if(grade.student == student){
                return grade;
            }
        }
        return null;
    }

    class Grade{
        Student student;
        int score;
        int maxScore = Assignment.this.maxScore;

        Grade(Student student, int score){
            this.student = student;
            this.score = score;
        }
    }
}

class Teacher{
    String name;
    ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    Teacher(String name){
        this.name = name;
    }
}

class Student {
    String name;
    ArrayList<Classroom> classrooms = new ArrayList<Classroom>();

    Student(String name){
        this.name = name;
    }

    public void joinClass(Classroom classroom){
        classrooms.add(classroom);
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here
        Teacher mrTeacher = new Teacher("Mr. Teacher");
        Student lazyboi = new Student("Lazy Boi");
        Classroom newClassroom = new Classroom(mrTeacher);
        newClassroom.addStudent(lazyboi);
    }
}


