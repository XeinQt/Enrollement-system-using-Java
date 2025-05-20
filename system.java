import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
	
public class system {
		static String id = "";
		static Scanner scan = new Scanner(System.in);
		static LocalDate date = LocalDate.now();
		static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		static String name_of_admin = "";
		static String username_of_admin = "";
		static String school_year = "";


	public static void main(String[] args){
		login();
	}
	// log in
	static void login(){
        while(true){
            try{
                System.out.println();
                System.out.println("========================");
                System.out.println("     DOrSU-BC SYSTEM");
                System.out.println("========================");
                System.out.println("****| Login | ****");
                System.out.println();
                System.out.print("Enter username: ");
                String uname = scan.nextLine();
                System.out.print("Enter password: ");
                String pword = scan.nextLine();
                boolean isFound = false;            
                        Scanner scan1 = new Scanner(new File("admin.txt"));
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(uname.equals(info[0]) && pword.equals(info[1])){
                                    name_of_admin = info[2];
									username_of_admin = info[0];
									school_year = info[6];
                                    isFound = true;
                                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                                        writer2.write(name_of_admin + " Login successfully!\n");
                                        writer2.close();
                                    break;
                                }else{
                                    isFound = false;
                                   
                                }
                            }
							scan1.close();
                            if(isFound == true){
                                    System.out.println();
                                    System.out.println("Login successfully!");
                                    System.out.println("------------------");
                                    System.out.println();
                                    dashboard();
                                    System.out.println();
                            }else{
                                     System.out.println("Error: Incorrect password or username!");
                                    System.out.println("--------------------------------------");
                                    System.out.println();
                                    continue;
                            }    

            }catch(Exception e){
                e.printStackTrace();
            }   
            
        }

    }

	//admin dashboard
	 static void dashboard(){
        while(true){
            try{
                    Scanner scan1 = new Scanner(new File("admin.txt"));
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(name_of_admin.equalsIgnoreCase(info[0])){
                             System.out.println("------------------------------");
                             System.out.println("Welcome " + info[2] + " " + info[3] + " " + info[4]);
                             break;
                        }
                    }
                    scan1.close(); 
					System.out.println();
					System.out.println("School year: " + school_year);
                    System.out.println("Date log: " + date);
                    System.out.println();
					System.out.println("1. School year management");
					System.out.println("2. Announcement management");
                    System.out.println("3. Add student account");
                    System.out.println("4. View/edit information");
					System.out.println("5. Course management");
                    System.out.println("6. Subject management");  
                    System.out.println("7. Schedule management"); 
                    System.out.println("8. Instructor management");
                    System.out.println("9. Enroll student");
                    System.out.println("10. View student course, year and section");
                    System.out.println("11. View instructor student by subject");
                    System.out.println("12. User management");
					System.out.println("13. Audit logs");
					System.out.println("14. Logout");
                    System.out.println();
                    System.out.print("Enter your choice(1-14): ");
                    String choice = scan.nextLine();
                    switch(choice){
						case "1":
							// check_pin_admin();
							school_year_management_menu();
							continue;
						case "2":
							
							continue;
						case "3":
							add_student_account();
							continue;
						case "4":
							edit_information_student();
							continue;
						case "5":
							course_management();
							continue;
						case "6":
							subject_management_menu();
                            continue;
						case "7":
                            shedule_menu();
							continue;
                        case "8":
                            ins_menu();
							continue;
						case "9":
                            menu_enroll();
							continue;
						case "10":
							continue;
						case "11":
							continue;
						case "12":
							check_pin_admin();
							user_management();
							continue;
						case "13":
							continue;
						case "14":
							continue;
						default:
							System.out.println();
							System.out.println("Error: Invalid choice!");
                            System.out.println();
                            continue;
					}
            }catch(Exception e){
                e.printStackTrace();
            }   
            

        }
    } 


