import java.io.*;
import java.util.*;
import java.time.LocalTime;

public class finals {

    public static void main(String[] args) {
       login();
    }

    static void dashboard(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("");
            viewActiveItems();
            System.out.println("");
            System.out.println("");
            System.out.println("======================================================================================");
            System.out.println("|                               Inventory Management System                          |");
            System.out.println("======================================================================================");
            System.out.println("| 1. Add Item                                                                        |");
            System.out.println("======================================================================================");
            System.out.println("| 2. Search Item                                                                     |");
            System.out.println("======================================================================================");
            System.out.println("| 3. Update Item                                                                     |");
            System.out.println("======================================================================================");
            System.out.println("| 4. Delete Item                                                                     |");
            System.out.println("======================================================================================");
            System.out.println("| 5. Restore Deleted Item                                                            |");
            System.out.println("======================================================================================");
            System.out.println("| 6. Logout                                                                          |");
            System.out.println("======================================================================================");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    searchItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    restoreDeletedItems();
                    break;
                case 6:
                    while (true) {
                        System.out.print("Are you sure you want Logout? (yes/no)  : ");  
                        String yn = scanner.nextLine();

                        if(yn.equalsIgnoreCase("yes")){
                            login();
                        } else if(yn.equalsIgnoreCase("no")){
                            dashboard();
                        }else{
                            System.out.println("Invalid input");
                            continue;
                        }
                    }  
                  
                default:
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }
        }
    }

    static void login(){
        LocalTime time = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        String name_of_admin = "";
        System.out.println();
        System.out.println("======================================================================================");
        System.out.println("|                                        Login                                       |");
        System.out.println("======================================================================================");
        System.out.println();
        while(true){
            try{
                System.out.print("Enter username: ");
                String uname = scanner.nextLine();
                System.out.print("Enter password: ");
                String pword = scanner.nextLine();
                boolean isFound = false;            
                        Scanner adminScan = new Scanner(new File("admin.txt"));
                            while(adminScan.hasNextLine()){
                                String data = adminScan.nextLine();
                                String[] info = data.split(",");
                                if(uname.equals(info[0]) && pword.equals(info[1])){
                                    name_of_admin = info[2];
                                    isFound = true;
                                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                                        writer2.write(name_of_admin + time +  " Login successfully!\n");
                                        writer2.close();
                                    break;
                                }else{
                                    isFound = false;
                                   
                                }
                            }
							adminScan.close();
                            if(isFound == true){
                                System.out.println();
                                System.out.println("======================================================================================");
                                System.out.println("|                                Login  Successfully!                                |");
                                System.out.println("======================================================================================");
                                while (true) {
                                    System.out.print("Continue 1: ");
                                    String back = scanner.nextLine();
                                    if(back.equalsIgnoreCase("1")){
                                        dashboard();
                                    }else{
                                        System.out.println("Invalid input!");
                                        continue;
                                    }
                                } 
                                
                            }else{
                                System.out.println("======================================================================================");
                                System.out.println("|                            Incorrect Password/Username!                            |");
                                System.out.println("======================================================================================");
                                continue;
                            }    

            }catch(Exception e){
                e.printStackTrace();
            }   
            
        }

    }

    //add items
    static void addItem() {
        Scanner scanner = new Scanner(System.in);

        List<String[]> pareha = new ArrayList<>();
        int nextId = 1;
        
        try {
            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
           
            while ((inventoryScan.hasNextLine())) {

                String data = inventoryScan.nextLine();
                String[] parts = data.split(",");
                    pareha.add(parts);
                    int currentId = Integer.parseInt(parts[0]);
                    if (currentId >= nextId) {
                            nextId = currentId + 1;
                    }
            }

            inventoryScan.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("======================================================================================");
        System.out.println("|                                     Add Item                                       |");
        System.out.println("======================================================================================");
        System.out.println();
        System.out.println();
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine().trim();

        for (String[] item : pareha) {
            if (item[1].equalsIgnoreCase(name)) {
                System.out.println("Item with name \"" + name + "\" already exists in the inventory.");
                while (true) {
                     System.out.print("Back 1: ");
                     String back = scanner.nextLine();
                    if(back.equalsIgnoreCase("1")){
                        return;
                    }else{
                        System.out.println("Invalid input!");
                        continue;
                }
            }

            }
        }

        int quantity;
        while (true) {
            System.out.print("Enter Quantity: ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative.");
                    continue;
                }
                break; 
            } catch (Exception e) {
                System.out.println("Please input a valid quantity.");
            }
        }

        int price;
        while (true) {

            System.out.print("Enter Price: ");
            try {
                price = Integer.parseInt(scanner.nextLine());
                if(price < 0){
                    System.out.println("Price cannot be negative");
                    continue;
                }
                break;
                
            } catch (Exception e) {
                System.out.println("Please input a valid Price");
            }
        }
        try{
            FileWriter writer = new FileWriter("inventory.txt", true);
            writer.write(nextId + "," + name + "," + quantity + "," + price + "," + "active" + "\n");
            writer.close();
            System.out.println();
            System.out.println("Item added successfully with ID: " + nextId);
            System.out.println();
            System.out.println("======================================================================================");
            System.out.println("|                                     Inventory List                                 |");
            System.out.println("======================================================================================");
            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
            System.out.println("======================================================================================");
            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", nextId, name, quantity, price);  
            System.out.println("======================================================================================");
            while (true) {
                System.out.print("Back 1: ");
                String back = scanner.nextLine();
                if(back.equalsIgnoreCase("1")){
                    return;
                }else{
                    System.out.println("Invalid input!");
                    continue;
                }
            } 

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //view items
   static void viewActiveItems() {

        try{
            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
            System.out.println("======================================================================================");
            System.out.println("|                                     Inventory List                                 |");
            System.out.println("======================================================================================");
            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
            System.out.println("======================================================================================");
            

            if(!inventoryScan.hasNextLine()) {
               System.out.println("|                              The Inventory is Empty!                               |");
               System.out.println("======================================================================================");
            }else{
                while ((inventoryScan.hasNextLine())) {
                    String data = inventoryScan.nextLine();
                    String[] parts = data.split(",");
                    if(parts[4].equalsIgnoreCase("active")){
                        System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                        System.out.println("======================================================================================");
                    }
                }   
            }
            inventoryScan.close();
           
        } catch (IOException e) {
            System.out.println("No items to display or error reading file.");
        }
    }

    //serach items
    static void searchItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================================================================");
        System.out.println("|                                   Search Item                                      |");
        System.out.println("======================================================================================");
        System.out.println();
        System.out.println();
        System.out.print("Enter Item (ID, NAME, QUANTITY, PRICE) to search: ");
        String searchProduct = scanner.nextLine().trim();
        boolean found = false;

        try {
            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
            System.out.println("======================================================================================");
            System.out.println("|                                     Inventory List                                 |");
            System.out.println("======================================================================================");
            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
            System.out.println("======================================================================================");

            while ((inventoryScan.hasNextLine())) {
                String data = inventoryScan.nextLine();
                String[] parts = data.split(",");
                   
                if ((parts[0].equalsIgnoreCase(searchProduct) || parts[1].equalsIgnoreCase(searchProduct) || parts[2].equalsIgnoreCase(searchProduct)
                || parts[3].equalsIgnoreCase(searchProduct)) && parts[4].equalsIgnoreCase("active")) {
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                    System.out.println("======================================================================================");
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("|                                  Item not found                                    |");
                System.out.println("======================================================================================");
            }
           
            inventoryScan.close();
            while (true) {
                System.out.print("Back 1: ");
                String back = scanner.nextLine();
                if(back.equalsIgnoreCase("1")){
                    return;
                }else{
                    System.out.println("Invalid input!");
                    continue;
                }
            } 
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
    
    //update items
    static void updateItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================================================================");
        System.out.println("|                                   Update Item                                      |");
        System.out.println("======================================================================================");
        System.out.println();
        System.out.println();
        System.out.print("Enter Item ID to update: ");
        String updateId = scanner.nextLine();
       
        boolean found = false;
       
        ArrayList<String> oldInventory = new ArrayList<>();
        try {
            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
            
            while (inventoryScan.hasNextLine()) {
                String data = inventoryScan.nextLine();
                oldInventory.add(data);

                String[] parts = data.split(",");
                if (parts[0].equals(updateId) && parts[4].equalsIgnoreCase("active")) {
                    System.out.println("======================================================================================");
                    System.out.println("|                                     Inventory List                                 |");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                    System.out.println("======================================================================================");
                    System.out.println();
                    System.out.println();           
                    found = true;
                } 
            }

            if (found) {
                String newName = "";
                while (true) {
                    System.out.print("Enter Name: ");
                    newName =  scanner.nextLine().trim();
                    boolean isDuplicate = false;
                    for (String oldInv : oldInventory) {
                        String[] info = oldInv.split(",");
                        if (info[1].equalsIgnoreCase(newName)) {
                            isDuplicate = true;
                            break;
                        } 
                    }
                    if(isDuplicate == false){
                       break;
                    }else{
                        System.out.println("Item already added!");
                    }
                       
                }
                int newQuantity;
                while (true) {
                    System.out.print("Enter Quantity: ");
                    try {
                        newQuantity = Integer.parseInt(scanner.nextLine());
                        if (newQuantity < 0) {
                            System.out.println("Quantity cannot be negative.");
                            continue;
                        }
                        break; 
                    } catch (Exception e) {
                        System.out.println("Please input a valid quantity.");
                    }
                }

                int newPrice;
                while (true) {

                    System.out.print("Enter Price: ");
                    try {
                        newPrice = Integer.parseInt(scanner.nextLine());
                        if(newPrice < 0){
                            System.out.println("Price cannot be negative");
                            continue;
                        }
                        break;
                        
                    } catch (Exception e) {
                        System.out.println("Please input a valid Price");
                    }
                }


                    FileWriter writer = new FileWriter("inventory.txt");
                    boolean isGood = false;
                    String idNiSya = "";
                    for (String oldInv : oldInventory) {

                        String[] info = oldInv.split(",");
                        if (info[0].equalsIgnoreCase(updateId)) {
                            idNiSya = info[0];
                            writer.write(info[0] + "," + newName + "," + newQuantity + "," + newPrice + "," + "active" + "\n");
                            isGood = true;
                        } else {
                            writer.write(oldInv + "\n");
                        }
                    }
                    writer.close();
                    if (isGood) {
                        System.out.println();
                        System.out.println("Item added successfully with ID: " + idNiSya);
                        System.out.println();
                        System.out.println("======================================================================================");
                        System.out.println("|                                     Inventory List                                 |");
                        System.out.println("======================================================================================");
                        System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                        System.out.println("======================================================================================");
                        System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", idNiSya, newName, newQuantity, newPrice);  
                        System.out.println("======================================================================================");         
                        while (true) {
                            System.out.print("Back 1: ");
                            String back = scanner.nextLine();
                            if(back.equalsIgnoreCase("1")){
                                return;
                            }else{
                                System.out.println("Invalid input!");
                                continue;
                            }
                        } 
                    }
            }else{
                System.out.println("======================================================================================");
                System.out.println("|                                     Inventory List                                 |");
                System.out.println("======================================================================================");
                System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                System.out.println("======================================================================================");
                System.out.println("|                                  Item not found                                    |");
                System.out.println("======================================================================================");
                while (true) {
                    System.out.print("Back 1: ");
                    String back = scanner.nextLine();
                    if(back.equalsIgnoreCase("1")){
                        return;
                    }else{
                        System.out.println("Invalid input!");
                        continue;
                    }
                } 
                
            }
        } catch (IOException e) {
            System.out.println("Error updating item.");
            return;
        }
    }

    //delete items
    static void deleteItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================================================================");
        System.out.println("|                                   Delete Item                                      |");
        System.out.println("======================================================================================");
        System.out.println();
        System.out.println();
        System.out.print("Enter Item ID to delete: ");
        String deleteId = scanner.nextLine();
       
        try  {
             ArrayList<String> oldInventory = new ArrayList<>();

            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
            boolean isFound = false;

            while (inventoryScan.hasNextLine()) {
                String data = inventoryScan.nextLine();
                String[] parts = data.split(",");
                oldInventory.add(data);
                if (parts[0].equalsIgnoreCase(deleteId) && parts[4].equalsIgnoreCase("active") ) {
                    System.out.println("======================================================================================");
                    System.out.println("|                                     Inventory List                                 |");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                    System.out.println("======================================================================================");
                    System.out.println();
                    System.out.println(); 
                    while (true) {
                        System.out.print("Are you sure you want to Delete " + parts[1] + "? (yes/no)  : ");  
                        String yn = scanner.nextLine();

                        if(yn.equalsIgnoreCase("yes")){
                            isFound = true;
                            break;
                        } else if(yn.equalsIgnoreCase("no")){
                            return;
                        }else{
                            System.out.println("Invalid input");
                            continue;
                        }
                    }  
                } 
            }

            if(isFound){
                FileWriter writer = new FileWriter("inventory.txt");
                boolean isGood = false;
                String idNiSya = "", nameNiSya = "", quatityNisya = "", priceNisya = "";
                for (String oldInv : oldInventory) {

                    String[] info = oldInv.split(",");
                    if (info[0].equalsIgnoreCase(deleteId)) {
                        idNiSya = info[0];
                        nameNiSya = info[1];
                        quatityNisya = info[2];
                        priceNisya = info[3];

                        writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + "dili" + "\n");
                        isGood = true;
                    } else {
                        writer.write(oldInv + "\n");
                    }
                }
                writer.close();
                if (isGood) {
                    System.out.println();
                    System.out.println("Item Deleted successfully with ID: " + idNiSya);
                    System.out.println();
                    System.out.println("======================================================================================");
                    System.out.println("|                                     Inventory List                                 |");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                    System.out.println("======================================================================================");
                    System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", idNiSya, nameNiSya, quatityNisya, priceNisya);  
                    System.out.println("======================================================================================");         
                    while (true) {
                        System.out.print("Back 1: ");
                        String back = scanner.nextLine();
                        if(back.equalsIgnoreCase("1")){
                            return;
                        }else{
                            System.out.println("Invalid input!");
                            continue;
                        }
                    } 
                }
            }else{
                System.out.println("Item not found!");
                 while (true) {
                    System.out.print("Back 1: ");
                    String back = scanner.nextLine();
                    if(back.equalsIgnoreCase("1")){
                        return;
                    }else{
                        System.out.println("Invalid input!");
                        continue;
                    }
                } 
            }
           
        } catch (Exception e) {
            System.out.println("Error deleting item.");
            return;
        }
    }

    //restore deleted items
    static void restoreDeletedItems(){
    
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println("======================================================================================");
        System.out.println("|                                   Restore Deleted Item                             |");
        System.out.println("======================================================================================");
        System.out.println();
        viewInActiveItems();
        System.out.println();

        System.out.println("1. Restore deleted items");
        System.out.println("2. Back");
        System.out.print("Enter you choice: ");
        String choice = scanner.nextLine();
        
        while (true) {
            if(choice.equals("1")){

                System.out.print("Enter ID to restore: ");
                String id = scanner.nextLine();

                ArrayList<String> oldInventory = new ArrayList<>();
                try {
                     Scanner inventoryScan = new Scanner(new File("inventory.txt"));
                     boolean isFound = false;
                     while (inventoryScan.hasNextLine()) {
                        String data = inventoryScan.nextLine();
                        String[] parts = data.split(",");
                        oldInventory.add(data);
                         if (parts[0].equalsIgnoreCase(id) && parts[4].equalsIgnoreCase("dili") ) {
                            System.out.println("======================================================================================");
                            System.out.println("|                                     Inventory List                                 |");
                            System.out.println("======================================================================================");
                            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                            System.out.println("======================================================================================");
                            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                            System.out.println("======================================================================================");
                            System.out.println();
                            System.out.println(); 
                            while (true) {
                                System.out.print("Are you sure you want to Restore " + parts[1] + "? (yes/no)  : ");  
                                String yn = scanner.nextLine();

                                if(yn.equalsIgnoreCase("yes")){
                                    isFound = true;
                                    break;
                                } else if(yn.equalsIgnoreCase("no")){
                                    return;
                                }else{
                                    System.out.println("Invalid input");
                                    continue;
                                }
                            }  
                        } 
                     }

                     if(isFound){
                        FileWriter writer = new FileWriter("inventory.txt");
                        boolean isGood = false;
                        String idNiSya = "", nameNiSya = "", quatityNisya = "", priceNisya = "";
                        for (String oldInv : oldInventory) {

                            String[] info = oldInv.split(",");
                            if (info[0].equalsIgnoreCase(id)) {
                                idNiSya = info[0];
                                nameNiSya = info[1];
                                quatityNisya = info[2];
                                priceNisya = info[3];

                                writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + "active" + "\n");
                                isGood = true;
                            } else {
                                writer.write(oldInv + "\n");
                            }
                        }

                        writer.close();
                        if (isGood) {
                            System.out.println();
                            System.out.println("Item Restored successfully with ID: " + idNiSya);
                            System.out.println();
                            System.out.println("======================================================================================");
                            System.out.println("|                                     Inventory List                                 |");
                            System.out.println("======================================================================================");
                            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
                            System.out.println("======================================================================================");
                            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", idNiSya, nameNiSya, quatityNisya, priceNisya);  
                            System.out.println("======================================================================================");         
                            while (true) {
                                System.out.print("Back 1: ");
                                String back = scanner.nextLine();
                                if(back.equalsIgnoreCase("1")){
                                    return;
                                }else{
                                    System.out.println("Invalid input!");
                                    continue;
                                }
                            } 
                        }
                     }else{
                        System.out.println("Item not found!");
                        while (true) {
                            System.out.print("Back 1: ");
                            String back = scanner.nextLine();
                            if(back.equalsIgnoreCase("1")){
                                return;
                            }else{
                                System.out.println("Invalid input!");
                                continue;
                            }
                        } 
                     }
                } catch (Exception e) {
                   e.printStackTrace();
                }
               


                
            }else if(choice.equals("2")){
                return;
            }else{
                System.out.println("Invalid Inpput!");
                continue;
            }
        }
    }

    //view in active items
    static void viewInActiveItems() {

        try{
            Scanner inventoryScan = new Scanner(new File("inventory.txt"));
            System.out.println("======================================================================================");
            System.out.println("|                                     Inventory List                                 |");
            System.out.println("======================================================================================");
            System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", "ID", "Name", "Quantity", "Price");
            System.out.println("======================================================================================");
            

            if(!inventoryScan.hasNextLine()) {
               System.out.println("|                              The Inventory is Empty!                               |");
               System.out.println("======================================================================================");
            }else{
                while ((inventoryScan.hasNextLine())) {
                    String data = inventoryScan.nextLine();
                    String[] parts = data.split(",");
                    if(parts[4].equalsIgnoreCase("dili")){
                        System.out.printf("| %-8s | %-40s | %-10s | %-15s |\n", parts[0], parts[1], parts[2], parts[3]);  
                        System.out.println("======================================================================================");
                    }
                }   
            }
            inventoryScan.close();
           
        } catch (IOException e) {
            System.out.println("No items to display or error reading file.");
        }
    }
}