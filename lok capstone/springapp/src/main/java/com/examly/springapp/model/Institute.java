package com.examly.springapp.model;
 
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Institute {
 
    @Id
    @GeneratedValue
    private long instituteId;
 
    private String instituteName;
    private String instituteDescription;
    private String instituteAddress;
    private String mobile;
    private String email;
    private long noOfCoursesAvailable;

    @OneToMany(mappedBy = "institute")
    private List<Course> courses = new ArrayList<>();

    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }
    public void addCourse(Course course) {
        this.courses.add(course);
        this.noOfCoursesAvailable++;
    }
 
    public void removeCourse(Course course) {
        this.courses.remove(course);
        this.noOfCoursesAvailable--;
    }

}
