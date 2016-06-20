package app;

import com.tech.dota.pearl2016.R;

import java.util.ArrayList;
import java.util.List;

import set.Credits;

/**
 * @author Yalantis
 */
public class AppCreditsInfo {
    public static final List<Credits> friends = new ArrayList<>();

    static {
        friends.add(new Credits(R.drawable.teddie, "Tejeshwar Reddy", R.color.sienna, "App Development"));
        friends.add(new Credits(R.drawable.teja, "Ravi Teja", R.color.saffron, "UI/UX Design"));
        friends.add(new Credits(R.drawable.harshit, "Harshit Agarwal", R.color.green, "App Development"));
        friends.add(new Credits(R.drawable.monil, "Monil Shah", R.color.pink, "Backend Development"));
        friends.add(new Credits(R.drawable.vibhu, "Vibhu Agarwal", R.color.orange, "UI/UX Design"));
        friends.add(new Credits(R.drawable.dota, "DEPT. OF TECHNICAL ARTS", R.color.saffron, "The DA VINCI of the MONALISA"));
    }
}
