package com.example.easypoi.controller;

import com.example.easypoi.model.PersonExportVo;
import com.example.easypoi.utils.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel 导出控制器
 *
 * @author novel
 * @date 2018/12/27
 */
@RestController
@RequestMapping("excel")
public class ExcelController {
    /**
     * 导出
     *
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<PersonExportVo> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportVo personVo = new PersonExportVo();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("/static/user1-128x128.jpg");
            personList.add(personVo);
        }
        ExcelUtils.exportExcel(personList, "员工信息表", "员工信息", PersonExportVo.class, "员工信息", response);
    }

    /**
     * 导出excel
     *
     * @return 结果
     */
    @GetMapping("/exportToFile")
    public Map<String, Object> export() {
        List<PersonExportVo> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PersonExportVo personVo = new PersonExportVo();
            personVo.setName("张三" + i);
            personVo.setUsername("张三" + i);
            personVo.setPhoneNumber("18888888888");
            personVo.setImageUrl("/static/user1-128x128.jpg");
            personList.add(personVo);
        }
        String fileName = ExcelUtils.exportExcelToFile(personList, "员工信息", PersonExportVo.class);
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        return map;
    }

    /**
     * 导入
     *
     * @param file
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return ExcelUtils.importExcel(file, PersonExportVo.class);
    }
}
