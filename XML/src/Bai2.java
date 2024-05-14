import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.*;

public class Bai2 {
  public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    students.add(new Student("Trieu", 19, 3.5));
    students.add(new Student("Trong", 19, 3.2));
    students.add(new Student("Thanh", 19, 3.8));

    try {
      XMLOutputFactory factory = XMLOutputFactory.newInstance();
      XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("student_list.xml"));

      writer.writeStartDocument("1.0");
      writer.writeCharacters("\n");
      writer.writeStartElement("students");
      writer.writeCharacters("\n  ");

      for (Student student : students) {
        writer.writeStartElement("student");
        writer.writeAttribute("name", student.getName());
        writer.writeAttribute("age", Integer.toString(student.getAge()));
        writer.writeAttribute("gpa", Double.toString(student.getGpa()));
        writer.writeEndElement();
        writer.writeCharacters("\n  ");
      }

      writer.writeCharacters("\n");
      writer.writeEndElement();
      writer.writeEndDocument();

      writer.flush();
      writer.close();
      System.out.println("Student list saved as student_list.xml");
    } catch (XMLStreamException | IOException e) {
      e.printStackTrace();
    }
  }

  static class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
      this.name = name;
      this.age = age;
      this.gpa = gpa;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    public double getGpa() {
      return gpa;
    }
  }
}
