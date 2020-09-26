public class Edge {
    // who this edge is from and to
    /**
     * directed edge - since different people have different preferences
     * e.g. bob plays soccer & joe doesn't
     * bob could care very much about whether a person plays soccer
     * and joe couldn't care less
     */
    Person from;
    Person to;

    // how much they match
    double match_status;

    Edge(Person a, Person b, double match) {
        from = a;
        to = b;
        match_status = match;
    }
}
