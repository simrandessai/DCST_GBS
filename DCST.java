
/**
 * @author Simran
 * Roll No: 2650
 * Description: DCST NIRF Management is a Java OOP project for the Department of Computer Science & Technology (DCST), 
 * Goa Business School, Goa University.
 * It stores students, teaching and non-teaching staff, programmes and courses (MCA, MSc AI, MSc DS, BSc-MSc DS), 
 * and academic and non-academic activities for 2023-24 to 2025-26.
 * It generates a NIRF report and demonstrates ten OOP concepts, including inheritance, polymorphism, generics, 
 * exception handling, reflection, serialization and cloning.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * NOTE: names of staff/students, participant counts, intake and placement
 * figures are SAMPLE data. Replace with real data before real submission.
 */
public class DCST {

    static final List<String> YEARS = List.of("2023-24", "2024-25", "2025-26");
    static final String REPORT_FILE = "NIRF_Report_DCST.txt";
    static final String STUDENT_FILE = "dcst_students.ser";

    static final List<Student> students = new ArrayList<>();
    static final List<TeachingStaff> teachers = new ArrayList<>();
    static final List<NonTeachingStaff> office = new ArrayList<>();
    static final List<Activity> activities = new ArrayList<>();
    static final List<Course> courses = CourseCatalog.all();

    static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    static boolean dataLoaded = false;

    public static void main(String[] args) throws Exception {
        System.out.println("________________________________________________________________");
        System.out.println("\nDCST, GOA BUSINESS SCHOOL - NIRF SUBMISSION (OOP SOLUTION)");
        System.out.println("Student Development Officer | Discipline: CST | 3 years");
        System.out.println("________________________________________________________________");
        runAllDemos();
    }

    static void runAllDemos() throws Exception {
        feature1ReferencingAndConstructors();
        feature2StaticAndEnums();
        feature3MultipleInheritance();
        feature4AccessModifiers();
        feature5AbstractClassesAndInterfaces();
        feature6ClassAsTypeAndMessagePassing();
        feature7Polymorphism();
        feature8GenericsAndCollections();
        feature9ExceptionHandling();
        feature10ReflectionPersistenceIOCloning();

        partB();
    }

    static void header(int no, String title) {
        System.out.println();
        System.out.println("________________________________________________________________");
        System.out.println("\nFEATURE " + no + ": " + title);
        System.out.println("________________________________________________________________\n");
    }

    // 1. Object referencing & constructors
    static void feature1ReferencingAndConstructors() {
        header(1, "Object Referencing & Constructors");

        Student s1 = new Student("Anaya Kamat", Program.MCA, 1, new Address("Panaji", "Goa", "403001"));
        Student s2 = s1; // s2 points to the SAME object
        System.out.println("s2 = s1  ->  s1 == s2 ? " + (s1 == s2));

        s2.setName("Sia K. Kamat"); // change through s2 ...
        System.out.println("After s2.setName(...), s1.getName() = " + s1.getName() + "   <- side effect of sharing");

        Student s3 = new Student("Anaya K. Kamat", Program.MCA, 1, new Address("Panaji", "Goa", "403001"));
        System.out.println("s3 is a NEW object with same data -> s1 == s3 ? " + (s1 == s3));

        System.out.println("Year before promote(s1): " + s1.getYear());
        promote(s1);
        System.out.println("Year after  promote(s1): " + s1.getYear() + "   <- method changed the caller's object");

        Student s4 = new Student("Ishaan Naik", Program.MSC_AI); // overloaded constructor
        System.out.println("Overloaded constructor -> " + s4.report());

        students.add(s1);
        students.add(s4);
        s1.setTempNote("scratch note (transient)");
    }

    static void promote(Student s) {
        s.nextYear(); // modifies the shared object
        s = null; // only the local copy of the reference is cleared
    }

    // 2. Static attributes, methods & enums
    static void feature2StaticAndEnums() {
        header(2, "Static Attributes, Methods & Enums");

        System.out.println("Members created so far (before loading data):");
        Person.displayMemberCount();
        loadSampleData();
        System.out.println("Members created after loading staff and students:");
        Person.displayMemberCount();

        System.out.println();
        System.out.println("Enum Program values:");
        for (Program p : Program.values()) {
            System.out.println(
                    "   " + p.ordinal() + ". " + p + " -> " + p.getFullName() + " (" + p.getYears() + " years)");
        }
        Program chosen = Program.valueOf("MSC_DS");
        switch (chosen) {
            case MCA -> System.out.println("   switch: MCA");
            case MSC_AI -> System.out.println("   switch: MSc AI");
            case MSC_DS -> System.out.println("   switch: MSc DS selected");
            case BSC_MSC_DS -> System.out.println("   switch: integrated programme");
        }
    }

