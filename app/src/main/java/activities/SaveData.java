package activities;

/**
 * Created by SIDDEEQ DESIGNER on 1/9/2018.
 */

public class SaveData
{
    String name,email;

    public SaveData(String name, String pswrd) {
        this.name=name;
        this.email=pswrd;
    }
    public SaveData() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
