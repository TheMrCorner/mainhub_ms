package com.mrcorner.mainhub.kanban.controller;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.kanban.service.KanbanService;
import com.mrcorner.mainhub.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathUtils.API_BASE_PATH + PathUtils.API_VERSION + "/kanban")
@RequiredArgsConstructor
public class KanbanController {

    private final KanbanService kanbanService;

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/table")
    public ResponseEntity<String> obtainKanbantable(){
        return kanbanService.obtainKanbanTable();
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/ordering-variables")
    public ResponseEntity<String> obtainOrderingVariables() {
        return kanbanService.obtainOrderingVariables();
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/projects")
    public ResponseEntity<String> obtainProjects() {
        return kanbanService.obtainProjects();
    }

    @PreAuthorize("hasAuthority('APP')")
    @GetMapping("/projects/{idProject}")
    public ResponseEntity<String> obtainProjectById(@PathVariable Integer idProject){
        return kanbanService.obtainProjectById(idProject);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/projects/new")
    public ResponseEntity<String> saveNewProject(@RequestBody String kanbanProject) throws InvalidDataException{
        return kanbanService.saveProject(kanbanProject);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PostMapping("/task/new")
    public ResponseEntity<String> saveNewTask(@RequestBody String kanbanTaskDto) throws InvalidDataException {
        return kanbanService.saveNewTask(kanbanTaskDto);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PutMapping("/projects/update")
    public ResponseEntity<String> updateProject(@RequestBody String kanbanProjectDto) throws DataNotFoundException, InvalidDataException {
        return kanbanService.updateProject(kanbanProjectDto);
    }

    @PreAuthorize("hasAuthority('APP')")
    @PutMapping("/task/update")
    public ResponseEntity<String> updateTask(@RequestBody String kanbanTaskDto) throws DataNotFoundException, InvalidDataException{
        return kanbanService.updateTask(kanbanTaskDto);
    }
}

