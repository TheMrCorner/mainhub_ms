package com.mrcorner.journal.monthly.service;

import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import com.mrcorner.journal.monthly.dto.HabitDto;
import com.mrcorner.journal.monthly.mapper.IHabitsMapper;
import com.mrcorner.journal.monthly.model.Habits;
import com.mrcorner.journal.monthly.repository.HabitsRepository;
import com.mrcorner.journal.monthly.repository.MonthHabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HabitService {

    // Repo and mapper
    HabitsRepository habitsRepository;
    IHabitsMapper habitsMapper;

    // MonthHabit
    MonthHabitRepository monthHabitRepository;

    // Monthly
    MonthlyService monthlyService;

    public HabitDto newHabit(HabitDto habitDto) throws InvalidDataException {
        Habits habits = habitsMapper.toEntity(habitDto);
        habits.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        habits.setDbModDate(new Timestamp(System.currentTimeMillis()));
        try{
            habits = habitsRepository.save(habits);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving a new habit " + ex.getMessage());
        } // catch
        return habitsMapper.toDto(habits);
    } // newHabit

    public HabitDto findHabitById(int habitId) throws DataNotFoundException {
        Optional<Habits> optionalHabits = habitsRepository.findById(habitId);
        if(optionalHabits.isEmpty()){
            throw new DataNotFoundException("Habit not found: " + habitId);
        } // if
        return habitsMapper.toDto(optionalHabits.get());
    } // findHabitById

    public List<HabitDto> findHabitsByDay(LocalDate dayDate) throws DataNotFoundException, InvalidDataException{
        Integer idMonth = monthlyService.findMonthByDay(dayDate);
        List<Habits> habitsByMonth = habitsRepository.findAllByIdHabitIn(monthHabitRepository.findAllIdHabitsByIdMonth(idMonth));
        return habitsMapper.toDtoList(habitsByMonth);
    } // findHabits

} // HabitService
