package com.example.controller;

import cn.hutool.json.JSONObject;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import com.example.vo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

	@Resource
	private AdminInfoService adminInfoService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private ClassifyInfoService classifyInfoService;
	@Resource
	private SubClassifyInfoService subClassifyInfoService;
	@Resource
	private CollectInfoService collectInfoService;
	@Resource
	private PraiseInfoService praiseInfoService;
	@Resource
	private NewsInfoService newsInfoService;
	@Resource
	private MessageInfoService messageInfoService;


    @GetMapping("/get/{modelName}")
    Result<List<EchartsData>> getEchartsData(@PathVariable String modelName) {
        List<EchartsData> list = new ArrayList<>();
        switch (modelName) {
			case "adminInfo":
				List<AdminInfoVo> adminInfoList = adminInfoService.findAll();
				List<AdminInfo> adminInfoMaleList = adminInfoList.stream().filter(x -> "男".equals(x.getSex())).collect(Collectors.toList());
				Map<String, Integer> adminInfoMap = new HashMap<>(2);
				adminInfoMap.put("男", adminInfoMaleList.size());
				adminInfoMap.put("女", adminInfoList.size() - adminInfoMaleList.size());
				getPieData("管理员", list, adminInfoMap);
				getBarData("管理员", list, adminInfoMap);

				break;
			case "userInfo":
				List<UserInfoVo> userInfoList = userInfoService.findAll();
				List<UserInfo> userInfoMaleList = userInfoList.stream().filter(x -> "男".equals(x.getSex())).collect(Collectors.toList());
				Map<String, Integer> userInfoMap = new HashMap<>(2);
				userInfoMap.put("男", userInfoMaleList.size());
				userInfoMap.put("女", userInfoList.size() - userInfoMaleList.size());
				getPieData("用户", list, userInfoMap);
				getBarData("用户", list, userInfoMap);

				break;

			case "classifyInfo":
				List<ClassifyInfoVo> classifyInfoList = classifyInfoService.findAll();
				Map<String, Integer> classifyInfoMap = new HashMap<>(2);
				for (ClassifyInfo classifyInfo : classifyInfoList) {
					Integer value = classifyInfoMap.get(classifyInfo.getName());
					if (value != null && value != 0) {
						classifyInfoMap.put(classifyInfo.getName(), value + 1);
					} else {
						classifyInfoMap.put(classifyInfo.getName(), 1);
					}
				}
				getPieData("菜谱大类", list, classifyInfoMap);
				getBarData("菜谱大类", list, classifyInfoMap);

				break;
			case "subClassifyInfo":
				List<SubClassifyInfoVo> subClassifyInfoList = subClassifyInfoService.findAll();
				Map<String, Integer> subClassifyInfoMap = new HashMap<>(2);
				for (SubClassifyInfo subClassifyInfo : subClassifyInfoList) {
					Integer value = subClassifyInfoMap.get(subClassifyInfo.getName());
					if (value != null && value != 0) {
						subClassifyInfoMap.put(subClassifyInfo.getName(), value + 1);
					} else {
						subClassifyInfoMap.put(subClassifyInfo.getName(), 1);
					}
				}
				getPieData("菜谱小类", list, subClassifyInfoMap);
				getBarData("菜谱小类", list, subClassifyInfoMap);

				break;
			case "collectInfo":
				List<CollectInfoVo> collectInfoList = collectInfoService.findAll();
				Map<String, Integer> collectInfoMap = new HashMap<>(2);
				for (CollectInfo collectInfo : collectInfoList) {
					Integer value = collectInfoMap.get(collectInfo.getName());
					if (value != null && value != 0) {
						collectInfoMap.put(collectInfo.getName(), value + 1);
					} else {
						collectInfoMap.put(collectInfo.getName(), 1);
					}
				}
				getPieData("收藏", list, collectInfoMap);
				getBarData("收藏", list, collectInfoMap);

				break;
			case "praiseInfo":
				List<PraiseInfoVo> praiseInfoList = praiseInfoService.findAll();
				Map<String, Integer> praiseInfoMap = new HashMap<>(2);
				for (PraiseInfo praiseInfo : praiseInfoList) {
					Integer value = praiseInfoMap.get(praiseInfo.getName());
					if (value != null && value != 0) {
						praiseInfoMap.put(praiseInfo.getName(), value + 1);
					} else {
						praiseInfoMap.put(praiseInfo.getName(), 1);
					}
				}
				getPieData("笔记点赞", list, praiseInfoMap);
				getBarData("笔记点赞", list, praiseInfoMap);

				break;
			case "newsInfo":
				List<NewsInfoVo> newsInfoList = newsInfoService.findAll();
				Map<String, Integer> newsInfoMap = new HashMap<>(2);
				for (NewsInfo newsInfo : newsInfoList) {
					Integer value = newsInfoMap.get(newsInfo.getName());
					if (value != null && value != 0) {
						newsInfoMap.put(newsInfo.getName(), value + 1);
					} else {
						newsInfoMap.put(newsInfo.getName(), 1);
					}
				}
				getPieData("饮食资讯", list, newsInfoMap);
				getBarData("饮食资讯", list, newsInfoMap);

				break;
			case "messageInfo":
				List<MessageInfoVo> messageInfoList = messageInfoService.findAll();
				Map<String, Integer> messageInfoMap = new HashMap<>(2);
				for (MessageInfo messageInfo : messageInfoList) {
					Integer value = messageInfoMap.get(messageInfo.getName());
					if (value != null && value != 0) {
						messageInfoMap.put(messageInfo.getName(), value + 1);
					} else {
						messageInfoMap.put(messageInfo.getName(), 1);
					}
				}
				getPieData("趣味答题", list, messageInfoMap);
				getBarData("趣味答题", list, messageInfoMap);

				break;

            default:
                break;
        }
        return Result.success(list);
    }

    @GetMapping("/options")
        Result<List<Map<String, String>>> getOptions() {
        List<Map<String, String>> options = new ArrayList<>();

		Map<String, String> optionMap1 = new HashMap<>();
		optionMap1.put("value", "adminInfo");
		optionMap1.put("label", "管理员信息");
		options.add(optionMap1);
		Map<String, String> optionMap2 = new HashMap<>();
		optionMap2.put("value", "userInfo");
		optionMap2.put("label", "用户信息");
		options.add(optionMap2);
		Map<String, String> optionMap3 = new HashMap<>();
		optionMap3.put("value", "classifyInfo");
		optionMap3.put("label", "菜谱大类信息");
		options.add(optionMap3);
		Map<String, String> optionMap4 = new HashMap<>();
		optionMap4.put("value", "subClassifyInfo");
		optionMap4.put("label", "菜谱小类信息");
		options.add(optionMap4);
		Map<String, String> optionMap5 = new HashMap<>();
		optionMap5.put("value", "collectInfo");
		optionMap5.put("label", "收藏信息");
		options.add(optionMap5);
		Map<String, String> optionMap6 = new HashMap<>();
		optionMap6.put("value", "praiseInfo");
		optionMap6.put("label", "笔记点赞信息");
		options.add(optionMap6);
		Map<String, String> optionMap7 = new HashMap<>();
		optionMap7.put("value", "newsInfo");
		optionMap7.put("label", "饮食资讯信息");
		options.add(optionMap7);
		Map<String, String> optionMap8 = new HashMap<>();
		optionMap8.put("value", "messageInfo");
		optionMap8.put("label", "趣味答题信息");
		options.add(optionMap8);

        return Result.success(options);
    }

    private void getPieData(String name, List<EchartsData> pieList, Map<String, Integer> dataMap) {
        EchartsData pieData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        Map<String, String> titleMap = new HashMap<>(2);
        titleMap.put("text", name + "信息");
        pieData.setTitle(titleMap);

        series.setName(name + "比例");
        series.setType("pie");
        series.setRadius("55%");

        List<Object> objects = new ArrayList<>();
        List<Object> legendList = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            objects.add(new JSONObject().putOpt("name", key).putOpt("value", value));
            legendList.add(key);
        }
        series.setData(objects);

        pieData.setSeries(Collections.singletonList(series));
        Map<String, Boolean> map = new HashMap<>();
        map.put("show", true);
        pieData.setTooltip(map);

        Map<String, Object> legendMap = new HashMap<>(4);
        legendMap.put("orient", "vertical");
        legendMap.put("x", "left");
        legendMap.put("y", "center");
        legendMap.put("data", legendList);
        pieData.setLegend(legendMap);

        pieList.add(pieData);
    }

    private void getBarData(String name, List<EchartsData> barList, Map<String, Integer> dataMap) {
        EchartsData barData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        List<Object> seriesObjs = new ArrayList<>();
        List<Object> xAxisObjs = new ArrayList<>();
            for (String key : dataMap.keySet()) {
            Integer value = dataMap.get(key);
            xAxisObjs.add(key);
            seriesObjs.add(value);
        }

        series.setType("bar");
        series.setName(name);
        series.setData(seriesObjs);
        barData.setSeries(Collections.singletonList(series));

        Map<String, Object> xAxisMap = new HashMap<>(1);
        xAxisMap.put("data", xAxisObjs);
        barData.setxAxis(xAxisMap);

        barData.setyAxis(new HashMap<>());

        Map<String, Object> legendMap = new HashMap<>(1);
        legendMap.put("data", Collections.singletonList(name));
        barData.setLegend(legendMap);

        Map<String, Boolean> map = new HashMap<>(1);
        map.put("show", true);
        barData.setTooltip(map);

        Map<String, String> titleMap = new HashMap<>(1);
        titleMap.put("text", name + "信息");
        barData.setTitle(titleMap);

        barList.add(barData);
    }
}
