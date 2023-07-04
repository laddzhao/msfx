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
import com.example.entity.CollectInfo;
import com.example.service.*;
import com.example.vo.CollectInfoVo;
import com.example.vo.CollectInfoVo;
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
@RequestMapping(value = "/collectInfo")
public class CollectInfoController {
    @Resource
    private CollectInfoService collectInfoService;

    @PostMapping
    public Result<CollectInfo> add(@RequestBody CollectInfoVo collectInfo, HttpServletRequest request) {
        collectInfoService.add(collectInfo, request);
        return Result.success(collectInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        collectInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody CollectInfoVo collectInfo) {
        collectInfoService.update(collectInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CollectInfo> detail(@PathVariable Long id) {
        CollectInfo collectInfo = collectInfoService.findById(id);
        return Result.success(collectInfo);
    }

    @GetMapping
    public Result<List<CollectInfoVo>> all() {
        return Result.success(collectInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CollectInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(collectInfoService.findPage(name, pageNum, pageSize, request));
    }

}
