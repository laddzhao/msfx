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
import com.example.entity.SubClassifyInfo;
import com.example.service.*;
import com.example.vo.SubClassifyInfoVo;
import com.example.vo.SubClassifyInfoVo;
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
@RequestMapping(value = "/subClassifyInfo")
public class SubClassifyInfoController {
    @Resource
    private SubClassifyInfoService subClassifyInfoService;

    @PostMapping
    public Result<SubClassifyInfo> add(@RequestBody SubClassifyInfoVo subClassifyInfo) {
        subClassifyInfoService.add(subClassifyInfo);
        return Result.success(subClassifyInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        subClassifyInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody SubClassifyInfoVo subClassifyInfo) {
        subClassifyInfoService.update(subClassifyInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SubClassifyInfo> detail(@PathVariable Long id) {
        SubClassifyInfo subClassifyInfo = subClassifyInfoService.findById(id);
        return Result.success(subClassifyInfo);
    }

    @GetMapping
    public Result<List<SubClassifyInfoVo>> all() {
        return Result.success(subClassifyInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<SubClassifyInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(subClassifyInfoService.findPage(name, pageNum, pageSize, request));
    }

    /**
    * 批量通过excel添加信息
    * @param file excel文件
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<SubClassifyInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(SubClassifyInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<SubClassifyInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (SubClassifyInfo info : resultList) {
                subClassifyInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
		row.put("name", "");
		row.put("description", "");
		row.put("classifyId", 1);

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=subClassifyInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}
