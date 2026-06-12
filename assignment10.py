class Address:
    def __init__(self, street, city, zipCode):
        self.street = street
        self.city = city
        self.zipCode = zipCode

    def __str__(self):
        return f"{self.street}, {self.city} - {self.zipCode}"


class Student:
    def __init__(self, name, age, address):
        self.name = name
        self._age = None  # protected attribute
        self.age = age    # setter will validate
        self.address = address  # Composition (HAS-A relationship)
        self.courses = []  # mutable list

    # Property for age with validation
    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value):
        if not isinstance(value, int) or value <= 0:
            raise ValueError("Age must be a positive integer")
        self._age = value

    # Method to add course
    def add_course(self, course):
        self.courses.append(course)

    # Display method
    def display(self):
        print(f"Name: {self.name}")
        print(f"Age: {self.age}")
        print(f"Address: {self.address}")
        print(f"Courses: {', '.join(self.courses) if self.courses else 'None'}")



class ScholarshipStudent(Student):
    def __init__(self, name, age, address, scholarshipAmount):
        super().__init__(name, age, address)
        self.scholarshipAmount = scholarshipAmount


    def display(self):
        super().display()  # call parent method
        print(f"Scholarship Amount: {self.scholarshipAmount}")


# -------------------- Testing --------------------

# Create Address object
addr = Address("MG Road", "Delhi", "110001")

# Create Student object
student = Student("Rupam", 20, addr)
student.add_course("Math")
student.add_course("Python")

print("Student Details:")
student.display()

print("\nAfter modifying course list (mutable behavior):")
student.add_course("Data Structures")
student.display()

# Create ScholarshipStudent object
sch_student = ScholarshipStudent("Aman", 22, addr, 5000)
sch_student.add_course("AI")
sch_student.add_course("ML")

print("\nScholarship Student Details:")
sch_student.display()
