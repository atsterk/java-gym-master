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
}