//ENROLL STUDENT
    static void menu_enroll(){
        while(true){
            System.out.println();
           System.out.println("****| Enroll student | ****");  
		   System.out.println(); 
                    System.out.println("1. Enroll student subject");
                    System.out.println("2. Update student enroll subejct");
                    System.out.println("3. Back");
                    System.out.println();
                    System.out.print("Enter your choice(1-3): ");
                    String choice = scan.nextLine();
                    switch(choice){
                        case "1":
                           enroll();
                            continue;
                        case "2": 
                            continue;
                        case "3":
                            return;
                        default:
                            System.out.println();
							System.out.println("Error: Invalid choice!");
                            System.out.println();
                            continue;
                    }


        }
    }
    //enroll
    static void enroll(){
        try{
            String id = "";
            boolean  isId = false;
            while(true){
                 System.out.println();
                System.out.println("****| Add subejct to student| ****");  
                System.out.println(); 
                viewActiveStudent();
                  System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();

                System.out.print("Enter student id to enroll all section subject: ");
                id = scan.nextLine();

                    if(id.equalsIgnoreCase("Exit")){
                        return;
                    }

                Scanner scan1 = new Scanner(new File("student.txt"));
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    String [] info = data.split(",");
                    if(id.equalsIgnoreCase(info[0]) && info[11].equalsIgnoreCase("Active")){
                        isId = true;
                    }
                }
                scan1.close();
                if(isId == true){
                        viewCourseWithSubject();
                        System.out.println("Please input correctly.");
                        System.out.println("Input 'Exit' if you want to exit");
                        System.out.println();

                        String code = "";
                        while(true){
                            System.out.print("Enter course code to enroll all section subject: ");
                            code = scan.nextLine();

                            if(code.equalsIgnoreCase("exit")){
                                return;
                            }
                            boolean isCourse = false;
                            Scanner scan2 =  new Scanner(new File("schedule.txt"));
                            while(scan2.hasNextLine()){
                                String data = scan2.nextLine();
                                String [] info = data.split(",");
                                if(code.equalsIgnoreCase(info[0])){
                                    isCourse = true;
                                }

                            }
                            scan2.close();

                            if(isCourse == true){
                                break;
                            }else{
                                System.out.println();
                                System.out.println("Code not found!");
                                System.out.println();
                                continue;
                                
                            }

                        }

                        String year = "";
                        while(true){
                            System.out.print("Enter year: ");
                            year = scan.nextLine();

                            if(year.equalsIgnoreCase("exit")){
                                return;
                            }
                            boolean isCourse = false;
                            Scanner scan2 =  new Scanner(new File("schedule.txt"));
                            while(scan2.hasNextLine()){
                                String data = scan2.nextLine();
                                String [] info = data.split(",");
                                if(year.equalsIgnoreCase(info[2])){
                                    isCourse = true;
                                }

                            }
                            scan2.close();

                            if(isCourse == true){
                                break;
                            }else{
                                System.out.println();
                                System.out.println("Year not found!");
                                System.out.println();
                                continue;
                                
                            }

                        }

                        int yearInt = Integer.parseInt(year);
                        showCoursess(yearInt);

                        String section = "";
                        while(true){
                            System.out.print("Enter section: ");
                            section = scan.nextLine();

                            if(section.equalsIgnoreCase("exit")){
                                return;
                            }
                            boolean isCourse = false;
                            Scanner scan2 =  new Scanner(new File("schedule.txt"));
                            while(scan2.hasNextLine()){
                                String data = scan2.nextLine();
                                String [] info = data.split(",");
                                if(section.equalsIgnoreCase(info[3])){
                                    isCourse = true;
                                }

                            }
                            scan2.close();

                            if(isCourse == true){
                                break;
                            }else{
                                System.out.println();
                                System.out.println("Section not found!");
                                System.out.println();
                                continue;
                            }

                        }
                        show_subejct_shedule(code, yearInt , section);

                        boolean isGood = true;
                         FileWriter writer = new FileWriter("student_subjects_enrolled.txt", true);
                         Scanner scan12 = new Scanner(new File("schedule.txt"));
                         String c = "";
                         String d = "";
                         String u =  "";
                         String da =   "";
                         String  t = "";
                         String ap = "";
                        while(scan12.hasNextLine()){
                            String data = scan12.nextLine();
                            String[] info = data.split(",");
                            c = info[4];
                            d = info[5];
                            u =  info[6];
                            da = info[7];
                            t = info[8];
                            ap = info[9];
                            if(code.equalsIgnoreCase(info[0]) && info[2].contains(yearInt+"") && info[3].contains(section+"")){
                                isGood = true;
                            }
                        }
                        
                        scan12.close();
                        if(isGood == true){
                             writer.write(id+ "," +school_year + "," + c + "," + d + "," + u + "," + da + "," + t + "," + ap+ "\n" );
                             writer.close();
                                System.out.println();
                                System.out.println("Subejcts added successfully!");
                                System.out.println();
                                FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                 writer1.write("Subejcts added successfully! \n");
                                 writer1.close();
                                return;
                        }

                }else{
                            System.out.println();
							System.out.println("Student not found!");
                            System.out.println();
                            continue;
                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void showCoursess(int year){
        while(true){
            try{
                        Scanner scan12 = new Scanner(new File("course.txt"));
                        System.out.println("Course sections");
                        System.out.println("+------------------------------------------------------------------------------------------+");
                        System.out.printf("| %-10s| %-50s | %-10s | %-10s | \n", "CODE", "DESCRIPTION","YEAR","SECTION");
                         System.out.println("+------------------------------------------------------------------------------------------+");
                        while(scan12.hasNextLine()){
                            String data = scan12.nextLine();
                            String[] info = data.split(",");
                            if(info[2].contains(year+"")){
                                System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]); 
                            }
                                            
                        }
                    System.out.println("+------------------------------------------------------------------------------------------+");
                        scan12.close();
                            FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                            writer2.write("Show coures" + "\n");
                            writer2.close();
                            return; 
            }catch(Exception e){
                e.printStackTrace();
            }
                    
        }
    }

    //mashow tanan course nga naay subjetcs
    static void viewCourseWithSubject(){
        try{
           
            System.out.println("+=======================================================================================+");
            System.out.printf("| %-10s| %-50s | %-20s |\n", "CODE", "DESCRIPTION","YEAR");
            System.out.println("+=======================================================================================+");
             Scanner scan9 = new Scanner(new File("courseYear.txt"));
            while(scan9.hasNextLine()){
                String data = scan9.nextLine();
                String[] info = data.split(",");
                    
                        System.out.printf("| %-10s| %-50s | %-20s |\n",info[0],info[1],info[2]);  
                    
                              
            }
             System.out.println("+=======================================================================================+");
            scan9.close();
            return;

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // maview anf student
    static void viewStudent(String id){
        try{
           
            System.out.println("+================================================================================+");
            System.out.printf("| %-10s| %-20s | %-20s | %-20s |  \n", "ID", "FIRSTNAME","MIDDLENAME","LASTNAME");
            System.out.println("+================================================================================+");
             Scanner scan9 = new Scanner(new File("student.txt"));
            while(scan9.hasNextLine()){
                String data = scan9.nextLine();
                String[] info = data.split(",");
                    if(id.equals(info[0])){
                        System.out.printf("| %-10s| %-20s | %-20s | %-20s |\n",info[0],info[1],info[2],info[3]);  
                    }
                              
            }
            System.out.println("+================================================================================+");
            scan9.close();
            return;

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

//instructor management
    static void ins_menu(){
        while(true){
                    System.out.println("1. Add instructor");
					System.out.println("2. Update Instructor");
                    System.out.println("3. Update status");
                    System.out.println("4. Assign in subject with schedule");
                    System.out.println("5. Delete assigned subject of instructor");
                    System.out.println("6. Back");
                     System.out.println();
                    System.out.print("Enter your choice(1-3): ");
                    String choice = scan.nextLine();
                    switch(choice){
                        case "1":
                            showinstructor();
                            ins_add();
                             showinstructor();
                            continue;
                         case "2":
                            showinstructor();
                            edi_instructor();
                             showinstructor();
                            continue;
                        case "3":
                            showinstructor();
                            updatestatus();
                            continue;
                         case "4":
                             showinstructor();
                            assign();
                            continue;
                         case "5":
                            showinstructor();
                            de_ins();
                            continue;
                        case "6":
                            de_ins();
                            return;
                        default:
                            System.out.println();
							System.out.println("Error: Invalid choice!");
                            System.out.println();
                            continue;
                        
                    }

        }
    }

    //delete
    static void de_ins(){
        try {
            String section = "";
            int year = 0;
            String course = "";
            String lname = "";
            String fname = "";
            String mname = "";
            ArrayList<String> s = new ArrayList<>();
                    System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                    System.out.println();
            while (true) {
                while (true) {
                    System.out.print("Enter Last name: ");
                    lname = scan.nextLine();
                    if (lname.equalsIgnoreCase("exit")) {
                        return;
                    }
                    if (naayNumber(lname)) {
                        continue;
                    } else if (lname == null || lname.trim().isEmpty()) {
                        continue;
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.print("Enter First name: ");
                    fname = scan.nextLine();
                    if (fname.equalsIgnoreCase("exit")) {
                        return;
                    }
                    if (naayNumber(fname)) {
                        continue;
                    } else if (fname == null || fname.trim().isEmpty()) {
                        continue;
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.print("Enter Middle name: ");
                    mname = scan.nextLine();
                    if (mname.equalsIgnoreCase("exit")) {
                        return;
                    }
                    if (naayNumber(mname)) {
                        continue;
                    } else {
                        break;
                    }
                }

                Scanner scan1 = new Scanner(new File("instructor.txt"));
                boolean isFname = false;
                while (scan1.hasNextLine()) {
                    String data = scan1.nextLine();
                    s.add(data);
                    String[] info = data.split(",");
                    if (fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0]) && mname.equalsIgnoreCase(info[2]) && info[5].equalsIgnoreCase("Active")) {
                        isFname = true;
                        
                    }
                }
                scan1.close();

                if (isFname == true) {
                    System.out.println("Subjects schedules");
                        System.out.println("Instructor" + lname + " " + lname + " " +  mname);
                            System.out.println("+-----------------------------------------------------------------------------------------------------+");
                                System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", "CODE", "DESCRIPTION","UNIT","DAY", "TIME", "PM/AM");
                                System.out.println("+-----------------------------------------------------------------------------------------------------+");
                                Scanner sssss = new Scanner(new File("enrollment.txt"));
                                while (sssss.hasNextLine()) {
                                    String data = sssss.nextLine();
                                    String[] info = data.split(",");
                                    if (lname.equalsIgnoreCase(info[0]) && info[2].contains(fname) && info[3].contains(mname)) {
                                        System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", info[4], info[5], info[6], info[7], info[8], info[9]);
                                    }
                                }
                                 System.out.println("+-----------------------------------------------------------------------------------------------------+");
                                sssss.close();
                                
                    String subcode = "";
                    String subDes = "";
                    String unit = "";
                    boolean isSubCode = false;
                    while (true) {
                        System.out.print("Enter subject code: ");
                        subcode = scan.nextLine();
                        if (subcode.equalsIgnoreCase("exit")) {
                            return;
                        }
                        String day = "";
                        String time = "";
                        String ap = "";
                        ArrayList<String> list = new ArrayList<>();
                        Scanner scan2 = new Scanner(new File("enrollemnt.txt"));
                        while (scan2.hasNextLine()) {
                            String data = scan2.nextLine();
                            list.add(data);
                            String[] info = data.split(",");
                            if (subcode.equalsIgnoreCase(info[4])) {
                                subDes = info[5];
                                unit = info[4];
                                day = info[7];
                                time = info[8];
                                ap = info[9];
                                isSubCode = true;
                                break;
                            }
                        }
                        scan2.close();

                        if (isSubCode) {
                            FileWriter writer = new FileWriter("enrollement.txt");
                            for(String lists: list){
                                String[] i = lists.split(",");
                                if(subcode.equalsIgnoreCase(i[6])){
                                    writer.write("");
                                }else{
                                    writer.write(lists + "\n");
                                }
                            }
                            writer.close();

                            System.out.println();
                            System.out.println("Deleted successfully!!");
                            System.out.println();
                            return;


                            
                        } else {
                            System.out.println();
                            System.out.println("Subject code not found!");
                            System.out.println();
                        }
                    }                 
                
                } else {
                
                    System.out.println();
                    System.out.println("Instructor not found!");
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    //assgin
    static void assign() {
     try {
          String section = "";
         int year = 0;
         String course = "";
        String lname = "";
        String fname = "";
        String mname = "";
        ArrayList<String> s = new ArrayList<>();
                System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
        while (true) {
            while (true) {
                System.out.print("Enter Last name: ");
                lname = scan.nextLine();
                if (lname.equalsIgnoreCase("exit")) {
                    return;
                }
                if (naayNumber(lname)) {
                    continue;
                } else if (lname == null || lname.trim().isEmpty()) {
                    continue;
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Enter First name: ");
                fname = scan.nextLine();
                if (fname.equalsIgnoreCase("exit")) {
                    return;
                }
                if (naayNumber(fname)) {
                    continue;
                } else if (fname == null || fname.trim().isEmpty()) {
                    continue;
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Enter Middle name: ");
                mname = scan.nextLine();
                if (mname.equalsIgnoreCase("exit")) {
                    return;
                }
                if (naayNumber(mname)) {
                    continue;
                } else {
                    break;
                }
            }

            Scanner scan1 = new Scanner(new File("instructor.txt"));
            boolean isFname = false;
            while (scan1.hasNextLine()) {
                String data = scan1.nextLine();
                s.add(data);
                String[] info = data.split(",");
                if (fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0]) && mname.equalsIgnoreCase(info[2]) && info[5].equalsIgnoreCase("Active")) {
                    isFname = true;
                    
                }
            }
            scan1.close();

            if (isFname == true) {



                    
                   String description = "";
                boolean isCourse = false;
                System.out.println("");
                showCourseYear();
                System.out.println("");
                while (true) {
                    System.out.print("Enter course: ");
                    course = scan.nextLine().toUpperCase();
                    if (course.equalsIgnoreCase("exit")) {
                        return;
                    }
                    Scanner scan1Course = new Scanner(new File("schedule.txt"));
                    while (scan1Course.hasNextLine()) {
                        String data = scan1Course.nextLine();
                        String[] info = data.split(",");
                        if (course.equalsIgnoreCase(info[0])) {
                            description = info[1];
                            isCourse = true;
                            break;
                        }
                    }
                    scan1Course.close();

                    if (isCourse) {
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Course not found!");
                        System.out.println();
                    }
                }

               
                boolean isYear = false;
                while (true) {
                    System.out.print("Enter year: ");
                    String input = scan.nextLine();
                    if (input.equalsIgnoreCase("exit")) {
                        return;
                    }
                    try {
                        year = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("Please enter a valid year.");
                        System.out.println();
                        continue;
                    }
                    Scanner scan2 = new Scanner(new File("course.txt"));
                    while (scan2.hasNextLine()) {
                        String data = scan2.nextLine();
                        String[] info = data.split(",");
                        if (info[2].contains(String.valueOf(year))) {
                            isYear = true;
                            break;
                        }
                    }
                    scan2.close();
                    if (!isYear) {
                        System.out.println();
                        System.out.println("Year not found!");
                        System.out.println();
                        continue;
                    } else {
                        break;
                    }
                }


                showCoursesss(year);
              
                boolean isSection = false;
                while (true) {
                    System.out.print("Enter section: ");
                    section = scan.nextLine().toUpperCase();
                    if (section.equalsIgnoreCase("exit")) {
                        return;
                    }
                    Scanner scan1Section = new Scanner(new File("schedule.txt"));
                    while (scan1Section.hasNextLine()) {
                        String data = scan1Section.nextLine();
                        String[] info = data.split(",");
                        if (section.equalsIgnoreCase(info[3])) {
                            isSection = true;
                            break;
                        }
                    }
                    scan1Section.close();

                    if (isSection) {
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Section not found!");
                        System.out.println();
                    }
                }

                String yearString = String.valueOf(year);
               
                System.out.println();
                System.out.println("Subjects schedules");
                String yearStrings = year +"";



                
                            System.out.println(course + " " + year + " " +  section);
                        System.out.println("+-----------------------------------------------------------------------------------------------------+");
                            System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", "CODE", "DESCRIPTION","UNIT","DAY", "TIME", "PM/AM");
                            System.out.println("+-----------------------------------------------------------------------------------------------------+");
                            Scanner sssss = new Scanner(new File("schedule.txt"));
                        while (sssss.hasNextLine()) {
                                String data = sssss.nextLine();
                                String[] info = data.split(",");
                                if (course.equalsIgnoreCase(info[0]) && info[2].contains(String.valueOf(year)) && info[3].contains(section)) {
                                    System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", info[4], info[5], info[6], info[7], info[8], info[9]);
                                }
                            }
                            sssss.close();
                            double totalUnits = 0;
                            Scanner ss = new Scanner(new File("schedule.txt"));
                            while(ss.hasNextLine()){
                                String data = ss.nextLine();
                                String [] info = data.split(",");

                                if(section.equalsIgnoreCase(info[3]) && info[2].contains(year+"") && course.equalsIgnoreCase(info[0])){
                                        double unitss = Double.parseDouble(info[6]);
                                            totalUnits = totalUnits + unitss;
                                    }
                            }
                            ss.close();
                        
                            System.out.println("+-----------------------------------------------------------------------------------------------------+");
                            System.out.println("| Total units                                | " +   totalUnits);
                        System.out.println("+--------------------------------------------+");
                        
                        
                            
                           
                       
                    

              
                    


                String subcode = "";
                String subDes = "";
                String unit = "";
                boolean isSubCode = false;
                while (true) {
                    System.out.print("Enter subject code: ");
                    subcode = scan.nextLine();
                    if (subcode.equalsIgnoreCase("exit")) {
                        return;
                    }
                    String day = "";
                    String time = "";
                    String ap = "";
                    Scanner scan2 = new Scanner(new File("schedule.txt"));
                    while (scan2.hasNextLine()) {
                        String data = scan2.nextLine();
                        String[] info = data.split(",");
                        if (subcode.equalsIgnoreCase(info[4])) {
                            subDes = info[5];
                            unit = info[6];
                            day = info[7];
                            time = info[8];
                            ap = info[9];
                            isSubCode = true;
                            break;
                        }
                    }
                    scan2.close();

                    if (isSubCode) {
                        boolean is = false;
                        Scanner scan121 = new Scanner(new File("enrollment.txt"));
                        while(scan121.hasNextLine()){
                            String data = scan121.nextLine();
                            String[] info = data.split(",");

                            if(info[9].equalsIgnoreCase(day) && info[0].equalsIgnoreCase(time) && ap.equalsIgnoreCase(info[10])){
                                is = true;
                            }
                        }
                        scan121.close();

                        if(is == true){
                            System.out.println();
                            System.out.println("You have already assign that time!");
                            System.out.println();
                        }else{
                            FileWriter writer = new FileWriter("enrollment.txt", true);
                            writer.write(lname + "," + fname + "," + mname + "," + course + "," + year + "," + section + "," + subcode + "," + subDes + "," + unit+ "," + day +"," + time + "," + ap  + "\n");
                            writer.close();

                            System.out.println();
                            System.out.println("Assign instructor successfully!");
                            System.out.println();

                            FileWriter writer1 = new FileWriter("auditLog.txt", true);
                            writer1.write("Assign instructor successfully!\n");
                            writer1.close();

                            return;
                        }










                        
                    } else {
                        System.out.println();
                        System.out.println("Subject code not found!");
                        System.out.println();
                    }
                }                 
             
            } else {
               
                System.out.println();
                System.out.println("Instructor not found!");
                System.out.println();
            }
        }
     } catch (Exception e) {
        e.printStackTrace();
     }
    }

    static void showCoursesss(int year){
        try{
                    Scanner scan12 = new Scanner(new File("course.txt"));
                    System.out.println("Course sections");
                    System.out.println("+------------------------------------------------------------------------------------------+");
                    System.out.printf("| %-10s| %-50s | %-10s | %-10s | \n", "CODE", "DESCRIPTION","YEAR","SECTION");
                   System.out.println("+------------------------------------------------------------------------------------------+");
                    while(scan12.hasNextLine()){
                        String data = scan12.nextLine();
                        String[] info = data.split(",");
                        if(info[2].contains(year+"")){
                            System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]); 
                            
                        }
                                           
                    }
                   System.out.println("+------------------------------------------------------------------------------------------+");
                    scan12.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("Show coures" + "\n");
                        writer2.close();
                        return; 
        }catch(Exception e){
            e.printStackTrace();
        }
                
    }

    //add instructor
    static void ins_add(){
        try{
                while(true){
                    String lname = "";
                     System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
                            while(true){
                                System.out.print("Enter Last name: ");
                                lname = scan.nextLine();
                                    if(lname.equalsIgnoreCase("exit")){
                                        return;
                                    }
                                    if(naayNumber(lname)){
                                        continue;
                                    }else if(lname == null || lname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }

                            String fname = "";
                            while(true){
                                System.out.print("Enter First name: ");
                                fname = scan.nextLine();
                                if(fname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(fname)){
                                        continue;
                                    }else if(fname == null || fname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }

                            String mname = "";
                            while(true){
                                System.out.print("Enter Middle name: ");
                                mname = scan.nextLine();
                                if(mname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(mname)){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }
                            String email = lname + fname + "@instructorbc.edu.ph";
                            String password = "DORSUBC2024";
                            String status = "Active";

                            boolean found = false;
                            Scanner scan1 = new Scanner(new File("instructor.txt"));
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(lname.equalsIgnoreCase(info[0]) && fname.equalsIgnoreCase(info[1]) && mname.equalsIgnoreCase(info[2])){
                                    found = true;
                                }
                            }
                            scan1.close();

                            if(found == true){
                                 System.out.println("");
                                System.out.println("Instructor already added!");
                                System.out.println("");
                                continue;
                            }else{
                                   FileWriter writer = new FileWriter("instructor.txt", true);
                                    writer.write(lname +"," + fname + "," + mname + "," + email + "," + password+ "," + status + " \n" );
                                    writer.close();

                                     FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                    writer1.write("Instructor added successfully! \n");
                                    writer1.close();

                                    System.out.println("");
                                    System.out.println("Instructor added successfully!");
                                    System.out.println("");
                                    return;
                            }

                         
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //edit instructor
    static void edi_instructor(){
        try{
             
              String lname = "";
               String fname = "";
                String mname = "";
            while(true){
                             System.out.println("Please input correctly.");
                            System.out.println("Input 'Exit' if you want to exit");
                            System.out.println();
                
                            while(true){
                                System.out.print("Enter Last name: ");
                                lname = scan.nextLine();
                                if(lname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(lname)){
                                        continue;
                                    }else if(lname == null || lname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }
                
            
           
               
                        while(true){
                                System.out.print("Enter First name: ");
                                fname = scan.nextLine();
                                if(fname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(fname)){
                                        continue;
                                    }else if(fname == null || fname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }
                    
                

                        
                        while(true){
                            System.out.print("Enter Middle name: ");
                            mname = scan.nextLine();
                            if(mname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(mname)){
                                    continue;
                                }else{
                                    break;
                                }
                        }

                Scanner scan1 = new Scanner(new File("instructor.txt"));
                boolean isFname = false;
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    
                    String[] info = data.split(",");
                    if(fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0]) && mname.equalsIgnoreCase(info[2])){
                        isFname = true;
                    }
                }
                scan1.close();
                if(isFname == true){

                             String nlname = "";
                        while(true){
                            System.out.print("Enter new Last name: ");
                            nlname = scan.nextLine();
                            if(nlname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(nlname)){
                                    continue;
                                }else if(nlname == null || nlname.trim().isEmpty()){
                                    continue;
                                }else{
                                    break;
                                }
                        }
                        String nfname = "";
                        while(true){
                            System.out.print("Enter new First name: ");
                            nfname = scan.nextLine();
                             if(nfname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(nfname)){
                                    continue;
                                }else if(nfname == null || nfname.trim().isEmpty()){
                                    continue;
                                }else{
                                    break;
                                }
                        }

                        String nmname = "";
                        while(true){
                            System.out.print("Enter Middle name: ");
                            nmname = scan.nextLine();
                            if(nmname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(nmname)){
                                    continue;
                                }else{
                                    break;
                                }
                        }
                        Scanner scan2= new Scanner(new File("instructor.txt"));
                        while(scan2.hasNextLine()){
                            String data = scan2.nextLine();
                            String[] info = data.split(",");
                            if(info[0].equalsIgnoreCase(nlname) && info[1].equalsIgnoreCase(nfname) && info[2].equalsIgnoreCase(nmname)){
                                 System.out.println();
                                System.out.println("Error: This insturcotr already add!");
                                System.out.println("--------------------------------");
                                System.out.println();
                                return;
                            }       
                        }
                        scan2.close();
                String email = nlname+nfname+"@instructorbc.edu.ph";
            Scanner scan121 = new Scanner(new File("instructor.txt"));
            ArrayList<String> s= new ArrayList<>();
            while(scan121.hasNextLine()){
                String data1 = scan121.nextLine();
                s.add(data1);
            }
            scan121.close();

            FileWriter writer = new FileWriter("instructor.txt");
            boolean isGood  = false;
            for(String ins: s){
                String[] info = ins.split(",");
                if(fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0])){
                    writer.write(nlname + "," + nfname + "," + nmname + "," + email+ "," + info[4] + ","+ info[5]+"\n");
                    isGood = true;
                }else{
                    writer.write(ins + "\n");
                }
            }
            writer.close();

            if(isGood == true){
                System.out.println();
                 System.out.println();
                                System.out.println("Instructor informatioon updated successfully!");
                                System.out.println();

                                FileWriter writer11 = new FileWriter("auditLog.txt",true);
                                writer11.write("Instructor informatioon updated successfully!\n");
                                writer11.close();
                                return;

            }


                }else{
                            System.out.println();
							System.out.println("Instructor not found!");
                            System.out.println();
                            continue;
                }

            }
            


           


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void updatestatus(){
        try{


           
              String lname = "";
               String fname = "";
                String mname = "";
            while(true){

                             System.out.println("Please input correctly.");
                            System.out.println("Input 'Exit' if you want to exit");
                            System.out.println();
                            while(true){
                                System.out.print("Enter Last name: ");
                                lname = scan.nextLine();
                                if(lname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(lname)){
                                        continue;
                                    }else if(lname == null || lname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }
                
            
           
               
                        while(true){
                                System.out.print("Enter First name: ");
                                fname = scan.nextLine();
                                if(fname.equalsIgnoreCase("exit")){
                                    return;
                                }
                                    if(naayNumber(fname)){
                                        continue;
                                    }else if(fname == null || fname.trim().isEmpty()){
                                        continue;
                                    }else{
                                        break;
                                    }
                            }
                    
                

                        
                        while(true){
                            System.out.print("Enter Middle name: ");
                            mname = scan.nextLine();
                            if(mname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(mname)){
                                    continue;
                                }else{
                                    break;
                                }
                        }

                Scanner scan1 = new Scanner(new File("instructor.txt"));
                boolean isFname = false;

                 ArrayList<String> s = new ArrayList<>();
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    s.add(data);
                    String[] info = data.split(",");
                    if(fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0]) && mname.equalsIgnoreCase(info[2])){
                        isFname = true;
                    }
                }
                scan1.close();
                if(isFname == true){
                    String stat = "";
                    boolean ka = false;
                        while(true){
                            System.out.print("Choice status(1. Active or 2.Inactive): ");
                            stat = scan.nextLine();

                            if(stat.equalsIgnoreCase("1")){
                                stat = "Active";
                                ka = true;
                                break;
                            }else if(stat.equalsIgnoreCase("2")){
                                stat = "Inactive";
                                ka= true;
                                break;
                            }else{
                                System.out.println("");
                                System.out.println("Invalid choice");
                                System.out.println("");
                                continue;
                            }
                        }
                       
                        if(ka == true){
                            FileWriter writer = new FileWriter("instructor.txt");
                            boolean isGood  = false;
                            for(String ins: s){
                                String[] info = ins.split(",");
                                if(fname.equalsIgnoreCase(info[1]) && lname.equalsIgnoreCase(info[0])){
                                    writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3]+ "," + info[4] + "," + stat+"\n");
                                    isGood = true;
                                }else{
                                    writer.write(ins + "\n");
                                }
                            }
                            writer.close();

                            if(isGood == true){
                                System.out.println();
                                System.out.println();
                                                System.out.println("Instructor informatioon updated successfully!");
                                                System.out.println();

                                                FileWriter writer11 = new FileWriter("auditLog.txt",true);
                                                writer11.write("Instructor informatioon updated successfully!\n");
                                                writer11.close();
                                                return;

                            }

                        }
        
                        

                }else{
                            System.out.println();
							System.out.println("Instructor not found!");
                            System.out.println();
                            return;
                }

            }
            



        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //show instructor
     static void showinstructor(){
         try{
                    Scanner scan12 = new Scanner(new File("instructor.txt"));
                     System.out.println("Instructor lists");
                     System.out.println("+----------------------------------------------------------------------------------------------------+");
                    System.out.printf("| %-25s| %-25s | %-20s | %-20s |\n", "LASTNAME", "FIRST NAME","MIDDLE NAME","STATUS");
                    System.out.println("+----------------------------------------------------------------------------------------------------+");
                    while(scan12.hasNextLine()){
                        String data = scan12.nextLine();
                        String[] info = data.split(",");
                            
                            System.out.printf("| %-25s| %-25s | %-20s | %-20s |\n", info[0],info[1],info[2],info[5]);                
                    }
                   System.out.println("+----------------------------------------------------------------------------------------------------+");
                    scan12.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("Show instructor by year" + "\n");
                        writer2.close();
                        return; 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void showinstructor_active() {
        try {
            Scanner scan12 = new Scanner(new File("instructor.txt"));
            System.out.println("Active instructors");
            System.out.println("+-----------------------------------------------------------------------------+");
            System.out.printf("| %-25s| %-25s | %-20s | \n", "FIRST NAME", "LAST NAME", "MIDDLE NAME");
            System.out.println("+-----------------------------------------------------------------------------+");
            while (scan12.hasNextLine()) {
                String data = scan12.nextLine();
                String[] info = data.split(",");
                if (info[5].equalsIgnoreCase("Active")) {
                    System.out.printf("| %-25s| %-25s | %-20s |\n", info[1], info[0], info[2]);
                }
            }
            System.out.println("+-----------------------------------------------------------------------------+");
            scan12.close();
            FileWriter writer2 = new FileWriter("auditLog.txt", true);
            writer2.write("Showed active instructors" + "\n");
            writer2.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//schedule management menu
    
    static void shedule_menu(){
        while(true){
                     System.out.println();
                    System.out.println("****| Schedule management| ****");  
                    System.out.println();
                    System.out.println("1. Add schedule subject to course");
					System.out.println("2. Update time subject to course");
                    System.out.println("3. Back");
                     System.out.println();
                    System.out.print("Enter your choice(1-3): ");
                    String choice = scan.nextLine();
                    switch(choice){
                        case "1":
                            add_schedule();
                            continue;
                         case "2":
                            edit_schedule();
                            return;
                         case "3":
                            dashboard();
                        default:
                            System.out.println();
							System.out.println("Error: Invalid choice!");
                            System.out.println();
                            continue;
                        
                    }

        }
    }
    //add scheule
     static void add_schedule(){
        try{
          
            System.out.println();
           System.out.println("****| Add subject schedule | ****");  
		   System.out.println(); 
             showCourseYear();
              System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
            String description = "";
            String course = "";
            String unit = "";
            String subDes = "";
            boolean isCourse = false;
           while(true) {
                System.out.print("Enter course code to add subject schedule: ");  
                 course  = scan.nextLine().toUpperCase();
                if(course.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan1 = new Scanner(new File("course.txt"));
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    String[] info = data.split(",");

                    if(course.equalsIgnoreCase(info[0])){
                        description = info[1];
                       isCourse = true;
                       break;
                    }
                }
                scan1.close();

                if(isCourse == true){
                    break;
                }else{
                    System.out.println(); 
                    System.out.println("Course not found!"); 
                    System.out.println(); 
                }
           }

            int year = 0;
                boolean isYear = false;
                while (true) {
                     System.out.print("Enter year: ");
                     if("Exit".contains(year+"")){
                        return;
                     }
                    try {
                        year = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("Please enter a valid year.");
                         System.out.println();
                        continue;
                    }
                    Scanner scan2 = new Scanner(new File("course.txt"));
                    while(scan2.hasNextLine()){
                        String data = scan2.nextLine();
                        String[] info = data.split(",");
                        if(info[2].contains(year+"")){
                            isYear = true;
                            break;
                        }
                    }
                    scan2.close();
                    if(isYear == false){
                         System.out.println();
                        System.out.println("Year not found!");
                        System.out.println();
                         continue;
                    }else{
                       break;
                    }
                }
           showCourses(year);
             System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
            String section = "";
            boolean isSection = false;
           while(true) {
                System.out.print("Enter section: ");  
                 section  = scan.nextLine().toUpperCase();
                if(section.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan1 = new Scanner(new File("course.txt"));
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    String[] info = data.split(",");

                    if(section.equalsIgnoreCase(info[3])){
                       isSection = true;
                       break;
                    }
                }
                scan1.close();

                if(isSection == true){
                    break;
                }else{
                    System.out.println(); 
                    System.out.println("Section not found!"); 
                    System.out.println(); 
                }
           }

            String yearString = year + "";
              System.out.println("Subjects");
            viewSubjectbyCourseYear(course, yearString);
             System.out.println(); 
              System.out.println("Subjects schedules"); 
            show_subejct_shedule(course, year , section);
           
            
            String subcode = "";
            boolean isSubCode = false;
            while(true){
                System.out.print("Enter subject code: "); 
                subcode = scan.nextLine().toUpperCase();;
                if(subcode.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan2 = new Scanner(new File("subject.txt"));
                while(scan2.hasNextLine()){
                    String data = scan2.nextLine();
                    String[] info = data.split(",");
                    if(subcode.equalsIgnoreCase(info[2])){
                        subDes = info[3];
                        unit = info[4];
                        isSubCode = true;
                        break;
                    }
                }
                scan2.close();
                if(isSubCode == true){
                    String sched  = "";
                    while(true){
                        System.out.print("Schedule class(1.MWF or 2.TTH or 3.F): "); 
                        sched = scan.nextLine();
                        if(sched.equalsIgnoreCase("exit")){
                                return;
                            }
                        if(sched.equalsIgnoreCase("1")){
                            sched = "MWF";
                            break;
                        }else if(sched.equalsIgnoreCase("2")){
                            sched = "TTH";
                            break;
                        }else if(sched.equalsIgnoreCase("3")){
                            sched = "F";
                            break;
                        }else{
                            System.out.println(); 
                            System.out.println("Invalid choice"); 
                            System.out.println(); 
                            continue;
                        }
                    }

                    System.out.println("Add time"); 
                    System.out.print("Start time: "); 
                    int stime = scan.nextInt();
                    System.out.print("End time: "); 
                    int etime = scan.nextInt();

                    String time = stime+":00" + "-" + etime+":00";
                    String ap = "";

                     while(true){
                        System.out.print("Schedule class(1.A.M or 2.P.M): "); 
                         ap = scan.nextLine();
                          if(ap.equalsIgnoreCase("exit")){
                                return;
                            }
                        if(ap.equalsIgnoreCase("1")){
                            ap = "A.M";
                            break;
                        }else if(ap.equalsIgnoreCase("2")){
                            ap = "P.M";
                            break;
                        }else{
                            System.out.println(); 
                            System.out.println("Invalid choice"); 
                            System.out.println(); 
                            continue;
                        }
                    }

                    Scanner scan1211 = new Scanner(new File("schedule.txt"));
                    boolean isUseTimes = false;
                    while(scan1211.hasNextLine()){
                        String data = scan1211.nextLine();
                        String[] info = data.split(",");
                        if(info[7].equalsIgnoreCase(sched) && info[8].equalsIgnoreCase(time) && ap.equalsIgnoreCase(info[9]) && course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1]) && info[2].contains(year+"")){                    
                            isUseTimes = true;
                            break;
                        }
                    }
                    scan1211.close();

                    if(isUseTimes){
                                System.out.println(); 
                                System.out.println("Schedule is same to other section!"); 
                                System.out.println(); 
                                show_subejct_shedule(course, year , section);
                                System.out.println("Please input correctly.");
                                System.out.println("Input 'Exit' if you want to exit");
                                System.out.println();
                                continue;
                    }


                    Scanner scan121 = new Scanner(new File("schedule.txt"));
                    boolean isUseTime = false;
                    while(scan121.hasNextLine()){
                        String data = scan121.nextLine();
                        String[] info = data.split(",");
                        if(info[7].equalsIgnoreCase(sched) && info[8].equalsIgnoreCase(time) && ap.equalsIgnoreCase(info[9]) && course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1]) && info[2].contains(year+"") && section.equalsIgnoreCase(info[3]) ){
                            
                            isUseTime = true;
                            break;
                        }
                    }
                    scan121.close();

                    if(isUseTime == true){
                             System.out.println(); 
                                System.out.println("Schedule already used!"); 
                                System.out.println(); 
                                show_subejct_shedule(course, year , section);
                                System.out.println("Please input correctly.");
                            System.out.println("Input 'Exit' if you want to exit");
                            System.out.println();
                            continue;
                    }else{
                        FileWriter writer = new FileWriter("schedule.txt", true);
                        writer.write(course + "," + description + "," + year + "," + section + "," + subcode+","+subDes+","+unit + "," + sched + "," + time + "," + ap + "\n");
                        writer.close();

                        System.out.println(); 
                        System.out.println("Schedule added sucessfully!!"); 
                        System.out.println(); 
                        show_subejct_shedule(course, year , section);
                        System.out.println("Please input correctly.");
                        System.out.println("Input 'Exit' if you want to exit");
                        System.out.println();

                    }

                    
                }else{
                    System.out.println(); 
                    System.out.println("Subject code not found!"); 
                    System.out.println(); 
                    continue;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // naa koy kulang diri pag show ra sa shceule
    //update schedule
    static void edit_schedule(){
        try{
            
           
           System.out.println("****| Update subject schedule | ****");  
		   System.out.println(); 
            
             showCourseYear();
             System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
            String description = "";
            String course = "";
            String unit = "";
            String subDes = "";
            boolean isCourse = false;
           while(true) {
                System.out.print("Enter course to update schedule: ");  
                 course  = scan.nextLine().toUpperCase();
                if(course.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan1 = new Scanner(new File("course.txt"));
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    String[] info = data.split(",");

                    if(course.equalsIgnoreCase(info[0])){
                        description = info[1];
                       isCourse = true;
                       break;
                    }
                }
                scan1.close();

                if(isCourse == true){
                    break;
                }else{
                    System.out.println(); 
                    System.out.println("Course not found!"); 
                    System.out.println(); 
                }
           }

            int year = 0;
                boolean isYear = false;
                while (true) {
                     System.out.print("Enter year: ");
                     if("Exit".contains(year+"")){
                        return;
                     }
                    try {
                        year = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("Please enter a valid year.");
                         System.out.println();
                        continue;
                    }
                    Scanner scan2 = new Scanner(new File("course.txt"));
                    while(scan2.hasNextLine()){
                        String data = scan2.nextLine();
                        String[] info = data.split(",");
                        if(info[2].contains(year+"")){
                            isYear = true;
                            break;
                        }
                    }
                    scan2.close();
                    if(isYear == false){
                         System.out.println();
                        System.out.println("Year not found!");
                        System.out.println();
                         continue;
                    }else{
                       break;
                    }
                }
           showCourses(year);
           System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();

            String section = "";
            boolean isSection = false;
           while(true) {
                System.out.print("Enter section: ");  
                 section  = scan.nextLine().toUpperCase();
                if(section.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan1 = new Scanner(new File("course.txt"));
                while(scan1.hasNextLine()){
                    String data = scan1.nextLine();
                    String[] info = data.split(",");

                    if(section.equalsIgnoreCase(info[3])){
                       isSection = true;
                       break;
                    }
                }
                scan1.close();

                if(isSection == true){
                    break;
                }else{
                    System.out.println(); 
                    System.out.println("Section not found!"); 
                    System.out.println(); 
                }
           }

            String yearString = year + "";
              System.out.println("Subjects schedules"); 
            show_subejct_shedule(course, year , section);
            
            String subcode = "";
            boolean isSubCode = false;
             while(true){
                System.out.print("Enter subject code: "); 
                subcode = scan.nextLine();
                if(subcode.equalsIgnoreCase("exit")){
                    return;
                }
                Scanner scan2 = new Scanner(new File("schedule.txt"));
                ArrayList<String> s = new ArrayList<>();
                while(scan2.hasNextLine()){
                    String data = scan2.nextLine();
                    s.add(data);
                    String[] info = data.split(",");
                    if(subcode.equalsIgnoreCase(info[4])){
                        isSubCode = true;
                        break;
                    }
                }
                scan2.close();
                if(isSubCode == true){
                        String sched  = "";
                    while(true){
                        System.out.print("Schedule new class(1.MWF or 2.TTH or 3.F): "); 
                        sched = scan.nextLine();
                        if(sched.equalsIgnoreCase("exit")){
                                return;
                            }
                        if(sched.equalsIgnoreCase("1")){
                            sched = "MWF";
                            break;
                        }else if(sched.equalsIgnoreCase("2")){
                            sched = "TTH";
                            break;
                        }else if(sched.equalsIgnoreCase("3")){
                            sched = "F";
                            break;
                        }else{
                            System.out.println(); 
                            System.out.println("Invalid choice"); 
                            System.out.println(); 
                            continue;
                        }
                    }

                    System.out.println("Add time "); 
                    System.out.print("New start time: "); 
                    int stime = scan.nextInt();
                    System.out.print("New end time: "); 
                    int etime = scan.nextInt();

                    String time = stime+":00" + "-" + etime+":00";
                    String ap = "";

                     while(true){
                        System.out.print("New schedule class(1.A.M or 2.P.M): "); 
                         ap = scan.nextLine();
                          if(ap.equalsIgnoreCase("exit")){
                                return;
                            }
                        if(ap.equalsIgnoreCase("1")){
                            ap = "A.M";
                            break;
                        }else if(ap.equalsIgnoreCase("2")){
                            ap = "P.M";
                            break;
                        }else{
                            System.out.println(); 
                            System.out.println("Invalid choice"); 
                            System.out.println(); 
                            continue;
                        }
                    }
                    FileWriter writer = new FileWriter("schedule.txt");
                    boolean isGood = true;
                    for(String sh:s ){
                        String[] info = sh.split(",");
                        if(subcode.equalsIgnoreCase(info[4]) && course.equalsIgnoreCase(info[0]) && info[2].contains(year+"")){
                            writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," +info[4] + "," + info[5] + "," + info[6] + "," + sched + "," + time + "," + ap + "\n");
                            isGood = true;
                        }else{
                            writer.write(sh + "\n");
                        }
                    }
                    writer.close();
                    if(isGood == true){
                            System.out.println(); 
                            System.out.println("Schedule update successfully!"); 
                            System.out.println(); 

                            FileWriter writer1 = new FileWriter("auditLog.txt", true);
                            writer1.write("Schedule update successfully!");
                            writer1.close();
                            return;
                    }


                }else{
                    System.out.println(); 
                    System.out.println("Subject code not found!"); 
                    System.out.println(); 
                    continue;
                   }   }

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //show schedule year
    static void show_subejct_shedule(String course, int year, String section){
        try{
             System.out.println(course + " " + year + " " +  section);
           System.out.println("+-----------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", "CODE", "DESCRIPTION","UNIT","DAY", "TIME", "PM/AM");
            System.out.println("+-----------------------------------------------------------------------------------------------------+");
            Scanner scan1 = new Scanner(new File("schedule.txt"));
           while (scan1.hasNextLine()) {
                String data = scan1.nextLine();
                String[] info = data.split(",");
                if (course.equalsIgnoreCase(info[0]) && info[2].contains(String.valueOf(year)) && info[3].contains(section)) {
                    System.out.printf("| %-10s| %-30s | %-10s | %-10s |  %-15s |%-10s |\n", info[4], info[5], info[6], info[7], info[8], info[9]);
                }
            }
            
             double totalUnits = 0;
            Scanner scan2 = new Scanner(new File("schedule.txt"));
            while(scan2.hasNextLine()){
                String data = scan2.nextLine();
                String [] info = data.split(",");

                 if(section.equalsIgnoreCase(info[3]) && info[2].contains(year+"") && course.equalsIgnoreCase(info[0])){
                         double unitss = Double.parseDouble(info[6]);
                            totalUnits = totalUnits + unitss;
                    }
            }
           
         
             System.out.println("+-----------------------------------------------------------------------------------------------------+");
            System.out.println("| Total units                                | " +   totalUnits);
           System.out.println("+--------------------------------------------+");
           
           
            
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

// course management menu
	static void course_management(){
        while(true){
            System.out.println();
            System.out.println("****| Course management | ****");
            System.out.println();
			 System.out.println("1. Add course & year");
            System.out.println("2. Add course, year & section");
            System.out.println("3. Update course, code & description");
            System.out.println("4. Update course & section");
            System.out.println("5. Delete course, year & section");
             System.out.println("6. Back");
             System.out.println();
               System.out.print("Enter your choice(1-6): ");
             String choice = scan.nextLine();
            switch(choice){
				case "1":
					course_year();
					continue;
                case "2":
                    addCourse_section_yawa();
                    continue;
                case "3":
                    editCourse();
                    continue;
                case "4":
                    editCourseSection();
                    continue;
                case "5":
                    deleteCourse();
                    continue;
                 case "6":
                    return;
                default:
					System.out.println();
                    System.out.println("Error: Invalid choice!");
                    System.out.println();
                    break;


            }

        }
    }
	
    //show course by year
    static void showCourseYear(){
         try{
                    Scanner scan12 = new Scanner(new File("courseYear.txt"));
                    System.out.println("Course year");
                    System.out.println("+-----------------------------------------------------------------------------+");
                    System.out.printf("| %-10s| %-50s | %-10s | \n", "CODE", "DESCRIPTION","YEAR");
                   System.out.println("+-----------------------------------------------------------------------------+");
                    while(scan12.hasNextLine()){
                        String data = scan12.nextLine();
                        String[] info = data.split(",");
                            System.out.printf("| %-10s| %-50s | %-10s |\n", info[0],info[1],info[2]);                
                    }
                   System.out.println("+-----------------------------------------------------------------------------+");
                    scan12.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("Show coures by year" + "\n");
                        writer2.close();
                        return; 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //SHOW COURSE BY YEAR AND SECTION
     static void showCourseYearSection(){
         try{
                    Scanner scan12 = new Scanner(new File("course.txt"));
                    System.out.println("Course year and section");
                      System.out.println("+------------------------------------------------------------------------------------------+");
                     System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", "CODE","DESCRIPTION","YEAR","SECTION"); 
                    System.out.println("+------------------------------------------------------------------------------------------+");
                    while(scan12.hasNextLine()){
                        String data = scan12.nextLine();
                        String[] info = data.split(",");
                            System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]);                
                    }
                   System.out.println("+------------------------------------------------------------------------------------------+");
                    scan12.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("Show coures by year" + "\n");
                        writer2.close();
                        return; 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	// add course year
	static void course_year() {
        while (true) {
            try {
               
                String course = "";  
                System.out.println();
                
                System.out.println();
                System.out.println("****| Add course year| ****");
                 System.out.println();
                 showCourseYear();
                 System.out.println("Please input correctly.");
                 System.out.println("Input 'Exit' if you want to exit");
                 System.out.println();
                while (true) {
                   
                    System.out.print("Enter course code: ");
                    course = scan.nextLine().toUpperCase();
                    if(course.equalsIgnoreCase("Exit")){
                        return;
                    }
                    if (course.matches(".*\\d.*")) {
							System.out.println();
                        System.out.println("Course name cannot contain numbers.");
							System.out.println();
                    }else if(course == null || course.trim().isEmpty()){
						System.out.println();
                           System.out.println("Please enter a valid course.");
						   	System.out.println();
                        continue;
                    }else {
                        break;
                    }
                }

               
                
                   String description = "";
                   while (true) {
                       
                        System.out.print("Enter description: ");
                        description = scan.nextLine().toUpperCase();
                        if(description.equals("Exit")){
                            return;
                        }
                        if(description == null || description.trim().isEmpty()){
                            continue;
                        }else {
                            break;
                        }
                   }
                    

                int year = 0;
                while (true) {
                   
                    System.out.print("Enter year: ");
                    try {
                        year = Integer.parseInt(scan.nextLine());
                        if("Exit".contains(year+"")){
                            return;
                        }else{
                            break;
                        }
                        
                    } catch (NumberFormatException e) {
							System.out.println();
                        System.out.println("Please enter a valid year.");
							System.out.println();
                    }
                }


                Scanner scan4 = new Scanner(new File("courseYear.txt"));
                boolean courseExists = false;
                while (scan4.hasNextLine()) {
                    String data = scan4.nextLine();
                    String[] info = data.split(",");
                    if (info[0].equalsIgnoreCase(course) && description.equalsIgnoreCase(info[1]) && Integer.parseInt(info[2].trim()) == year) {
                        courseExists = true;
                        break;
                    }
                }
                scan4.close();

                if (courseExists) {
                      System.out.println();
                    System.out.println("This Course, description  &  year already exists!");
                      System.out.println();
                      return;
                } else {
                    FileWriter writer = new FileWriter("courseYear.txt", true);
                    writer.write(course + ","+ description + "," + year + "\n");
                    writer.close();
                      System.out.println();
                    System.out.println("Course added successfully!");
                      System.out.println();

                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write(course + "," + description + "," + year +" Added successfully!\n");
                        writer2.close(); 
                        showCourseYear();
						return;
                }
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	//add course section
	static void addCourse_section_yawa() {
        
            try {
                while (true) {
               
                        String course = "";
                        System.out.println();
                        System.out.println("****| Add course section | ****");  
                        System.out.println();
                        showCourseYear();  
                        System.out.println("Please input correctly.");
                        System.out.println("Input 'Exit' if you want to exit");
                        System.out.println();
                        boolean isFound = false;
                        boolean isFounds = false;
                        
                        while (true) {
                        
                            System.out.print("Enter course code: ");
                            course = scan.nextLine().toUpperCase();
                            if(course.equalsIgnoreCase("Exit")){
                                return;
                            }
                            if (course.matches(".*\\d.*")) {
                                    System.out.println();
                                System.out.println("Course name cannot contain numbers.");
                                    System.out.println();
                                    continue;
                            }else if(course == null || course.trim().isEmpty()){
                                System.out.println();
                                System.out.println("Please enter a valid course.");
                                    System.out.println();
                                continue;
                            }

                            Scanner scan1 = new Scanner(new File("courseYear.txt"));
                            
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(course.equalsIgnoreCase(info[0])){
                                    isFound = true;
                                    break;
                                }
                            }
                            scan1.close();
                            if(isFound == true){
                                break;
                            }else{
                                System.out.println();
                                System.out.println("Course code not found!");
                                    System.out.println();
                                    continue;
                            }
                        }

                    
                        
                        String description = "";
                        while (true) {
                            
                                System.out.print("Enter description: ");
                                description = scan.nextLine().toUpperCase();
                                if(description.equals("Exit")){
                                    return;
                                }
                                if(description == null || description.trim().isEmpty()){
                                    continue;
                                }
                                Scanner scan1 = new Scanner(new File("courseYear.txt"));
                                boolean isFoundsssss= false;
                                while(scan1.hasNextLine()){
                                    String data = scan1.nextLine();
                                    String[] info = data.split(",");
                                    if(description.equalsIgnoreCase(info[1])){
                                        isFoundsssss = true;
                                        break;
                                    }
                                }
                                scan1.close();
                                if(isFoundsssss == true){
                                    break;
                                }else{
                                    System.out.println();
                                    System.out.println("Course code not found!");
                                        System.out.println();
                                        continue;
                                }
                        }
                            

                        int year = 0;
                        boolean isFoundss = false;
                        while (true) {
                        
                            System.out.print("Enter year: ");
                            try {
                                year = Integer.parseInt(scan.nextLine());
                                if("Exit".contains(year+"")){
                                    return;
                                }else{
                                    break;
                                }
                                
                            } catch (NumberFormatException e) {
                                    System.out.println();
                                System.out.println("Please enter a valid year.");
                                    System.out.println();
                            }

                                Scanner scan1 = new Scanner(new File("courseYear.txt"));
                                
                                while(scan1.hasNextLine()){
                                    String data = scan1.nextLine();
                                    String[] info = data.split(",");
                                    if(info[2].equalsIgnoreCase(year+"")){
                                        isFoundss = true;
                                        break;
                                    }
                                }
                                scan1.close();
                                if(isFoundss == true){
                                    break;
                                }else{
                                    System.out.println();
                                    System.out.println("Course code not found!");
                                        System.out.println();
                                        continue;
                                }
                            
                        }
                        showCourses(year);
                                        char section = ' ';
                                        while (true) {
                                        
                                            System.out.print("Enter section: ");
                                            String input = scan.nextLine();
                                            if(input == null || input.trim().isEmpty()){
                                                continue;
                                            }

                                            if(input.equalsIgnoreCase("Exit")){
                                                return;
                                            }
                                            
                                            boolean  h = false;
                                            input = input.trim().toUpperCase();
                                            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                                                section = input.charAt(0);
                                                h = true;
                                               
                                            } else {
                                                    System.out.println();
                                                System.out.println("Please enter a single letter for the section.");
                                                    System.out.println();
                                                    continue;
                                            }
                                            if(h==true){
                                                 Scanner scan4 = new Scanner(new File("course.txt"));
                                                boolean courseExists = false;
                                                while (scan4.hasNextLine()) {
                                                    String data = scan4.nextLine();
                                                    String[] info = data.split(",");
                                                    if (info[0].equalsIgnoreCase(course) && description.equalsIgnoreCase(info[1]) && Integer.parseInt(info[2].trim()) == year && info[3].charAt(0) == section) {
                                                        courseExists = true;
                                                        break;
                                                    }
                                                }
                                                scan4.close();
                                                if (courseExists) {
                                                    System.out.println();
                                                    System.out.println("This Course, description, year & section already exists!");
                                                    System.out.println();
                                                    continue;
                                                } else{
                                                    break;
                                                }

                                            }
                                        }

                                            FileWriter writer = new FileWriter("course.txt", true);
                                            writer.write(course + ","+ description + "," + year + "," + section + "\n");
                                            writer.close();
                                            System.out.println();
                                            System.out.println("Course added successfully!");
                                            System.out.println();
                                            showCourses(year);
                                                FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                                                writer2.write(course + "," + description + "," + year + "," + section + " Added successfully!\n");
                                                writer2.close(); 
                                                return;
                                        

                        

                        
                 }

            } catch (Exception e) {
                e.printStackTrace();
            }
       
    }

    //edit course year
    static void editCourse(){
        while(true){
            try{
                System.out.println();
                
                System.out.println("****| Edit course code and description | ****");
                System.out.println();
                showCourseYear();
                System.out.println("Please input correctly.");
                System.out.println("Input 'Exit' if you want to exit.");
                System.out.println();
                String course = "";
                boolean isCourse = false;
                while (true) {
                    
                    System.out.print("Enter course code: ");
                    course = scan.nextLine();
                    if(course.equalsIgnoreCase("Exit")){
                        return;
                    }else if (course.matches(".*\\d.*")) {
                        System.out.println();
                        System.out.println("Course name cannot contain numbers.");
                         System.out.println();
                        continue;
                    }else if(course == null || course.trim().isEmpty()){
                        continue;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isCourse = true;
                            break;
                        }
                    }
                    scan1.close();
                    if(isCourse == false){
                         System.out.println();
                        System.out.println("Course code not found!");
                        System.out.println();
                        continue;   
                       
                    }else{
                         break;
                    }

                }

               
                
                   String description = "";
                   boolean isDescription = false;
                   while (true) {
                        
                        System.out.print("Enter description: ");
                        if(description.equalsIgnoreCase("Exit")){
                            return;
                        }
                        description = scan.nextLine().toUpperCase();
                        if(description == null || description.trim().isEmpty()){
                            continue;
                        }
                            Scanner scan1 = new Scanner(new File("courseYear.txt"));
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(description.equalsIgnoreCase(info[1])){
                                    isDescription = true;
                                    break;
                                }
                            }
                            scan1.close();
                            if(isDescription == false){
                                System.out.println();
                                System.out.println("Description code not found!");
                                System.out.println();
                                continue;   
                            
                            }else{
                                break;
                            }
                   }
                
               


                ArrayList<String> bsit = new ArrayList<>();
                boolean isFound = false;
                Scanner scan12 = new Scanner(new File("courseYear.txt"));
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.printf("| %-10s| %-50s | %-10s | \n", "CODE", "DESCRIPTION","YEAR");
                System.out.println("+-----------------------------------------------------------------------------+");
                while(scan12.hasNextLine()){
                    String data = scan12.nextLine();
                    bsit.add(data);
                    String[] info = data.split(",");
                    if(course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1])){
                         System.out.printf("| %-10s| %-50s | %-10s | \n", info[0],info[1],info[2]);
                         isFound = true;
                    }
                }
                System.out.println("+-----------------------------------------------------------------------------+");
                scan12.close();

                if(isFound == true){
                        String courses = "";
                        while (true) {
                            System.out.print("Enter new course code: ");
                            courses = scan.nextLine().toUpperCase();
                            if (course.matches(".*\\d.*")) {
                                System.out.println();
                                System.out.println("Course name cannot contain numbers.");
                                System.out.println();
                                continue;
                            }else if(courses == null || courses.trim().isEmpty()){
                                continue;
                            }else{
                                break;
                            }

                        }


                               String des = "";
                                while (true) {
                                        System.out.print("Enter new description: ");
                                        des = scan.nextLine().toUpperCase();
                                        if(des == null || des.trim().isEmpty()){
                                            continue;
                                        }else{
                                            break;
                                        }
                                }

                      
                        
                         Scanner scan4 = new Scanner(new File("courseYear.txt"));
                            boolean courseExists = false;
                            while (scan4.hasNextLine()) {
                                String data = scan4.nextLine();
                                String[] info = data.split(",");
                                if (info[0].equalsIgnoreCase(courses)&& des.equalsIgnoreCase(info[1])) {
                                    courseExists = true;
									System.out.println("");
                                     System.out.println("Error: This Course already exists!");
                                    System.out.println("");
                                 return;
                                    
                                }
                            }
                            scan4.close();
                            FileWriter writer =  new FileWriter("courseYear.txt");
                            boolean isUpdated = false;
                            for(String users:bsit){
                                String[] info = users.split(",");
                                if(course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1])){
                                    writer.write(courses +"," + des+","+info[2]+"\n");


                                     FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                                    writer2.write(courses + "," + des + "," + info[2] + " Added successfully!\n");
                                    writer2.close();
                                    isUpdated = true;    
                                }else{
                                    writer.write(users+"\n");
                                }
                            }
                             writer.close();

                            Scanner scan121 = new Scanner(new File("course.txt"));
                            boolean makaEdit = false;
                            ArrayList<String> s = new ArrayList<>();
                            while(scan121.hasNextLine()){
                                String data = scan121.nextLine();
                                s.add(data);
                                String[] info = data.split(",");
                                if(info[0].equalsIgnoreCase(course) && info[2].equalsIgnoreCase(description)){
                                    makaEdit = true;
                                }
                            }
                            scan121.close();

                            FileWriter writer3 =  new FileWriter("course.txt");
                            for(String ss:s){
                                String[] info = ss.split(",");
                                if(course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1])){
                                    writer3.write(courses +"," + des+","+info[2]+ "," + info[3] + "\n");    
                                }else{
                                    writer3.write(ss+"\n");
                                }
                            }
                             writer3.close();



                            if(isUpdated == true){
								 System.out.println();
                                    System.out.println("Course edited successfully!");
                                     System.out.println();
                                    
                                  
                                    Scanner scan13 = new Scanner(new File("courseYear.txt"));
                                    System.out.println("+-----------------------------------------------------------------------------+");
                                    System.out.printf("| %-10s| %-50s | %-10s | \n", "CODE", "DESCRIPTION","YEAR");
                                    System.out.println("+-----------------------------------------------------------------------------+");
                                    while(scan13.hasNextLine()){
                                        String data = scan13.nextLine();                                
                                        String[] info = data.split(",");
                                            System.out.printf("| %-10s| %-50s | %-10s | \n", info[0],info[1],info[2]);          
                                    }
                                    System.out.println("+-----------------------------------------------------------------------------+");
                                    scan13.close();
                                     
                                      System.out.println();
                                      return;
                            }
                                     
                           
                }else{
                    System.out.println("Error: course not found!");
                    System.out.println("------------------------");
                    System.out.println();

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

	//edit course year and secntion
	static void editCourseSection(){
        while(true){
            try{
                System.out.println();
                
                System.out.println("****| Edit course | ****");
                System.out.println();
                showCourseYearSection();
                System.out.println("Please input correctly.");
                System.out.println("Input 'Exit' if you want to exit.");
                System.out.println();
                String course = "";
                boolean isCourse = false;
                while (true) {
                    
                    System.out.print("Enter course code: ");
                    course = scan.nextLine();
                    if(course.equalsIgnoreCase("Exit")){
                        return;
                    }else if (course.matches(".*\\d.*")) {
                        System.out.println();
                        System.out.println("Course name cannot contain numbers.");
                         System.out.println();
                        continue;
                    }else if(course == null || course.trim().isEmpty()){
                        continue;
                    }
                    Scanner scan1 = new Scanner(new File("course.txt"));
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isCourse = true;
                            break;
                        }
                    }
                    scan1.close();
                    if(isCourse == false){
                         System.out.println();
                        System.out.println("Course code not found!");
                        System.out.println();
                        continue;   
                       
                    }else{
                         break;
                    }

                }

               
                
                   String description = "";
                   boolean isDescription = false;
                   while (true) {
                        
                        System.out.print("Enter description: ");
                        if(description.equalsIgnoreCase("Exit")){
                            return;
                        }
                        description = scan.nextLine().toUpperCase();
                        if(description == null || description.trim().isEmpty()){
                            continue;
                        }
                            Scanner scan1 = new Scanner(new File("course.txt"));
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(description.equalsIgnoreCase(info[1])){
                                    isDescription = true;
                                    break;
                                }
                            }
                            scan1.close();
                            if(isDescription == false){
                                System.out.println();
                                System.out.println("Description code not found!");
                                System.out.println();
                                continue;   
                            
                            }else{
                                break;
                            }
                   }
                
                int year = 0;
                boolean isYear = false;
                while (true) {
                     System.out.print("Enter year: ");
                     if("Exit".contains(year+"")){
                        return;
                     }
                    try {
                        year = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println();
                        System.out.println("Please enter a valid year.");
                         System.out.println();
                        continue;
                    }
                    Scanner scan2 = new Scanner(new File("course.txt"));
                    while(scan2.hasNextLine()){
                        String data = scan2.nextLine();
                        String[] info = data.split(",");
                        if(info[2].contains(year+"")){
                            isYear = true;
                            break;
                        }
                    }
                    scan2.close();
                    if(isYear == false){
                         System.out.println();
                        System.out.println("Year not found!");
                        System.out.println();
                         continue;
                    }else{
                       break;
                    }
                }

                char section = ' ';
                boolean isSection = false;
                while (true) {
                   
                    System.out.print("Enter section: ");
                    String input = scan.nextLine();

                    if(input == null || input.trim().isEmpty()){
                        continue;
                    }

                    if("Exit".contains(input)){
                        return;
                    }
                  
                    input = input.trim().toUpperCase();

                    if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                        section = input.charAt(0);
                    } else {
                        System.out.println();
                        System.out.println("Please enter a single letter for the section.");
                         System.out.println();
                        continue;
                    }
                    Scanner scan3 = new Scanner(new File("course.txt"));
                    while(scan3.hasNextLine()){
                        String data = scan3.nextLine();
                        String[] info = data.split(",");
                        if(info[3].contains(section+"")){
                            isSection = true;
                            break;
                        }
                    }
                    scan3.close();
                    if(isSection == false){
                         System.out.println();
                        System.out.println("Error: Section not found!");
                        System.out.println();
                        continue;

                       
                    }else{
                        break;
                    }
                    
                }


                ArrayList<String> bsit = new ArrayList<>();
                boolean isFound = false;
                Scanner scan12 = new Scanner(new File("course.txt"));
                System.out.println("+------------------------------------------------------------------------------------------+");
                System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", "CODE", "DESCRIPTION","YEAR","SECTION");
                System.out.println("+------------------------------------------------------------------------------------------+");
                while(scan12.hasNextLine()){
                    String data = scan12.nextLine();
                    bsit.add(data);
                    String[] info = data.split(",");
                    if(course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1]) && info[2].contains(year+"") && info[3].equalsIgnoreCase(section+"")){
                         System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]);
                         isFound = true;
                    }
                }
                System.out.println("+------------------------------------------------------------------------------------------+");
                scan12.close();

                if(isFound == true){
                        int years = 0;
                        while (true) {
                            System.out.print("Enter new year: ");
                            try {
                                years = Integer.parseInt(scan.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid year.");
                            }
                        }

                        char sections;
                        while (true) {
                            System.out.print("Enter new section: ");
                            String input = scan.nextLine().trim().toUpperCase();

                            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                                sections = input.charAt(0);
                                break;
                            } else {
                                System.out.println("Please enter a single letter for the section.");
                            }
                        }
                         Scanner scan4 = new Scanner(new File("course.txt"));
                            boolean courseExists = false;
                            while (scan4.hasNextLine()) {
                                String data = scan4.nextLine();
                                String[] info = data.split(",");
                                if (info[0].equalsIgnoreCase(course)&& description.equalsIgnoreCase(info[1]) && Integer.parseInt(info[2].trim()) == year && info[3].charAt(0) == section) {
                                    courseExists = true;
									System.out.println("");
                                     System.out.println("Error: This Course already exists!");
                                    System.out.println("");
                                 continue;
                                    
                                }
                            }
                            scan4.close();
                            FileWriter writer =  new FileWriter("course.txt");
                            boolean isUpdated = false;
                            for(String users:bsit){
                                String[] info = users.split(",");
                                if(course.equalsIgnoreCase(info[0]) && description.equalsIgnoreCase(info[1]) && info[2].contains(year+"") && info[3].equalsIgnoreCase(section+"")){
                                    writer.write(info[0] +"," + info[1]+","+years+","+sections+"\n");


                                     FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                                    writer2.write(info[0] + "," + info[1] + "," + years + "," + sections + " Added successfully!\n");
                                    writer2.close();
                                    isUpdated = true;    
                                }else{
                                    writer.write(users+"\n");
                                }
                            }
                             writer.close();
                            if(isUpdated == true){
								 System.out.println();
                                    System.out.println("Course and year edited successfully!");
                                     System.out.println();

                                    
                                        
                                        Scanner scan16 = new Scanner(new File("course.txt"));
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", "CODE", "DESCRIPTION","YEAR","SECTION");
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        while(scan16.hasNextLine()){
                                            String data = scan16.nextLine();
                                           
                                            String[] info = data.split(",");
                                            
                                                System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]);
                                        
                                            
                                        }
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        scan16.close();
                                                            
                                      return;
                            }
                                     
                           
                }else{
                    System.out.println("Error: course not found!");
                    System.out.println("------------------------");
                    System.out.println();

                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
	//delete course
	static void deleteCourse(){
		try{

		
				System.out.println();
                
                System.out.println();
                System.out.println("****| Delete course | ****");
                 System.out.println();
                  Scanner scan16 = new Scanner(new File("course.txt"));
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", "CODE", "DESCRIPTION","YEAR","SECTION");
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        while(scan16.hasNextLine()){
                                            String data = scan16.nextLine();
                                           
                                            String[] info = data.split(",");
                                            
                                                System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]);
                                        
                                            
                                        }
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        scan16.close();
                                         System.out.println("Please input correctly.");
                                        System.out.println("Input 'Exit' if you want to exit");
                                         System.out.println();
				ArrayList<String> list = new ArrayList<>();
				String code = "";
				String des = "";
				String year = "";
				String section = "";
				 while(true){

					boolean isCode = false;
					while(true){
						
						System.out.print("Enter course code: ");
					  	code = scan.nextLine();
                        if(code.equalsIgnoreCase("exit")){
                            return;
                        }
						Scanner scan1 = new Scanner(new File("course.txt"));
						while(scan1.hasNextLine()){
							String data = scan1.nextLine();
							list.add(data);
							String[] info = data.split(",");
							if(info[0].equalsIgnoreCase(code)){
								isCode = true;
							}
						}
					 	 scan1.close();
						if(isCode == true){
							break;
						}else{
							 System.out.println();
							 System.out.println("Course code not found!: ");
							  System.out.println();
							  continue;
						}

					}

					boolean isDes = false;
					while(true){
						System.out.print("Enter description: ");
					  	des = scan.nextLine();

						Scanner scan1 = new Scanner(new File("course.txt"));
						while(scan1.hasNextLine()){
							String data = scan1.nextLine();
							String[] info = data.split(",");
							if(info[1].equalsIgnoreCase(des)){
								isDes = true;
							}
						}
					 	 scan1.close();
						if(isDes == true){
							break;
						}else{
							 System.out.println();
							 System.out.println("Description code not found!: ");
							  System.out.println();
							  continue;
						}

					}
					boolean isYear = false;
					while(true){
						 System.out.print("Enter year: ");
					  	year = scan.nextLine();

						Scanner scan1 = new Scanner(new File("course.txt"));
						while(scan1.hasNextLine()){
							String data = scan1.nextLine();
							String[] info = data.split(",");
							if(info[2].equalsIgnoreCase(year)){
								isYear = true;
							}
						}
					 	 scan1.close();
						if(isYear == true){
							break;
						}else{
							 System.out.println();
							 System.out.println("Year code not found!: ");
							  System.out.println();
							  continue;
						}

					}

					boolean isSection = false;
					while(true){
						   System.out.print("Enter section: ");
					  		section = scan.nextLine();

						Scanner scan1 = new Scanner(new File("course.txt"));
						while(scan1.hasNextLine()){
							String data = scan1.nextLine();
							String[] info = data.split(",");
							if(info[3].equalsIgnoreCase(section)){
								isSection = true;
							}
						}
					 	 scan1.close();
						if(isSection == true){
							break;
						}else{
							 System.out.println();
							 System.out.println("Section not found! code not found!: ");
							  System.out.println();
							  continue;
						}

					}
					  
					if(isCode == true && isDes == true && isYear == true & isSection){
						FileWriter writer = new FileWriter("course.txt");
						boolean isDeleted = false;
						for(String l: list){
							String[] info = l.split(",");
							if(info[0].equalsIgnoreCase(code) && info[1].equalsIgnoreCase(des) && info[2].equalsIgnoreCase(year) && info[3].equalsIgnoreCase(section)){
								writer.write("");
								isDeleted = true;
							}else{
								writer.write(l + "\n");
							}

						}
						writer.close();
						if(isDeleted == true){
							 System.out.println();
							 System.out.println("Course Deleted successfully!");
							  System.out.println();
                               Scanner scan15 = new Scanner(new File("course.txt"));
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", "CODE", "DESCRIPTION","YEAR","SECTION");
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        while(scan15.hasNextLine()){
                                            String data = scan15.nextLine();
                                           
                                            String[] info = data.split(",");
                                            
                                                System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]);
                                        
                                            
                                        }
                                        System.out.println("+------------------------------------------------------------------------------------------+");
                                        scan15.close();

							  FileWriter writer1 = new FileWriter("auditLog.txt", true);
							  writer1.write("Course Deleted successfully! \n");
							  writer1.close();
							  return;
						}

					}
					  
		
				 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//show courses BY SECTION AND YEAR
	static void showCourses(int year){
        try{
                    Scanner scan12 = new Scanner(new File("course.txt"));
                    System.out.println("Course sections");
                    System.out.println("+------------------------------------------------------------------------------------------+");
                    System.out.printf("| %-10s| %-50s | %-10s | %-10s | \n", "CODE", "DESCRIPTION","YEAR","SECTION");
                   System.out.println("+------------------------------------------------------------------------------------------+");
                    while(scan12.hasNextLine()){
                        String data = scan12.nextLine();
                        String[] info = data.split(",");
                        if(info[2].contains(year+"")){
                            System.out.printf("| %-10s| %-50s | %-10s | %-10s |\n", info[0],info[1],info[2],info[3]); 
                        }
                                           
                    }
                   System.out.println("+------------------------------------------------------------------------------------------+");
                    scan12.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("Show coures" + "\n");
                        writer2.close();
                        return; 
        }catch(Exception e){
            e.printStackTrace();
        }
                
    }

// Subject management menu
	static void subject_management_menu(){
         while(true){
			System.out.println();
           System.out.println("****| Manage subject | ****");  
		   System.out.println();  
		   System.out.println("1. View subjects by course & year"); 
            System.out.println("2. Add subject by course & year");
			System.out.println("3. Update subject by course & year");
            System.out.println("4. Delete subject by course & year");
			System.out.println("5. Back");
            System.out.println();
            System.out.print("Enter your choice(1-5): ");
            String choice = scan.nextLine();
            switch(choice){
                case "1":	
				showSubjectperCourse();
                    continue;
                case "2":
					add_sub();
                     continue;
                case "3":
                    editSub();  
                    continue;
				case "4":
                    deleteSub();
                    continue;
				case "5":
                    return; 
                default:
							System.out.println();
							System.out.println("Error: Invalid choice!");
                            System.out.println();
                            continue;


            }
        }
    }
	//add sub
	static void add_sub() {
        try{
            
            while(true){
                 String year = "";
                 String course = "";
                 while(true){
                    System.out.println();
                    System.out.println("****| Add subject by course & year | ****");  
                    System.out.println();  
                    showCourseYear();   
                    System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                     System.out.println();  
                   

                    
                    System.out.print("Enter course code: ");
                    course = scan.nextLine().toUpperCase();

                    if(course.equalsIgnoreCase("Exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Course code not found!");
                        System.out.println();
                    }
                 }
                
                while(true){
                    System.out.print("Enter year: ");
                    year = scan.nextLine();

                     if(year.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(year.equalsIgnoreCase(info[2])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Year not found!");
                        System.out.println();
                    }
                 }


                while(true){
                   
                      System.out.println();
                      System.out.println("Subejcts");
                    viewSubjectbyCourseYear(course, year);
                        System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                     System.out.println(); 

                         String code  = "";
                        while(true){
                            System.out.print("Enter subject code: ");
                            code = scan.nextLine().toUpperCase();

                            if(code.equalsIgnoreCase("exit")){
                                    return;
                            }

                            if(code == null || code.trim().isEmpty()){
                                System.out.println("");
                                System.out.println("Subject code is empty!");
                                System.out.println();
                                continue;
                            }
                            Scanner scan1 = new Scanner(new File("subject.txt"));
                            boolean isFound = false;
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(code.equalsIgnoreCase(info[2])){
                                    isFound = true;
                                }
                            }
                            scan1.close();
                            if(isFound == true){
                                 System.out.println("");
                                System.out.println("Subject code already exist!");
                                System.out.println();
                                continue;
                            }else{
                                break;
                            }
                        }


                        String description  = "";
                        while(true){
                            System.out.print("Enter description: ");
                            description = scan.nextLine();

                            if(description.equalsIgnoreCase("exit")){
                                    return;
                            }
                             if(description == null || description.trim().isEmpty()){
                                System.out.println("");
                                System.out.println("Subject code is empty!");
                                System.out.println();
                                continue;
                            }
                            Scanner scan1 = new Scanner(new File("subject.txt"));
                            boolean isFound = false;
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                String[] info = data.split(",");
                                if(description.equalsIgnoreCase(info[3])){
                                    isFound = true;
                                }
                            }
                            scan1.close();
                            if(isFound == true){
                                 System.out.println("");
                                System.out.println("Description already exist!");
                                System.out.println();
                                continue;
                            }else{
                                break;
                            }
                        }
                        

                        

                        double units = 0;
                        while (true) {
                            System.out.print("Enter units (2-3): ");
                            
                            try {
                                
                                units = Double.parseDouble(scan.nextLine());
                                if(units > 3 || units == 1){
                                    continue;
                                }else{
                                    break;
                                }
                                    
                            
                            } catch (NumberFormatException e) {
                                System.out.println();
                                System.out.println("Please enter a valid unit.");
                                System.out.println();
                            }
                        }

                        FileWriter writer = new FileWriter("subject.txt", true);
                        writer.write(course + "," + year + "," + code + "," + description + "," + units + "\n");
                        writer.close();

                                System.out.println(course + " " + year);
                                System.out.println(code + " Added successfully! \n");
                               
                        
                        FileWriter writer1 = new FileWriter("auditLog.txt");
                        writer1.write(code + " Added successfully! \n");
                        writer1.close();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }

    //show ang subjct per section
    static void showSubjectperCourse(){
        try{
             String year = "";
                 String course = "";
                 while(true){
                    System.out.println();
                    System.out.println("****| Add subject | ****");  
                    System.out.println();  
                    showCourseYear();   
                    System.out.println("Input 'Exit' to exit");

                    
                    System.out.print("Enter course code to update subject: ");
                    course = scan.nextLine();

                    if(course.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Course code not found!");
                        System.out.println();
                    }
                 }
                
                while(true){
                    System.out.print("Enter year: ");
                    year = scan.nextLine();

                     if(year.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(year.equalsIgnoreCase(info[2])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Year not found!");
                        System.out.println();
                    }
                 }
                 System.out.println("");
                 System.out.println("Subejcts");
                 System.out.println("Course :" + course + "               Year : " + year);
                  
                  
                 viewSubjectbyCourseYear(course , year);
                 return;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //EDIT SUBJECT
    static void editSub(){
        try{
                
                String year = "";
                 String course = "";
                 String newdescription  = "";
                  String subcode  = "";
                 ArrayList<String> s = new ArrayList<>();
                 while(true){
                    System.out.println();
                    System.out.println("****| Update subject | ****");  
                    System.out.println();  
                    showCourseYear();   
                    System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                    System.out.println(); 

                    
                    System.out.print("Enter course code to update subejct: ");
                    course = scan.nextLine().toUpperCase();

                    if(course.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Course code not found!");
                        System.out.println();
                    }
                 }
                
                while(true){
                    System.out.print("Enter year: ");
                    year = scan.nextLine();

                     if(year.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(year.equalsIgnoreCase(info[2])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Year not found!");
                        System.out.println();
                    }
                 }

                 while(true){
                     System.out.println("");
                    viewSubjectbyCourseYear(course, year);
                    System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                    System.out.println("");
                       System.out.print("Enter subject code to update: ");
                       String code = scan.nextLine();
                    boolean isFound = false;

                    
                    Scanner scan1 = new Scanner(new File("subject.txt"));
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        s.add(data);
                        String[] info = data.split(",");
                        if(code.equalsIgnoreCase(info[2])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                            
                              System.out.println("+-----------------------------------------------------------------------------+");
                                System.out.printf("| %-10s| %-50s | %-10s | \n", "CODE", "DESCRIPTION","UNITS");
                                System.out.println("+-----------------------------------------------------------------------------+");
                                Scanner scan3 = new Scanner(new File("subject.txt"));
                                while(scan3.hasNextLine()){
                                    String data = scan3.nextLine();
                                    String[] info = data.split(",");
                                    if(code.equalsIgnoreCase(info[2])){
                                       System.out.printf("| %-10s| %-50s | %-10s | \n", info[2], info[3],info[4 ]);
                                    }

                                }
                                 System.out.println("+-----------------------------------------------------------------------------+");
                                  System.out.println();
                                scan3.close();
                            while(true){

                               
                                System.out.print("Enter new subject code: ");
                                subcode = scan.nextLine().toUpperCase();

                                if(subcode.equalsIgnoreCase("exit")){
                                        return;
                                }

                                 if(subcode == null || subcode.trim().isEmpty()){
                                    System.out.println("");
                                    System.out.println("Description is empty!");
                                    System.out.println();
                                    continue;
                                }
                                Scanner scan2 = new Scanner(new File("subject.txt"));
                                boolean isFounds = false;
                                boolean isSame = false;
                                String oldCode = "";
                                while(scan2.hasNextLine()){
                                    String data = scan2.nextLine();
                                    String[] info = data.split(",");
                                    
                                    if(subcode.equalsIgnoreCase(info[2])){
                                        oldCode = info[2];
                                        isFounds = true;
                                    }
                                }
                                scan2.close();
                                if(oldCode.equalsIgnoreCase(subcode)){
                                    break;
                                }else if(isFounds == true){
                                    System.out.println("");
                                    System.out.println("Subject code already exist!");
                                    System.out.println();
                                    continue;
                                }else{
                                    break;
                                }
                            }

                             
                            while(true){
                                System.out.print("Enter new description code: ");
                                newdescription = scan.nextLine().toUpperCase();

                                if(newdescription.equalsIgnoreCase("exit")){
                                        return;
                                }
                                if(newdescription == null || newdescription.trim().isEmpty()){
                                    System.out.println("");
                                    System.out.println("Description is empty!");
                                    System.out.println();
                                    continue;
                                }
                                Scanner scan2 = new Scanner(new File("subject.txt"));
                                boolean isFounds = false;
                                 boolean isSame = false;
                                 String oldDes = "";
                                while(scan2.hasNextLine()){
                                    String data = scan2.nextLine();
                                    String[] info = data.split(",");
                                   
                                    if(newdescription.equalsIgnoreCase(info[2])){
                                         oldDes = info[3];
                                        isFounds = true;
                                    }
                                }
                                scan2.close();
                                 if(oldDes.equalsIgnoreCase(newdescription)){
                                    break;
                                }else if(isFounds == true){
                                    System.out.println("");
                                    System.out.println("Description already exist!");
                                    System.out.println();
                                    continue;
                                }else{
                                    break;
                                }
                            }

                            double units = 0;
                            while (true) {
                                System.out.print("Enter units (2-3): ");
                                
                                try {
                                    
                                    units = Double.parseDouble(scan.nextLine());
                                    if(units > 3 || units == 1){
                                        continue;
                                    }else{
                                        break;
                                    }
                                        
                                
                                } catch (NumberFormatException e) {
                                    System.out.println();
                                    System.out.println("Please enter a valid unit.");
                                    System.out.println();
                                }
                            }

                            FileWriter writer = new FileWriter("subject.txt");
                            boolean isGood = false;
                            for(String subjects: s){
                                String[] info = subjects.split(",");
                                if(code.equalsIgnoreCase(info[2])){
                                    writer.write(info[0] + "," + info[1] + "," + subcode + "," + newdescription + "," + units + "\n");
                                    isGood = true;
                                }else{
                                    writer.write(subjects + "\n");
                                }
                            }
                            writer.close();

                            if(isGood == true){
                                    System.out.println();
                                    System.out.println(code + " Updated successfully! \n");
                                     FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                     writer1.write(code + " Updated successfully! \n");
                                     writer1.close();
                                    return;
                            }



                    }else{
                        System.out.println("");
                        System.out.println("Subject code not found!");
                        System.out.println();
                    }



                 }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //delete subject
     static void deleteSub(){
        try{


                 String year = "";
                 String course = "";
                 String newdescription  = "";
                  String subcode  = "";
                 
                 while(true){
                    System.out.println();
                    System.out.println("****| Delete subject | ****");  
                    System.out.println(); 
                    System.out.println("Course"); 
                    showCourseYear();   
                     System.out.println("Please input correctly.");
                    System.out.println("Input 'Exit' if you want to exit");
                     System.out.println(); 

                    
                    System.out.print("Enter course code to delete subject: ");
                    course = scan.nextLine();

                    if(course.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(course.equalsIgnoreCase(info[0])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Course code not found!");
                        System.out.println();
                    }
                 }
                
                while(true){
                    System.out.print("Enter year: ");
                    year = scan.nextLine();

                     if(year.equalsIgnoreCase("exit")){
                        return;
                    }
                    Scanner scan1 = new Scanner(new File("courseYear.txt"));
                    boolean isFound = false;
                    while(scan1.hasNextLine()){
                        String data = scan1.nextLine();
                        String[] info = data.split(",");
                        if(year.equalsIgnoreCase(info[2])){
                            isFound = true;
                        }

                    }
                    scan1.close();
                    if(isFound == true){
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("Year not found!");
                        System.out.println();
                    }
                 }

                 while(true){
                        System.out.println();
                         System.out.println("Subject");
                        viewSubjectbyCourseYear(course , year);
                        System.out.println("Please input correctly.");
                        System.out.println("Input 'Exit' if you want to exit");
                        System.out.println(); 
                        System.out.print("Enter subject code to delete: ");
                        String code = scan.nextLine();

                         if(code.equalsIgnoreCase("exit")){
                            return;
                        }
                        boolean deleted = false;
                        Scanner scan1 = new Scanner(new File("subject.txt"));
                        ArrayList<String> s = new ArrayList<>();
                        while(scan1.hasNextLine()){
                            String data = scan1.nextLine();
                            s.add(data);
                            String [] info = data.split(",");

                            if(code.equalsIgnoreCase(info[2])){
                                deleted = true;
                            }
                        }
                        scan1.close();
                        boolean isGood = false;
                        FileWriter writer = new FileWriter("subject.txt");
                        for(String subject: s){
                            String[] info1 = subject.split(",");
                            if(code.equalsIgnoreCase(info1[2])){
                                writer.write("");
                                isGood = true;
                            }else{
                                writer.write(subject + "\n");
                            }
                        }
                        writer.close();

                        if(isGood == true){
                            System.out.println("");
                            System.out.println("Subject deleted successfully!");
                            System.out.println();

                            FileWriter writer1 = new FileWriter("auditLog.txt", true);
                            writer1.write("Subject deleted successfully! \n");
                            writer1.close();
                            continue;

                        }
                        
                 }

        
        }catch(Exception e){
            e.printStackTrace();
        }
    }


	//view subject by course & year
	public static void viewSubjectbyCourseYear(String course, String year){
		try{
            Scanner scan1 = new Scanner(new File("subject.txt"));
             System.out.println("+-----------------------------------------------------------------------------+");
            System.out.printf("| %-10s| %-50s | %-10s | \n", "CODE", "DESCRIPTION","UNITS");
             System.out.println("+-----------------------------------------------------------------------------+");
            while(scan1.hasNextLine()){
                String data = scan1.nextLine();
                String[] info = data.split(",");
                if(course.equalsIgnoreCase(info[0]) && year.equalsIgnoreCase(info[1])){
                    System.out.printf("| %-10s| %-50s | %-10s | \n", info[2], info[3],info[4]);
                }
            }
            scan1.close();
            System.out.println("+-----------------------------------------------------------------------------+");
            return;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//view/ edit information
	 static void edit_information_student(){
        while(true){
            try{
                    System.out.println();
					System.out.println("**** | View/Edit student | ****");
					System.out.println();
                    System.out.println("1. View/Update Active student information ");
                     System.out.println("2. View/Update Inactive student information ");
                    System.out.println("3. Update status");
                    System.out.println("4. Back");

                    System.out.println();
                    System.out.print("Enter your choice(1-2): ");
                    String choice = scan.nextLine();
                    switch(choice){
                        case "1":
                            System.out.println();
                            viewActiveStudent();
                            edit_student();
                            continue;
                        case "2":
                             System.out.println();
                            viewInactiveStudent();
                            edit_student();
                            continue;
                         case "3":
                             System.out.println();
                             editStatus();
                             continue;
                        case "4":
                             return;
                        default:
                            System.out.println("Error: Invalid choice!");
                            System.out.println("----------------------");
                            System.out.println();
                            continue;
                    }
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

	//edit status
	static void editStatus(){
        while(true){
            try{
               String c = "";
                 boolean prompt = false;
                
                  ArrayList<String> st = new ArrayList<>();
                while(true){
                     System.out.println();
                    System.out.println("****| Edit status | ****");
                     System.out.println();
                     showAllStudents();
                       System.out.println();
                    while(true){
                         String dataSudlanan = "";
                            System.out.print("Enter student ID to edit: ");
                            scan = new Scanner(System.in);
                            String id = scan.nextLine();
                    
                            Scanner scan1 = new Scanner(new File("student.txt"));
                           
                            while(scan1.hasNextLine()){
                                String data = scan1.nextLine();
                                st.add(data);
                                String[] info = data.split(",");
                                if(info[0].equalsIgnoreCase(id)){
                                    prompt = true;
                                }
                            }
                           scan1.close();
                            if(prompt == true){
                                             boolean isGood = false;
                                                while(true){
                                                    System.out.print("Change status (1.Active or 2.Inactive): ");
                                                    c = scan.nextLine();
                                                    if(c.equalsIgnoreCase("1")){
                                                        c = "Active";  
                                                        isGood = true; 
                                                            break;        
                                                    }else if(c.equalsIgnoreCase("2")){
                                                        c = "Inactive";
                                                            isGood = true;
                                                            break;
                                                    }else{
                                                        System.out.println("Error: Invalid choice!\nChoose (1. Active or 2. Inactive)");
                                                        System.out.println("---------------------------------");
                                                        System.out.println();
                                                        continue;
                                                    }
                                                }
                                                    if(isGood == true){
                                                            boolean isChangeStatus = false;
                                                                FileWriter writer = new FileWriter("student.txt");
                                                                for(String ha: st){
                                                                    String[] info = ha.split(",");
                                                                    if(id.equalsIgnoreCase(info[0])){
                                                                        writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," +info[5] + "," +info[6] + "," +info[7] + "," +info[8] + "," +info[9] + "," +info[10] + ","+ c +  "\n" );
                                                                        isChangeStatus = true;
                                                                    }else{
                                                                        writer.write(ha +"\n");
                                                                    }
                                                                }
                                                                writer.close();
                                                            if(isChangeStatus == true){
                                                                System.out.println("Status update successfully!");
                                                                System.out.println("---------------------------");
                                                                System.out.println();
                                                                return;
                                                            }
                                                    }

                                
                            }else{
                                System.out.println("Error: Student not found!");
                                System.out.println("----------------------");
                                System.out.println();
                                continue;
                            }
                    }
                    
                            
                    
                }



            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

	//show all student
	static void showAllStudents(){
        try{
            Scanner scan9 = new Scanner(new File("student.txt"));
             System.out.println();
            System.out.println("Student Active");
          	 System.out.println("+==============================================================================================+");
            System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s | \n", "ID", "FIRSTNAME","MIDDLENAME","LASTNAME", "STATUS");
            System.out.println("+==============================================================================================+");
            while(scan9.hasNextLine()){
                String data = scan9.nextLine();
                String[] info = data.split(",");

                    System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s |\n",info[0],info[1],info[2],info[3],info[11]);            
            }
            System.out.println("+==============================================================================================+");
            scan9.close();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("View active student \n");
                        writer2.close();
                        return; 
        
       }catch(Exception e){
        e.printStackTrace();    
       }
    }

	//edit student Informatiom
	static void edit_student(){
		try{
				String ids = "";
				String lname = "";
                 String fname = "";
                 String mname = "";
                 String birthdateStr = "";
                 String age = "";
                 String contact = "";
                String gender ="";
                String address = "";
                String email = "";
                String password = "";
                String status = "";
			while(true){
				System.out.println("Input 'Exit' to exit");
				 System.out.print("Enter student id to edit: ");
				 String id = scan.nextLine();

				 if(id.equalsIgnoreCase("exit")){
					return;
				 }

				ArrayList<String> list = new ArrayList<>();
				 Scanner scan1 = new Scanner(new File("student.txt"));
				 boolean studentFound = false;
				 while(scan1.hasNextLine()){
					String data = scan1.nextLine();
					list.add(data);
					String[] info = data.split(",");
					if(id.equalsIgnoreCase(info[0])){
						studentFound = true;
						ids = info [0];
						lname= info [1];
						fname= info [2];
						mname= info [3];
						birthdateStr= info [4];
						age= info [5];
					    gender	= info [6];
						contact= info [7];
						address= info [8];
						email = info [9];
						password= info [10];
						status = info [11];
					}
				 }
					scan1.close();
				 if(studentFound == true){

						System.out.println();
						System.out.println("Edit Information of student");                                                                                     
						System.out.println("-------------------------->");
						System.out.println();
						System.out.println("1. Last name: " + lname + "  First name: " + fname+ "  Middle name: " + mname);
						System.out.println("2. Birthday " + birthdateStr +" Age: " + age);
						System.out.println("3. Gender: " + gender);
						System.out.println("4. Contact: " + contact);
						System.out.println("5. Address: " + address);
						System.out.println("6. Back");
						System.out.println();
						System.out.print("Enter your choice(1-6): ");
						String c = scan.nextLine();

						switch(c){
							case "1":

								 String nlname = "";
                                while (true) {
                                    System.out.print("Enter new Last name: ");
                                    nlname = scan.nextLine();
                                    if (naayNumber(nlname) || nlname == null || nlname.trim().isEmpty()) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }

                                String nfname = "";
                                while (true) {
                                    System.out.print("Enter new First name: ");
                                    nfname = scan.nextLine();
                                    if (naayNumber(nfname) || nfname == null || nfname.trim().isEmpty()) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }

                                String nmname = "";
                                while (true) {
                                    System.out.print("Enter new Middle name: ");
                                    nmname = scan.nextLine();
                                    if (naayNumber(nmname)) {
                                        continue;
                                    } else {
                                        break;
                                    }
                                }

                                boolean isNaa = false;
                                ArrayList<String> s = new ArrayList<>();
                                Scanner scan7 = new Scanner(new File("student.txt"));
                                while (scan7.hasNextLine()) {
                                    String data = scan7.nextLine();
                                    s.add(data);
                                    String[] info = data.split(",");
                                    if (info[1].equalsIgnoreCase(nlname) && info[2].equalsIgnoreCase(nfname) && info[3].equalsIgnoreCase(nmname)) {
                                        isNaa = true;
                                    }
                                }
                                scan7.close();

                                if (isNaa == true) {
                                    System.out.println();
                                    System.out.println("Error: This student already exists!");
                                    System.out.println();
                                    return;
                                } else {
                                    FileWriter writer = new FileWriter("student.txt");
                                    boolean isGood = false;
                                    for (String l : s) {
                                        String[] info = l.split(",");
                                        if (ids.equalsIgnoreCase(info[0])) {
                                            info[9] = nlname + nfname + "@bc.edu.ph";
                                            writer.write(info[0] + "," + nlname + "," + nfname + "," + nmname + "," + info[4] + "," + info[5] + "," + info[6] + "," + info[7] + "," + info[8] + "," + info[9] + "," + info[10] + "," + info[11] + "\n");
                                            isGood = true;
                                        } else {
                                            writer.write(l + "\n");
                                        }
                                    }
                                    writer.close();
                                    if (isGood == true) {
                                        System.out.println();
                                        System.out.println("Firstname, Lastname, and Middle name updated successfully");
                                        System.out.println();

                                        FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                        writer1.write("Firstname, Lastname, and Middle name updated successfully \n");
                                        writer1.close();
                                        return;
                                    }
                                }
                            
                            case "2":
                                    String birthdateStrs = "";
                                    int ages = 0;
                                    while (true) {
                                        try {
                                            System.out.print("Enter new birthdate (YYYY-MM-DD): ");
                                            birthdateStrs = scan.nextLine();

                                            if (birthdateStrs.equalsIgnoreCase("exit")) {
                                                return;
                                            }

                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            sdf.setLenient(false);
                                            Date birthdate = sdf.parse(birthdateStrs);

                                            Calendar cal = Calendar.getInstance();
                                            cal.setTime(birthdate);
                                            int birthYear = cal.get(Calendar.YEAR);
                                            int birthMonth = cal.get(Calendar.MONTH) + 1;
                                            int birthDay = cal.get(Calendar.DAY_OF_MONTH);
                                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                                            ages = currentYear - birthYear;

                                            // Validate birthdate
                                            if (birthYear > currentYear || birthMonth > 12 || birthMonth < 1 || birthDay > 31 || birthDay < 1) {
                                                System.out.println("Invalid birthdate. Please enter a valid date.");
                                                continue;
                                            } else {
                                                boolean isNaaa = false;
                                                ArrayList<String> lists = new ArrayList<>();
                                                Scanner scan8 = new Scanner(new File("student.txt"));
                                                while(scan8.hasNextLine()) {
                                                    String data = scan8.nextLine();
                                                    lists.add(data);
                                                    String[] info = data.split(",");
                                                    if (info[4].equalsIgnoreCase(birthdateStr)&& ids.equalsIgnoreCase(info[0])) {
                                                        isNaaa = true;
                                                    }
                                                }
                                                scan8.close();

                                                if (isNaaa == true) {
                                                    FileWriter writer = new FileWriter("student.txt");
                                                    boolean isGood = false;
                                                    for (String l : lists) {
                                                        String[] info = l.split(",");
                                                        if (birthdateStr.equalsIgnoreCase(info[4])&& ids.equalsIgnoreCase(info[0])) {
                                                            writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + birthdateStrs + "," + ages + "," + info[6] + "," + info[7] + "," + info[8] + "," + info[9] + "," + info[10] + "," + info[11] + "\n");
                                                            isGood = true;
                                                        } else {
                                                            writer.write(l + "\n");
                                                        }
                                                    }
                                                    writer.close();
                                                    if (isGood) {
                                                        System.out.println();
                                                        System.out.println("Birthdate and age updated successfully");
                                                        System.out.println();

                                                        FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                                        writer1.write("Birthdate and age updated successfully \n");
                                                        writer1.close();
                                                        return;
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Error: Invalid birthdate format.");
                                            continue;
                                        }
                                    }

                            case "3":
                               String genders ="";
                                while(true){
                                    System.out.print("Enter new Gender (1. Male or 2.Female): ");
                                    genders = scan.nextLine();
                                    if(genders.equalsIgnoreCase("exit")){
                                                return;
                                    }

                                    if(genders.equals("1")){
                                        genders = "Male";
                                        break;
                                    }else if(genders.equals("2")){
                                        genders = "Female";
                                        break;
                                    }else{
                                        continue;
                                    }
                                }

                                 boolean isNaaa = false;
                                ArrayList<String> lists = new ArrayList<>();
                                Scanner scan8 = new Scanner(new File("student.txt"));
                                while (scan8.hasNextLine()) {
                                    String data = scan8.nextLine();
                                    lists.add(data);
                                    String[] info = data.split(",");
                                    if (info[6].equalsIgnoreCase(gender)&& ids.equalsIgnoreCase(info[0])) {
                                        isNaaa = true;
                                    }
                                }
                                scan8.close();

                                if (isNaaa) {
                                    FileWriter writer = new FileWriter("student.txt");
                                    boolean isGood = false;
                                    for (String l : lists) {
                                        String[] info = l.split(",");
                                        if (gender.equalsIgnoreCase(info[6])&& ids.equalsIgnoreCase(info[0])) {
                                            writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + "," + genders + "," + info[7] + "," + info[8] + "," + info[9] + "," + info[10] + "," + info[11] + "\n");
                                            isGood = true;
                                        } else {
                                            writer.write(l + "\n");
                                        }
                                    }
                                    writer.close();
                                    if (isGood) {
                                        System.out.println();
                                        System.out.println("Gender updated successfully");
                                        System.out.println();

                                        FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                        writer1.write("Gender updated successfully \n");
                                        writer1.close();
                                        return;
                                    }
                                }


                            case "4":
                                String contactes = "";
                                while(true){
                                    System.out.print("Enter contact number: ");
                                    String contacts = scan.nextLine();
                                    if(contacts.equalsIgnoreCase("exit")){
                                                return;
                                    }
                                    if(contacts.length() == 11 && contacts.matches("\\d{11}")){
                                        contactes = contacts;
                                        break;
                                    }else{
                                        System.out.println("Error: Invalid contact number!");
                                        continue;
                                    }

                                }

                                boolean isNaaaa = false;
                                ArrayList<String> listss = new ArrayList<>();
                                Scanner scan9 = new Scanner(new File("student.txt"));
                                while (scan9.hasNextLine()) {
                                    String data = scan9.nextLine();
                                    listss.add(data);
                                    String[] info = data.split(",");
                                    if (info[7].equalsIgnoreCase(contact) && ids.equalsIgnoreCase(info[0])) {
                                        isNaaaa = true;
                                    }
                                }
                                scan9.close();

                                if (isNaaaa) {
                                    FileWriter writer = new FileWriter("student.txt");
                                    boolean isGood = false;
                                    for (String l : listss) {
                                        String[] info = l.split(",");
                                        if (info[7].equalsIgnoreCase(contact) && ids.equalsIgnoreCase(info[0])) {
                                            writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + "," + info[6] + "," + contactes + "," + info[8] + "," + info[9] + "," + info[10] + "," + info[11] + "\n");
                                            isGood = true;
                                        } else {
                                            writer.write(l + "\n");
                                        }
                                    }
                                    writer.close();
                                    if (isGood) {
                                        System.out.println();
                                        System.out.println("Contact updated successfully");
                                        System.out.println();

                                        FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                        writer1.write("Contact updated successfully \n");
                                        writer1.close();
                                        return;
                                    }
                                }

                            case "5":
                                String addresss = "";
                                while(true){
                                    System.out.print("Enter Address\nPlease dont use (" + "," +"): ");
                                    addresss = scan.nextLine();
                                    if(addresss.equalsIgnoreCase("exit")){
                                                return;
                                    }
                                    if(addresss == null ||  addresss.trim().isEmpty()){
                                        continue;
                                    }else if(addresss.contains(",")){
                                        System.out.println("Please dont use (" + "," +")");
                                        continue;
                                    }else{
                                        break;
                                    }
                                }
                                boolean isNaaaaa = false;
                                ArrayList<String> listsss = new ArrayList<>();
                                Scanner scan10 = new Scanner(new File("student.txt"));
                                while (scan10.hasNextLine()) {
                                    String data = scan10.nextLine();
                                    listsss.add(data);
                                    String[] info = data.split(",");
                                    if (info[8].equalsIgnoreCase(address) && ids.equalsIgnoreCase(info[0])) {
                                        isNaaaaa = true;
                                    }
                                }
                                scan10.close();

                                if (isNaaaaa) {
                                    FileWriter writer = new FileWriter("student.txt");
                                    boolean isGood = false;
                                    for (String l : listsss) {
                                        String[] info = l.split(",");
                                        if (info[8].equalsIgnoreCase(address) && ids.equalsIgnoreCase(info[0])) {
                                            writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + "," + info[6] + "," + info[7] + "," + addresss + "," + info[9] + "," + info[10] + "," + info[11] + "\n");
                                            isGood = true;
                                        } else {
                                            writer.write(l + "\n");
                                        }
                                    }
                                    writer.close();
                                    if (isGood) {
                                        System.out.println();
                                        System.out.println("Address updated successfully");
                                        System.out.println();

                                        FileWriter writer1 = new FileWriter("auditLog.txt", true);
                                        writer1.write("Address updated successfully \n");
                                        writer1.close();
                                        return;
                                    }
                                }
                            case "6":
                                return;
                            default:
                                System.out.println("");
                                System.out.println("Invalid choice");
                                System.out.println("");
                                continue;
                        }
				 }else{
					System.out.println("");
					 System.out.println("Student not found!");
					 System.out.println("");
					 continue;
				 }
			}


		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// view inactive student
	static void viewInactiveStudent(){
        try{
            Scanner scan9 = new Scanner(new File("student.txt"));
             System.out.println();
            System.out.println("Student Inactive");
          	 System.out.println("+==============================================================================================+");
            System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s | \n", "ID", "FIRSTNAME","MIDDLENAME","LASTNAME", "STATUS");
            System.out.println("+==============================================================================================+");
            while(scan9.hasNextLine()){
                String data = scan9.nextLine();
                String[] info = data.split(",");
                if(info[11].equalsIgnoreCase("Inactive")){
                    System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s |\n",info[0],info[1],info[2],info[3],info[11]);
                }
                      
            }
            System.out.println("+==============================================================================================+");
            scan9.close();

                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write("View inactive student \n");
                        writer2.close();
                        return; 
       }catch(Exception e){
        e.printStackTrace();    
       }
    }

	//view active student
	static void viewActiveStudent() {
        try {
            Scanner fileScanner = new Scanner(new File("student.txt"));

            System.out.println();
            System.out.println("Active Students");
            System.out.println("+==============================================================================================+");
            System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s | \n", "ID", "FIRST NAME", "MIDDLE NAME", "LAST NAME", "STATUS");
            System.out.println("+==============================================================================================+");

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] info = data.split(",");
                if (info.length >= 12 && info[11].equalsIgnoreCase("Active")) {
                    // Displaying details of active students
                    System.out.printf("| %-10s| %-20s | %-20s | %-20s | %-11s |\n", info[0], info[1], info[2], info[3], info[11]);
                }
            }

            System.out.println("+==============================================================================================+");
            fileScanner.close();

            // Writing to audit log
            FileWriter writer = new FileWriter("auditLog.txt", true);
            writer.write("Viewed active students\n");
            writer.close();
            
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
    }

//add student account
	static void add_student_account(){
        
         while (true) {
                try {
                        System.out.println();
						System.out.println("**** | Add student | ****");
						System.out.println();
                        System.out.println("Input 'Exit' to exit");
                        System.out.println();
                    
                    String lastId = getLastIdFromFile();

                   
                    int num = 1;
                    if (lastId != null) {
                        String[] parts = lastId.split("-");
                        num = Integer.parseInt(parts[1]) + 1;
                    }
            
                    FileWriter writer = new FileWriter("student.txt", true);
                    Scanner scan2 = new Scanner(new File("student.txt"));
                
                
                         String id = "";

                
                    
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                        int year = Integer.parseInt(dateFormat.format(currentDate));
                                          
                        String numformat = String.format("%04d", num);
                        id = year + "-" + numformat;
                            
                    

                        String lname = "";
                        while(true){
                            System.out.print("Enter Last name: ");
                            lname = scan.nextLine();
                            if(lname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(lname)){
                                    continue;
                                }else if(lname == null || lname.trim().isEmpty()){
                                    continue;
                                }else{
                                    break;
                                }
                        }
                        String fname = "";
                        while(true){
                            System.out.print("Enter First name: ");
                            fname = scan.nextLine();
                             if(fname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(fname)){
                                    continue;
                                }else if(fname == null || fname.trim().isEmpty()){
                                    continue;
                                }else{
                                    break;
                                }
                        }

                        String mname = "";
                        while(true){
                            System.out.print("Enter Middle name: ");
                            mname = scan.nextLine();
                            if(mname.equalsIgnoreCase("exit")){
                                return;
                            }
                                if(naayNumber(mname)){
                                    continue;
                                }else{
                                    break;
                                }
                        }
                        while(scan2.hasNextLine()){
                            String data = scan2.nextLine();
                            String[] info = data.split(",");
                            if(info[1].equalsIgnoreCase(lname) && info[2].equalsIgnoreCase(fname) && info[3].equalsIgnoreCase(mname)){
                                 System.out.println();
                                System.out.println("Error: This student already add!");
                                System.out.println("--------------------------------");
                                System.out.println();
                                return;
                            }       
                        }
                        scan2.close();

                        String birthdateStr = "";
                            int age = 0;
                            while(true) {
                                try {
                                    System.out.print("Enter birthdate (YYYY-MM-DD): ");
                                    birthdateStr = scan.nextLine();

                                    if(birthdateStr.equalsIgnoreCase("exit")){
                                        return;
                                    }
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    sdf.setLenient(false);
                                    Date birthdate = sdf.parse(birthdateStr);

                                
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(birthdate);
                                    int birthYear = cal.get(Calendar.YEAR);
                                    int birthMonth = cal.get(Calendar.MONTH) + 1;
                                    int birthDay = cal.get(Calendar.DAY_OF_MONTH);
                                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                                
                                    if (birthYear > currentYear || birthMonth > 12 || birthMonth < 1 || birthDay > 31 || birthDay < 1) {
                                        System.out.println("Invalid birthdate. Please enter a valid date.");
                                        continue; 
                                    }

                                
                                    age = currentYear - birthYear;
                                    break; 
                                } catch (Exception e) {
                                    System.out.println("Error: Invalid birthdate format.");
                                    continue; 
                                }
                            }

                            System.out.println("Age: " + age);

                       
                        String gender ="";
                        while(true){
                            System.out.print("Enter Gender (1. Male or 2.Female): ");
                            gender = scan.nextLine();
                             if(gender.equalsIgnoreCase("exit")){
                                        return;
                            }

                            if(gender.equals("1")){
                                gender = "Male";
                                break;
                            }else if(gender.equals("2")){
                                gender = "Female";
                                break;
                            }else{
                                continue;
                            }
                        }
                        String contact = "";
                        while(true){
                            System.out.print("Enter contact number: ");
                            String contacts = scan.nextLine();
                             if(contacts.equalsIgnoreCase("exit")){
                                        return;
                            }
                            if(contacts.length() == 11 && contacts.matches("\\d{11}")){
                                contact = contacts;
                                break;
                            }else{
                                System.out.println("Error: Invalid contact number!");
                                continue;
                            }

                        }
                       
                       
                        String address = "";
                        while(true){
                            System.out.print("Enter Address\nPlease dont use (" + "," +"): ");
                            address = scan.nextLine();
                            if(address.equalsIgnoreCase("exit")){
                                        return;
                            }
                            if(address == null ||  address.trim().isEmpty()){
                                continue;
                            }else if(address.contains(",")){
                                 System.out.println("Please dont use (" + "," +")");
                                continue;
                            }else{
                                break;
                            }
                        }
                        

                        String uname = fname+lname+"@bc.edu.ph";
                        String pword = "DORSU2024";
                        String stat = "Active";
                    
                        writer.write(id + "," + lname + "," + fname + "," + mname + "," + birthdateStr+","+ age + "," + gender + ","+ contact + "," + address +","+ uname + "," + pword + "," + stat +"\n");
                        writer.close();
                        num++;     
                        System.out.println();
                        System.out.println("Added successfully!");
                        System.out.println("----------------------");
                        System.out.println();
                        FileWriter writer2 = new FileWriter("auditLog.txt" , true);
                        writer2.write(id + "," + lname + "," + fname + "," + mname + " Added successfully! \n");
                        writer2.close();
                        return; 
                
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        
    }
	//is naay number
	public static boolean naayNumber(String st){
        return st.matches(".*\\d.*");
    }

	//kuhaon ang last number
	static String getLastIdFromFile() {
        try {
            Scanner scan3 = new Scanner(new File("student.txt"));
            String lastId = null;
            while (scan3.hasNextLine()) {
                lastId = scan3.nextLine().split(",")[0];
            }
            scan3.close();
            return lastId;
        } catch (FileNotFoundException e) {
            return null; 
        }
    }

//school year management menu
	static void school_year_management_menu(){
		while(true){
					System.out.println();
					System.out.println("**** | School year management | ****");
					System.out.println();
					System.out.println("1. View school year");
                    System.out.println("2. Use school year");
                    System.out.println("3. Add school year");
                    System.out.println("4. Update school year");
                    System.out.println("5. Deactivate school year");
					System.out.println("6. Activate school year");
					System.out.println("7. Back");
					System.out.println();
					System.out.print("Enter your choice(1-6): ");
					String choice = scan.nextLine();
					switch(choice){
						case "1":
							view_school_year();
							continue;
						case "2":
							use_school_year();
							continue;
						case "3":
							add_school_year();
							continue;
						case "4":
							update_school_year();
							continue;
						case "5":
							school_year_deactivate();
							continue;
						case "6":
							school_year_activate();
							continue;
						case "7":
							return;
						default:
							System.out.println("Error: Invalid choice!");
                            System.out.println("----------------------");
                            System.out.println();
                            continue;

						
					}		
		}
	}

	//deactivate school year
	static void school_year_deactivate(){
		try{
			while(true){
				view_school_year();
				System.out.print("Enter school year (YYYY-YYYY format): ");
                String schoolYear = scan.nextLine();

				Scanner scan1 = new Scanner(new File("school_year.txt"));
				ArrayList<String> list = new ArrayList<>();
				boolean isFound = true;
				while(scan1.hasNextLine()){
					String data = scan1.nextLine();
					list.add(data);
					String[] info = data.split(",");
					if(info[0].equalsIgnoreCase(schoolYear)){
						isFound = true;
					}
				}

				if(isFound == true){
					while(true){
						System.out.print("Choice (1. Deactivate or 2. Cancel): ");
                		String pick = scan.nextLine();
						if(pick.equalsIgnoreCase("1")){

							FileWriter writer = new FileWriter("school_year.txt");
							boolean isGood = false;
							for(String l: list){
								String[] info = l.split(",");
								if(schoolYear.equalsIgnoreCase(info[0])){
									writer.write(info[0] + "," + "Inactive" + "\n");
									isGood = true;
								}else{
									writer.write(l + "\n");
								}
							}
							writer.close();

							if(isGood == true){

								System.out.println();
								System.out.println("School year deactivate successfully!");
								System.out.println();
								FileWriter writer1 = new FileWriter("auditLog.txt", true);
								writer1.write("School year deactivate successfully!" + "\n");
								writer1.close();
								return;
							}



						} else if(pick.equalsIgnoreCase("2")){
							return;
						}else{
							System.out.println();
							System.out.println("Invalid choice!");
                            System.out.println();
							continue;
						}
					}

				}else{
					System.out.println();
					System.out.println("Year not found!");
                     System.out.println();
					 continue;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	//activate school year
	static void school_year_activate(){
		try{
			while(true){
				view_school_year_Inactive();
				System.out.print("Enter school year (YYYY-YYYY format): ");
                String schoolYear = scan.nextLine();

				Scanner scan1 = new Scanner(new File("school_year.txt"));
				ArrayList<String> list = new ArrayList<>();
				boolean isFound = true;
				while(scan1.hasNextLine()){
					String data = scan1.nextLine();
					list.add(data);
					String[] info = data.split(",");
					if(info[0].equalsIgnoreCase(schoolYear)){
						isFound = true;
					}
				}

				if(isFound == true){
					while(true){
						System.out.print("Choice (1. Activate or 2. Cancel): ");
                		String pick = scan.nextLine();
						if(pick.equalsIgnoreCase("1")){

							FileWriter writer = new FileWriter("school_year.txt");
							boolean isGood = false;
							for(String l: list){
								String[] info = l.split(",");
								if(schoolYear.equalsIgnoreCase(info[0])){
									writer.write(info[0] + "," + "Active" + "\n");
									isGood = true;
								}else{
									writer.write(l + "\n");
								}
							}
							writer.close();

							if(isGood == true){

								System.out.println();
								System.out.println("School year deactivate successfully!");
								System.out.println();
								FileWriter writer1 = new FileWriter("auditLog.txt");
								writer1.write("School year activate successfully!" + "\n");
								writer1.close();
								return;
							}



						} else if(pick.equalsIgnoreCase("2")){
							return;
						}else{
							System.out.println();
							System.out.println("Invalid choice!");
                            System.out.println();
							continue;
						}
					}

				}else{
					System.out.println();
					System.out.println("Year not found!");
                     System.out.println();
					 continue;
				}
			}

		}catch(Exception e){
				e.printStackTrace();
		}
		
	}


	// use school year
	static void use_school_year(){
		try{
			System.out.println();
            System.out.println("Use school year ");
            System.out.println("-------------->");
            System.out.println();
			view_school_year();
			  System.out.println();

			while(true){
				String schoolYear;
                do {
                    System.out.print("Enter school year (YYYY-YYYY format): ");
                    schoolYear = scan.nextLine();
                } while (!isValidSchoolYearFormat(schoolYear));

                boolean isSchoolYear = false;
                Scanner scan1 = new Scanner(new File("school_year.txt"));
                while (scan1.hasNextLine()) {
                    String data = scan1.nextLine();
					String[] info = data.split(",");
                    if (info[0].equalsIgnoreCase(schoolYear)) {
                        isSchoolYear = true;
                        break;
                    }
                }
                scan1.close();
				ArrayList<String> list = new ArrayList<>();
				Scanner scan2 = new Scanner(new File("admin.txt"));
				while(scan2.hasNextLine()){
					String data = scan2.nextLine();
					list.add(data);
				}
				scan2.close();

				if(isSchoolYear == true){
					boolean isGood = false;
					FileWriter writer = new FileWriter("admin.txt");
					for(String l: list){
						String[] info = l.split(",");
						if(username_of_admin.equalsIgnoreCase(info[0])){
                            school_year = schoolYear;   
							writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + "," + schoolYear + "\n" );
							isGood = true;
						}else{
							writer.write(l+ "\n");
						}
					}
					writer.close();
					if(isGood == true){
						System.out.println();
						System.out.println("School year use successfully!");
						System.out.println();
						FileWriter writer1 = new FileWriter("auditLog.txt");
						writer1.write("School year use successfully! \n");
						writer1.close();
						return;
					}


				}else{
					System.out.println();
                    System.out.println("School year not found!");
                    System.out.println();
                    continue;
				}
			}


		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//view school year active
	static void view_school_year(){
		 try {
            Scanner scan1 = new Scanner(new File("school_year.txt"));
			 System.out.println();
            System.out.println("List of school years:");
            System.out.println("+--------------------------------------------+");
            System.out.printf("| %-20s| %-20s |\n", "School year", "Status");
           System.out.println("+--------------------------------------------+");
            while (scan1.hasNextLine()) {
                String data = scan1.nextLine();
				String[] info = data.split(",");
				if(info[1].equalsIgnoreCase("Active")){
                	System.out.printf("| %-20s| %-20s |\n", info[0], info[1]);
				}
            }
			System.out.println("+--------------------------------------------+");
            scan1.close();
        } catch (Exception  e) {
           e.printStackTrace();
        }
	}
	// view deactivate school year
	static void view_school_year_Inactive(){
		 try {
            Scanner scan1 = new Scanner(new File("school_year.txt"));
			 System.out.println();
            System.out.println("List of school years:");
            System.out.println("+--------------------------------------------+");
            System.out.printf("| %-20s| %-20s |\n", "School year", "Staus");
           System.out.println("+--------------------------------------------+");
            while (scan1.hasNextLine()) {
                String data = scan1.nextLine();
				String[] info = data.split(",");
				if(info[1].equalsIgnoreCase("Inactive")){
                	System.out.printf("| %-20s| %-20s |\n", info[0], info[1]);
				}
            }
			System.out.println("+--------------------------------------------+");
            scan1.close();
        } catch (Exception  e) {
           e.printStackTrace();
        }
	}

	//school year format
	private static boolean isValidSchoolYearFormat(String schoolYear) {
        return schoolYear.matches("\\d{4}-\\d{4}");
    }

	//add school year
	static void add_school_year() {
        try {
            System.out.println();
            System.out.println("Add school year ");
            System.out.println("-------------->");
            System.out.println();
            while (true) {
                String schoolYear;
                do {
                    System.out.print("Enter school year (YYYY-YYYY format): ");
                    schoolYear = scan.nextLine();
                } while (!isValidSchoolYearFormat(schoolYear));

                boolean isSchoolYear = false;
                Scanner scan1 = new Scanner(new File("school_year.txt"));
                while (scan1.hasNextLine()) {
                    String data = scan1.nextLine();
					String[] info = data.split(",");
                    if (info[0].equalsIgnoreCase(schoolYear)) {
                        isSchoolYear = true;
                        break;
                    }
                }
                scan1.close();

                if (isSchoolYear) {
                    System.out.println();
                    System.out.println("School year already exists!");
                    System.out.println();
                    continue;
                } else {
                    FileWriter writer = new FileWriter("school_year.txt", true);
                    writer.write(schoolYear + "," + "Active" + "\n");
                    writer.close();

                    System.out.println();
                    System.out.println("School year added successfully!");
                    System.out.println();

                    FileWriter writer2 = new FileWriter("auditLog.txt", true);
                    writer2.write("School year added successfully! \n");
                    writer2.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

	//update school year
	static void update_school_year() {
        try {
            System.out.println();
            System.out.println("Update school year ");
            System.out.println("----------------->");
            System.out.println();

            view_school_year();
            System.out.println();

            while (true) {
                System.out.print("Enter school year to update (YYYY-YYYY format): ");
                String schoolYear = scan.nextLine();

                boolean isSchoolYearFound = false;
                ArrayList<String> list = new ArrayList<>();
                Scanner scan1 = new Scanner(new File("school_year.txt"));
                    while (scan1.hasNextLine()) {
                        String data = scan1.nextLine();
                        list.add(data);
                        String[] info = data.split(",");
                        if (info[0].equalsIgnoreCase(schoolYear)) {
                            isSchoolYearFound = true;
                            break;
                        }
                    }
                scan1.close();

                if (!isSchoolYearFound) {
                    System.out.println();
                    System.out.println("School year not found!");
                    System.out.println();
                    continue;
                }else{

					while (true) {
                    System.out.print("Enter new school year (YYYY-YYYY format): ");
                    String newSchoolYear = scan.nextLine();
                    if (!isValidSchoolYearFormat(newSchoolYear)) {
                        System.out.println();
                        System.out.println("Invalid school year format.");
                        System.out.println();
                        continue;
                    }

                    boolean isSame = false;
                    Scanner scan2 = new Scanner(new File("school_year.txt"));
                        while (scan2.hasNextLine()) {
                            String data = scan2.nextLine();
                            String[] info = data.split(",");
                            if (info[0].equalsIgnoreCase(newSchoolYear)) {
                                isSame = true;
                                break;
                            }
                        }
                    scan2.close();

                    if (isSame) {
                        System.out.println();
                        System.out.println("School year already exists!");
                        System.out.println();
                        continue;
                    }else{
						 FileWriter writer = new FileWriter("school_year.txt");
                         boolean isGood = false;
                        for (String l : list) {
                            String[] info = l.split(",");
                            if (info[0].equalsIgnoreCase(schoolYear)) {
                                writer.write(newSchoolYear + "," + info[1] + "\n");
                                isGood = true;
                            } else {
                                writer.write(l + "\n");
                            }
                        }
                        writer.close();
                        if (isGood) {
                            System.out.println();
                            System.out.println("School year updated successfully!");
                            System.out.println();
                            FileWriter writer1 = new FileWriter("auditLog.txt", true);
                            writer1.write("School year updated successfully! \n");
                            writer1.close();
                            return;
                        }
					}   
                }

					
				}

                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
    }


//user management menu
	static void user_management(){
		while(true){
			 System.out.println();
			System.out.println("**** | User management | ****");
			 System.out.println();
			 		System.out.println("1. Update information");
                    System.out.println("2. Update password");
                    System.out.println("3. Update pin");
                    System.out.println("4. Back");
					System.out.println();
					 System.out.print("Enter your choice(1-4): ");
					String choice = scan.nextLine();
					switch(choice){
						case "1":
							updateInformation();
							continue;
						case "2":
							update_password_admin();
							continue;
						case "3":
							update_pin_admin();
							continue;
						case "4":
							return;
						default:
							System.out.println("Error: Invalid choice!");
                            System.out.println("----------------------");
                            System.out.println();
                            continue;
							
					}
		}
	}
	//update pin of admin
	static void update_pin_admin(){
		while(true){
			try{
				System.out.println();
				System.out.println("Update pin ");
				System.out.println("--------->");
				System.out.println();
				

				ArrayList<String> list = new ArrayList<>();
				while(true){
					System.out.print("Enter your old pin: ");
					String password = scan.nextLine();
					boolean isPassword = false;
					Scanner scan1 = new Scanner(new File("admin.txt"));
					while(scan1.hasNextLine()){
						String data = scan1.nextLine();
						list.add(data);
						String[] info = data.split(",");
						if(info[5].equals(password)){
							isPassword = true;
							break;
						}
					}
					scan1.close();
					if(isPassword == true){
						String new_password = "";
                        while(true){
                            System.out.print("Enter new pin: ");
                            String contacts = scan.nextLine();
                            if(contacts.length() == 6 && contacts.matches("\\d{6}")){
                                new_password = contacts;
                                break;
                            }else{
								System.out.println();
                                System.out.println("Enter 6 digits pin!");
								System.out.println();
                                continue;
                            }

                        }

						while(true){
							System.out.print("Confirm pin: ");
							String con_password = scan.nextLine();
							Scanner scan2 = new Scanner(new File("admin.txt"));
							while(scan2.hasNextLine()){
								String data = scan2.nextLine();
								String[] info = data.split(",");
									if(con_password.equalsIgnoreCase(new_password)){
										FileWriter writer = new FileWriter("admin.txt");
										writer.write(info[0] + "," + info[1] + "," + info[2] + "," + info[3] + "," + info[4] + "," + new_password + ","+ info[6] + "\n");
										writer.close();
										System.out.println();
										System.out.println("Pin updated successfully!");
										System.out.println();

										FileWriter writer1 = new FileWriter("auditLog.txt", true);
										writer1.write("Admin Pin updated successfully! \n");
										writer1.close();
										return;
									}else{
										System.out.println();
										System.out.println("Password not match!");
										System.out.println("------------------>");
										System.out.println();
										continue;
									}
							}
							
							
						}


					}else{
						System.out.println();
						System.out.println("Password incorrect!");
						System.out.println("------------------>");
						System.out.println();
						continue;
					}
				}


			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//update password of admin
	static void update_password_admin(){
		while(true){
			try{
				System.out.println();
				System.out.println("Update password ");
				System.out.println("-------------->");
				System.out.println();
				

				ArrayList<String> list = new ArrayList<>();
				while(true){
					System.out.print("Enter your old password: ");
					String password = scan.nextLine();
					boolean isPassword = false;
					Scanner scan1 = new Scanner(new File("admin.txt"));
					while(scan1.hasNextLine()){
						String data = scan1.nextLine();
						list.add(data);
						String[] info = data.split(",");
						if(info[1].equals(password)){
							isPassword = true;
							break;
						}
					}
					scan1.close();
					if(isPassword == true){
						System.out.print("Enter new password: ");
						String new_password = scan.nextLine();

						while(true){
							System.out.print("Confirm password: ");
							String con_password = scan.nextLine();
							Scanner scan2 = new Scanner(new File("admin.txt"));
							while(scan2.hasNextLine()){
								String data = scan2.nextLine();
								String[] info = data.split(",");
									if(con_password.equalsIgnoreCase(new_password)){
										FileWriter writer = new FileWriter("admin.txt");
										writer.write(info[1] + "," + new_password + "," + info[2] + "," + info[3] + "," + info[4] + "," + info[5] + info[6] + "\n");
										writer.close();
										System.out.println();
										System.out.println("Password updated successfully!");
										System.out.println();

										FileWriter writer1 = new FileWriter("auditLog.txt", true);
										writer1.write("Admin Password updated successfully!");
										writer1.close();
										return;
									}else{
										System.out.println();
										System.out.println("Password not match!");
										System.out.println("------------------>");
										System.out.println();
										continue;
									}
							}
							
							
						}


					}else{
						System.out.println();
						System.out.println("Password incorrect!");
						System.out.println("------------------>");
						System.out.println();
						continue;
					}
				}


			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//user management update information
	static void updateInformation(){
		try{	
			while(true){
				ArrayList<String> lists = new ArrayList<>();
				Scanner scan1 = new Scanner(new File("admin.txt"));
				boolean isFound = false;
				while(scan1.hasNextLine()){
					String data = scan1.nextLine();
					lists.add(data);
					String[] info = data.split(",");
					if(username_of_admin.equalsIgnoreCase(info[0])){
						isFound = true;
						break;
					}
				}
				scan1.close();
				if(isFound == true){
							System.out.println();
							System.out.println("Update Information ");
							System.out.println("------------------>");
							System.out.println();
							showAdminInfo();
							System.out.println();

							String lname = "";
							while(true){
								System.out.print("Enter Last name: ");
								lname = scan.nextLine();
									if(naayNumber(lname)){
										continue;
									}else if(lname == null || lname.trim().isEmpty()){
										continue;
									}else{
										break;
									}
							}

							String fname = "";
							while(true){
								System.out.print("Enter First name: ");
								fname = scan.nextLine();
									if(naayNumber(fname)){
										continue;
									}else if(fname == null || fname.trim().isEmpty()){
										continue;
									}else{
										break;
									}
							}
							String mname = "";
							while(true){
								System.out.print("Enter Middle name: ");
								mname = scan.nextLine();
									if(naayNumber(mname)){
										continue;
									}else{
										break;
									}
							}
							FileWriter writer = new FileWriter("admin.txt");
							boolean isFounds = false;
							for(String list: lists){
								String[] info = list.split(",");
								if(username_of_admin.equals(info[0])){
									writer.write(info[0] + "," + info[1] + "," + lname + "," + fname + "," + mname + "," + info[5]+ ","+ info[6] + "\n");
									isFounds = true;
								}else{
									writer.write(list + "\n");
								}
							}
							writer.close();

							if(isFounds == true){
								System.out.println();
								System.out.println("Admin updated information sucessfully!");
								System.out.println();

								FileWriter writer1 = new FileWriter("auditLog.txt", true);
								writer1.write("Admin updated information sucessfully! \n");
								writer1.close();
								return;
							}

				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//show admin information
	static void showAdminInfo(){
		try{
			Scanner scan1 = new Scanner(new File("Admin.txt"));
			System.out.println("+-------------------------------------------------------------------+");
            System.out.printf("| %-20s| %-20s | %-20s |  \n", "LASTNAME","FIRSTNAME","MIDDLENAME");
            System.out.println("+-------------------------------------------------------------------+");
			while(scan1.hasNextLine()){
				String data = scan1.nextLine();
				String[] info = data.split(",");
				if(name_of_admin.equalsIgnoreCase(info[2])){
					 System.out.printf("| %-20s| %-20s | %-20s |  \n", info[1],info[2],info[3]);
					 break;
				}
			}
			System.out.println("+-------------------------------------------------------------------+");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// check pin
	static void  check_pin_admin(){
		try{
			while(true){
				 System.out.print("Enter pin: ");
				 String pin = scan.nextLine();
				 Scanner scan1 = new Scanner(new File("admin.txt"));
				 boolean foundPin = false;
				 while(scan1.hasNextLine()){
					String data = scan1.nextLine();
					String[] info = data.split(",");
					if(info[5].equals(pin)){
						foundPin = true;
						break;
					}
				 }
				 scan1.close();
				 if(foundPin == true){
					break;
				 }else{
					System.out.println();
					System.out.println("Incorrect pin!");
					System.out.println();
					continue;
				 }


			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
