package Applications.model;

public class CV {
    private final String fullname;
    private final String email;
    private final String phone;
    private final String address;
    private final String education;
    private final String skills;
    private final String workExperience;
    private final String projects;

    public CV(String fullname, String email, String phone, String address,
              String education, String skills, String workExperience, String projects) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.workExperience = workExperience;
        this.projects = projects;
    }

    public String getFullname() { return fullname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getWorkExperience() { return workExperience; }
    public String getProjects() { return projects; }
}
