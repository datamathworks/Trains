package com.java.trains;

import java.util.*;


public class Utility {

    private Map<Character,Map<Character,Integer>> routesMap;

    public Utility(String[] routes){
        routesMap = analyzeRoutes(routes);
    }

    private Map<Character,Map<Character,Integer>> analyzeRoutes(String[] routes){
        Map<Character,Map<Character,Integer>> tempRoutes = new HashMap<>();

        // sorting routes
        Arrays.sort(routes);

        for(int i = 0; i < routes.length;i++){
            if(tempRoutes.containsKey(routes[i].charAt(0))){
                tempRoutes.get(routes[i].charAt(0)).put(routes[i].charAt(1),
                        Integer.parseInt(routes[i].substring(2)));

            } else{
                Map<Character,Integer> nodes = new HashMap<>();
                nodes.put(routes[i].charAt(1), Integer.parseInt(routes[i].substring(2)));
                tempRoutes.put(routes[i].charAt(0),nodes);
            }
        }

        return tempRoutes;
    }

    public int getDistance(Character[] curRoute){
        if (curRoute.length > 1) {
            int totalDistance = 0;
            for (int i = 0; i < curRoute.length; i++) {
                if(curRoute.length > i + 1) {
                    if(routesMap.containsKey(curRoute[i]) && routesMap.get(curRoute[i]).containsKey(curRoute[i + 1])){
                        totalDistance += routesMap.get(curRoute[i]).get(curRoute[i + 1]);
                    } else {
                        return -1;
                    }
                }
            }
            return totalDistance;
        }
        return -1;
    }


    public Set<String> generateRoutes(char startStop, char endStop) {
        Set<String> existingRoutes = new HashSet<>();
        for(Character nextStop : routesMap.get(startStop).keySet()){
            List<String> routes = buildRoute(nextStop, endStop, new HashSet<>());
            for (String route : routes){
                existingRoutes.add(startStop + route);
            }
        }

        return existingRoutes;
    }


    public int getStopsCount(Character startStop, Character endStop, int stopsCount) {

        Set<String> routes = generateRoutes(startStop, endStop);
        Integer stops = stopsCount++;

        for (Iterator<String> iterator = routes.iterator(); iterator.hasNext();) {
            String route =  iterator.next();

            if (route.length()  <=  stops)
                iterator.remove();
        }

        return routes.size();
    }

    public int getMinDistance(Character startStop, Character endStop) {
        Set<String> routes = generateRoutes(startStop,endStop);

        int minDistance = 0;

        for(String route : routes) {
            Character[] routeArray = route.chars().mapToObj(ch -> (char)ch).toArray(Character[]::new);
            int distance = getDistance(routeArray);
            if(distance == 0){
                return distance;
            }
            if(minDistance == 0 || minDistance > distance){
                minDistance = distance;
            }
        }

        return minDistance;
    }


    public Integer getShorterRoutesCount(String[] routes, int threshold) {

        int routeCount = 0;

        for(int i = 0; i < routes.length; i++){
            Character[] routeArray = routes[i].chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            Integer distance = getDistance(routeArray);
            if(distance > 0 && distance <= threshold){
                routeCount++;
            }
        }

        return routeCount;
    }

    public int getRouteCountByStop(Character startStop, Character endStop, int countStops) {

        Set<String> routes = new HashSet<>();
        int stopsLeft = countStops - 1;
        for(Character nextStop : routesMap.get(startStop).keySet()){
            List<String> routesList = buildRouteStops(nextStop, endStop, stopsLeft);
            for (String route : routesList){
                routes.add(startStop + route);
            }
        }

        return routes.size();
    }


    private List<String> buildRoute(Character curStop, Character endStop, Set<Character> prevStops){
        List<String> routes = new ArrayList<>();
        if(curStop.equals(endStop)){
            routes.add(endStop.toString());
            return routes;
        }

        for (Character nextStation : routesMap.get(curStop).keySet()) {
            Set<Character> previous = new HashSet<>();
            previous.addAll(prevStops);
            if(!prevStops.contains(nextStation)) {
                previous.add(curStop);
                List<String> routesList = buildRoute(nextStation, endStop, previous);
                for (String route : routesList) {
                    routes.add(curStop + route);
                }
            }
        }

        return routes;
    }

    private List<String> buildRouteStops(Character curStop, Character endStop, int stopCount){
        List<String> routes = new ArrayList<>();
        if(curStop.equals(endStop) && stopCount == 0){
            routes.add(endStop.toString());
            return routes;
        }

        int stopCountRemaining = stopCount - 1;
        if(stopCountRemaining >= 0) {
            for (Character nextStop : routesMap.get(curStop).keySet()) {
                List<String> routesList = buildRouteStops(nextStop, endStop, stopCountRemaining);
                for (String route : routesList) {
                    routes.add(curStop + route);
                }
            }
        }

        return routes;
    }
}
