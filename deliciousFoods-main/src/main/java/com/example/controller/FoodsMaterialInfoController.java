package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.FoodsMaterialInfo;
import com.example.service.FoodsMaterialInfoService;
import com.example.vo.FoodsMaterialInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/foodsMaterialInfo")
public class FoodsMaterialInfoController {

    @Resource
    private NxSystemFileController nxSystemFileController;
    @Resource
    private FoodsMaterialInfoService foodsMaterialInfoService;
    @PostMapping
    public Result<FoodsMaterialInfo> add(@RequestBody FoodsMaterialInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        info.setUserName(account.getName());
        info.setLevel(account.getLevel());
        info.setUploadUserId(account.getId());
        foodsMaterialInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        FoodsMaterialInfo info = foodsMaterialInfoService.findById(id);
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能删除他人的记录");
        }
        foodsMaterialInfoService.delete(id);
        // 删除对应文件记录
        if (info.getFileId() != null) {
            nxSystemFileController.deleteFile(info.getFileId().toString());
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody FoodsMaterialInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能修改他人的记录");
        }
        foodsMaterialInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<FoodsMaterialInfoVo> detail(@PathVariable Long id) {
        FoodsMaterialInfoVo info = foodsMaterialInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<FoodsMaterialInfoVo>> all() {
        return Result.success(foodsMaterialInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<FoodsMaterialInfoVo>> page(@PathVariable String name,
                                             @RequestParam(defaultValue = "1") Integer pageNum,
                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                             HttpServletRequest request) {
        return Result.success(foodsMaterialInfoService.findPage(name, pageNum, pageSize));
    }

}
