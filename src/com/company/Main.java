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

    public Assignment newAssignment(String name, int maxScore){
        Assignment assignment = new Assignment(this,name,maxScore);
        assignments.add(assignment);
        return assignment;
    }

}

class Assignment{
    Classroom classroom;
    String name;
    float maxScore;
    ArrayList<Grade> grades;

    Assignment(Classroom classroom, String name, float maxScore){
        this.classroom = classroom;
        this.maxScore = maxScore;
        this.name = name;
        grades = new ArrayList<>();

    }

    public String toString(){
        return this.name;
    }

    public String toTable(){
        StringBuilder theString = new StringBuilder();
        theString.append("Grades for assignment: ").append(this.name).append("\n-----------------------------------\n");
        for (Grade grade: grades) {
            theString.append(grade.toString()).append("\n");
        }
        return theString.toString();
    }

    public void gradeAssignment(Student student, float score){
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

    private class Grade{
        Student student;
        float score;
        float maxScore = Assignment.this.maxScore;

        Grade(Student student, float score){
            this.student = student;
            this.score = score;
        }

        public float getPercentage(){
            return this.score/this.maxScore;
        }

        private String getLetter(){
            String gradeValue;
            if(this.getPercentage() > 95){
                gradeValue = "A";
            }else if(this.getPercentage() > 90) {
                gradeValue = "A-";
            }else if(this.getPercentage() > 86.5) {
                gradeValue = "B+";
            }else if(this.getPercentage() > 84.5) {
                gradeValue = "B";
            }else if(this.getPercentage() > 80) {
                gradeValue = "B-";
            }else if(this.getPercentage() > 76.5) { // C
                gradeValue = "C+";
            }else if(this.getPercentage() > 74.5) {
                gradeValue = "C";
            }else if(this.getPercentage() > 70) {
                gradeValue = "C-";
            }else if(this.getPercentage() > 66.5) {
                gradeValue = "D+";
            }else if(this.getPercentage() > 64.5) {
                gradeValue = "D";
            }else if(this.getPercentage() > 60) {
                gradeValue = "D-";
            }else{
                gradeValue = "E";
            }
            return gradeValue;
        }// Don't ever un collapse this

        public String toString(){
            return "Student: \"" + this.student + "\" Grade: " + this.getLetter();
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

    public String toString(){
        return name;
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here
        Teacher mrTeacher = new Teacher("Mr. Teacher");
        Classroom newClassroom = new Classroom(mrTeacher);
        for (int i = 0; i < 50; i++) {
            Student newStudent = new Student("Lazy Boi# " + i);
            newClassroom.addStudent(newStudent);
        }
        Assignment newAssignment = newClassroom.newAssignment("Test Assignment",100);
        for (Student student: newAssignment.classroom.students) {
            newAssignment.gradeAssignment(student, 67);
        }
        System.out.println(newAssignment.toTable());
    }
}


