package co.grandcircus.midterm;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Copy-paste into your project verbatim
public class FileLinesHelper {

   private Path filePath;

   public FileLinesHelper(Path filePath) {
      this.filePath = filePath;
   }

   public FileLinesHelper(String filePath) {
      this(Paths.get(filePath));
   }
   
   private void ensureFileExists() throws IOException {
      if ( Files.notExists(filePath) ) {
         Files.createFile(filePath);
      }
   }
   
   public List<String> readFile() {
      // ** Example of reading a file into a list
      
      try {
         ensureFileExists();
         return Files.readAllLines(filePath);
      } catch (FileNotFoundException ex) {
         return new ArrayList<>();
      } catch (IOException ex) {
         ex.printStackTrace();
         return new ArrayList<>();
      }
   }
   
   
}