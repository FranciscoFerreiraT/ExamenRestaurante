import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
  
  private static final String ENTRANTES_FILE = "entrantes.txt";

  private static final String PRIMEROS_FILE = "primeros.txt";

  private static final String SEGUNDOS_FILE = "segundos.txt";

  private static final String POSTRES_FILE = "postres.txt";

  private static final String MENU_FILE = "menu.txt";
  
  private static String[] entrantes;

  private static String[] primeros;

  private static String[] segundos;

  private static String[] postres;

  private static String[] menu;
  
  private static Scanner scanner = new Scanner(System.in);

  private static Random random = new Random();
  
  public static void main(String[] args) {
    
    loadFiles();
    boolean exit = false;
    
    while (!exit) {
      System.out.println("\n--- Restaurante ---");
      System.out.println("1. Visualizar menú actual");
      System.out.println("2. Generar nuevo menú");
      System.out.println("3. Salir");
      System.out.print("Seleccione una opción: ");
      
      String option = scanner.nextLine();
      
      switch (option) {
        case "1":
          showMenu();
          break;
        case "2":
          generateMenu();
          break;
        case "3":
          exit = true;
          System.out.println("¡Hasta pronto!");
          break;
        default:
          System.out.println("Opción inválida");
          break;
      }
    }
  }
  
  private static void loadFiles() {
    try {
      entrantes = loadMenu(ENTRANTES_FILE);
      primeros = loadMenu(PRIMEROS_FILE);
      segundos = loadMenu(SEGUNDOS_FILE);
      postres = loadMenu(POSTRES_FILE);
    } catch (IOException e) {
      System.out.println("Error al cargar los archivos");
    }
  }
  
  private static String[] loadMenu(String fileName) throws IOException {
    File file = new File(fileName);
    Scanner fileScanner = new Scanner(file);
    String[] menu = new String[0];
    while (fileScanner.hasNextLine()) {
      String item = fileScanner.nextLine();
      menu = addMenuItem(menu, item);
    }
    fileScanner.close();
    return menu;
  }
  
  private static String[] addMenuItem(String[] menu, String item) {
    String[] newMenu = new String[menu.length + 1];
    for (int i = 0; i < menu.length; i++) {
      newMenu[i] = menu[i];
    }
    newMenu[menu.length] = item;
    return newMenu;
  }
  
  private static void showMenu() {
    if (menu == null) {
      System.out.println("No hay un menú guardado");
    } else {
      System.out.println("--- Menú actual ---");
      for (int i = 0; i < menu.length; i++) {
        System.out.println(menu[i]);
      }
    }
  }
  
  private static void generateMenu() {
    if (menu != null) {
      System.out.print("Ya hay un menú guardado. ¿Desea sobreescribirlo? (S/N): ");
      String overwrite = scanner.nextLine();
      if (!overwrite.equalsIgnoreCase("S")) {
        return;
      }
    }
}
    
}