package com.jk.controller;

import com.jk.ConstantConf;
import com.jk.MyLog;
import com.jk.bean.PhoneCount;
import com.jk.bean.User;
import com.jk.rmi.UserClient;
import com.jk.utils.Fence;
import com.jk.utils.FileUtil;
import com.jk.utils.ImportExcelUtil;
import com.jk.utils.Morse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserClient userClient;
    /**
     * @Author chh
     * @Description //TODO 到手机登录页面
     * @Date 11:36 2019/5/20
     * @Param
     * @return
     **/

    @RequestMapping("toPhoneLogin")
    public String toPhoneLogin(){
        return "phoneLogin";
    }

    @MyLog(value = "手机登录记录")
    @RequestMapping("phoneLogin")
    @ResponseBody
    public  HashMap<String,Object> phoneLogin(User user,String imgcode, String phonecode,HttpServletRequest request){
        HashMap<String, Object> hashMap = new HashMap<>();
        HttpSession session = request.getSession();
        if(!StringUtils.isEmpty(imgcode)){
            String imgcode1 = (String) session.getAttribute("imgcode");
            if(!imgcode.equals(imgcode1)){
                hashMap.put("code",1);
                hashMap.put("msg","图片验证码错误");
             return hashMap;
            }
        }
        hashMap = userClient.phoneLogin(user, phonecode);

        return hashMap;
    }

    /**
     * @Author chh
     * @Description //TODO     到登陆页面    带着model
     * @Date 17:26 2019/5/19
     * @Param
     * @return            phoneNumber    password
     **/
    @RequestMapping("toLogin")
    public String comLogin(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        Morse morse = new Morse();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantConf.cookieNamePaw)) {
                    String value = cookie.getValue();
                    if (value != null) {
                        String[] split = value.split(ConstantConf.splitC);
                        if(split.length>1){
                            model.addAttribute("phoneNumber", split[0]);
                            model.addAttribute("password", morse.decode(split[1]));
                        }else{
                            model.addAttribute("phoneNumber", split[0]);
                        }
                    }
                }
            }
        }
        return "login";
    }

    @RequestMapping("toComLogin")
    public String comLogin(){
        return "comLogin";
    }
    /**
     * @Author chh
     * @Description //TODO  跳转到注册   表单页面
     * @Date 18:07 2019/5/19
     * @Param 
     * @return 
     **/

    @RequestMapping("regsFaHuo")
    public String regsFaHuo(Integer usertype, Model model){

            model.addAttribute("usertype",usertype);
            return "faHuo";

    }
    /**
     * @Author chh
     * @Description //TODO     到注册页面
     * @Date 17:27 2019/5/19
     * @Param
     * @return 
     **/
    @RequestMapping("toRegs")
    public String toRegs(){
        return "regs";
    }
    /**
     * @Author chh
     * @Description //TODO  查注册类型
     * @Date 20:38 2019/5/17
     * @Param
     * @return
     **/
   @RequestMapping("findRegType")
   @ResponseBody
   public List<User> findRegType(){
       return userClient.findRegType();
   }


/**
 * @Author chh
 * @Description //TODO    根据手机查询用户
 * @Date 20:39 2019/5/17
 * @Param
 * @return
 **/
