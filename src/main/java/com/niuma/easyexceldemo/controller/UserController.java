package com.niuma.easyexceldemo.controller;


import com.alibaba.excel.EasyExcel;
import com.niuma.easyexceldemo.common.BaseResponse;
import com.niuma.easyexceldemo.common.ErrorCode;
import com.niuma.easyexceldemo.common.ResultUtils;
import com.niuma.easyexceldemo.excel.listeners.UserDataListener;
import com.niuma.easyexceldemo.excel.model.ExcelUserData;
import com.niuma.easyexceldemo.exception.BusinessException;
import com.niuma.easyexceldemo.model.domain.User;
import com.niuma.easyexceldemo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author niumazlb
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 上传 excel 并解析，解析逻辑在 Listener 里
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<Boolean> uploadDataByExcel(@RequestBody MultipartFile file) {
        try {
            //判断文件类型是否正确
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!".xls".equalsIgnoreCase(fileType) && !".xlsx".equalsIgnoreCase(fileType)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件格式错误");
            }
            //读文件
            EasyExcel.read(file.getInputStream(), ExcelUserData.class, new UserDataListener()).sheet().doRead();
            return ResultUtils.success(true);
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 下载文件，通过 easyExcel 写 excel 再传输到前端
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        try {
            // 这里注意 有同学反应使用 swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            List<User> userList = userService.list();
            List<ExcelUserData> excelDataList = userList.stream().map(user -> {
                ExcelUserData excelData = new ExcelUserData();
                excelData.setName(user.getName());
                excelData.setOldScore(user.getOldScore());
                excelData.setCurrentScore(user.getCurrentScore());
                excelData.setGrowScore(user.getCurrentScore() - user.getOldScore());
                return excelData;
            }).sorted(Comparator.comparingInt(ExcelUserData::getGrowScore)).collect(Collectors.toList());
            EasyExcel.write(response.getOutputStream(), ExcelUserData.class).sheet().doWrite(excelDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
