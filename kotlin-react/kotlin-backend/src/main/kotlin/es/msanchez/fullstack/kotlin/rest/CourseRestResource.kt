package es.msanchez.fullstack.kotlin.rest

import es.msanchez.fullstack.kotlin.entity.Course
import es.msanchez.fullstack.kotlin.service.CourseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("courses")
class CourseRestResource(private val courseService: CourseService) {

    init {
        this.courseService.initializeTestData()
    }

    @GetMapping
    fun getAllCourses(): MutableIterable<Course> {
        return this.courseService.findAll()
    }

    @GetMapping("instructors/{username}")
    fun getCoursesByUsername(@PathVariable username: String): MutableIterable<Course> {
        return this.courseService.findAllByUsername(username)
    }

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable id: Long) {
        this.courseService.deleteOne(id)
    }

}