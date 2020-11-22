/**
 * @author Ryder Roth, Jack Haehl
 * @version 1
 * @since 11/22/2020
 * student class, which holds the students ID, first name,
 * last name, program, academic level, and ASURite
 */
public class Student
{
    /**
     * student ID
     */
    private int ID;

    /**
     * students first name
     */
    private String firstName;

    /**
     * students last name
     */
    private String lastName;

    /**
     * program the student is in
     */
    private String program;

    /**
     * academic level of the student
     */
    private String academicLevel;

    /**
     * ASURite ID of the student
     */
    private String ASURite;

    /**
     * constructor for student
     * @param ID                Student ID
     * @param firstName         Student's First Name
     * @param lastName          Student's Last Name
     * @param program           Student's Academic Program
     * @param academicLevel     Student's Academic Level
     * @param ASURite           Student's ASURite ID
     */
    public Student(int ID, String firstName, String lastName, String program, String academicLevel, String ASURite)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.academicLevel = academicLevel;
        this.ASURite = ASURite;
    }

    /**
     * returns the id of the student
     * @return ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * returns the first name of the student
     * @return firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * returns the last name of the student
     * @return lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * returns the program of the student
     * @return program
     */
    public String getProgram()
    {
        return program;
    }

    /**
     * returns the academic level of the student
     * @return academicLevel
     */
    public String getAcademicLevel()
    {
        return academicLevel;
    }

    /**
     * returns the ASURite of the student
     * @return ASURite
     */
    public String getASURite()
    {
        return ASURite;
    }
}
