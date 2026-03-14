package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Assertions.assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        //Проверить, что за понедельник вернулось одно занятие
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
        //Проверить, что за вторник не вернулось занятий
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        Assertions.assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        // Проверить, что за понедельник вернулось одно занятие
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> trainingSessionsForThursday =
                timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        ArrayList<TimeOfDay> expectedTrainingSessionsForThursday = new ArrayList<>();
        expectedTrainingSessionsForThursday.add(new TimeOfDay(13, 0));
        expectedTrainingSessionsForThursday.add(new TimeOfDay(20, 0));
        Assertions.assertIterableEquals(expectedTrainingSessionsForThursday, trainingSessionsForThursday.navigableKeySet());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        Assertions.assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY));
        // Проверить, что за вторник не вернулось занятий
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        Assertions.assertEquals(1,
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                        new TimeOfDay(13, 0)).size());
        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        Assertions.assertNull(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY,
                new TimeOfDay(14, 0)));
        //Проверить, что за понедельник в 14:00 не вернулось занятий
    }

}