@RequestMapping("findUserByPhone")
@ResponseBody
public HashMap<String,Object> findUserByPhone(String phoneNumber){

    return userClient.findUserByPhone(phoneNumber);
}

    /**
     * @Author chh
     * @Description //TODO       前台项目  登录   (发货方、物流公司可登陆)
     * @Date 10:31 2019/5/17
     * @Param                 usertype    password   phoneNumber
     * @return                uuid   type   code  msg
     **/
    @MyLog(value = "发货方与物流公司登录记录")
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String,Object> login(User user, HttpServletResponse response, HttpServletRequest request){
        User user1 = userClient.login(user);
        HashMap<String, Object> hashMap= new HashMap<>();
        if (user1 != null) {
            //如果密码正确判断是否选择了记住密码
            if (user.getRemPwd() != null) {
                //如果选择了记住密码  存入cookie中
                Morse morse = new Morse();
                Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user1.getPhoneNumber() + ConstantConf.splitC + morse.encryption(user.getPassword()));//TODO
                cookie.setMaxAge(604800);
                response.addCookie(cookie);
            } else {
                //如果没有勾选记住密码,只记住账号
               Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user.getPhoneNumber());
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
            }
        } else {
            Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user.getPhoneNumber());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            hashMap.put("code", 1);
            hashMap.put("msg", "密码或者账号输入错误");
            return hashMap;
        }
        HttpSession session = request.getSession();
        session.setAttribute(session.getId(),user1);
        hashMap.put("type", user1.getUsertype());
         //TODO redis
        hashMap.put("code", 0);
        hashMap.put("msg", "登录成功");
        return hashMap;
    }

    /**
     * @Author chh
     * @Description //TODO 短信验证
     * @Date 11:51 2019/5/16
     * @Param            phoneNumber
     * @return           code   msg    yzm
     **/
    @RequestMapping("duanxin")
    @ResponseBody
    public HashMap<String, Object> phoneTest(String phoneNumber){
        HashMap<String, Object> hashMap = userClient.phoneTest(phoneNumber);
        return hashMap;

    }
    /**
     * @Author chh
     * @Description //TODO 图片验证码
     * @Date 11:51 2019/5/16
     * @Param            phoneNumber
     * @return           code   msg    yzm
     **/
    @RequestMapping("verificationCode")
    public void phoneTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        int width = 85;
        int height = 32;
        int lines = 5;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 18));

        // 随机数字
        Random r = new Random(new Date().getTime());
        String code = "";
        for (int i = 0; i < 4; i++) {
            int a = r.nextInt(10);
            code += a;
            int y = 10 + r.nextInt(20);// 10~30范围内的一个整数，作为y坐标

            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);

            g.drawString("" + a, 5 + i * width / 4, y);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("imgcode", code);
        // 干扰线
        for (int i = 0; i < lines; i++) {
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }

        g.dispose();// 类似于流中的close()带动flush()---把数据刷到img对象当中
        // 告诉客户端，输出的格式
        response.reset();
        // 告诉客户端，输出的格式
        response.setContentType("image/jpeg");
        ImageIO.write(img, "jpg", response.getOutputStream());
    }
    /**
     * @Author chh
     * @Description //TODO 注册    (包含一个消息队列 phone)
     * @Date 11:40 2019/5/16
     * @Param               user   imgcode    phonecode
     * @return             type     code   msg
     **/
    @RequestMapping("reg")
    @ResponseBody
    public  HashMap<String,Object> saveUser(User user, String phonecode, HttpServletRequest request){
        HashMap<String, Object> hashMap = new HashMap<>();
        //验证码
   HttpSession session = request.getSession();
   /* String imgcode1 = session.getAttribute("imgcode").toString();
    if(!imgcode1.equals(imgcode)){
        hashMap.put("code", 1);
        hashMap.put("msg", "验证码错误");
        return hashMap;
    }*/
        return userClient.saveUser(user,phonecode);
    }


    /**
     * @Author chh
     * @Description //TODO     查询密码  通过UUID
     * @Date 15:12 2019/5/17
     * @Param                       uuid
     * @return                      code   msg    on
     **/
    @RequestMapping("findPassword")
    @ResponseBody
    public HashMap<String,Object> findUserFromRedis(String uuid) {
        return userClient.findUserFromRedis(uuid);
    }


    /**
     * @Author chh
     * @Description //TODO      后台系统登录    （仅物流公司可登陆）
     * @Date 12:01 2019/5/17
     * @Param              usertype    password   phoneNumber
     * @return           uuid   type   code  msg
     **/
    @MyLog(value = "后台公司登录记录")
    @RequestMapping("comLogin")
    @ResponseBody
    public HashMap<String,Object> comLogin(User user, HttpServletResponse response, HttpServletRequest request){
        HashMap<String, Object> hashMap= new HashMap<>();
        User user1 = userClient.login(user);
        if(user1.getUsertype()==1){
            hashMap.put("code",1);
            hashMap.put("msg","您目前没有权限登录");
            return hashMap;
        }
        if (user1 != null) {
            //如果密码正确判断是否选择了记住密码
            if (user.getRemPwd() != null) {
                //如果选择了记住密码  存入cookie中     加密
                Morse morse = new Morse();
                Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user1.getPhoneNumber() + ConstantConf.splitC + morse.encryption(user.getPassword()));//TODO
                cookie.setMaxAge(604800);
                response.addCookie(cookie);
            } else {
                //如果没有勾选记住密码,只记住账号
                Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user.getPhoneNumber());
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
            }
        } else {
            Cookie cookie = new Cookie(ConstantConf.cookieNamePaw, user.getPhoneNumber());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            hashMap.put("code", 1);
            hashMap.put("msg", "密码或者账号输入错误");
            return hashMap;
        }
        HttpSession session = request.getSession();
        session.setAttribute(session.getId(),user1);
        hashMap.put("type", user1.getUsertype());
        hashMap.put("code", 0);
        hashMap.put("msg", "登录成功");
        return hashMap;

    }


    

    /**
     * @Author chh
     * @Description //TODO     前台批量导出  或者是  全选按钮一起导出
     * @Date 17:16 2019/5/17
     * @Param              ids
     * @return
     **/
    @ResponseBody
    @RequestMapping("exportPoi")
    public ResponseEntity<byte[]> exportExcel(String ids) throws IOException {
        List<PhoneCount> userList =userClient.findUserListByIds(ids);
        String[] title={"ID","账号","状态"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        for (int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i=0; i<userList.size();i++){
            HSSFRow row1 = sheet.createRow(i+1);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue(userList.get(i).getId());
            HSSFCell cell2 = row1.createCell(1);
            cell2.setCellValue(userList.get(i).getPhoneNumber());
            HSSFCell cell3 = row1.createCell(2);
            cell3.setCellValue(userList.get(i).getStatus());

        }

        String pathname = "C:\\Users\\wo\\Desktop\\1.xlsx";
        File file = new File(pathname);
        file.createNewFile();
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(file);
        workbook.write(fileOutputStream);
        return FileUtil.FileDownload(pathname);


    }

    /**
     * @Author chh
     * @Description //TODO   导入
     * @Date 17:47 2019/5/17
     * @Param            传入  一个  file 要求name为  upfile
     * @return
     **/
//导入
    @RequestMapping("enterPoi")
    @ResponseBody
    public Boolean enterPoi(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipart= (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在");
        }
        InputStream in = file.getInputStream();
        List<List<Object>> bankListByExcel2 = new ImportExcelUtil().getBankListByExcel2(in, file.getOriginalFilename());
        System.out.println(bankListByExcel2);
        List<PhoneCount> phoneCounts = new ArrayList<>();
        for (int i=0;i<bankListByExcel2.size();i++){
            List<Object> objects = bankListByExcel2.get(i);
            PhoneCount phoneCount1 = new PhoneCount();
            phoneCount1.setId(UUID.randomUUID().toString());
            phoneCount1.setPhoneNumber(objects.get(1).toString());
            phoneCount1.setStatus(Integer.valueOf(objects.get(2).toString()));
            phoneCounts.add(phoneCount1);
        }

        try {
            PhoneCount phoneCount = new PhoneCount();
            phoneCount.setPhoneCounts(phoneCounts);
            userClient.savePoi(phoneCount);
            in.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    
    







}
