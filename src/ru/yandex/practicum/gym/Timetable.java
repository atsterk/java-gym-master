package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public Timetable() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingSessionsForDay = new TreeMap<>();
            timetable.put(dayOfWeek, trainingSessionsForDay);
        }
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingSessionsForDay = timetable.get(dayOfWeek);

        if (trainingSessionsForDay.containsKey(timeOfDay)) {
            trainingSessionsForDay.get(timeOfDay).add(trainingSession);
        } else {
            ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
            trainingSessions.add(trainingSession);
            timetable.get(dayOfWeek).put(timeOfDay, trainingSessions);
        }
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek).getOrDefault(timeOfDay, new ArrayList<>());
    }

    public TreeMap<Integer, ArrayList<Coach>> getCountByCoaches() {
        HashMap<Coach, Integer> unsortedMap = new HashMap<>();

        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> sessionsForDay : timetable.values()) {
            for (ArrayList<TrainingSession> sessions : sessionsForDay.values()) {
                for (TrainingSession trainingSession : sessions) {
                    unsortedMap.put(trainingSession.getCoach(), unsortedMap.getOrDefault(trainingSession.getCoach(), 0) + 1);
                }
            }
        }

        TreeMap<Integer, ArrayList<Coach>> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        for (Coach coach : unsortedMap.keySet()) {
            if (sortedMap.containsKey(unsortedMap.get(coach))) {
                sortedMap.get(unsortedMap.get(coach)).add(coach);
            } else {
                ArrayList<Coach> coaches = new ArrayList<>();
                coaches.add(coach);
                sortedMap.put(unsortedMap.get(coach), coaches);
            }
        }

        return sortedMap;
    }
}
