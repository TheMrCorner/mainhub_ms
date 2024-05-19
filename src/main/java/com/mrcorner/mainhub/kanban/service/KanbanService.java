package com.mrcorner.mainhub.kanban.service;


import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.utils.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final Http http;

    @Value("${app.urls.daily.dailySourcePath}")
    private String dailySourcePath;

    public ResponseEntity<String> obtainKanbanTable(){
        String url = dailySourcePath + "/kanban/table";
        return http.getRequest(url);
    }

    public ResponseEntity<String> saveNewTask(String taskDto) throws InvalidDataException {
        String url = dailySourcePath + "/kanban/task/new";
        return http.postRequest(url, taskDto);
    }

    public ResponseEntity<String> updateTask(String taskDto) throws InvalidDataException{
        String url = dailySourcePath + "/kanban/task/update";
        return http.putRequest(url, taskDto);
    }

    public ResponseEntity<String> obtainOrderingVariables() throws DataNotFoundException{
        String url = dailySourcePath + "/kanban/ordering-variables";
        return http.getRequest(url);
    }

    public ResponseEntity<String> obtainProjectById(Integer idProject) throws DataNotFoundException{
        String url = dailySourcePath + "/kanban/projects/" + idProject;
        return http.getRequest(url);
    }

    public ResponseEntity<String> obtainProjects() throws DataNotFoundException{
        String url = dailySourcePath + "/kanban/projects";
        return http.getRequest(url);
    }

    public ResponseEntity<String> saveProject(String kanbanProjectDto) throws InvalidDataException {
        String url = dailySourcePath + "/kanban/projects/new";
        return http.postRequest(url, kanbanProjectDto);
    }

    public ResponseEntity<String> updateProject(String kanbanProjectDto) throws DataNotFoundException, InvalidDataException {
        String url = dailySourcePath + "/kanban/projects/update";
        return http.putRequest(url, kanbanProjectDto);
    }
}

