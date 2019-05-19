package com.jk.controller;

import com.jk.bean.PhoneCount;
import com.jk.bean.User;
import com.jk.service.PoiService;
import com.jk.utils.FileUtil;
import com.jk.utils.ImportExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class PoiController {
    @Autowired
    private PoiService poiService;

/**
 * @Author chh
 * @Description //TODO     前台批量导出  或者是  全选按钮一起导出
 * @Date 17:16 2019/5/17
 * @Param              ids
 * @return
 **/
@ResponseBody
@RequestMapping("exportPoi")
public List<PhoneCount> exportExcel(String ids) throws IOException {
    List<PhoneCount> userList =poiService.findUserListByIds(ids);
    return userList;
}


/**
 * @Author chh
 * @Description //TODO   导入
 * @Date 17:47 2019/5/17
 * @Param
 * @return
 **/
//导入
@RequestMapping("enterPoi")
@ResponseBody
public void enterPoi(@RequestBody PhoneCount phoneCounts) {

        poiService.savePoi(phoneCounts);

}






}
