package com.example.controller;

import com.example.common.Result;
import com.example.entity.NotesInfo;
import com.example.service.NotesInfoService;
import com.example.vo.NotesInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/notesInfo")
public class NotesInfoController {
    @Resource
    private NotesInfoService notesInfoService;

    @PostMapping
    public Result<NotesInfo> add(@RequestBody NotesInfoVo info) {
        notesInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        notesInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody NotesInfoVo info) {
        notesInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<NotesInfo> detail(@PathVariable Long id) {
        NotesInfo info = notesInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<NotesInfoVo>> all() {
        return Result.success(notesInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<NotesInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(notesInfoService.findPage(name, pageNum, pageSize, request));
    }
}
