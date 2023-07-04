package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.PraiseInfo;
import com.example.service.*;
import com.example.vo.PraiseInfoVo;
import com.example.vo.PraiseInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/praiseInfo")
public class PraiseInfoController {
    @Resource
    private PraiseInfoService praiseInfoService;

    @PostMapping
    public Result<PraiseInfo> add(@RequestBody PraiseInfoVo praiseInfo, HttpServletRequest request) {
        praiseInfoService.add(praiseInfo, request);
        return Result.success(praiseInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        praiseInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody PraiseInfoVo praiseInfo) {
        praiseInfoService.update(praiseInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<PraiseInfo> detail(@PathVariable Long id) {
        PraiseInfo praiseInfo = praiseInfoService.findById(id);
        return Result.success(praiseInfo);
    }

    @GetMapping
    public Result<List<PraiseInfoVo>> all() {
        return Result.success(praiseInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<PraiseInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(praiseInfoService.findPage(name, pageNum, pageSize, request));
    }


}
