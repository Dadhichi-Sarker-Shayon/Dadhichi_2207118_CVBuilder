package application;
public class CV {
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String skills;
    private String workExperience;
    private String project;

    public CV(String fullname,String email,String phone,String address,String education,String skills,String workexperience,String project )
    {
        this.fullname=fullname;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.education=education;
        this.skills=skills;
        this.workExperience=workExperience;
        this.project=project;

    }

    public String getFullname() {
        return fullname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getEducation() {
        return education;
    }
    public String getSkills() {
        return skills;
    }
    public String getWorkexperience() {
        return workExperience;
    }
    public String getProject() {
        return project;
    }
}
