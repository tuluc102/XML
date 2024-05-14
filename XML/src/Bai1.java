import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bai1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nhập đường dẫn thư mục: ");
    String directoryPath = scanner.nextLine();

    File directory = new File(directoryPath);
    if (!directory.exists() || !directory.isDirectory()) {
      System.out.println("Đường dẫn không hợp lệ hoặc không phải là một thư mục.");
      return;
    }

    String xmlString = generateXML(directory);
    System.out.println(xmlString);

    saveXMLToFile(xmlString, "directory_listing.xml");

    scanner.close();
  }

  private static String generateXML(File directory) {
    StringBuilder xmlBuilder = new StringBuilder();
    xmlBuilder.append("<").append(directory.getName()).append(">\n");
    generateXMLRecursive(xmlBuilder, directory, 1);
    xmlBuilder.append("</").append(directory.getName()).append(">");
    return xmlBuilder.toString();
  }

  private static void generateXMLRecursive(StringBuilder xmlBuilder, File directory, int depth) {
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        xmlBuilder.append("\t".repeat(depth)).append("<");
        if (file.isDirectory()) {
          xmlBuilder.append(file.getName()).append(">\n");
          generateXMLRecursive(xmlBuilder, file, depth + 1);
          xmlBuilder.append("\t".repeat(depth)).append("</").append(file.getName()).append(">\n");
        } else {
          xmlBuilder.append("file>").append(file.getName()).append("</file>\n");
        }
      }
    }
  }

  private static void saveXMLToFile(String xmlString, String fileName) {
    try {
      FileWriter writer = new FileWriter(fileName);
      writer.write(xmlString);
      writer.close();
      System.out.println("Đã lưu thành công cây thư mục dưới dạng tệp XML: " + fileName);
    } catch (IOException e) {
      System.out.println("Đã xảy ra lỗi khi lưu tệp XML.");
      e.printStackTrace();
    }
  }
}
