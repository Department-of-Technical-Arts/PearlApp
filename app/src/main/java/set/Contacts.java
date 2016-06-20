package set;

/**
 * @author Yalantis
 */
public class Contacts {
    private final String contactname;
    private int avatar;
    private int background;
    private String phone;
    private String job;
    public Contacts(int avatar, String contactname, int background, String job, String phone) {
        this.avatar = avatar;
        this.contactname = contactname;
        this.background = background;
        this.phone=phone;
        this.job=job;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getContactname() {
        return contactname;
    }

    public int getBackground() {
        return background;
    }

    public String getPhone() {
        return phone;
    }

    public String getJob() {
        return job;
    }
}
