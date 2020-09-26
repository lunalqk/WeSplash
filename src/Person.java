import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

/**
 * after person accepts or rejects someone,
 * change this person's interests attributes,
 * then re-search connections
 */

// structure for a person - store all data related to the people here
public class Person {
    // everyone who can potentially match with this person
    // storing edge instead of person to store the weight
    // only store people who he didn't reject (or took long time to)
    ArrayList<Edge> connections;

    // people who this person has already rejected within a certain amount of time
    // HashMap<person, time>
    HashMap<Person, Double> rejections;

    // store all of the person's interests (on scale of 1-100)
    HashMap<String, Double> interests;

    Person(HashMap userInfo) {
        connections = new ArrayList<>();
        rejections = new HashMap<>();

        // user fills out userInfo during login process
        interests = userInfo;
    }

    // find a person that has similar interests to this person
    void search() {
        if (connections.size() == 0) {
            /**
             * find random person from allPeople in GraphOfPeople
             */
            HashMap<String, Double> temp = new HashMap<>();
            // temp - this person
            Person a = new Person(temp);
            // temp - random person
            Person b = new Person(temp);
            Edge e = new Edge(a, b, compare(b));
            connections.add(e);

            System.out.println("return person somewhere");
            return;
        }
        for (Edge e : connections) {
            searchHelper(e.to);
        }
        System.out.println("return person somewhere");
    }

    // search helper method
    Person searchHelper(Person p) {
        boolean searched = false;
        // see if current person has been searched already
        for (Edge e : connections) {
            if (e.to == p) {
                searched = true;
            }
        }

        // if this person has not been searched
        if (!searched) {
            // arbitrary number - change
            // if this person matches with the other person
            if (compare(p) <= 100) {
                // return person
                return p;
            } else {
                // don't return person
                return null;
            }
        } else {
            // search through all available next person
            for (Edge next : p.connections) {
                // set up temporary person
                Person temp = searchHelper(next.to);
                // if there is a match, return and break the loop
                if (temp != null) {
                    return temp;
                }
            }
            // if there are no matches in this current node
            return null;
        }
    }

    // compare the interests of both the people and give a value of how much they match
    // concept - could be improved a lot
    double compare(Person b) {
        double match_rating = 0;
        for (String s : interests.keySet()) {
            double a_value = interests.get(s);
            double b_value = b.interests.get(s);

            // if a_value == 0 then it has not been initialized yet
            if (a_value > 0) {
                // take difference of their interests
                match_rating += Math.abs(a_value - b_value);
            }
        }
        return match_rating;
    }

    /**
     * perhaps add function to keep track of how long the person stays on the profile of the other person
     * since the longer they stay
     * the more likely they have matching interests
     */
    void accept(Person b, double time) {
        for (String s : interests.keySet()) {
            double a_value = interests.get(s);
            double b_value = b.interests.get(s);

            // make all a_values closer to b_values (since they match)
            if (a_value > b_value) {
                a_value -= (a_value - b_value) / 2 * (-(1/(time + 0.5)) + 2);
            } else if (a_value < b_value) {
                a_value += (b_value - a_value) / 2 * (-(1/(time + 0.5)) + 2);
            }
            interests.put(s, a_value);
        }

        for (Edge e : connections) {
            e.match_status = compare(e.to);
        }
    }

    void reject(Person b, double time) {
        for (String s : interests.keySet()) {
            double a_value = interests.get(s);
            double b_value = b.interests.get(s);

            // make all a_values closer to b_values (since they match)
            if (a_value > b_value) {
                a_value += (a_value - b_value) / 2 * (-(1/(time + 0.5)) + 2);
            } else if (a_value < b_value) {
                a_value -= (b_value - a_value) / 2 * (-(1/(time + 0.5)) + 2);
            }
            interests.put(s, a_value);
        }

        for (Edge e : connections) {
            e.match_status = compare(e.to);
        }
    }
}
