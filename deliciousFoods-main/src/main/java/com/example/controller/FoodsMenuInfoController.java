package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.FoodsMenuInfo;
import com.example.service.FoodsMenuInfoService;
import com.example.vo.FoodsMenuInfoVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/foodsMenuInfo")
public class FoodsMenuInfoController {

    @Resource
    private NxSystemFileController nxSystemFileController;
    @Resource
    private FoodsMenuInfoService foodsMenuInfoService;

    @PostMapping
    public Result<FoodsMenuInfo> add(@RequestBody FoodsMenuInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        info.setUserName(account.getName());
        info.setLevel(account.getLevel());
        info.setUploadUserId(account.getId());
        foodsMenuInfoService.add(info);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        FoodsMenuInfo info = foodsMenuInfoService.findById(id);
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能删除他人的记录");
        }
        foodsMenuInfoService.delete(id);
        // 删除对应文件记录
        if (info.getFileId() != null) {
            nxSystemFileController.deleteFile(info.getFileId().toString());
        }
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody FoodsMenuInfo info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (!account.getLevel().equals(info.getLevel()) || !account.getId().equals(info.getUploadUserId())) {
            return Result.error("1001", "不能修改他人的记录");
        }
        foodsMenuInfoService.update(info);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<FoodsMenuInfoVo> detail(@PathVariable Long id) {
        FoodsMenuInfoVo info = foodsMenuInfoService.findById(id);
        return Result.success(info);
    }

    @GetMapping
    public Result<List<FoodsMenuInfoVo>> all() {
        return Result.success(foodsMenuInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<FoodsMenuInfoVo>> page(@PathVariable String name,
                                                  @RequestParam(required = false) Long classifyId,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                                  HttpServletRequest request) {
        return Result.success(foodsMenuInfoService.findPage(name, classifyId, pageNum, pageSize));
    }

    @GetMapping("/page/user/{name}")
    public Result<PageInfo<FoodsMenuInfoVo>> userPage(@PathVariable String name,
                                                      @RequestParam(required = false) String username,
                                                      @RequestParam(required = false) Integer level,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                                      HttpServletRequest request) {
        return Result.success(foodsMenuInfoService.findPageByUser(name, username, level, pageNum, pageSize));
    }

}
