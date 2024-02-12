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
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
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

    public DailyPreviewDto saveDailyPreview(DailyPreviewDto dailyPreviewDto) throws InvalidDataException {
        DailyPreview dailyPreview;
        Optional<DailyPreview> optionalDailyPreview = dailyPreviewRepository.findDailyPreviewByCurrentDay(dailyPreviewDto.getCurrentDay());
        optionalDailyPreview.ifPresent(preview -> log.info("Day already exists, updating it"));

        if(optionalDailyPreview.isEmpty()) {
            dailyPreview = dailyPreviewMapper.toEntity(dailyPreviewDto);
            dailyPreview.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        } // if
        else{
            dailyPreview = optionalDailyPreview.get();
            dailyPreview.setGoal(dailyPreviewDto.getGoal());
            dailyPreview.setGratefulData(dailyPreviewDto.getGratefulData());
            dailyPreview.setGreatData(dailyPreviewDto.getGreatData());
        } // else
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
        savedDailyPreview.setDailyTargets(saveDailyTarget(dailyPreviewDto.getDailyTargets()));
        savedDailyPreview.setDailyEvents(saveDailyEventList(dailyPreviewDto.getDailyEvents(), dailyPreview.getIdDay()));
        savedDailyPreview.setMealPrep(saveDailyMealPrep(dailyPreviewDto.getMealPrep()));

        return savedDailyPreview;
    } // newDailyPreview

    public DailyTargetDto saveDailyTarget(DailyTargetDto dailyTargetDto) throws InvalidDataException{
        DailyTarget dailyTarget;

        if(dailyTargetDto.getIdTarget() == 0) {
            dailyTarget = dailyTargetMapper.toEntity(dailyTargetDto);
            dailyTarget.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        } // if
        else{
            dailyTarget = dailyTargetRepository.getReferenceById(dailyTargetDto.getIdTarget());
            dailyTarget.setFirstTarget(dailyTargetDto.getFirstTarget());
            dailyTarget.setFtDuration((short) dailyTargetDto.getFtDuration().ordinal());
            dailyTarget.setSecondTarget(dailyTargetDto.getSecondTarget());
            dailyTarget.setStDuration((short) dailyTargetDto.getStDuration().ordinal());
            dailyTarget.setThirdTarget(dailyTargetDto.getThirdTarget());
            dailyTarget.setTtDuration((short) dailyTargetDto.getTtDuration().ordinal());
        } // else
        dailyTarget.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyTarget = dailyTargetRepository.save(dailyTarget);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Exception saving daily targets: " + ex.getMessage());
        } // catch

        return dailyTargetMapper.toDto(dailyTarget);
    } // newDailyTarget

    public List<DailyEventDto> saveDailyEventList(List<DailyEventDto> dailyEventList, int idDay) throws InvalidDataException{
        dailyEventList.forEach(dailyEventDto -> {
            DailyEvent dailyEvent;
            dailyEventDto.setIdDay(idDay);

            if(dailyEventDto.getIdEvent() == 0) {
                dailyEvent = dailyEventMapper.toEntity(dailyEventDto);
                dailyEvent.setDbInsDate(new Timestamp(System.currentTimeMillis()));
            } // if
            else{
                dailyEvent = dailyEventRepository.getReferenceById(dailyEventDto.getIdEvent());
                updateDailyEvent(dailyEventDto, dailyEvent);
            } // else
            dailyEvent.setDbModDate(new Timestamp(System.currentTimeMillis()));

            try{
                dailyEvent = dailyEventRepository.save(dailyEvent);
            } // try
            catch(IllegalArgumentException ex){
                throw new InvalidDataException("Error saving daily event: " + ex);
            } // catch

            dailyEventDto = dailyEventMapper.toDto(dailyEvent);
        }); // forEach
        return dailyEventList;
    } // newDailyEventList

    public DailyEventDto saveDailyEvent(DailyEventDto dailyEventDto) throws InvalidDataException{
        if(dailyEventDto.getIdDay() <= 0){
            throw new InvalidDataException("No day provided for the event");
        } // if

        DailyEvent dailyEvent;

        if(dailyEventDto.getIdEvent() == 0) {
            dailyEvent = dailyEventMapper.toEntity(dailyEventDto);
            dailyEvent.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        } // if
        else{
            dailyEvent = dailyEventRepository.getReferenceById(dailyEventDto.getIdEvent());
            updateDailyEvent(dailyEventDto, dailyEvent);
        } // else
        dailyEvent.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyEvent = dailyEventRepository.save(dailyEvent);
        } // try
        catch(DataIntegrityViolationException | IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving the event " + ex.getMessage());
        } // catch

        return dailyEventMapper.toDto(dailyEvent);
    } // newDailyEvent

    private void updateDailyEvent(DailyEventDto dailyEventDto, DailyEvent dailyEvent){
        dailyEvent.setDescription(dailyEventDto.getDescription());
        dailyEvent.setTitle(dailyEventDto.getTitle());
        dailyEvent.setImportant(dailyEventDto.isImportant());
        dailyEvent.setIniHour(dailyEventDto.getIniHour());
        dailyEvent.setEndHour(dailyEventDto.getEndHour());
    }

    public DailyMealPrepDto saveDailyMealPrep(DailyMealPrepDto dailyMealPrepDto) throws InvalidDataException{
        DailyMealPrep dailyMealPrep;

        if(dailyMealPrepDto.getIdMealPrep() == 0) {
            dailyMealPrep = dailyMealPrepMapper.toEntity(dailyMealPrepDto);
            dailyMealPrep.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        } // if
        else{
            dailyMealPrep = dailyMealPrepRepository.getReferenceById(dailyMealPrepDto.getIdMealPrep());
            dailyMealPrep.setMidday(dailyMealPrepDto.getMidday());
            dailyMealPrep.setSupper(dailyMealPrepDto.getSupper());
        } // else
        dailyMealPrep.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyMealPrep = dailyMealPrepRepository.save(dailyMealPrep);
        } // try
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving daily meal prep: " + ex.getMessage());
        } // catch

        return dailyMealPrepMapper.toDto(dailyMealPrep);
    } // newDailyMealPrep

    public DailyReviewDto saveDailyReview(DailyReviewDto dailyReviewDto) throws InvalidDataException{
        if(dailyReviewDto.getIdDay() <= 0){
            throw new InvalidDataException("No day provided for the review");
        } // if

        DailyReview dailyReview;
        if(dailyReviewDto.getIdDailyReview() == 0) {
            dailyReview = dailyReviewMapper.toEntity(dailyReviewDto);
            dailyReview.setDbInsDate(new Timestamp(System.currentTimeMillis()));
        }
        else{
            dailyReview = dailyReviewRepository.getReferenceById(dailyReviewDto.getIdDailyReview());
            dailyReview.setMood((short) dailyReviewDto.getMood().ordinal());
            dailyReview.setNotes(dailyReviewDto.getNotes());
            dailyReview.setFtComplete(dailyReviewDto.isFtComplete());
            dailyReview.setStComplete(dailyReviewDto.isStComplete());
            dailyReview.setTtComplete(dailyReviewDto.isTtComplete());
            dailyReview.setPlanToReality(dailyReviewDto.getPlanToReality());
            dailyReview.setWinDay(dailyReviewDto.getWinDay());
        }
        dailyReview.setDbModDate(new Timestamp(System.currentTimeMillis()));

        try{
            dailyReview = dailyReviewRepository.save(dailyReview);
        } // try
        catch(DataIntegrityViolationException | IllegalArgumentException ex){
            throw new InvalidDataException("Error while saving the review " + ex.getMessage());
        } // catch

        List<DailyHabitsDto> dailyHabitsList = saveDailyHabits(dailyReviewDto.getDailyHabits());

        DailyReviewDto savedDailyReview = dailyReviewMapper.toDto(dailyReview);
        savedDailyReview.setDailyHabits(dailyHabitsList);

        return savedDailyReview;
    } // DailyReviewDto

    public List<DailyHabitsDto> saveDailyHabits(List<DailyHabitsDto> dailyHabitsDto) throws InvalidDataException{
        try{
            dailyHabitsDto.forEach(dailyHabitDto -> {
                DailyHabit dailyHabit;
                if(dailyHabitDto.getIdDailyHabit() == 0) {
                    dailyHabit = dailyHabitsMapper.toEntity(dailyHabitDto);
                    dailyHabit.setDbInsDate(new Timestamp(System.currentTimeMillis()));
                } // if
                else{
                    dailyHabit = dailyHabitRepository.getReferenceById(dailyHabitDto.getIdDailyHabit());
                    dailyHabit.setStatus((short) dailyHabitDto.getStatus().ordinal());
                } // else
                dailyHabit.setDbModDate(new Timestamp(System.currentTimeMillis()));

                dailyHabit = dailyHabitRepository.save(dailyHabit);
                dailyHabitDto = dailyHabitsMapper.toDto(dailyHabit);
                dailyHabitDto.setHabit(habitService.findHabitById(dailyHabit.getIdHabit()));
            }); // forEach
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
