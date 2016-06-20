package set;

/**
 * @author Yalantis
 */
public class Credits {
    private final String contactname;
    private int avatar;
    private int background;
    private String job;

    public Credits(int avatar, String contactname, int background, String job) {
        this.avatar = avatar;
        this.contactname = contactname;
        this.background = background;
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

    public String getJob() {
        return job;
    }
}
