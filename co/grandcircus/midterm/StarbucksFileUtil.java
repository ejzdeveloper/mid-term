package co.grandcircus.midterm;
import java.util.ArrayList;
import java.util.List;



public class StarbucksFileUtil {

   private static FileLinesHelper linesHelper = new FileLinesHelper("src/menuOptions.txt");

    //convert a line of text from the file to a new item instance
   private static Menu convertLineToItem(String line) {
      String[] parts = line.split(", ");
      Menu item = new Menu();
      item.setName(parts[0]);
      item.setPrice(Double.parseDouble(parts[1]));
      return item;
   }

   public static List<Menu> readFile() {
      List<String> lines = linesHelper.readFile();
      List<Menu> items = new ArrayList<>(lines.size());
      for (String line : lines) {
         items.add(convertLineToItem(line));
      }
      return items;
   }
   
}//end classs