    // 3. Multiple inheritance
    static void feature3MultipleInheritance() {
        header(3, "Multiple Inheritance");

        TeachingStaff t = teachers.get(0);
        System.out.println("TeachingStaff extends Person (one class) implements Teacher, Mentor (two interfaces)");
        System.out.println("  Teacher view : " + ((Teacher) t).teach());
        System.out.println("  Mentor view  : " + ((Mentor) t).mentor());
        System.out.println("  Clashing default describeDuty() resolved in the class:");
        System.out.println("     " + t.describeDuty());
        System.out.println();
        System.out.println("Difference:");
        System.out.println("Interface inheritance: a class can implement many interfaces (Teacher, Mentor).");
        System.out.println(
                "Implementation inheritance: a class can extend only ONE class, so 'extends A, B' is an error.");
    }

    // 4. Access modifiers
    static void feature4AccessModifiers() {
        header(4, "Access Modifiers");

        AccessDemo.insideClassDemo();

        AccessDemo d = new AccessDemo(); // outside the class, same package
        System.out.println("   [same package, DCST]  " + d.publicField);
        System.out.println("   [same package, DCST]  " + d.protectedField);
        System.out.println("   [same package, DCST]  " + d.defaultField);
        System.out.println("   [same package, DCST]  private via getter -> " + d.getPrivateField());
        // d.privateField -> compile error: private

        new OutsideAccess().run(); // outside the class, other package
    }

    // 5. Abstract classes & interfaces
    static void feature5AbstractClassesAndInterfaces() {
        header(5, "Abstract Classes & Interfaces");

        System.out.println("Abstract class Activity -> AcademicActivity / NonAcademicActivity (2025-26 sample):");
        int shownAcademic = 0, shownNon = 0;
        for (Activity a : activities) {
            if (!a.getAcademicYear().equals("2025-26"))
                continue;
            boolean academic = a instanceof AcademicActivity;
            if ((academic && shownAcademic++ < 2) || (!academic && shownNon++ < 2)) {
                System.out.println("   " + a.category() + " -> " + a.execute());
            }
        }

        System.out.println("Abstract class Person -> getRole() differs per subclass:");
        System.out.println("   " + students.get(0).getRole() + " | " + teachers.get(0).getRole() + " | "
                + office.get(0).getRole());

        Reportable custom = () -> "Interface Reportable implemented with a lambda";
        System.out.println("Interface with method: " + custom.report());
    }

    // 6. Class as a type & message passing
    static void feature6ClassAsTypeAndMessagePassing() {
        header(6, "Class as a Type & Message Passing");

        submitToNirf(students.get(0));
        submitToNirf(teachers.get(0));
        submitToNirf(office.get(0));
        submitToNirf(courses.get(1));
        submitToNirf(activities.get(0));

        allocateDuty(teachers.get(1));
    }

    // accepts ANY object implementing Reportable
    static void submitToNirf(Reportable r) {
        System.out.println("   NIRF portal received -> " + r.report());
    }

    static void allocateDuty(Teacher t) {
        System.out.println("   Message teach() sent to a Teacher -> " + t.teach());
    }

    // 7. Polymorphism
    static void feature7Polymorphism() {
        header(7, "Polymorphism: Overriding & Overloading");

        List<Person> everyone = new ArrayList<>();
        everyone.addAll(students);
        everyone.addAll(teachers);
        everyone.addAll(office);
        System.out.println("Overriding: same call report(), different behaviour at run time:");
        for (Person p : everyone.subList(0, 1))
            System.out.println("   " + p.report());
        System.out.println("   " + teachers.get(0).report());
        System.out.println("   " + office.get(0).report());

        System.out.println("Overloading: describe() vs describe(boolean):");
        Person p = students.get(0);
        System.out.println("   describe()      -> " + p.describe());
        System.out.println("   describe(true)  -> " + p.describe(true));

        System.out.println("Overloading: totalCredits(List) vs totalCredits(Course...):");
        List<Course> mcaSem1 = new ArrayList<>();
        for (Course c : courses)
            if (c.getProgram() == Program.MCA && c.getSemester() == 1)
                mcaSem1.add(c);
        System.out.println("   MCA Sem I credits (List version)    = " + totalCredits(mcaSem1));
        System.out.println("   Two courses     (varargs version)   = " + totalCredits(courses.get(1), courses.get(2)));
    }

