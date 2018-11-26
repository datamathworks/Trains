package com.java.trains;

public class MainApp {

    public static void main(String[] args) {
        String input = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

        String[] routeNames = input.split(",");

        System.out.println("------- Loading Routes... -------");
        for(String route : routeNames) {
            System.out.print(route + ",");
        }
        System.out.println("");
        System.out.println("------- Loading Routes... -------");

        Utility trainHelper = new Utility(routeNames);

        System.out.println("The distance of the route A-B-C");
        System.out.println("Output #1 : " + analyzeRouteDistance(trainHelper.getDistance(new Character[]{'A','B','C'})));

        System.out.println("The distance of the route A-D");
        System.out.println("Output #2 : " + analyzeRouteDistance(trainHelper.getDistance(new Character[]{'A','D'})));

        System.out.println("The distance of the route A-D-C");
        System.out.println("Output #3 : " + analyzeRouteDistance(trainHelper.getDistance(new Character[]{'A','D','C'})));

        System.out.println("The distance of the route A-E-B-C-D");
        System.out.println("Output #4 : " + analyzeRouteDistance(trainHelper.getDistance(new Character[]{'A','E','B','C','D'})));

        System.out.println("The distance of the route A-E-D");
        System.out.println("Output #5 : " + analyzeRouteDistance(trainHelper.getDistance(new Character[]{'A','E','D'})));

        System.out.println("The number of trips starting at C and ending at C with a maximum of 3 stops");
        System.out.println("Output #6 : " + trainHelper.getStopsCount('C','C',3));

        System.out.println("The number of trips starting at A and ending at C with exactly 4 stops");
        System.out.println("Output #7 : " + trainHelper.getRouteCountByStop('A','C',4));

        System.out.println("The length of the shortest route (in terms of distance to travel) from A to C");
        System.out.println("Output #8 : " + analyzeRouteDistance(trainHelper.getMinDistance('A','C')));

        System.out.println("The length of the shortest route (in terms of distance to travel) from B to B");
        System.out.println("Output #9 : " + analyzeRouteDistance(trainHelper.getMinDistance('B','B')));

        System.out.println("The number of different routeNames from C to C with a distance of less than 30");
        System.out.println("data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC");
        System.out.println("Output #10 : " + analyzeRouteDistance(trainHelper.getShorterRoutesCount(new String[]{"CDC", "CEBC", "CEBCDC", "CDCEBC", "CDEBC", "CEBCEBC", "CEBCEBCEBC"},
                30)));
    }

    private static String analyzeRouteDistance(int distanceFromTo){
        if(distanceFromTo == -1){
            return "NO SUCH ROUTE";
        }
        return Integer.toString(distanceFromTo);
    }
}


