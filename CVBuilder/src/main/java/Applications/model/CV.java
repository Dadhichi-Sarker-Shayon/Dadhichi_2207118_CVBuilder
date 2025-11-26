package Applications.model;

public class CV {
    private int id;
    private String fullname, email, phone, address, education, skills, workExperience, project, profileImagePath;

    public CV(int id, String fullname, String email, String phone, String address,
              String education, String skills, String workExperience, String project, String profileImagePath) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.workExperience = workExperience;
        this.project = project;
        this.profileImagePath = profileImagePath;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; } // <-- add setter to remember id
    public String getFullname() { return fullname; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getWorkExperience() { return workExperience; }
    public String getProject() { return project; }
    public String getProfileImagePath() { return profileImagePath; }
}