    static int totalCredits(List<Course> list) {
        int sum = 0;
        for (Course c : list)
            sum += c.getCredits();
        return sum;
    }

    static int totalCredits(Course... list) {
        return totalCredits(Arrays.asList(list));
    }

    // 8. Generics and collections
    static void feature8GenericsAndCollections() {
        header(8, "Generics & Collection Framework");

        Registry<Student> studentRegistry = new Registry<>("Student");
        for (Student s : students)
            studentRegistry.add(s);
        studentRegistry.printAll();

        Registry<Course> courseRegistry = new Registry<>("MCA Sem II DSC");
        for (Course c : courses) {
            if (c.getProgram() == Program.MCA && c.getSemester() == 2 && c.getType().equals("DSC"))
                courseRegistry.add(c);
        }
        System.out.println(
                "   Registry<Course> size = " + courseRegistry.size() + ", first = " + courseRegistry.get(0).getCode());

        System.out.println("Generic method first(): " + Registry.first(students).getName()
                + " | " + Registry.first(courses).getCode());
        Registry.printAll(teachers, "Generic method printAll(Collection<E>) - teaching staff");

        // List
        List<Student> ranked = new ArrayList<>(students);
        ranked.sort(Comparator.comparingDouble(Student::average).reversed());
        System.out.println("List (ranked by average):");
        for (Student s : ranked)
            System.out.printf("   %-18s %.1f%n", s.getName(), s.average());

        // Queue
        Queue<String> interviewQueue = new ArrayDeque<>(List.of("Student A", "Student B", "Student C"));
        interviewQueue.offer("Student D");
        System.out.println("Queue (placement interviews, FIFO): next = " + interviewQueue.poll()
                + ", then " + interviewQueue.poll() + ", remaining " + interviewQueue);

        // Set
        Set<String> recruiters = new HashSet<>(
                List.of("Company A", "Company B", "Company A", "Company C", "Company B"));
        System.out.println("Set (duplicates removed, sorted via TreeSet): " + new TreeSet<>(recruiters));

        // Map
        Map<Program, List<Course>> byProgram = new EnumMap<>(Program.class);
        for (Course c : courses)
            byProgram.computeIfAbsent(c.getProgram(), k -> new ArrayList<>()).add(c);
        System.out.println("Map<Program, List<Course>> (courses per programme):");
        for (Map.Entry<Program, List<Course>> e : byProgram.entrySet()) {
            System.out.println("   " + e.getKey() + " -> " + e.getValue().size() + " courses");
        }
        Map<String, Integer> placed = new TreeMap<>(placements());
        System.out.println("Map<String,Integer> (students placed per year, SAMPLE): " + placed);
    }

    static Map<String, Integer> placements() {
        return Map.of("2023-24", 28, "2024-25", 31, "2025-26", 35);
    }

    // 9. Exception handling
    static void feature9ExceptionHandling() {
        header(9, "Exception Handling");

        Student s = students.get(0);

        try { // custom checked exception
            s.addMark(105);
            System.out.println("   not reached");
        } catch (InvalidMarksException e) {
            System.out.println("   Caught custom exception: " + e.getMessage());
        } finally {
            System.out.println("   finally: validation of marks finished");
        }

        try {
            int zero = students.size() - students.size();
            System.out.println(10 / zero);
        } catch (ArithmeticException e) {
            System.out.println("   Caught ArithmeticException: " + e.getMessage());
        }

        try {
            Object o = null;
            o.toString();
        } catch (NullPointerException e) {
            System.out.println("   Caught NullPointerException (null object used)");
        }

        try {
            courses.get(999);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("   Caught IndexOutOfBoundsException: " + e.getMessage());
        }

        try {
            Integer.parseInt("20x5");
        } catch (NumberFormatException | NullPointerException e) { // multi-catch
            System.out.println("   Caught (multi-catch): " + e.getMessage());
        }

        try { // throwing an exception manually
            checkAcademicYear("2025/26");
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught thrown exception: " + e.getMessage());
        } finally {
            System.out.println("   finally: academic year check finished");
        }
        System.out.println("   Program continues normally after all runtime errors.");
    }

