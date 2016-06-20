package app;

import com.tech.dota.pearl2016.R;

import java.util.ArrayList;
import java.util.List;

import set.Contacts;

/**
 * @author Yalantis
 */
public class ContactsInfo {
    public static final List<Contacts> friends = new ArrayList<>();

    static {
        friends.add(new Contacts(R.drawable.akshay, "Akshay Bhat", R.color.sienna, "Sponsorship and Marketing", "8096877109"));
        friends.add(new Contacts(R.drawable.kaushal, "Kaushal Raut", R.color.saffron, "General Secretary", "8501926741"));
        friends.add(new Contacts(R.drawable.keshav, "Keshav Chandra", R.color.green, "Cultural Secretary", "7732098738"));
        friends.add(new Contacts(R.drawable.ganesh, "Ganesh Cherukuri", R.color.pink, "Logistics and Operations", "7661079288"));
        friends.add(new Contacts(R.drawable.deepak, "Deepak Gupta", R.color.orange, "Hospitality and Accommodation","7730090396"));
        friends.add(new Contacts(R.drawable.harsh, "Harsh Garg", R.color.saffron, "Website and Creatives", "9010923721"));
    }
}
