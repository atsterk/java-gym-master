package ru.yandex.practicum.gym;

import java.sql.Time;
import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();
        if (timetable.containsKey(dayOfWeek)) {
            //timetable.get(dayOfWeek).get(timeOfDay).add(trainingSession);
            if (timetable.get(dayOfWeek).containsKey(timeOfDay)) {
                timetable.get(dayOfWeek).get(timeOfDay).add(trainingSession);
            } else {
                ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
                trainingSessions.add(trainingSession);
                timetable.get(dayOfWeek).put(timeOfDay, trainingSessions);
            }
        } else {
            ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
            trainingSessions.add(trainingSession);

            TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingSessionsForTheDay = new TreeMap<>();
            trainingSessionsForTheDay.put(timeOfDay, trainingSessions);

            timetable.put(dayOfWeek, trainingSessionsForTheDay);
        }
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (timetable.containsKey(dayOfWeek)) {
            return timetable.get(dayOfWeek);
        } else {
            System.out.println("В данный день тренировок не запланировано");
            return null;
        }
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (timetable.containsKey(dayOfWeek)) {
            if (timetable.get(dayOfWeek).containsKey(timeOfDay)) {
                return timetable.get(dayOfWeek).get(timeOfDay);
            }
        }
        System.out.println("В этот день в это время тренировок не запланировано");
        return null;
    }

    public TreeMap<Integer, ArrayList<Coach>> getCountByCoaches() {
        HashMap<Coach, Integer> unsortedMap = new HashMap<>();
        for (DayOfWeek dayOfWeek : timetable.keySet()) {
            for (TimeOfDay timeOfDay : timetable.get(dayOfWeek).keySet()) {
                for (TrainingSession trainingSession : timetable.get(dayOfWeek).get(timeOfDay)) {
                    unsortedMap.put(trainingSession.getCoach(),
                            unsortedMap.getOrDefault(trainingSession.getCoach(), 0) + 1);
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
        if (sortedMap.isEmpty()) {
            return null;
        }

        return sortedMap;
    }
}