    static void checkAcademicYear(String y) {
        if (!y.matches("\\d{4}-\\d{2}")) {
            throw new IllegalArgumentException("Bad academic year format: " + y + " (expected e.g. 2025-26)");
        }
    }

    // 10. Reflection, persistence, I/O, cloning
    static void feature10ReflectionPersistenceIOCloning() throws Exception {
        header(10, "Reflection, Persistence, I/O & Cloning");

        // ---- Reflection ----
        System.out.println("A) Reflection on class Student");
        Student s = students.get(0);
        Class<?> c = s.getClass();
        System.out.println("   Class       : " + c.getName() + " (" + Modifier.toString(c.getModifiers()) + ")");
        System.out.println("   Superclass  : " + c.getSuperclass().getSimpleName());
        List<String> ifaces = new ArrayList<>();
        for (Class<?> i : c.getInterfaces())
            ifaces.add(i.getSimpleName());
        System.out.println("   Interfaces  : " + ifaces);
        System.out.println("   Fields:");
        for (Field f : c.getDeclaredFields()) {
            if (f.isSynthetic() || Modifier.isStatic(f.getModifiers()))
                continue;
            System.out.println("      " + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " "
                    + f.getName());
        }
        List<String> methodNames = new ArrayList<>();
        for (Method m : c.getDeclaredMethods())
            if (!m.isSynthetic())
                methodNames.add(m.getName());
        methodNames.sort(null);
        System.out.println("   Methods     : " + methodNames);

        Method avg = c.getMethod("average");
        System.out.println("   Invoked average() by reflection -> " + avg.invoke(s));
        Field yearField = c.getDeclaredField("year");
        yearField.setAccessible(true);
        System.out.println("   Read private field 'year' by reflection -> " + yearField.get(s));
        Constructor<?> ctor = c.getConstructor(String.class, Program.class);
        Student made = (Student) ctor.newInstance("Reflected Student", Program.MSC_DS);
        System.out.println("   Object created by reflection -> " + made.report());
        Class<?> forName = Class.forName("Course");
        System.out.println("   Class.forName -> " + forName.getSimpleName());

        // ---- Persistence ----
        System.out.println();
        System.out.println("B) Persistence (serialization to file " + STUDENT_FILE + ")");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
            out.writeObject(new ArrayList<>(students));
        }
        System.out.println("   Saved " + students.size() + " students. tempNote before saving = " + s.getTempNote());
        List<Student> restored;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
            @SuppressWarnings("unchecked")
            List<Student> tmp = (List<Student>) in.readObject();
            restored = tmp;
        }
        System.out.println("   Restored " + restored.size() + " students from file:");
        for (Student r : restored)
            System.out.println("      " + r.report());
        System.out.println(
                "   transient tempNote after restore = " + restored.get(0).getTempNote() + "  (not persisted)");

        // ---- I/O ----
        System.out.println();
        System.out.println("C) Input / Output (console + files)");
        System.out.print("   Enter the academic year to highlight in the report [2025-26]: ");
        String focus = IN.readLine();
        if (focus == null || focus.isBlank())
            focus = "2025-26";
        focus = focus.trim();
        try {
            checkAcademicYear(focus);
        } catch (IllegalArgumentException e) {
            System.out.println("   " + e.getMessage() + " -> using 2025-26");
            focus = "2025-26";
        }
        System.out.println("   Focus year = " + focus);

        String report = buildReport(focus);
        try (BufferedWriter w = Files.newBufferedWriter(Path.of(REPORT_FILE), StandardCharsets.UTF_8)) {
            w.write(report);
        }
        List<String> lines = Files.readAllLines(Path.of(REPORT_FILE), StandardCharsets.UTF_8);
        System.out.println("   Report written to " + REPORT_FILE + " and read back: " + lines.size() + " lines.");

        // ---- RTTI ----
        System.out.println();
        System.out.println("D) RTTI (run-time type identification)");
        List<Person> everyone = new ArrayList<>();
        everyone.addAll(students);
        everyone.addAll(teachers);
        everyone.addAll(office);
        int stu = 0, tea = 0, non = 0;
        for (Person p : everyone) {
            if (p instanceof Student)
                stu++;
            else if (p instanceof TeachingStaff)
                tea++;
            else if (p instanceof NonTeachingStaff)
                non++;
        }
        System.out.println(
                "   instanceof count -> Student=" + stu + ", TeachingStaff=" + tea + ", NonTeachingStaff=" + non);
        System.out.println("   getClass() of first 3 persons -> "
                + everyone.get(0).getClass().getSimpleName() + ", "
                + everyone.get(students.size()).getClass().getSimpleName() + ", "
                + everyone.get(everyone.size() - 1).getClass().getSimpleName());

        // ---- Cloning ----
        System.out.println();
        System.out.println("E) Shallow vs Deep cloning");
        Student original = students.get(0);
        try {
            original.addMark(80);
            original.addMark(90);
        } catch (InvalidMarksException e) {
            throw new IllegalStateException(e);
        }
        Student shallow = original.clone();
        Student deep = original.deepClone();
        original.getAddress().setCity("Margao");
        try {
            original.addMark(70);
        } catch (InvalidMarksException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("   original after changes : " + original.getAddress() + " | marks " + original.getMarks());
        System.out.println("   shallow clone          : " + shallow.getAddress() + " | marks " + shallow.getMarks()
                + "   <- shares address & marks");
        System.out.println("   deep clone             : " + deep.getAddress() + " | marks " + deep.getMarks()
                + "   <- independent copy");
        System.out.println("   original == shallow ? " + (original == shallow)
                + " | original.getAddress() == shallow.getAddress() ? "
                + (original.getAddress() == shallow.getAddress())
                + " | original.getAddress() == deep.getAddress() ? " + (original.getAddress() == deep.getAddress()));
        System.out
                .println("   (clone() and deserialization bypass constructors, so the static counter does not change)");
        Person.displayMemberCount();
    }

    // PART B - listing of programmes, courses, activities
    static void partB() throws IOException {
        System.out.println();
        System.out.println("________________________________________________________________");
        System.out.println(" PART B: NIRF SUBMISSION LISTING (read back from " + REPORT_FILE + ")");
        System.out.println("________________________________________________________________");
        for (String line : Files.readAllLines(Path.of(REPORT_FILE), StandardCharsets.UTF_8)) {
            System.out.println(line);
        }
    }

    static String buildReport(String focusYear) {
        StringBuilder sb = new StringBuilder();
        sb.append("NIRF DATA - DEPARTMENT OF COMPUTER SCIENCE & TECHNOLOGY (DCST), GOA BUSINESS SCHOOL\n");
        sb.append("Period: ").append(YEARS.get(0)).append(" to ").append(YEARS.get(YEARS.size() - 1))
                .append("    Focus year: ").append(focusYear).append("\n");
        sb.append("(* = assumed subject, not from the official syllabus. Names/figures are sample data.)\n\n");

        sb.append("A. PROGRAMMES OFFERED\n");
        for (Program p : Program.values()) {
            sb.append("   ").append(p).append(" - ").append(p.getFullName()).append(" (").append(p.getYears())
                    .append(" years)\n");
        }

        sb.append("\nB. COURSES / SUBJECTS (Semester I and II)\n");
        for (Program p : Program.values()) {
            sb.append("  ").append(p.getFullName()).append("\n");
            for (int sem = 0; sem <= 2; sem++) {
                List<Course> list = new ArrayList<>();
                for (Course c : courses)
                    if (c.getProgram() == p && c.getSemester() == sem)
                        list.add(c);
                if (list.isEmpty())
                    continue;
                sb.append("    ").append(sem == 0 ? "Bridge course" : "Semester " + (sem == 1 ? "I" : "II"))
                        .append("  (total credits: ").append(totalCredits(list)).append(")\n");
                for (Course c : list)
                    sb.append("      ").append(c.report()).append("\n");
            }
        }

        sb.append("\nC. TEACHING STAFF\n");
        for (TeachingStaff t : teachers)
            sb.append("   ").append(t.report()).append("\n");
        sb.append("\nD. NON-TEACHING STAFF\n");
        for (NonTeachingStaff n : office)
            sb.append("   ").append(n.report()).append("\n");

        sb.append("\nE. ACADEMIC ACTIVITIES\n");
        appendActivities(sb, "Academic", focusYear);
        sb.append("\nF. NON-ACADEMIC ACTIVITIES\n");
        appendActivities(sb, "Non-Academic", focusYear);

        sb.append("\nG. PLACEMENTS (students placed, SAMPLE figures)\n");
        for (Map.Entry<String, Integer> e : new TreeMap<>(placements()).entrySet()) {
            sb.append("   ").append(e.getKey()).append(" : ").append(e.getValue()).append(" students")
                    .append(e.getKey().equals(focusYear) ? "   <== focus year" : "").append("\n");
        }
        return sb.toString();
    }

    static void appendActivities(StringBuilder sb, String category, String focusYear) {
        for (String y : YEARS) {
            sb.append("  ").append(y).append(y.equals(focusYear) ? "  <== focus year" : "").append("\n");
            for (Activity a : activities) {
                if (a.getAcademicYear().equals(y) && a.category().equals(category)) {
                    sb.append("     - ").append(a.getName()).append(": ").append(a.description).append("\n");
                }
            }
        }
    }

    static void loadSampleData() {
        if (dataLoaded)
            return;
        dataLoaded = true;
        teachers.add(new TeachingStaff("Dr. Rohit Gaonkar", TeachingStaff.Designation.PROFESSOR,
                "Operating Systems", "Linux Lab"));
        teachers.add(new TeachingStaff("Dr. Sneha Pal", TeachingStaff.Designation.ASSOCIATE_PROFESSOR,
                "Database Management Systems", "DBMS Lab"));
        teachers.add(new TeachingStaff("Prof. Bikram Chauhan", TeachingStaff.Designation.ASSISTANT_PROFESSOR,
                "Web Development", "Internet Technology"));
        teachers.add(new TeachingStaff("Dr. Jesly D'Souza", TeachingStaff.Designation.ASSISTANT_PROFESSOR,
                "Fundamentals of AI", "Machine Learning"));
        teachers.add(new TeachingStaff("Prof. Crieton Vaz", TeachingStaff.Designation.ASSISTANT_PROFESSOR,
                "Fundamentals of Data Science", "Data Mining"));

        office.add(new NonTeachingStaff("Mr. Sra Parab", "Lab Technician"));
        office.add(new NonTeachingStaff("Ms. Lata Das", "Office Assistant"));
        office.add(new NonTeachingStaff("Mr. Sagar Kelkar", "System Administrator"));

        students.add(new Student("Rhea Menezes", Program.MCA, 2, new Address("Margao", "Goa", "403601")));
        students.add(new Student("Aditya Kulkarni", Program.MSC_DS, 1, new Address("Mapusa", "Goa", "403507")));
        students.add(new Student("Tanvi Dessai", Program.BSC_MSC_DS, 1, new Address("Ponda", "Goa", "403401")));
        students.add(new Student("Noel Silva", Program.MSC_AI, 2, new Address("Vasco", "Goa", "403802")));

        int[][] marks = { { 72, 85, 90 }, { 65, 70 }, { 88, 92, 79 }, { 55, 61, 68 }, { 78, 83 }, { 91, 87 } };
        for (int i = 0; i < students.size() && i < marks.length; i++) {
            for (int m : marks[i]) {
                try {
                    students.get(i).addMark(m);
                } catch (InvalidMarksException e) {
                    System.out.println("Skipped: " + e.getMessage());
                }
            }
        }

        // Activities for each of the 3 years
        String[][] academic = {
                { "ISA (Internal Semester Assessment)", "Continuous internal assessment of each course" },
                { "Midpoint Assessment", "Mid-semester test for every course" },
                { "SEA (Semester End Assessment)", "End-semester university examination" },
                { "Study Tour", "Industry / institute visit for students" },
                { "Campus Placements", "Recruitment drives conducted for final-year students" },
                { "Industry Internship", "Internship with partner organisations" },
                { "Guest Lectures and Workshops", "Sessions by industry and academic experts" },
                { "Dissertation / Project Viva", "Evaluation of final-semester dissertations" },
                { "Hackathon / Coding Contest", "Technical competition for students" }
        };
        String[][] nonAcademic = {
                { "InfoFest", "Annual technical and cultural fest of the department" },
                { "Plateaunia", "Annual fest event" },
                { "Play-on", "Sports event for students" },
                { "Freshers' Welcome", "Orientation and welcome for the new batch" },
                { "Farewell", "Send-off for outgoing students" },
                { "Alumni Meet", "Interaction between alumni and students" },
                { "Blood Donation / Social Outreach Camp", "Community service activity" }
        };
        int idx = 0;
        for (String y : YEARS) {
            for (String[] a : academic)
                activities.add(new AcademicActivity(a[0], y, a[1], 60 + idx * 5));
            for (String[] n : nonAcademic)
                activities.add(new NonAcademicActivity(n[0], y, n[1], "DCST student committee"));
            idx++;
        }
    }
}
