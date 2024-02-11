package com.mrcorner.journal.daily.service;

import com.mrcorner.journal.daily.dto.DailyEventDto;
import com.mrcorner.journal.daily.dto.DailyHabitsDto;
import com.mrcorner.journal.daily.dto.DailyMealPrepDto;
import com.mrcorner.journal.daily.dto.DailyPreviewDto;
import com.mrcorner.journal.daily.dto.DailyReviewDto;
import com.mrcorner.journal.daily.dto.DailyTargetDto;
import com.mrcorner.journal.daily.mapper.IDailyEventMapper;
import com.mrcorner.journal.daily.mapper.IDailyHabitsMapper;
import com.mrcorner.journal.daily.mapper.IDailyMealPrepMapper;
import com.mrcorner.journal.daily.mapper.IDailyPreviewMapper;
import com.mrcorner.journal.daily.mapper.IDailyReviewMapper;
import com.mrcorner.journal.daily.mapper.IDailyTargetMapper;
import com.mrcorner.journal.daily.model.DailyEvent;
import com.mrcorner.journal.daily.model.DailyHabit;
import com.mrcorner.journal.daily.model.DailyMealPrep;
import com.mrcorner.journal.daily.model.DailyPreview;
import com.mrcorner.journal.daily.model.DailyReview;
import com.mrcorner.journal.daily.model.DailyTarget;
import com.mrcorner.journal.daily.repository.DailyEventRepository;
import com.mrcorner.journal.daily.repository.DailyHabitRepository;
import com.mrcorner.journal.daily.repository.DailyMealPrepRepository;
import com.mrcorner.journal.daily.repository.DailyPreviewRepository;
import com.mrcorner.journal.daily.repository.DailyReviewRepository;
import com.mrcorner.journal.daily.repository.DailyTargetRepository;
import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import com.mrcorner.journal.monthly.service.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyService {

    // DailyPreview
    DailyPreviewRepository dailyPreviewRepository;
    IDailyPreviewMapper dailyPreviewMapper;

    // DailyTarget
    DailyTargetRepository dailyTargetRepository;
    IDailyTargetMapper dailyTargetMapper;

    // DailyEvent
    DailyEventRepository dailyEventRepository;
    IDailyEventMapper dailyEventMapper;

    // DailyMealPrep
    DailyMealPrepRepository dailyMealPrepRepository;
    IDailyMealPrepMapper dailyMealPrepMapper;

    // DailyReview
    DailyReviewRepository dailyReviewRepository;
    IDailyReviewMapper dailyReviewMapper;

    // DailyHabits
    DailyHabitRepository dailyHabitRepository;
    IDailyHabitsMapper dailyHabitsMapper;
    HabitService habitService;

    public DailyPreviewDto newDailyPreview(DailyPreviewDto dailyPreviewDto) throws InvalidDataException {

        // First check if day exists
        DailyPreview dailyPreview = dailyPreviewMapper.toEntity(dailyPreviewDto);

        Optional<DailyPreview> optionalDailyPreview = dailyPreviewRepository.findDailyPreviewByCurrentDay(dailyPreview.getCurrentDay());
        optionalDailyPreview.ifPresent(preview -> {
            throw new InvalidDataException("Day already exists " + preview.getCurrentDay() + " " + preview.getIdDay());
        });

        dailyPreview.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        dailyPreview.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyPreview = dailyPreviewRepository.save(dailyPreview);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error saving preview day: " + ex.getMessage());
        } // catch

        // Set new id
        dailyPreviewDto.getDailyTargets().setIdDay(dailyPreview.getIdDay());
        dailyPreviewDto.getMealPrep().setIdDay(dailyPreview.getIdDay());

        // Format new result as dto and save the rest of the info
        DailyPreviewDto savedDailyPreview = dailyPreviewMapper.toDto(dailyPreview);
        savedDailyPreview.setDailyTargets(newDailyTarget(dailyPreviewDto.getDailyTargets()));
        savedDailyPreview.setDailyEvents(newDailyEventList(dailyPreviewDto.getDailyEvents(), dailyPreview.getIdDay()));
        savedDailyPreview.setMealPrep(newDailyMealPrep(dailyPreviewDto.getMealPrep()));

        return savedDailyPreview;
    } // newDailyPreview

    public DailyTargetDto newDailyTarget(DailyTargetDto dailyTargetDto) throws InvalidDataException{
        DailyTarget dailyTarget = dailyTargetMapper.toEntity(dailyTargetDto);

        dailyTarget.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        dailyTarget.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyTarget = dailyTargetRepository.save(dailyTarget);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Exception saving daily targets: " + ex.getMessage());
        } // catch

        return dailyTargetMapper.toDto(dailyTarget);
    } // newDailyTarget

    public List<DailyEventDto> newDailyEventList(List<DailyEventDto> dailyEventList, int idDay) throws InvalidDataException{
        List<DailyEvent> dailyEvents = dailyEventMapper.toEntityList(dailyEventList);

        dailyEvents.forEach(dailyEvent -> {
            dailyEvent.setIdDay(idDay);
            dailyEvent.setDbInsDate(new Timestamp(System.currentTimeMillis()));
            dailyEvent.setDbModDate(new Timestamp(System.currentTimeMillis()));

            try{
                dailyEvent = dailyEventRepository.save(dailyEvent);
            } // try
            catch(IllegalArgumentException ex){
                throw new InvalidDataException("Error saving daily event: " + ex);
            } // catch
        });

        return dailyEventMapper.toDtoList(dailyEvents);
    } // newDailyEventList

    public DailyEventDto newDailyEvent(DailyEventDto dailyEventDto) throws InvalidDataException{
        if(dailyEventDto.getIdDay() <= 0){
            throw new InvalidDataException("No day provided for the event");
        } // if

        DailyEvent dailyEvent = dailyEventMapper.toEntity(dailyEventDto);
        dailyEvent.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        dailyEvent.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyEvent = dailyEventRepository.save(dailyEvent);
        } // try
        catch(DataIntegrityViolationException | IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving the event " + ex.getMessage());
        } // catch

        return dailyEventMapper.toDto(dailyEvent);
    } // newDailyEvent

    public DailyMealPrepDto newDailyMealPrep(DailyMealPrepDto dailyMealPrepDto) throws InvalidDataException{
        DailyMealPrep dailyMealPrep = dailyMealPrepMapper.toEntity(dailyMealPrepDto);

        dailyMealPrep.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        dailyMealPrep.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyMealPrep = dailyMealPrepRepository.save(dailyMealPrep);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving daily meal prep: " + ex.getMessage());
        } // catch

        return dailyMealPrepMapper.toDto(dailyMealPrep);
    } // newDailyMealPrep

    public DailyReviewDto newDailyReview(DailyReviewDto dailyReviewDto) throws InvalidDataException{
        if(dailyReviewDto.getIdDay() <= 0){
            throw new InvalidDataException("No day provided for the review");
        } // if

        DailyReview dailyReview = dailyReviewMapper.toEntity(dailyReviewDto);
        dailyReview.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        dailyReview.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyReview = dailyReviewRepository.save(dailyReview);
        } // try
        catch(DataIntegrityViolationException | IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving the review " + ex.getMessage());
        } // catch

        List<DailyHabitsDto> dailyHabitsList = newDailyHabits(dailyReviewDto.getDailyHabits());

        DailyReviewDto savedDailyReview = dailyReviewMapper.toDto(dailyReview);
        savedDailyReview.setDailyHabits(dailyHabitsList);

        return savedDailyReview;
    } // DailyReviewDto

    public List<DailyHabitsDto> newDailyHabits(List<DailyHabitsDto> dailyHabitsDto) throws InvalidDataException{
        try{
            dailyHabitsDto.forEach(dailyHabitDto -> {
                DailyHabit dailyHabit = dailyHabitsMapper.toEntity(dailyHabitDto);
                dailyHabit.setDbInsDate(new Timestamp(System.currentTimeMillis()));
                dailyHabit.setDbModDate(new Timestamp(System.currentTimeMillis()));

                dailyHabit = dailyHabitRepository.save(dailyHabit);
                dailyHabitDto = dailyHabitsMapper.toDto(dailyHabit);
                dailyHabitDto.setHabit(habitService.findHabitById(dailyHabit.getIdHabit()));
            });
            return dailyHabitsDto;
        } // try
        catch(DataIntegrityViolationException | IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving the dailyhabit " + ex.getMessage());
        } // catch
    } // newDailyHabits

    public DailyReviewDto findDailyReview(int idDay) throws DataNotFoundException{
        Optional<DailyReview> optDailyReview = dailyReviewRepository.findByIdDay(idDay);
        if(optDailyReview.isEmpty()){
            throw new DataNotFoundException("Could not find any review for day " + idDay);
        } // if

        DailyReviewDto dailyReviewDto = dailyReviewMapper.toDto(optDailyReview.get());
        dailyReviewDto.setDailyHabits(findDailyHabits(idDay));
        return dailyReviewDto;
    } // findDailyReview

    public List<DailyHabitsDto> findDailyHabits(int idDay) throws DataNotFoundException{
        List<DailyHabit> dailyHabits = dailyHabitRepository.findAllByIdDay(idDay);
        List<DailyHabitsDto> dailyHabitsDtos = dailyHabitsMapper.toDtoList(dailyHabits);

        dailyHabitsDtos.forEach(dailyHabitsDto ->
                dailyHabitsDto.setHabit(habitService.findHabitById(dailyHabitsDto.getIdHabit())));
        return dailyHabitsDtos;
    } // findDailyHabits

    public DailyPreviewDto findDailyPreview(LocalDate dayDate) throws InvalidDataException, DataNotFoundException{
        try {
            Optional<DailyPreview> optionalDailyPreview = dailyPreviewRepository.findDailyPreviewByCurrentDay(dayDate);
            if (optionalDailyPreview.isEmpty()) {
                throw new DataNotFoundException("Daily preview with date " + dayDate + " not found.");
            } // if

            DailyPreview dailyPreview = optionalDailyPreview.get();
            DailyPreviewDto dailyPreviewDto = dailyPreviewMapper.toDto(dailyPreview);
            dailyPreviewDto.setDailyEvents(findAllEventsByIdDay(dailyPreview.getIdDay()));
            dailyPreviewDto.setDailyTargets(findDailyTargetByIdDay(dailyPreview.getIdDay()));
            dailyPreviewDto.setMealPrep(findDailyMealPrepByIdDay(dailyPreview.getIdDay()));

            return dailyPreviewDto;
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error while trying to find dat " + dayDate);
        } // catch
    } // findDailyPreview

    public DailyTargetDto findDailyTargetByIdDay(int idDay) throws DataNotFoundException, InvalidDataException {
        try {
            Optional<DailyTarget> optionalDailyTarget = dailyTargetRepository.findByIdDay(idDay);
            if (optionalDailyTarget.isEmpty()) {
                throw new DataNotFoundException("Could not find targets for day " + idDay);
            } // if

            return dailyTargetMapper.toDto(optionalDailyTarget.get());
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error trying to find dailytargets for id " + idDay);
        } // catch
    } // findDailyTargetByIdDay

    public DailyMealPrepDto findDailyMealPrepByIdDay(int idDay) throws DataNotFoundException, InvalidDataException{
        try {
            Optional<DailyMealPrep> optionalDailyMealPrep = dailyMealPrepRepository.findByIdDay(idDay);
            if (optionalDailyMealPrep.isEmpty()) {
                throw new DataNotFoundException("Could not find mealprep for day " + idDay);
            } // if

            return dailyMealPrepMapper.toDto(optionalDailyMealPrep.get());
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error trying to find dailymealprep for day " + idDay);
        } // catch
    } // findDailyMealPrepByIdDay

    public List<DailyEventDto> findAllEventsByIdDay(int idDay) throws InvalidDataException{
        try{
            List<DailyEvent> dailyEvents = dailyEventRepository.findAllByIdDay(idDay);
            return dailyEventMapper.toDtoList(dailyEvents);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error trying to find events for day " + idDay);
        } // catch
    } // findAllEventsByIdDay
} // DailyService
